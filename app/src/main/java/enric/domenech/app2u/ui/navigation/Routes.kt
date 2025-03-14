package enric.domenech.app2u.ui.navigation

import kotlinx.serialization.Serializable

/**
 * Routes
 *
 * Define los destinos de navegación para la aplicación utilizando objetos serializables.
 *
 * Este archivo contiene las definiciones de todas las rutas de navegación disponibles
 * en la aplicación, permitiendo una navegación type-safe entre pantallas mediante
 * objetos Kotlin en lugar de cadenas de texto.
 *
 * El uso de la anotación @Serializable permite que estos objetos de ruta puedan ser
 * serializados para su paso entre composables y durante el proceso de navegación.
 */


/**
 * Destino de navegación para la pantalla principal (Home) de la aplicación.
 *
 * Implementado como un objeto singleton sin parámetros ya que la pantalla Home
 * no requiere argumentos para su navegación.
 */
@Serializable
object HOME

/**
 * Destino de navegación para la pantalla de detalle.
 *
 * @property detailId Identificador numérico único del elemento cuyo detalle
 * se desea visualizar. Este ID se utiliza para recuperar la información
 * específica del elemento seleccionado.
 */
@Serializable
data class DETAIL(val detailId: Int)