package com.mamontov.pokemondex.ui.component.pokemon.stats

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mamontov.pokemondex.R
import com.mamontov.pokemondex.domain.entity.PokemonStats
import com.mamontov.pokemondex.ui.theme.PokemonDexTheme

@Composable
fun PokemonStatsComponent(
    pokemonStats: PokemonStats,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        PokemonStatsItemComponent(
            text = stringResource(R.string.pokedex_pokemon_stats_hp),
            stats = pokemonStats.health,
            color = color,
        )
        PokemonStatsItemComponent(
            text = stringResource(R.string.pokedex_pokemon_stats_attack),
            stats = pokemonStats.attack,
            color = color,
        )
        PokemonStatsItemComponent(
            text = stringResource(R.string.pokedex_pokemon_stats_defence),
            stats = pokemonStats.defense,
            color = color,
        )
        PokemonStatsItemComponent(
            text = stringResource(R.string.pokedex_pokemon_stats_special_attack),
            stats = pokemonStats.specialAttack,
            color = color,
        )
        PokemonStatsItemComponent(
            text = stringResource(R.string.pokedex_pokemon_stats_special_defence),
            stats = pokemonStats.specialDefence,
            color = color,
        )
        PokemonStatsItemComponent(
            text = stringResource(R.string.pokedex_pokemon_stats_speed),
            stats = pokemonStats.speed,
            color = color,
        )
        Text(
            text = stringResource(R.string.pokedex_pokemon_stats_base_total, pokemonStats.total),
            style = MaterialTheme.typography.bodySmall.copy(
                textAlign = TextAlign.End,
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun Preview() {
    PokemonDexTheme() {
        PokemonStatsComponent(
            pokemonStats = PokemonStats(
                health = 10,
                attack = 20,
                defense = 100,
                specialAttack = 105,
                specialDefence = 200,
                speed = 167,
            ),
        )
    }
}