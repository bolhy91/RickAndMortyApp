package com.bolhy91.rickandmortyapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bolhy91.rickandmortyapp.presentation.characters_detail.CharacterDetailScreen
import com.bolhy91.rickandmortyapp.presentation.characters_lists.CharacterListScreen

@Composable
fun RickAndMortyNavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Destination.CharacterList.route) {
        characterListScreenNav(navHostController)
        characterDetailScreenNav(navHostController)
    }
}

fun NavGraphBuilder.characterListScreenNav(navHostController: NavHostController) {
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

fun NavGraphBuilder.characterDetailScreenNav(navHostController: NavHostController) {
    composable(
        route = Destination.CharacterDetail.route + "/{id}",
        arguments = Destination.CharacterDetail.arguments
    ) {
        CharacterDetailScreen()
    }
}
