package com.carlospolo.melichallenge.data.repository

import com.carlospolo.melichallenge.BuildConfig
import com.carlospolo.melichallenge.mock.MOCK_GET_ITEMS_ENTITY
import com.carlospolo.melichallenge.mock.MOCK_ITEM_DETAIL_ENTITY
import com.carlospolo.melichallenge.data.network.remote.ProductsApiService
import com.carlospolo.melichallenge.utils.MeliResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ProductsRepositoryImplTest {

    @Mock
    private lateinit var productsApiService: ProductsApiService

    private lateinit var repository: ProductsRepositoryImpl
    private lateinit var closeable: AutoCloseable

    @Before
    fun setUp() {
        closeable = MockitoAnnotations.openMocks(this)
        repository = ProductsRepositoryImpl(productsApiService)
    }

    @Test
    fun `getItemsBySearch should return Success when API call is successful`() = runTest {
        // Arrange
        val searchQuery = "laptop"
        `when`(productsApiService.getItemsBySearch(BuildConfig.SITE_ID, searchQuery)).thenReturn(
            MOCK_GET_ITEMS_ENTITY
        )

        // Act
        val result = repository.getItemsBySearch(searchQuery)

        // Assert
        assertTrue(result is MeliResult.Success)
        assertEquals(1, (result as MeliResult.Success).data.size)
        assertEquals(MOCK_GET_ITEMS_ENTITY.results.first().title, result.data.first().title)
    }

    @Test
    fun `getItemsBySearch should return Error when API call fails`() = runTest {
        // Arrange
        val searchQuery = "laptop"
        `when`(productsApiService.getItemsBySearch(BuildConfig.SITE_ID, searchQuery)).thenThrow(RuntimeException("Network Error"))

        // Act
        val result = repository.getItemsBySearch(searchQuery)

        // Assert
        assertTrue(result is MeliResult.Error)
        assertEquals("Network Error", (result as MeliResult.Error).exception.message)
    }

    @Test
    fun `getDetailItem should return Success when API call is successful`() = runTest {
        // Arrange
        val productId = "123"
        `when`(productsApiService.getDetailItem(productId)).thenReturn(MOCK_ITEM_DETAIL_ENTITY)

        // Act
        val result = repository.getDetailItem(productId)

        // Assert
        assertTrue(result is MeliResult.Success)
        assertEquals(MOCK_ITEM_DETAIL_ENTITY.title, (result as MeliResult.Success).data.title)
    }

    @Test
    fun `getDetailItem should return Error when API call fails`() = runTest {
        // Arrange
        val productId = "123"
        `when`(productsApiService.getDetailItem(productId)).thenThrow(RuntimeException("API Failure"))

        // Act
        val result = repository.getDetailItem(productId)

        // Assert
        assertTrue(result is MeliResult.Error)
        assertEquals("API Failure", (result as MeliResult.Error).exception.message)
    }
}