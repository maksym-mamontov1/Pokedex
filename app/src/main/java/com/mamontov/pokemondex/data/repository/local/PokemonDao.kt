package com.mamontov.pokemondex.data.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mamontov.pokemondex.data.repository.local.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Query(
        "SELECT * FROM pokemon_table " +
                "WHERE (:query = '' OR LOWER(name) LIKE LOWER('%' || :query || '%') " +
                "OR LOWER(types) LIKE LOWER('%' || :query || '%') " +
                "OR LOWER(abilities) LIKE LOWER('%' || :query || '%')) " +
                "ORDER BY name ASC"
    )
    fun observePokemons(query: String): Flow<List<PokemonEntity>>

    @Query(
        "SELECT * FROM pokemon_table " +
                "WHERE favorite = 1 " +
                "AND (:query = '' OR LOWER(name) LIKE LOWER('%' || :query || '%') " +
                "OR LOWER(types) LIKE LOWER('%' || :query || '%') " +
                "OR LOWER(abilities) LIKE LOWER('%' || :query || '%')) " +
                "ORDER BY name ASC"
    )
    fun observeFavoritePokemons(query: String): Flow<List<PokemonEntity>>

    @Query("SELECT * FROM pokemon_table WHERE id == :pokemonId")
    suspend fun getPokemon(pokemonId: Int): PokemonEntity

    @Query("SELECT id FROM pokemon_table WHERE favorite = 1")
    suspend fun getFavoriteIds(): List<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPokemons(pokemonsEntity: List<PokemonEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPokemon(pokemonEntity: PokemonEntity)

    @Query("SELECT COUNT(*) FROM pokemon_table WHERE favorite = 1")
    fun observeFavoriteCount(): Flow<Int>
}