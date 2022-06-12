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
import com.example.androidshop.configs.UnsafeSSLConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    fun provideApi():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://10.0.2.2:8080/")
            .client(UnsafeSSLConfig.unsafeOkHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideSliderApi():SliderApi{
        return provideApi().create(SliderApi::class.java)
    }

    @Provides
    @Singleton
    fun provideContentApi():ContentApi{
        return provideApi().create(ContentApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBlogApi():BlogApi{
        return provideApi().create(BlogApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserApi():UserApi{
        return provideApi().create(UserApi::class.java)
    }
    @Provides
    @Singleton
    fun provideInvoiceApi(): InvoiceApi {
        return provideApi().create(InvoiceApi::class.java)
    }
    @Provides
    @Singleton
    fun provideTransactionApi(): TransactionApi {
        return provideApi().create(TransactionApi::class.java)
    }
    @Provides
    @Singleton
    fun provideColorApi(): ColorApi {
        return provideApi().create(ColorApi::class.java)
    }
    @Provides
    @Singleton
    fun provideProductApi(): ProductApi {
        return provideApi().create(ProductApi::class.java)
    }
    @Provides
    @Singleton
    fun provideProductCategoryApi(): ProductCategoryApi {
        return provideApi().create(ProductCategoryApi::class.java)
    }
}