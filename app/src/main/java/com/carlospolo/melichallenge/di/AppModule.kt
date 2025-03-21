package com.carlospolo.melichallenge.di

import com.carlospolo.melichallenge.BuildConfig
import com.carlospolo.melichallenge.data.network.remote.ProductsApiService
import com.carlospolo.melichallenge.data.repository.ProductsRepositoryImpl
import com.carlospolo.melichallenge.domain.repository.ProductsRepository
import com.carlospolo.melichallenge.domain.usecase.GetProductDetailUseCase
import com.carlospolo.melichallenge.domain.usecase.GetProductsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Dagger-Hilt module that provides dependencies for the application.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides a singleton instance of [Retrofit] for making API requests.
     *
     * @return A configured [Retrofit] instance.
     */
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Provides an implementation of [ProductsApiService] using the provided [Retrofit] instance.
     *
     * @param retrofit The [Retrofit] instance used to create the API service.
     * @return An instance of [ProductsApiService].
     */
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ProductsApiService {
        return retrofit.create(ProductsApiService::class.java)
    }

    /**
     * Provides an implementation of [ProductsRepository].
     *
     * @param productsApiService The API service used to fetch product data.
     * @return An instance of [ProductsRepository] that interacts with the API.
     */
    @Provides
    @Singleton
    fun provideProductsRepository(productsApiService: ProductsApiService): ProductsRepository {
        return ProductsRepositoryImpl(productsApiService)
    }

    /**
     * Provides an instance of [GetProductsUseCase].
     *
     * @param productsRepository The repository used to fetch products.
     * @return An instance of [GetProductsUseCase].
     */
    @Provides
    fun provideGetProductsUseCase(productsRepository: ProductsRepository): GetProductsUseCase {
        return GetProductsUseCase(productsRepository)
    }

    /**
     * Provides an instance of [GetProductDetailUseCase].
     *
     * @param productsRepository The repository used to fetch product details.
     * @return An instance of [GetProductDetailUseCase].
     */
    @Provides
    fun provideGetProductDetailUseCase(productsRepository: ProductsRepository): GetProductDetailUseCase {
        return GetProductDetailUseCase(productsRepository)
    }
}