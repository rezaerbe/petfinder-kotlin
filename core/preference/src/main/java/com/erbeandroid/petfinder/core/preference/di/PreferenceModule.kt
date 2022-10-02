package com.erbeandroid.petfinder.core.preference.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.erbeandroid.petfinder.core.preference.datasource.PetFinderPreferenceDataSource
import com.erbeandroid.petfinder.core.preference.datasource.PreferenceDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface PreferenceModule {

    @Binds
    fun bindsPreferenceDataSource(
        petFinderPreferenceDataSource: PetFinderPreferenceDataSource
    ): PreferenceDataSource

    companion object {
        @Provides
        fun provideMasterKeyAlias(): String {
            return MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
        }

        @Provides
        @Singleton
        fun provideSharedPreferences(
            masterKeyAlias: String,
            @ApplicationContext context: Context
        ): SharedPreferences {
            return EncryptedSharedPreferences.create(
                "secret_shared_prefs",
                masterKeyAlias,
                context,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        }
    }
}