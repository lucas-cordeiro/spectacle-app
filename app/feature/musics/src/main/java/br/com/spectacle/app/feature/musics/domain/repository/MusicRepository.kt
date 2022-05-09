package br.com.spectacle.app.feature.musics.domain.repository

import br.com.spectacle.app.feature.musics.domain.model.Music

internal interface MusicRepository {
    suspend fun getFavoriteMusics(): List<Music>
    suspend fun getMusicsByQuery(query: String): List<Music>

    suspend fun addFavoriteMusic(musicId: Long)
    suspend fun removeFavoriteMusic(musicId: Long)
}