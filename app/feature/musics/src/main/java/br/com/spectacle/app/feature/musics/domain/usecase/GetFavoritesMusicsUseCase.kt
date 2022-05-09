package br.com.spectacle.app.feature.musics.domain.usecase

import br.com.spectacle.app.feature.musics.domain.model.Music
import br.com.spectacle.app.feature.musics.domain.repository.MusicRepository

internal class GetFavoritesMusicsUseCase(
    private val musicRepository: MusicRepository
) {
    suspend operator fun invoke(): List<Music> {
        return musicRepository.getFavoriteMusics()
    }
}