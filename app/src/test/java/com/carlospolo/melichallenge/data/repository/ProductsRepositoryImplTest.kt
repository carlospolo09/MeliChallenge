package com.carlospolo.melichallenge.data.repository

import com.carlospolo.melichallenge.data.repository.strategy.CacheFetchStrategy
import com.carlospolo.melichallenge.data.repository.strategy.ProductRepositoryStrategy
import com.carlospolo.melichallenge.data.repository.strategy.RemoteFetchStrategy
import com.carlospolo.melichallenge.mock.MOCK_PRODUCT_DETAIL_MODEL
import com.carlospolo.melichallenge.mock.MOCK_PRODUCT_ITEM_MODEL_LIST
import com.carlospolo.melichallenge.utils.MeliResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
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
    private lateinit var remoteFetchStrategy: RemoteFetchStrategy

    @Mock
    private lateinit var cacheFetchStrategy: CacheFetchStrategy

    private lateinit var strategyContext: ProductRepositoryStrategy
    private lateinit var repository: ProductsRepositoryImpl
    private lateinit var closeable: AutoCloseable

    @Before
    fun setUp() {
        closeable = MockitoAnnotations.openMocks(this)
        strategyContext = ProductRepositoryStrategy(remoteFetchStrategy, cacheFetchStrategy)
        repository = ProductsRepositoryImpl(strategyContext)
    }

    @After
    fun tearDown() {
        closeable.close()
    }

    @Test
    fun `getItemsBySearch should return Success when remote strategy succeeds`() = runTest {
        // Arrange
        val searchQuery = "laptop"
        `when`(remoteFetchStrategy.getItemsBySearch(searchQuery)).thenReturn(
            MeliResult.Success(MOCK_PRODUCT_ITEM_MODEL_LIST)
        )

        // Act
        val result = repository.getItemsBySearch(searchQuery)

        // Assert
        assertTrue(result is MeliResult.Success)
        assertEquals(2, (result as MeliResult.Success).data.size)
        assertEquals(MOCK_PRODUCT_ITEM_MODEL_LIST.first().title, result.data.first().title)
    }

    @Test
    fun `getItemsBySearch should return Error when remote strategy fails`() = runTest {
        // Arrange
        val searchQuery = "laptop"
        `when`(remoteFetchStrategy.getItemsBySearch(searchQuery)).thenReturn(
            MeliResult.Error(RuntimeException("Network Error"))
        )

        // Act
        val result = repository.getItemsBySearch(searchQuery)

        // Assert
        assertTrue(result is MeliResult.Error)
        assertEquals("Network Error", (result as MeliResult.Error).exception.message)
    }

    @Test
    fun `getDetailItem should return Success when remote strategy succeeds`() = runTest {
        // Arrange
        val productId = "123"
        `when`(remoteFetchStrategy.getDetailItem(productId)).thenReturn(
            MeliResult.Success(MOCK_PRODUCT_DETAIL_MODEL)
        )

        // Act
        val result = repository.getDetailItem(productId)

        // Assert
        assertTrue(result is MeliResult.Success)
        assertEquals(MOCK_PRODUCT_DETAIL_MODEL.title, (result as MeliResult.Success).data.title)
    }

    @Test
    fun `getDetailItem should return Error when remote strategy fails`() = runTest {
        // Arrange
        val productId = "123"
        `when`(remoteFetchStrategy.getDetailItem(productId)).thenReturn(
            MeliResult.Error(RuntimeException("API Failure"))
        )

        // Act
        val result = repository.getDetailItem(productId)

        // Assert
        assertTrue(result is MeliResult.Error)
        assertEquals("API Failure", (result as MeliResult.Error).exception.message)
    }
}