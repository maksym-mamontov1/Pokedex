package com.mamontov.pokemondex.ui.screen.pokedex.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mamontov.pokemondex.R
import com.mamontov.pokemondex.domain.entity.Pokemon
import com.mamontov.pokemondex.domain.entity.PokemonStats
import com.mamontov.pokemondex.ui.component.TitleComponent
import com.mamontov.pokemondex.ui.component.pokemon.PokemonItem
import com.mamontov.pokemondex.ui.theme.PokemonDexTheme
import kotlinx.coroutines.launch

@Composable
fun PokedexScreenComponent(
    pokemons: List<Pokemon>,
    isLoading: Boolean,
    onFavoriteClick: (Pokemon) -> Unit,
    onSearchChanged: (query: String) -> Unit,
) {
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    
    val scrollProgress by remember {
        derivedStateOf {
            val firstItemOffset = scrollState.firstVisibleItemScrollOffset.toFloat()
            val firstItemIndex = scrollState.firstVisibleItemIndex
            
            if (firstItemIndex > 0) 1f
            else (firstItemOffset / 200f).coerceIn(0f, 1f)
        }
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp),
    ) {
        TitleComponent(
            title = stringResource(R.string.pokedex_pokemon_title),
            onSearchChanged = onSearchChanged,
            scrollProgress = scrollProgress,
        ) {
            coroutineScope.launch {
                scrollState.animateScrollToItem(0)
            }
        }
        if (isLoading) Box(
            modifier = Modifier.weight(1f).fillMaxWidth()
        )  {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else LazyColumn(
            state = scrollState,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 24.dp, top = 16.dp),
        ) {
            items(pokemons) { pokemon ->
                PokemonItem(
                    pokemon = pokemon,
                    onFavoriteClick = onFavoriteClick,
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    PokemonDexTheme {
        PokedexScreenComponent(
            pokemons = listOf(
                Pokemon(
                    id = 0,
                    name = "Bulbozavr",
                    avatarUrl = "",
                    types = listOf("bug", "water"),
                    abilities = listOf("Power", "Grower"),
                    pokemonStats = PokemonStats(
                        health = 10,
                        attack = 20,
                        defense = 100,
                        specialAttack = 100,
                        specialDefence = 100,
                        speed = 100,
                    ),
                    isFavorite = false,
                )
            ),
            isLoading = true,
            onFavoriteClick = {},
            onSearchChanged = {},
        )
    }
}