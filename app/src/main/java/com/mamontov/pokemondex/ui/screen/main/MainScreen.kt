package com.mamontov.pokemondex.ui.screen.main

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mamontov.pokemondex.ui.screen.favorite.FavoriteScreen
import com.mamontov.pokemondex.ui.screen.pokedex.PokedexScreen
import com.mamontov.pokemondex.ui.theme.color.Red
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainScreen() {
    val viewModel = koinViewModel<MainViewModel>()
    val state by viewModel.state.collectAsState()
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val items = listOf(
        BottomNavItem.Pokedex,
        BottomNavItem.Wishlist
    )

    DisposableEffect(null) {
        viewModel.onScreenStart()
        onDispose {}
    }

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEach { item ->
                    val isSelected =
                        currentDestination?.hierarchy?.any { it.hasRoute(item::class) } == true
                    NavigationBarItem(
                        icon = {
                            BadgedBox(
                                badge = {
                                    if (item is BottomNavItem.Wishlist && state.favoriteCount > 0) {
                                        Badge(
                                            containerColor = Red,
                                            contentColor = Color.White,
                                            modifier = Modifier.offset(x = 4.dp, y = (-4).dp)
                                        ) {
                                            Text(text = state.favoriteCount.toString())
                                        }
                                    }
                                },
                            ) {
                                Icon(
                                    painter = painterResource(item.iconRes),
                                    contentDescription = stringResource(item.labelRes)
                                )
                            }
                        },
                        label = {
                            Text(text = stringResource(item.labelRes))
                        },
                        selected = isSelected,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = Color.Unspecified,
                            unselectedTextColor = Color.Unspecified
                        ),
                        onClick = {
                            navController.navigate(item) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            NavHost(
                navController = navController,
                startDestination = BottomNavItem.Pokedex,
                enterTransition = {
                    if (targetState.destination.hasRoute(BottomNavItem.Wishlist::class)) {
                        slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(400))
                    } else {
                        slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(400))
                    }
                },
                exitTransition = {
                    if (targetState.destination.hasRoute(BottomNavItem.Wishlist::class)) {
                        slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(400))
                    } else {
                        slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(400))
                    }
                }
            ) {
                composable<BottomNavItem.Pokedex> {
                    PokedexScreen()
                }
                composable<BottomNavItem.Wishlist> {
                    FavoriteScreen()
                }
            }
        }
    }
}