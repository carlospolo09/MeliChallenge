package com.carlospolo.melichallenge.navegation

/**
 * Represents the navigation destinations within the application.
 *
 * @property route The navigation route associated with the screen.
 */
sealed class Screen(val route: String) {

    /**
     * Screen for the product search.
     */
    data object Search : Screen("search")

    /**
     * Screen for displaying a list of products based on a search query.
     *
     * @property route The navigation route requiring a search parameter.
     */
    data object ProductList : Screen("productList/{search}") {

        /**
         * Generates the navigation route for a specific search query.
         *
         * @param search The search keyword used to populate the route.
         * @return The formatted route string.
         */
        fun createRoute(search: String) = "productList/$search"
    }

    /**
     * Screen for displaying the details of a selected product.
     *
     * @property route The navigation route requiring a product ID parameter.
     */
    data object ProductDetail : Screen ("productDetail/{productId}") {

        /**
         * Generates the navigation route for a specific product.
         *
         * @param productId The unique identifier of the product.
         * @return The formatted route string.
         */
        fun createRoute(productId: String) = "productDetail/$productId"
    }
}