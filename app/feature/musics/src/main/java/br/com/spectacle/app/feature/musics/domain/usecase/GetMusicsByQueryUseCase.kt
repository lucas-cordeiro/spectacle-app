package br.com.spectacle.app.feature.musics.domain.usecase

import br.com.spectacle.app.feature.musics.domain.model.Music
import br.com.spectacle.app.feature.musics.domain.repository.MusicRepository

internal class GetMusicsByQueryUseCase(
    private val musicRepository: MusicRepository
) {
    suspend operator fun invoke(query: String): List<Music> {
        if(query.isBlank()) return emptyList()
        return musicRepository.getMusicsByQuery(query)
    }
}