package com.carlospolo.melichallenge.data.network.remote

import com.carlospolo.melichallenge.data.network.model.GetItemsEntity
import com.carlospolo.melichallenge.data.network.model.ItemDetailEntity
import com.carlospolo.melichallenge.data.network.utils.Constants.ARGUMENT_IDS
import com.carlospolo.melichallenge.data.network.utils.Constants.ARGUMENT_Q
import com.carlospolo.melichallenge.data.network.utils.Constants.ARGUMENT_SITE_ID
import com.carlospolo.melichallenge.data.network.utils.Constants.PATH_GET_ITEMS_BY_SEARCH
import com.carlospolo.melichallenge.data.network.utils.Constants.PATH_GET_ITEM_DETAIL
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Service interface for interacting with the product-related API endpoints.
 */
interface ProductsApiService {

    /**
     * Retrieves a list of items based on a search query.
     *
     * @param siteId The ID of the site (e.g., country or marketplace).
     * @param search The search keyword to filter items.
     * @return A [GetItemsEntity] containing the search results.
     */
    @GET(PATH_GET_ITEMS_BY_SEARCH)
    suspend fun getItemsBySearch(
        @Path(ARGUMENT_SITE_ID) siteId: String,
        @Query(ARGUMENT_Q) search: String
    ): GetItemsEntity

    /**
     * Retrieves the details of a specific item.
     *
     * @param productId The unique identifier of the product.
     * @return An [ItemDetailEntity] containing the product details.
     */
    @GET(PATH_GET_ITEM_DETAIL)
    suspend fun getDetailItem(
        @Query(ARGUMENT_IDS) productId: String
    ): ItemDetailEntity
}