package com.devyouup.novisign.modules.playlists.di

import com.devyouup.novisign.modules.core.utils.BASE_URL
import com.devyouup.novisign.modules.core.utils.ServiceGenerator
import com.devyouup.novisign.modules.playlists.data.apiservice.PlaylistsApiService
import com.devyouup.novisign.modules.playlists.data.repository.PlaylistsRepositoryImpl
import com.devyouup.novisign.modules.playlists.domain.repository.PlaylistsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PlaylistsAppModule {

    @Provides
    @Singleton
    fun providePlaylistsApi(): PlaylistsApiService {
        return ServiceGenerator.createService(
            serviceClass = PlaylistsApiService::class.java,
            baseUrl = BASE_URL,
            headers = null
        )
    }

    @Provides
    @Singleton
    fun providePlaylistsRepository(
        api: PlaylistsApiService
    ) : PlaylistsRepository {
        return PlaylistsRepositoryImpl(apiService = api)
    }
}