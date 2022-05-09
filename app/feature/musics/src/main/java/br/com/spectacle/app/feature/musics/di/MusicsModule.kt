package br.com.spectacle.app.feature.musics.di

import br.com.spectacle.app.feature.musics.data.remote.MusicsRemoteDataSource
import br.com.spectacle.app.feature.musics.data.repository.MusicRepositoryImpl
import br.com.spectacle.app.feature.musics.domain.repository.MusicRepository
import br.com.spectacle.app.feature.musics.domain.usecase.AddFavoriteMusicUseCase
import br.com.spectacle.app.feature.musics.domain.usecase.GetFavoritesMusicsUseCase
import br.com.spectacle.app.feature.musics.domain.usecase.GetMusicsByQueryUseCase
import br.com.spectacle.app.feature.musics.domain.usecase.RemoveFavoriteMusicUseCase
import br.com.spectacle.app.feature.musics.presentation.MusicViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val musicsModule = module {
    viewModel {
        val remoteDataSource = MusicsRemoteDataSource(
            httpClient = get()
        )
        val musicRepository: MusicRepository = MusicRepositoryImpl(
            remoteDataSource = remoteDataSource
        )

        val addFavoriteMusicUseCase = AddFavoriteMusicUseCase(musicRepository)
        val removeFavoriteMusicUseCase = RemoveFavoriteMusicUseCase(musicRepository)
        val getFavoriteMusicUseCase = GetFavoritesMusicsUseCase(musicRepository)
        val getMusicsByQueryUseCase = GetMusicsByQueryUseCase(musicRepository)

        MusicViewModel(
            getFavoritesMusicsUseCase = getFavoriteMusicUseCase,
            getMusicsByQueryUseCase = getMusicsByQueryUseCase,
            addFavoriteMusicUseCase = addFavoriteMusicUseCase,
            removeFavoriteMusicUseCase = removeFavoriteMusicUseCase
        )
    }
}