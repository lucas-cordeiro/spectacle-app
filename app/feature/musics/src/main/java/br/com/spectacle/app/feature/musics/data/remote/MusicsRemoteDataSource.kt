package br.com.spectacle.app.feature.musics.data.remote

import br.com.spectacle.app.core.data.remote.SpectacleHttpClient
import br.com.spectacle.app.core.data.remote.model.ListResponse
import br.com.spectacle.app.feature.musics.data.remote.model.MusicResponse
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post

internal class MusicsRemoteDataSource(
    private val httpClient: SpectacleHttpClient,
) {
    suspend fun getFavoriteMusics(): ListResponse<MusicResponse> {
        return httpClient().get("/users/musics")
    }
    suspend fun getMusicsByQuery(query: String): ListResponse<MusicResponse> {
        return httpClient().get("/search/musics"){
            parameter("q",query)
        }
    }

    suspend fun addFavoriteMusic(musicId: Long) {
        return httpClient().post("/users/musics"){
            body = hashMapOf("id" to musicId)
        }
    }
    suspend fun removeFavoriteMusic(musicId: Long){
        return httpClient().delete("/users/musics"){
            body = hashMapOf("id" to musicId)
        }
    }
}