package enric.domenech.app2u.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import enric.domenech.app2u.ui.screens.home.HomeViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.java.KoinJavaComponent.inject

/**
 * NetworkMonitor
 *
 * Clase responsable de monitorear el estado de la conexión a internet del dispositivo.
 * Utiliza ConnectivityManager para registrar callbacks que detectan cambios en la
 * conectividad de red y actualiza un StateFlow para reflejar el estado actual.
 *
 * Cuando la conexión se restablece, sincroniza automáticamente los "likes" pendientes
 * que fueron almacenados localmente durante la desconexión.
 */

class NetworkMonitor(context: Context) {

    // Obtiene una instancia del ViewModel para sincronizar likes pendientes
    private val vm by inject<HomeViewModel>(HomeViewModel::class.java)

    // StateFlow para exponer el estado de conectividad actual
    private val _isConnected = MutableStateFlow(false)
    val isConnected: StateFlow<Boolean> get() = _isConnected

    // Gestor de conectividad del sistema para monitorear el estado de red
    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    // Callback que responde a los cambios en el estado de la red
    private val networkCallback = object : ConnectivityManager.NetworkCallback() {

        // Se ejecuta cuando una red con conectividad está disponible
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            _isConnected.value = true
            println("onAvailable sync data")
            vm.syncPendingLikes()
        }

        // Se ejecuta cuando se pierde la conectividad de red
        override fun onLost(network: Network) {
            super.onLost(network)
            _isConnected.value = false
        }
    }

    // Inicialización: registra el callback para monitorear cambios en la red
    init {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

}