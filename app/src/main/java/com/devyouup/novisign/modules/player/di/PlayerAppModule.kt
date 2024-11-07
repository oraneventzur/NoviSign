package com.devyouup.novisign.modules.player.di

import com.devyouup.novisign.modules.core.utils.BASE_URL
import com.devyouup.novisign.modules.core.utils.ServiceGenerator
import com.devyouup.novisign.modules.player.data.apiservice.PlayerApiService
import com.devyouup.novisign.modules.player.data.repository.PlayerRepositoryImpl
import com.devyouup.novisign.modules.player.domain.repository.PlayerRepository
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
object PlayerAppModule {

    @Provides
    @Singleton
    fun providePlayerApi(): PlayerApiService {
        return ServiceGenerator.createService(
            serviceClass = PlayerApiService::class.java,
            baseUrl = BASE_URL,
            headers = null
        )
    }

    @Provides
    @Singleton
    fun providePlayerRepository(
        api: PlayerApiService
    ) : PlayerRepository {
        return PlayerRepositoryImpl(apiService = api)
    }

}