package com.erbeandroid.petfinder.core.firebase.firestore

import android.util.Log
import com.erbeandroid.petfinder.core.common.dispatcher.Dispatcher
import com.erbeandroid.petfinder.core.common.dispatcher.PetFinderDispatcher.*
import com.erbeandroid.petfinder.core.common.state.StateData
import com.erbeandroid.petfinder.core.firebase.model.Post
import com.erbeandroid.petfinder.core.firebase.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FirebaseFirestoreManagerImpl @Inject constructor(
    private val reference: FirebaseFirestore,
    firebaseAuth: FirebaseAuth,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher
) : FirebaseFirestoreManager {

    private val user = firebaseAuth.currentUser

    override val state = MutableStateFlow<String?>(null)
    override val postDetail = MutableStateFlow<StateData<Post?>>(StateData.Loading)

    override fun addUser() {
        if (user != null) {
            val postUser = User(user.uid, user.displayName, user.phoneNumber)
            reference
                .collection("users")
                .document(user.uid)
                .set(postUser)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("TAG", "addUserFirestore: Success")
                    } else {
                        Log.d("TAG", "addUserFirestore: Failed")
                    }
                }
        } else {
            Log.d("TAG", "addUserFirestore: User null")
        }
    }

    override fun addPost(title: String, description: String) {
        if (user != null) {
            reference
                .collection("users")
                .document(user.uid)
                .get()
                .addOnSuccessListener { snapshot ->
                    val userDatabase = snapshot.toObject<User>()
                    if (userDatabase == null) {
                        state.value = "User database null"
                    } else {
                        writePost(userDatabase, title, description)
                    }
                }
                .addOnFailureListener {
                    state.value = "Failed"
                }
        } else {
            state.value = "User null"
        }
    }

    private fun writePost(user: User, title: String, description: String) {
        val key = reference.collection("posts").document().id

        val post = Post(key, user.uid, user.name, title, description)
        val postMap = post.toMap()

        val postReference = reference.collection("posts").document(key)
        val userPostReference = reference
            .collection("user-posts").document(user.uid!!)
            .collection("posts").document(key)

        reference
            .runBatch { batch ->
                batch.set(postReference, postMap)
                batch.set(userPostReference, postMap)
            }
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    state.value = "Success"
                } else {
                    state.value = "Failed"
                }
            }
    }

    override fun listPost(): Flow<StateData<List<Post>>> = callbackFlow {
        val postReference = reference.collection("posts")
        val postListener = EventListener<QuerySnapshot> { snapshot, error ->
            if (error != null) {
                trySend(StateData.Error(error))
                return@EventListener
            }
            snapshot?.let {
                val postList = snapshot.toObjects<Post>()
                trySend(StateData.Success(postList))
            }
        }
        val registration = postReference.addSnapshotListener(postListener)
        awaitClose {
            registration.remove()
        }
    }.flowOn(ioDispatcher)

    override fun detailPost(key: String) {
        reference
            .collection("posts")
            .document(key)
            .get()
            .addOnSuccessListener { snapshot ->
                val post = snapshot.toObject<Post>()
                postDetail.value = StateData.Success(post)
            }
            .addOnFailureListener { error ->
                StateData.Error(error)
            }
    }
}