package enric.domenech.app2u.ui.screens.home

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import enric.domenech.app2u.ui.screens.home.components.HomeBottomAppBar
import enric.domenech.app2u.ui.screens.home.components.HomeContent
import enric.domenech.app2u.ui.screens.home.components.HomeTopAppBar

@Composable
fun HomeView(
    nav: NavHostController, vm: HomeViewModel
) {

    val data = vm.dataState.collectAsState()

    Scaffold(
        topBar = { HomeTopAppBar() },
        bottomBar = { HomeBottomAppBar() },
        content = { paddingValues ->
            HomeContent(
                vm = vm, nav = nav, data = data.value, paddingValues = paddingValues
            )
        })
}