package com.mamontov.pokemondex.di

import com.mamontov.pokemondex.ui.screen.favorite.FavoriteViewModel
import com.mamontov.pokemondex.ui.screen.main.MainViewModel
import com.mamontov.pokemondex.ui.screen.pokedex.PokedexViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {

    viewModel {
        PokedexViewModel(
            observePokemonsUseCase = get(),
            changePokemonFavoriteUseCase = get(),
        )
    }
    viewModel {
        FavoriteViewModel(
            observePokemonFavoriteUseCase = get(),
            changePokemonFavoriteUseCase = get(),
        )
    }
    viewModel {
        MainViewModel(
            initPokemonsUseCase = get(),
            observeFavoriteCountUseCase = get(),
        )
    }
}