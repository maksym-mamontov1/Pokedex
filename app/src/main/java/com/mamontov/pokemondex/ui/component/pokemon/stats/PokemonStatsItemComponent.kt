package com.mamontov.pokemondex.ui.component.pokemon.stats

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.mamontov.pokemondex.domain.entity.PokemonStats
import com.mamontov.pokemondex.ui.theme.PokemonDexTheme

@Composable
fun PokemonStatsItemComponent(
    text: String,
    stats: Int,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.fillMaxWidth(0.25f)
        )

        LinearProgressIndicator(
            progress = { stats.toFloat() / PokemonStats.MAX_STAT },
            trackColor = MaterialTheme.colorScheme.background.copy(alpha = 0.2f),
            color = color,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = "$stats",
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.fillMaxWidth(0.15f)
        )
    }
}

@Preview
@Composable
private fun Preview() {
    PokemonDexTheme {
        PokemonStatsItemComponent(
            text = "HP",
            stats = 100,
        )
    }
}