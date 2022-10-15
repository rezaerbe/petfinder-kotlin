package com.erbeandroid.petfinder.core.firebase.database

import android.util.Log
import com.erbeandroid.petfinder.core.common.dispatcher.Dispatcher
import com.erbeandroid.petfinder.core.common.dispatcher.PetFinderDispatcher.IO
import com.erbeandroid.petfinder.core.common.state.StateData
import com.erbeandroid.petfinder.core.firebase.model.Post
import com.erbeandroid.petfinder.core.firebase.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FirebaseDatabaseManagerImpl @Inject constructor(
    private val reference: DatabaseReference,
    firebaseAuth: FirebaseAuth,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher
) : FirebaseDatabaseManager {

    private val user = firebaseAuth.currentUser

    override val state = MutableStateFlow<String?>(null)
    override val postDetail = MutableStateFlow<StateData<Post?>>(StateData.Loading)

    override fun addUser() {
        if (user != null) {
            val postUser = User(user.uid, user.displayName, user.phoneNumber)
            reference
                .child("users")
                .child(user.uid)
                .setValue(postUser)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("TAG", "addUserDatabase: Success")
                    } else {
                        Log.d("TAG", "addUserDatabase: Failed")
                    }
                }
        } else {
            Log.d("TAG", "addUserDatabase: User null")
        }
    }

    override fun addPost(title: String, description: String) {
        if (user != null) {
            reference
                .child("users")
                .child(user.uid)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val userDatabase = snapshot.getValue<User>()
                        if (userDatabase == null) {
                            state.value = "User database null"
                        } else {
                            writePost(userDatabase, title, description)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        state.value = "Failed"
                    }
                })
        } else {
            state.value = "User null"
        }
    }

    private fun writePost(user: User, title: String, description: String) {
        val key = reference.child("posts").push().key
        if (key == null) {
            state.value = "Key null"
            return
        }

        val post = Post(key, user.uid, user.name, title, description)
        val postMap = post.toMap()

        val update = hashMapOf<String, Any>(
            "/posts/$key" to postMap,
            "/user-posts/${user.uid}/posts/$key" to postMap
        )

        reference.updateChildren(update)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    state.value = "Success"
                } else {
                    state.value = "Failed"
                }
            }
    }

    override fun listPost(): Flow<StateData<List<Post>>> = callbackFlow {
        val postReference = reference.child("posts")
        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val postList = arrayListOf<Post>()
                for (postSnapshot in snapshot.children) {
                    val post = postSnapshot.getValue<Post>()
                    post?.let { data ->
                        postList.add(data)
                    }
                }
                trySend(StateData.Success(postList))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(StateData.Error(error.toException()))
            }
        }
        postReference.addValueEventListener(postListener)
        awaitClose {
            postReference.removeEventListener(postListener)
        }
    }.flowOn(ioDispatcher)

    override fun detailPost(key: String) {
        reference
            .child("posts")
            .child(key)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val post = snapshot.getValue<Post>()
                    postDetail.value = StateData.Success(post)
                }

                override fun onCancelled(error: DatabaseError) {
                    postDetail.value = StateData.Error(error.toException())
                }
            })
    }
}