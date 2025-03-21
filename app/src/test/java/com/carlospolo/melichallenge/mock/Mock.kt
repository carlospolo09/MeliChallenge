package com.carlospolo.melichallenge.mock

import com.carlospolo.melichallenge.data.network.model.GetItemsEntity
import com.carlospolo.melichallenge.data.network.model.Installments
import com.carlospolo.melichallenge.data.network.model.ItemDetailEntity
import com.carlospolo.melichallenge.data.network.model.Location
import com.carlospolo.melichallenge.data.network.model.Paging
import com.carlospolo.melichallenge.data.network.model.PictureEntity
import com.carlospolo.melichallenge.data.network.model.Product
import com.carlospolo.melichallenge.data.network.model.Seller
import com.carlospolo.melichallenge.data.network.model.Shipping
import com.carlospolo.melichallenge.domain.model.ProductDetailModel
import com.carlospolo.melichallenge.domain.model.ProductItemModel

internal val MOCK_GET_ITEMS_ENTITY = GetItemsEntity(
    siteId = "MLA",
    query = "laptop",
    paging = Paging(
        total = 100,
        offset = 0,
        limit = 50,
        primaryResults = 50
    ),
    results = listOf(
        Product(
            id = "MLA123456",
            siteId = "MLA",
            title = "Laptop Gamer",
            price = 1500.0,
            currencyId = "ARS",
            availableQuantity = 10,
            buyingMode = "buy_it_now",
            listingTypeId = "gold_pro",
            stopTime = "2025-12-31T23:59:59.999Z",
            condition = "new",
            permalink = "https://www.mercadolibre.com.ar/laptop-gamer-mla123456",
            thumbnail = "https://example.com/image.jpg",
            acceptsMercadoPago = true,
            installments = Installments(
                quantity = 12,
                amount = 125.0,
                rate = 0.0,
                currencyId = "ARS"
            ),
            shipping = Shipping(
                freeShipping = true,
                mode = "me2",
                tags = listOf("self_service_out"),
                logisticType = "drop_off",
                storePickUp = false
            ),
            originalPrice = 1600.0,
            categoryId = "MLA1652",
            officialStoreId = 1234,
            catalogProductId = "MLA1652-1234",
            catalogListing = true,
            seller = Seller(
                id = 567890,
                powerSellerStatus = "gold",
                carDealer = false,
                realEstateAgency = false,
                tags = listOf("normal")
            ),
            state = Location(id = "AR-B", name = "Buenos Aires"),
            city = Location(id = "AR-CABA", name = "CABA")
        )
    )
)

internal val MOCK_ITEM_DETAIL_ENTITY = ItemDetailEntity(
    title = "Smartphone XYZ",
    sellerId = 123456789L,
    categoryId = "MLA1055",
    officialStoreId = 9876,
    price = 999.99,
    basePrice = 999.99,
    originalPrice = 1099.99,
    currencyId = "ARS",
    initialQuantity = 100,
    buyingMode = "buy_it_now",
    listingTypeId = "gold_special",
    condition = "new",
    permalink = "https://www.mercadolibre.com.ar/smartphone-xyz",
    thumbnailId = "123abc",
    thumbnail = "https://example.com/thumbnail.jpg",
    acceptsMercadoPago = true,
    status = "active",
    pictures = listOf(
        PictureEntity(secureUrl = "https://example.com/image1.jpg"),
        PictureEntity(secureUrl = "https://example.com/image2.jpg")
    ),
    warranty = "1 año de garantía oficial",
    dateCreated = "2025-01-01T12:00:00.000Z",
    lastUpdated = "2025-03-01T18:30:00.000Z"
)

internal val MOCK_PRODUCT_DETAIL_MODEL = ProductDetailModel(
    title = "Laptop Gamer XYZ",
    price = "$2,500,000",
    originalPrice = "$2,800,000",
    warranty = "12 meses de garantía",
    condition = "Nuevo",
    imageUrls = listOf(
        "https://example.com/image1.jpg",
        "https://example.com/image2.jpg"
    ),
    permalink = "https://www.mercadolibre.com/item/123"
)

internal val MOCK_PRODUCT_ITEM_MODEL_LIST = listOf(
    ProductItemModel(
        id = "MLA123456",
        title = "Laptop Gamer XYZ",
        originalPrice = "5000.00",
        price = "4500.00",
        thumbnail = "https://example.com/image1.jpg"
    ),
    ProductItemModel(
        id = "MLA789012",
        title = "Smartphone ABC",
        originalPrice = null,
        price = "1200.00",
        thumbnail = "https://example.com/image2.jpg"
    )
)
