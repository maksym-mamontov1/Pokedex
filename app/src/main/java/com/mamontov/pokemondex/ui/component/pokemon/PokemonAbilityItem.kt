package com.mamontov.pokemondex.ui.component.pokemon

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mamontov.pokemondex.ui.theme.PokemonDexTheme

@Composable
fun PokemonAbilityItem(
    ability: String,
    modifier: Modifier = Modifier,
    borderColor: Color = MaterialTheme.colorScheme.primary
) {

    Text(
        text = ability,
        style = MaterialTheme.typography.bodyMedium.copy(
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Bold
        ),
        modifier = modifier
            .border(1.dp, borderColor, CircleShape)
            .padding(horizontal = 6.dp),
    )

}

@Preview
@Composable
private fun Preview() {
    PokemonDexTheme() {
        PokemonAbilityItem(
            ability = "Sample"
        )
    }
}