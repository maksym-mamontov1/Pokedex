package com.mamontov.pokemondex.ui.component.pokemon

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mamontov.pokemondex.ui.theme.PokemonDexTheme

@Composable
fun PokemonTypeItem(
    type: String,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.primary,
) {
    Text(
        text = type,
        style = MaterialTheme.typography.bodyMedium.copy(
            color = MaterialTheme.colorScheme.onPrimary,
        ),
        modifier = modifier
            .background(
                shape = CircleShape,
                color = containerColor
            ).padding(horizontal = 8.dp)
    )
}

@Preview
@Composable
private fun Preview() {
    PokemonDexTheme() {
        PokemonTypeItem(
            type = "Sample"
        )
    }
}