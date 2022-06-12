package com.bolhy91.rickandmortyapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bolhy91.rickandmortyapp.presentation.characters_lists.CharacterListScreen

@Composable
fun RickAndMortyNavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Destination.CharacterList.route) {
        CharacterListScreenNav(navHostController)
    }
}

fun NavGraphBuilder.CharacterListScreenNav(navHostController: NavHostController) {
    composable(
        route = Destination.CharacterList.route,
    ) {
        CharacterListScreen(
            onClickItem = { id ->
                navHostController.navigate("${Destination.CharacterDetail.route}/${id}")
            }
        )
    }
}

fun NavGraphBuilder.CharacterDetailScreenNav(navHostController: NavHostController) {
    composable(
        route = Destination.CharacterList.route,
    ) {
    }
}
