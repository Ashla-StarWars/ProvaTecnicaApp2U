package enric.domenech.app2u.di.koin

import android.os.Build
import androidx.annotation.RequiresApi
import coil3.ImageLoader
import coil3.request.crossfade
import enric.domenech.app2u.data.network.NetworkServiceImpl
import enric.domenech.app2u.data.repositories.RepositoryImpl
import enric.domenech.app2u.ui.screens.home.HomeViewModel
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import java.util.Base64

// Define el módulo de Koin
@RequiresApi(Build.VERSION_CODES.O)
val appModule = module {

    single {
        ImageLoader.Builder(get())
            .crossfade(true)
            .build()
    }

    // singleton Json con prettyPrint
    single {
        Json {
            prettyPrint = true;
            ignoreUnknownKeys = true
        }
    }

    // singleton HTTPS Client
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

    viewModel { HomeViewModel(get()) }

}


fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(appModule)
    }

    //val networkServices: NetworkServicesImpl = getKoin().get()
    //Log.d(TAG, "CLIENTE HTTP INYECTADO en NetworkServices: $networkServices")

    //val viewModelProfile: ProfileViewModel = getKoin().get()
    //Log.d(TAG, "VIEWMODEL INYECTADO en ProfileViewModel: $viewModelProfile")
}