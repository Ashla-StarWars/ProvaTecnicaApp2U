package enric.domenech.app2u.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import enric.domenech.app2u.ui.screens.home.HomeView
import enric.domenech.app2u.ui.screens.home.HomeViewModel

@Composable
fun Navigation(
    nav: NavHostController,
) {

    NavHost(navController = nav, startDestination = HOME) {

        composable<HOME> {
            HomeView(
//                nav = nav,
//                vm = HomeViewModel(),
            )
        }

//        composable<DETAIL> { backStackEntry ->
//            val detail = backStackEntry.toRoute<DETAIL>()
//            DetailView(
//                nurseId = detail.nurseId,
//                nav = nav,
//                vm = DetailViewModel(
//                    repository = koinInject(),
//                    nurseId = detail.nurseId
//                )
//            )
//        }
    }
}