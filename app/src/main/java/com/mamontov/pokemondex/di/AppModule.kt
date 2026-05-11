package com.mamontov.pokemondex.di

val AppModule = listOf(
    NetworkModule,
    DataBaseModule,
    RepositoryModule,
    DataSourceModule,
    MapperModule,
    ViewModelModule,
    UseCaseModule,
)
