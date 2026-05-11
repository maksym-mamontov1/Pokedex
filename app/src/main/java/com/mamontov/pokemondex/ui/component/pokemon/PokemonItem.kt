package com.mamontov.pokemondex.ui.component.pokemon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mamontov.pokemondex.R
import com.mamontov.pokemondex.domain.entity.Pokemon
import com.mamontov.pokemondex.domain.entity.PokemonStats
import com.mamontov.pokemondex.ui.component.pokemon.stats.PokemonStatsComponent
import com.mamontov.pokemondex.ui.theme.PokemonDexTheme
import androidx.compose.ui.graphics.Color
import com.mamontov.pokemondex.ui.theme.color.PokemonTypeColors
import com.mamontov.pokemondex.ui.theme.color.LocalPokemonTypeColorScheme
import com.mamontov.pokemondex.ui.theme.color.Red
import com.mamontov.pokemondex.ui.theme.color.getColorForType

@Composable
fun PokemonItem(
    pokemon: Pokemon,
    onFavoriteClick: (Pokemon) -> Unit,
    modifier: Modifier = Modifier,
) {
    val typeColorScheme = LocalPokemonTypeColorScheme.current
    val typeColorsFromSchema = typeColorScheme.getColorForType(
        type = pokemon.types.firstOrNull() ?: "",
        skipForCard = true
    )
    val typeColors = PokemonTypeColors(
        primary = typeColorsFromSchema.primary.takeIf { it != Color.Unspecified }
            ?: MaterialTheme.colorScheme.primaryContainer,
        secondary = typeColorsFromSchema.secondary.takeIf { it != Color.Unspecified }
            ?: MaterialTheme.colorScheme.primary
    )

    Card(
        colors = CardDefaults.cardColors(
            containerColor = typeColors.primary
        ),
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Row {
                AsyncImage(
                    pokemon.avatarUrl.takeIf { it != "" },
                    contentScale = ContentScale.FillBounds,
                    contentDescription = null,
                    modifier = Modifier
                        .size(85.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(12.dp),
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f),
                ) {
                    Text(
                        pokemon.name,
                        style = MaterialTheme.typography.titleLarge.copy(
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold,
                        )
                    )

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        items(pokemon.types) { type ->
                            PokemonTypeItem(
                                type = type,
                                containerColor = typeColorScheme.getColorForType(type).secondary
                            )
                        }
                    }

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        items(pokemon.abilities) { ability ->
                            PokemonAbilityItem(
                                ability = ability,
                                borderColor = typeColors.secondary
                            )
                        }
                    }
                }

                IconButton(
                    onClick = { onFavoriteClick(pokemon) },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary
                    )
                ) {
                    Icon(
                        painter = painterResource(
                            if (pokemon.isFavorite) R.drawable.ic_pokemon_favorite_selected
                            else R.drawable.ic_pokemon_favorite
                        ),
                        tint = if (pokemon.isFavorite) Red
                        else MaterialTheme.colorScheme.onTertiary,
                        contentDescription = null,
                    )
                }
            }
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 16.dp)
            )

            PokemonStatsComponent(
                pokemonStats = pokemon.pokemonStats,
                color = typeColors.secondary,
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    PokemonDexTheme() {
        PokemonItem(
            pokemon = Pokemon(
                id = 0,
                name = "Bulbozavr",
                avatarUrl = "",
                types = listOf("bug", "water"),
                abilities = listOf("Power", "Ability"),
                pokemonStats = PokemonStats(
                    health = 10,
                    attack = 20,
                    defense = 100,
                    specialAttack = 100,
                    specialDefence = 100,
                    speed = 100,
                ),
                isFavorite = true,
            ),
            onFavoriteClick = {}
        )
    }
}