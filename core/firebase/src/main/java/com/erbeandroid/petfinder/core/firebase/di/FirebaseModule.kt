package com.erbeandroid.petfinder.core.firebase.di

import androidx.fragment.app.FragmentActivity
import com.erbeandroid.petfinder.core.firebase.authentication.PhoneLoginManager
import com.erbeandroid.petfinder.core.firebase.authentication.PhoneLoginManagerImpl
import com.erbeandroid.petfinder.core.firebase.database.FirebaseDatabaseManager
import com.erbeandroid.petfinder.core.firebase.database.FirebaseDatabaseManagerImpl
import com.erbeandroid.petfinder.core.firebase.firestore.FirebaseFirestoreManager
import com.erbeandroid.petfinder.core.firebase.firestore.FirebaseFirestoreManagerImpl
import com.erbeandroid.petfinder.core.firebase.user.FirebaseUserManager
import com.erbeandroid.petfinder.core.firebase.user.FirebaseUserManagerImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface FirebaseModule {

    @Binds
    fun bindFirebaseUserManager(
        firebaseUserManagerImpl: FirebaseUserManagerImpl
    ): FirebaseUserManager

    @Binds
    fun bindPhoneLoginManager(
        phoneLoginManagerImpl: PhoneLoginManagerImpl
    ): PhoneLoginManager

    @Binds
    fun bindFirebaseDatabaseManager(
        firebaseDatabaseManagerImpl: FirebaseDatabaseManagerImpl
    ): FirebaseDatabaseManager

    @Binds
    fun bindFirebaseFirestoreManager(
        firebaseFirestoreManagerImpl: FirebaseFirestoreManagerImpl
    ): FirebaseFirestoreManager

    companion object {
        @Provides
        @Singleton
        fun provideFragmentActivity(): FragmentActivity {
            return FragmentActivity()
        }

        @Provides
        @Singleton
        fun provideFirebaseAuth(): FirebaseAuth {
            return Firebase.auth
        }

        @Provides
        @Singleton
        fun provideFirebaseDatabase(): DatabaseReference {
            return Firebase.database.reference
        }

        @Provides
        @Singleton
        fun provideFirebaseFirestore(): FirebaseFirestore {
            return Firebase.firestore
        }
    }
}