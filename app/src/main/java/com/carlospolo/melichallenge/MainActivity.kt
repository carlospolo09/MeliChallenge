package com.carlospolo.melichallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.carlospolo.melichallenge.navegation.Screen
import com.carlospolo.melichallenge.presentation.ui.productdetail.ProductDetailScreen
import com.carlospolo.melichallenge.presentation.ui.productlist.ProductListScreen
import com.carlospolo.melichallenge.presentation.ui.productsearch.ProductSearchScreen
import com.carlospolo.melichallenge.utils.Constants.ARGUMENT_PRODUCT_ID
import com.carlospolo.melichallenge.utils.Constants.ARGUMENT_SEARCH
import com.carlospolo.melichallenge.utils.Constants.N_A
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main activity of the MeliChallengeApp.
 *
 * This activity sets up the navigation structure using Jetpack Compose
 * and manages the navigation between different screens.
 *
 * - Annotated with `@AndroidEntryPoint` to enable Hilt dependency injection.
 * - Uses `NavHost` to define navigation between search, product list, and product detail screens.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /**
     * Called when the activity is created.
     * Sets up the navigation graph for the app using Jetpack Compose.
     *
     * @param savedInstanceState The saved instance state bundle.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = Screen.Search.route) {

                // Search Screen: Navigates to the product list when a search is performed.
                composable(Screen.Search.route) {
                    ProductSearchScreen { search ->
                        navController.navigate(Screen.ProductList.createRoute(search))
                    }
                }

                // Product List Screen: Displays a list of products based on the search query.
                composable(
                    route = Screen.ProductList.route,
                    arguments = listOf(navArgument(ARGUMENT_SEARCH) { type = NavType.StringType })
                ) { backStackEntry ->
                    val search = backStackEntry.arguments?.getString(ARGUMENT_SEARCH) ?: N_A
                    ProductListScreen(
                        search = search,
                        onProductClick = { productId ->
                            navController.navigate(Screen.ProductDetail.createRoute(productId))
                        },
                        onBack = {
                            navController.popBackStack()
                        }
                    )
                }

                // Product Detail Screen: Displays details of a selected product.
                composable(
                    route = Screen.ProductDetail.route,
                    arguments = listOf(navArgument(ARGUMENT_PRODUCT_ID) { type = NavType.StringType })
                ) { backStackEntry ->
                    val productId = backStackEntry.arguments?.getString(ARGUMENT_PRODUCT_ID) ?: N_A
                    ProductDetailScreen(
                        productId = productId,
                        onBack = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}