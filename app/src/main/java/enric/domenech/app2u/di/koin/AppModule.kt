package enric.domenech.app2u.di.koin

import enric.domenech.app2u.data.realmDB.RealmResult
import enric.domenech.app2u.data.network.NetworkServiceImpl
import enric.domenech.app2u.data.repositories.RepositoryImpl
import enric.domenech.app2u.ui.screens.detail.DetailViewModel
import enric.domenech.app2u.ui.screens.home.HomeViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import java.util.Base64

val appModule = module {

    // Realm
    single {
        val config = RealmConfiguration.Builder(schema = setOf(RealmResult::class))
            .name("app2u.realm")
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.open(config)
    }

    // Json
    single {
        Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        }
    }

    // HTTPS Client
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(get())
            }
            defaultRequest {
                url {
                    protocol = URLProtocol.HTTPS

                    host = "inphototest.app2u.es"
                    val username = "test@gmail.com"
                    val password = "1234"
                    val userPass = "$username:$password"
                    val encoded = Base64.getEncoder().encodeToString(userPass.toByteArray())
                    headers.append(HttpHeaders.Authorization, "Basic $encoded")
                    headers.append(HttpHeaders.Accept, "application/json")
                }
            }
        }
    }

    // APIs
    single { NetworkServiceImpl(get(), get()) }

    // Repositories
    single { RepositoryImpl(get()) }

    // ViewModels
    viewModel { HomeViewModel(get(), get()) }
    viewModel { (dataId: Int) -> DetailViewModel(dataId, get(), get()) }

}

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(appModule)
    }
}