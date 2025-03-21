package com.carlospolo.melichallenge.domain.usecase

import com.carlospolo.melichallenge.domain.usecase.GetProductDetailUseCase
import com.carlospolo.melichallenge.domain.repository.ProductsRepository
import com.carlospolo.melichallenge.mock.MOCK_PRODUCT_DETAIL_MODEL
import com.carlospolo.melichallenge.utils.MeliResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetProductDetailUseCaseTest {

    @Mock
    private lateinit var repository: ProductsRepository

    private lateinit var useCase: GetProductDetailUseCase

    @Before
    fun setUp() {
        useCase = GetProductDetailUseCase(repository)
    }

    @Test
    fun `invoke should emit Loading and then Success when repository returns data`() = runTest {
        // Arrange
        val productId = "123"
        `when`(repository.getDetailItem(productId)).thenReturn(MeliResult.Success(MOCK_PRODUCT_DETAIL_MODEL))

        // Act
        val result = useCase(productId).toList()

        // Assert
        assertEquals(2, result.size)
        assertTrue(result[0] is MeliResult.Loading)
        assertTrue(result[1] is MeliResult.Success)
        assertEquals(MOCK_PRODUCT_DETAIL_MODEL, (result[1] as MeliResult.Success).data)
    }

    @Test
    fun `invoke should emit Loading and then Error when repository returns an error`() = runTest {
        // Arrange
        val productId = "123"
        val error = RuntimeException("API Failure")
        `when`(repository.getDetailItem(productId)).thenReturn(MeliResult.Error(error))

        // Act
        val result = useCase(productId).toList()

        // Assert
        assertEquals(2, result.size)
        assertTrue(result[0] is MeliResult.Loading)
        assertTrue(result[1] is MeliResult.Error)
        assertEquals("API Failure", (result[1] as MeliResult.Error).exception.message)
    }
}