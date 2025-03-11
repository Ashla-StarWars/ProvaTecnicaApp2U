package enric.domenech.app2u

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import coil3.util.DebugLogger
import enric.domenech.app2u.ui.navigation.Navigation
import enric.domenech.app2u.ui.theme.ProvaTecnicaApp2UTheme



@OptIn(ExperimentalCoilApi::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            ProvaTecnicaApp2UTheme {
                setSingletonImageLoaderFactory { context -> getAsyncImageLoader(context) }
                Navigation(nav = rememberNavController())
            }
        }
    }
}

fun getAsyncImageLoader(context: PlatformContext) =
    ImageLoader.Builder(context).crossfade(true).logger(DebugLogger()).build()