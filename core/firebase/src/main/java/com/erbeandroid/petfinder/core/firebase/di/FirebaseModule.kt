package com.erbeandroid.petfinder.core.firebase.di

import androidx.fragment.app.FragmentActivity
import com.erbeandroid.petfinder.core.firebase.authentication.PhoneLoginManager
import com.erbeandroid.petfinder.core.firebase.authentication.PhoneLoginManagerImpl
import com.erbeandroid.petfinder.core.firebase.database.manager.FirebaseDatabaseManager
import com.erbeandroid.petfinder.core.firebase.database.manager.FirebaseDatabaseManagerImpl
import com.erbeandroid.petfinder.core.firebase.user.FirebaseUserManager
import com.erbeandroid.petfinder.core.firebase.user.FirebaseUserManagerImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
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
    fun bindPhoneLoginManager(
        phoneLoginManagerImpl: PhoneLoginManagerImpl
    ): PhoneLoginManager

    @Binds
    fun bindFirebaseUserManager(
        firebaseUserManagerImpl: FirebaseUserManagerImpl
    ): FirebaseUserManager

    @Binds
    fun bindFirebaseDatabaseManager(
        firebaseDatabaseManagerImpl: FirebaseDatabaseManagerImpl
    ): FirebaseDatabaseManager

    companion object {
        @Provides
        @Singleton
        fun provideFirebaseAuth(): FirebaseAuth {
            return Firebase.auth
        }

        @Provides
        @Singleton
        fun provideFragmentActivity(): FragmentActivity {
            return FragmentActivity()
        }

        @Provides
        @Singleton
        fun provideFirebaseDatabase(): DatabaseReference {
            return Firebase.database("https://petfinder-erbe-default-rtdb.asia-southeast1.firebasedatabase.app/").reference
        }
    }
}