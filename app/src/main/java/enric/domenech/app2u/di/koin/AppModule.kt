package enric.domenech.app2u.di.koin

import enric.domenech.app2u.ui.screens.home.HomeViewModel
import kotlinx.serialization.json.Json
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

// Define el módulo de Koin
val appModule = module {

    // Configuración de Json con prettyPrint
    single {
        Json { prettyPrint = true; ignoreUnknownKeys = true }
    }

    // HttpClient client singleton
//    single {
//        HttpClient(OkHttp) {
//            val json = get<Json>()
//            install(ContentNegotiation) {
//                json(json)
//            }
//            install(Logging) {
//                level = LogLevel.BODY
//            }
//            install(DefaultRequest) {
//                url {
//                    protocol = URLProtocol.HTTP
//                    host = "10.118.3.210"
//                    port = 8080
//                }
//            }
//        }
//    }

    viewModel { HomeViewModel() }

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