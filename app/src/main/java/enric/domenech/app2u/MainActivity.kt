package enric.domenech.app2u

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import enric.domenech.app2u.ui.navigation.Navigation
import enric.domenech.app2u.ui.theme.ProvaTecnicaApp2UTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            ProvaTecnicaApp2UTheme {
                Navigation(nav = rememberNavController())
            }
        }
    }
}