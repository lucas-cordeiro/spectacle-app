package br.com.spectacle.app.feature.movies.data.remote

import br.com.spectacle.app.core.data.remote.SpectacleHttpClient
import br.com.spectacle.app.core.data.remote.model.ListResponse
import br.com.spectacle.app.feature.movies.data.remote.model.GenreResponse
import br.com.spectacle.app.feature.movies.data.remote.model.MovieResponse
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post

internal class MovieRemoteDataSource(
    private val httpClient: SpectacleHttpClient
) {
    suspend fun getPopulars(): ListResponse<MovieResponse> {
        return httpClient().get("/search/movies/populars")
    }

    suspend fun getFavorites(): ListResponse<MovieResponse> {
        return httpClient().get("/users/movies")
    }

    suspend fun addFavoriteMovie(movieId: Long) {
        return httpClient().post("/users/movies"){
            body = hashMapOf("id" to movieId)
        }
    }

    suspend fun removeFavoriteMovie(movieId: Long) {
        return httpClient().delete("/users/movies"){
            body = hashMapOf("id" to movieId)
        }
    }

    suspend fun getGenres(): ListResponse<GenreResponse> {
        return httpClient().get("/search/movies/genres")
    }
}