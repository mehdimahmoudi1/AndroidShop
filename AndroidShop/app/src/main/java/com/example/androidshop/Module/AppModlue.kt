package com.example.androidshop.Module

import com.example.androidshop.api.costomers.UserApi
import com.example.androidshop.api.invoices.InvoiceApi
import com.example.androidshop.api.invoices.TransactionApi
import com.example.androidshop.api.products.ColorApi
import com.example.androidshop.api.products.ProductApi
import com.example.androidshop.api.products.ProductCategoryApi
import com.example.androidshop.api.site.BlogApi
import com.example.androidshop.api.site.ContentApi
import com.example.androidshop.api.site.SliderApi
import com.example.androidshop.repositories.customer.UserRepository
import com.example.androidshop.repositories.invoices.InvoiceRepository
import com.example.androidshop.repositories.invoices.TransactionRepository
import com.example.androidshop.repositories.products.ColorRepository
import com.example.androidshop.repositories.products.ProductCategoryRepository
import com.example.androidshop.repositories.products.ProductRepository
import com.example.androidshop.repositories.site.BlogRepository
import com.example.androidshop.repositories.site.ContentRepository
import com.example.androidshop.repositories.site.SliderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModlue {

    @Provides
    @Singleton
    fun provideBlogRepository(api: BlogApi) = BlogRepository(api)

    @Provides
    @Singleton
    fun provideSliderRepository(api: SliderApi) = SliderRepository(api)

    @Provides
    @Singleton
    fun provideContentRepository(api: ContentApi) = ContentRepository(api)

    @Provides
    @Singleton
    fun provideColorRepository(api: ColorApi) = ColorRepository(api)

    @Provides
    @Singleton
    fun provideProductRepository(api: ProductApi) = ProductRepository(api)

    @Provides
    @Singleton
    fun provideProductCategoryRepository(api: ProductCategoryApi) = ProductCategoryRepository(api)

    @Provides
    @Singleton
    fun provideUserRepository(api: UserApi) = UserRepository(api)

    @Provides
    @Singleton
    fun provideInvoiceRepository(api: InvoiceApi) = InvoiceRepository(api)

    @Provides
    @Singleton
    fun provideTransactionRepository(api: TransactionApi) = TransactionRepository(api)
}