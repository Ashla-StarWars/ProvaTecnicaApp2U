package enric.domenech.app2u

import android.app.Application

import enric.domenech.app2u.di.koin.appModule
import enric.domenech.app2u.di.koin.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

/**
 * MyApp
 *
 * Clase de aplicación personalizada que inicializa el framework de inyección de dependencias Koin.
 *
 * Esta clase es responsable de:
 * - Configurar la instancia global de la aplicación Android
 * - Inicializar el contenedor de dependencias Koin
 * - Establecer el nivel de logging para depuración
 * - Proporcionar el contexto de la aplicación a Koin
 * - Cargar los módulos de inyección de dependencias
 *
 * MyApp actúa como punto central para la configuración inicial de la aplicación
 * y asegura que todos los componentes tengan acceso a sus dependencias necesarias
 * a través de Koin desde el momento en que se lanza la aplicación.
 */
class MyApp : Application() {

    /**
     * Inicializa la aplicación y configura la inyección de dependencias con Koin.
     *
     * Este función realiza las siguientes operaciones:
     * 1. Llama al función onCreate de la clase padre
     * 2. Inicializa el framework Koin con la configuración necesaria
     * 3. Establece el nivel de logging para depuración
     * 4. Proporciona el contexto de la aplicación para componentes que lo requieran
     * 5. Carga los módulos definidos en appModule para la inyección de dependencias
     */
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MyApp)
            modules(appModule)
        }
    }
}