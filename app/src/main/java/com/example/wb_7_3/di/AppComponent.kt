package com.example.wb_7_3.di

import android.content.Context
import android.util.Log
import com.example.wb_7_3.data.local.db.FavoriteCatDao
import com.example.wb_7_3.data.local.db.FavoriteCatsDB
import com.example.wb_7_3.data.repository.GetCatRepositoryImpl
import com.example.wb_7_3.data.repository.GetFavoriteCatsRepositoryImpl
import com.example.wb_7_3.data.repository.PostFavoriteCatRepositoryImpl
import com.example.wb_7_3.domain.repository.GetCatRepository
import com.example.wb_7_3.domain.repository.GetFavoriteCatsRepository
import com.example.wb_7_3.domain.repository.PostFavoriteCatRepository
import com.example.wb_7_3.presentation.favorite.FavoriteFragment
import com.example.wb_7_3.presentation.search.SearchFragment
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.Provides
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*


@Component(modules = [DataModule::class, DomainBinds::class])
interface AppComponent {
    fun inject(searchFragment: SearchFragment)
    fun inject(favoriteFragment: FavoriteFragment)
}

@Module
class DataModule (private val context: Context) {

    @Provides
    fun provideContext(): Context{
        return context
    }

    @Provides
    fun provideDao(context: Context): FavoriteCatDao {
        return FavoriteCatsDB.getInstance(context = context).favoriteCatDao()
    }

    @Provides
    fun getClient(): HttpClient{
        return HttpClient() {
            install(JsonFeature) {
                serializer = KotlinxSerializer(
                kotlinx.serialization.json.Json {
                    ignoreUnknownKeys = true
                }
                )
            }

            install(Logging){
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d("Network Message", "log: $message")
                    }
                }
                level = LogLevel.ALL
            }
        }
    }
}

@Module
interface DomainBinds{

    @Binds
    fun getCatRepositoryImplToInterface(
        repositoryImpl: GetCatRepositoryImpl
    ): GetCatRepository

    @Binds
    fun postFavoriteCatRepositoryImplToInterface(
        repositoryImpl: PostFavoriteCatRepositoryImpl
    ): PostFavoriteCatRepository

    @Binds
    fun getFavoriteCatsRepositoryImplToInterface(
        repositoryImpl: GetFavoriteCatsRepositoryImpl
    ): GetFavoriteCatsRepository

}