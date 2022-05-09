package br.com.spectacle.app.feature.musics.domain.usecase

import br.com.spectacle.app.feature.musics.domain.repository.MusicRepository

internal class AddFavoriteMusicUseCase(
    private val musicRepository: MusicRepository
) {
    suspend operator fun invoke(musicId: Long) {
        return musicRepository.addFavoriteMusic(musicId)
    }
}