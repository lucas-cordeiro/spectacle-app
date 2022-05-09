package br.com.spectacle.app.feature.movies.di

import br.com.spectacle.app.feature.movies.data.remote.MovieRemoteDataSource
import br.com.spectacle.app.feature.movies.data.repository.MovieRepositoryImpl
import br.com.spectacle.app.feature.movies.domain.repository.MovieRepository
import br.com.spectacle.app.feature.movies.domain.usecase.AddFavoriteMovieUseCase
import br.com.spectacle.app.feature.movies.domain.usecase.GetFavoritesMoviesUseCase
import br.com.spectacle.app.feature.movies.domain.usecase.GetGenresMoviesUseCase
import br.com.spectacle.app.feature.movies.domain.usecase.GetPopularsMoviesUseCase
import br.com.spectacle.app.feature.movies.domain.usecase.RemoveFavoriteMovieUseCase
import br.com.spectacle.app.feature.movies.presentation.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val moviesModule = module {
    viewModel {
        val remoteDataSource = MovieRemoteDataSource(
            httpClient = get()
        )
        val movieRepository: MovieRepository = MovieRepositoryImpl(
            remoteDataSource = remoteDataSource
        )

        val getPopularsMoviesUseCase = GetPopularsMoviesUseCase(
            movieRepository = movieRepository
        )
        val getFavoritesMoviesUseCase = GetFavoritesMoviesUseCase(
            movieRepository =  movieRepository
        )
        val addFavoriteMovieUseCase = AddFavoriteMovieUseCase(
            movieRepository = movieRepository
        )
        val removeFavoriteMovieUseCase = RemoveFavoriteMovieUseCase(
            movieRepository = movieRepository
        )
        val getGenresMoviesUseCase = GetGenresMoviesUseCase(
            movieRepository = movieRepository
        )
        MoviesViewModel(
            getGenresMoviesUseCase = getGenresMoviesUseCase,
            getFavoritesMoviesUseCase = getFavoritesMoviesUseCase,
            addFavoriteMovieUseCase = addFavoriteMovieUseCase,
            removeFavoriteMovieUseCase = removeFavoriteMovieUseCase,
            getPopularsMoviesUseCase = getPopularsMoviesUseCase
        )
    }
}