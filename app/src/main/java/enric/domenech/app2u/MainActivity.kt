package enric.domenech.app2u

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import enric.domenech.app2u.ui.navigation.Navigation
import enric.domenech.app2u.ui.theme.ProvaTecnicaApp2UTheme

/**
 * MainActivity
 *
 * Actividad principal que actúa como punto de entrada de la aplicación.
 *
 * Esta clase es responsable de:
 * - Inicializar la interfaz de usuario con Jetpack Compose
 * - Configurar el sistema de navegación de la aplicación
 * - Aplicar el tema visual personalizado (ProvaTecnicaApp2UTheme)
 * - Habilitar la visualización edge-to-edge para una experiencia inmersiva
 *
 * La clase utiliza Jetpack Compose como framework de UI y configura
 * un NavController central que gestiona toda la navegación entre pantallas
 * definidas en el componente Navigation.
 */
class MainActivity : ComponentActivity() {

    /**
     * Inicializa la actividad y configura la interfaz de usuario.
     *
     * Este función realiza las siguientes operaciones:
     * 1. Llama al función onCreate de la clase padre
     * 2. Habilita el modo edge-to-edge para aprovechar toda la pantalla
     * 3. Configura el contenido Compose de la actividad con el tema personalizado
     * 4. Inicializa el sistema de navegación con un controlador de navegación
     *
     * @param savedInstanceState Estado guardado de la actividad, puede ser null si es la primera creación.
     */
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