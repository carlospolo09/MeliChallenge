package com.carlospolo.melichallenge.data.network.utils

/**
 * Object containing constant values used throughout the application.
 */
object Constants {
    // API Endpoints
    /** Path to fetch items by search query. */
    internal const val PATH_GET_ITEMS_BY_SEARCH = "/sites/{site_id}/search"

    /** Path to fetch item details. */
    internal const val PATH_GET_ITEM_DETAIL = "/items"

    // API Parameters
    /** Query parameter for the site ID. */
    internal const val ARGUMENT_SITE_ID = "site_id"

    /** Query parameter for the search keyword. */
    internal const val ARGUMENT_Q = "q"

    /** Query parameter for item IDs. */
    internal const val ARGUMENT_IDS = "ids"

    // Error Log Messages
    /** Error message for failures when fetching product details. */
    internal const val ERROR_LOG_DETAIL_ITEM = "Error fetching detail for productId: %s"

    /** Error message for failures when fetching a list of products. */
    internal const val ERROR_LOG_LIST_ITEM = "Error fetching items for search: %s"
}