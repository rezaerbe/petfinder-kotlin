package com.erbeandroid.petfinder.core.firebase.database

import android.util.Log
import com.erbeandroid.petfinder.core.common.dispatcher.Dispatcher
import com.erbeandroid.petfinder.core.common.dispatcher.PetFinderDispatcher.IO
import com.erbeandroid.petfinder.core.firebase.model.Post
import com.erbeandroid.petfinder.core.firebase.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
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
    private val firebaseAuth: FirebaseAuth,
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher
) : FirebaseDatabaseManager {

    override val state = MutableStateFlow<String?>(null)
    override val postDetail = MutableStateFlow<Post?>(null)

    private fun currentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    override fun addUser() {
        currentUser()?.let { user ->
            val postUser = User(user.displayName, user.phoneNumber)
            reference
                .child("users")
                .child(user.uid)
                .setValue(postUser)
                .addOnSuccessListener {
                    Log.d("TAG", "addUser: Success")
                }
                .addOnFailureListener {
                    Log.d("TAG", "addUser: Failed")
                }
        }
    }

    override fun addPost(title: String, description: String) {
        currentUser()?.let { user ->
            reference
                .child("users")
                .child(user.uid)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val userDatabase = snapshot.getValue<User>()
                        if (userDatabase == null) {
                            Log.d("TAG", "User null")
                        } else {
                            writePost(user.uid, userDatabase.name.toString(), title, description)
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.d("TAG", "onCancelled: ")
                        state.value = "Cancelled"
                    }
                })
        }
    }

    private fun writePost(uid: String, name: String, title: String, description: String) {
        val key = reference.child("posts").push().key
        if (key == null) {
            Log.d("TAG", "Key null")
            return
        }

        val post = Post(key, uid, name, title, description)
        val postMap = post.toMap()

        val update = hashMapOf<String, Any>(
            "/posts/$key" to postMap,
            "/user-posts/$uid/$key" to postMap
        )

        reference.updateChildren(update)
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
                trySend(postList.toList())
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("TAG", "onCancelled: ")
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
                    postDetail.value = post
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.d("TAG", "onCancelled: ")
                }
            })
    }
}