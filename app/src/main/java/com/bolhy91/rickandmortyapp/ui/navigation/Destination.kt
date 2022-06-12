package com.bolhy91.rickandmortyapp.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Destination(
    val route: String,
    val arguments: List<NamedNavArgument>
) {
    object CharacterList : Destination("characters", emptyList())
    object CharacterDetail : Destination("character",
        arguments = listOf(
            navArgument("id") {
                type = NavType.IntType
                defaultValue = 1
            }
        ))
}
