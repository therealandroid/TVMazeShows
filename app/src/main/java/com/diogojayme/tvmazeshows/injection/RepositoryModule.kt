package com.diogojayme.tvmazeshows.injection

import com.diogojayme.tvmazeshows.repository.ShowsApi
import com.diogojayme.tvmazeshows.repository.ShowsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideShowsRepository(api: ShowsApi) =
        ShowsRepository(api)

}