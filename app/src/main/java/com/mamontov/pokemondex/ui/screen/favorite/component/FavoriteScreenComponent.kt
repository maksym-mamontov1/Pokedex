package com.mamontov.pokemondex.ui.screen.favorite.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
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

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@Composable
fun FavoriteScreenComponent(
    pokemons: List<Pokemon>,
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
            title = stringResource(R.string.pokedex_pokemon_favorite_title),
            onSearchChanged = onSearchChanged,
            scrollProgress = scrollProgress,
        ) {
            coroutineScope.launch {
                scrollState.animateScrollToItem(0)
            }
        }
        if (pokemons.isEmpty()) {
            FavoritePlaceholderComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        } else {
            LazyColumn(
                state = scrollState,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 24.dp, top = 16.dp),
            ) {
                items(
                    items = pokemons,
                    key = { it.id }
                ) { pokemon ->
                    PokemonItem(
                        pokemon = pokemon,
                        onFavoriteClick = onFavoriteClick,
                        modifier = Modifier.animateItem()
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    PokemonDexTheme {
        FavoriteScreenComponent(
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
                    isFavorite = true,
                )
            ),
            onFavoriteClick = {},
            onSearchChanged = {},
        )
    }
}

@Preview(
    showSystemUi = true
)
@Composable
private fun Empy_Preview() {
    PokemonDexTheme {
        FavoriteScreenComponent(
            pokemons = listOf(
            ),
            onFavoriteClick = {},
            onSearchChanged = {},
        )
    }
}