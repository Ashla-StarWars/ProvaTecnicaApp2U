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
        composable<HOME> {
            HomeView(
                nav = nav,
                vm = HomeViewModel(
                    koinInject(),
                    koinInject()
                ),
            )
        }
        composable<DETAIL> { backStackEntry ->
            val detail = backStackEntry.toRoute<DETAIL>()
            DetailView(
                nav = nav,
                dataId = detail.detailId,
                vm = DetailViewModel(
                    repository = koinInject(),
                    dataId = detail.detailId,
                    realm = koinInject()
                )
            )
        }
    }
}
