package br.com.spectacle.app.feature.musics.domain.usecase

import br.com.spectacle.app.feature.musics.domain.repository.MusicRepository

internal class RemoveFavoriteMusicUseCase(
    private val musicRepository: MusicRepository
) {
    suspend operator fun invoke(musicId: Long) {
        return musicRepository.removeFavoriteMusic(musicId)
    }
}