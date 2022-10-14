package com.erbeandroid.petfinder.core.firebase.firestore

import android.util.Log
import com.erbeandroid.petfinder.core.common.dispatcher.Dispatcher
import com.erbeandroid.petfinder.core.common.dispatcher.PetFinderDispatcher.*
import com.erbeandroid.petfinder.core.firebase.model.Post
import com.erbeandroid.petfinder.core.firebase.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
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
    private val firebaseAuth: FirebaseAuth,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher
) : FirebaseFirestoreManager {

    override val state = MutableStateFlow<String?>(null)
    override val postDetail = MutableStateFlow<Post?>(null)

    private fun currentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    override fun addUser() {
        currentUser()?.let { user ->
            val postUser = User(user.displayName, user.phoneNumber)
            reference
                .collection("users")
                .document(user.uid)
                .set(postUser)
                .addOnSuccessListener {
                    Log.d("TAG", "addUser: Success")
                }
                .addOnFailureListener {
                    Log.d("TAG", "addUser: Failed")
                    state.value = "Failed"
                }
        }
    }

    override fun addPost(title: String, description: String) {
        currentUser()?.let { user ->
            reference
                .collection("users")
                .document(user.uid)
                .get()
                .addOnSuccessListener { snapshot ->
                    val userDatabase = snapshot.toObject<User>()
                    if (userDatabase == null) {
                        Log.d("TAG", "User null")
                    } else {
                        writePost(user.uid, userDatabase.name.toString(), title, description)
                    }
                }
                .addOnFailureListener {
                    Log.d("TAG", "addPost: Failed")
                }
        }
    }

    private fun writePost(uid: String, name: String, title: String, description: String) {
        val key = reference.collection("posts").document().id

        val post = Post(key, uid, name, title, description)
        val postMap = post.toMap()

        val postReference = reference.collection("posts").document(key)
        val userPostReference = reference
            .collection("user-posts").document(uid)
            .collection("posts").document(key)

        reference
            .runBatch { batch ->
                batch.set(postReference, postMap)
                batch.set(userPostReference, postMap)
            }
            .addOnSuccessListener {
                Log.d("TAG", "writePost: Success")
                state.value = "Success"
            }
            .addOnFailureListener {
                Log.d("TAG", "writePost: Failed")
                state.value = "Failed"
            }
    }

    override fun listPost(): Flow<List<Post>> = callbackFlow {
        val postReference = reference.collection("posts")
        val postListener = EventListener<QuerySnapshot> { snapshot, error ->
            if (error != null) {
                Log.d("TAG", "listPost: Failed")
                return@EventListener
            }
            snapshot?.let {
                val post = snapshot.toObjects<Post>()
                trySend(post)
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
                postDetail.value = post
            }
            .addOnFailureListener {
                Log.d("TAG", "detailPost: Failed")
            }
    }
}