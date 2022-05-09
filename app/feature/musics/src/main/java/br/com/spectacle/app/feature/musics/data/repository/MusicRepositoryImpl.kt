package br.com.spectacle.app.feature.musics.data.repository

import br.com.spectacle.app.feature.musics.data.remote.MusicsRemoteDataSource
import br.com.spectacle.app.feature.musics.domain.model.Music
import br.com.spectacle.app.feature.musics.domain.repository.MusicRepository

internal class MusicRepositoryImpl(
    private val remoteDataSource: MusicsRemoteDataSource
) : MusicRepository {
    override suspend fun getFavoriteMusics(): List<Music> {
        val response = remoteDataSource.getFavoriteMusics()
        return response.data.map { input -> input.map() }
    }
    override suspend fun getMusicsByQuery(query: String): List<Music> {
        val response = remoteDataSource.getMusicsByQuery(query)
        return response.data.map { input -> input.map() }
    }

    override suspend fun addFavoriteMusic(musicId: Long) {
        return remoteDataSource.addFavoriteMusic(musicId)
    }

    override suspend fun removeFavoriteMusic(musicId: Long) {
        return remoteDataSource.removeFavoriteMusic(musicId)
    }
}