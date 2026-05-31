package com.template.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.template.app.ui.screens.detail.DetailScreen
import com.template.app.ui.screens.home.HomeScreen

/**
 * Navigation route constants.
 */
object Routes {
    const val HOME = "home"
    const val DETAIL = "detail/{itemId}"

    fun detailRoute(itemId: Int) = "detail/$itemId"
}

/**
 * Top-level navigation graph.
 * Add new screens by adding route constants and composable calls.
 */
@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String = Routes.HOME
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Home screen — list of items
        composable(route = Routes.HOME) {
            HomeScreen(
                onItemClick = { itemId ->
                    navController.navigate(Routes.detailRoute(itemId))
                }
            )
        }

        // Detail screen — item details
        composable(
            route = Routes.DETAIL,
            arguments = listOf(
                navArgument("itemId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt("itemId") ?: return@composable
            DetailScreen(
                itemId = itemId,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
