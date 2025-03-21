package com.carlospolo.melichallenge.domain.usecase

import com.carlospolo.melichallenge.domain.repository.ProductsRepository
import com.carlospolo.melichallenge.mock.MOCK_PRODUCT_ITEM_MODEL_LIST
import com.carlospolo.melichallenge.utils.MeliResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class GetProductsUseCaseTest {

    @Mock
    private lateinit var repository: ProductsRepository

    private lateinit var useCase: GetProductsUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = GetProductsUseCase(repository)
    }

    @Test
    fun `invoke should return Success when repository returns data`() = runTest {
        // Arrange
        val searchQuery = "laptop"
        `when`(repository.getItemsBySearch(searchQuery)).thenReturn(MeliResult.Success(MOCK_PRODUCT_ITEM_MODEL_LIST))

        // Act
        val result = useCase(searchQuery).drop(1).first()

        // Assert
        assertTrue(result is MeliResult.Success)
        assertEquals(MOCK_PRODUCT_ITEM_MODEL_LIST, (result as MeliResult.Success).data)
    }

    @Test
    fun `invoke should return Error when repository returns an error`() = runTest {
        // Arrange
        val searchQuery = "laptop"
        val errorMessage = "Network error"
        `when`(repository.getItemsBySearch(searchQuery)).thenReturn(MeliResult.Error(Exception(errorMessage)))

        // Act
        val result = useCase(searchQuery).drop(1).first()

        // Assert
        assertTrue(result is MeliResult.Error)
        assertEquals(errorMessage, (result as MeliResult.Error).exception.message)
    }
}