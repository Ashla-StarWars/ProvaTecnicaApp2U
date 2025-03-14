package enric.domenech.app2u.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import enric.domenech.app2u.ui.screens.detail.DetailView
import enric.domenech.app2u.ui.screens.detail.DetailViewModel
import enric.domenech.app2u.ui.screens.home.HomeView
import enric.domenech.app2u.ui.screens.home.HomeViewModel
import org.koin.compose.koinInject

@Composable
fun Navigation(
    nav: NavHostController,
) {
    NavHost(
        navController = nav,
        startDestination = HOME
    ) {

            /**
             * Pantalla inicial que muestra la lista de resultados
             * Inyecta las dependencias necesarias en el ViewModel
             */
            composable<HOME> {
                HomeView(
                    nav = nav,
                    vm = HomeViewModel(
                        koinInject(),
                        koinInject(),
                        koinInject()
                    ),
                )
            }

            /**
             * Pantalla de detalle que muestra información específica de un resultado
             * Recibe el ID del elemento a mostrar como parámetro de navegación
             */
            composable<DETAIL> { backStackEntry ->
                val detail = backStackEntry.toRoute<DETAIL>()
                DetailView(
                    nav = nav,
                    dataId = detail.detailId,
                    vm = DetailViewModel(
                        dataId = detail.detailId,
                        networkRepository = koinInject(),
                        realmRepository = koinInject()
                    )
                )
            }

    }
}
