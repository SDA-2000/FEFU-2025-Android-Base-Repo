package co.feip.fefu2025.app.di

import co.feip.fefu2025.data.repositories.AnimeRepositoryImpl
import co.feip.fefu2025.domain.repositories.AnimeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AnimeRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindAnimeRepository(
        impl: AnimeRepositoryImpl
    ): AnimeRepository
}