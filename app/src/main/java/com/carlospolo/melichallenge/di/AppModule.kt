package com.carlospolo.melichallenge.di

import com.carlospolo.melichallenge.BuildConfig
import com.carlospolo.melichallenge.data.network.remote.ProductsApiService
import com.carlospolo.melichallenge.data.repository.ProductsRepositoryImpl
import com.carlospolo.melichallenge.data.repository.strategy.CacheFetchStrategy
import com.carlospolo.melichallenge.data.repository.strategy.ProductRepositoryStrategy
import com.carlospolo.melichallenge.data.repository.strategy.RemoteFetchStrategy
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
     * Provides an instance of [RemoteFetchStrategy] to handle data retrieval from a remote API.
     *
     * @param apiService The API service used to fetch product data.
     * @return An instance of [RemoteFetchStrategy].
     */
    @Provides
    fun provideRemoteFetchStrategy(apiService: ProductsApiService): RemoteFetchStrategy {
        return RemoteFetchStrategy(apiService)
    }

    /**
     * Provides an instance of [CacheFetchStrategy] to handle data retrieval from a local cache.
     * Currently, this strategy is not implemented.
     *
     * @return An instance of [CacheFetchStrategy].
     */
    @Provides
    fun provideCacheFetchStrategy(): CacheFetchStrategy {
        return CacheFetchStrategy()
    }

    /**
     * Provides an instance of [ProductRepositoryStrategy] to manage the selection of
     * data retrieval strategies.
     *
     * @param remoteStrategy The strategy for fetching data from a remote source.
     * @param cacheStrategy The strategy for fetching data from a local cache.
     * @return An instance of [ProductRepositoryStrategy].
     */
    @Provides
    fun provideStrategyContext(
        remoteStrategy: RemoteFetchStrategy,
        cacheStrategy: CacheFetchStrategy
    ): ProductRepositoryStrategy {
        return ProductRepositoryStrategy(remoteStrategy, cacheStrategy)
    }

    /**
     * Provides an instance of [ProductsRepository] that utilizes a strategy-based approach
     * for fetching product data.
     *
     * @param strategyContext The strategy context that determines the data source.
     * @return An instance of [ProductsRepositoryImpl].
     */
    @Provides
    fun provideProductsRepository(strategyContext: ProductRepositoryStrategy): ProductsRepository {
        return ProductsRepositoryImpl(strategyContext)
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