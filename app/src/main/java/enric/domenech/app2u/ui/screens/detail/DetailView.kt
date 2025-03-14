package enric.domenech.app2u.ui.screens.detail

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavHostController
import enric.domenech.app2u.ui.screens.detail.components.DetailContent
import enric.domenech.app2u.ui.screens.detail.components.DetailTopAppBar
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun DetailView(
    dataId: Int,
    nav: NavHostController,
    vm: DetailViewModel = koinViewModel { parametersOf(dataId) }
) {

    val item = vm.dataState.collectAsState()

    Scaffold(
        topBar = { DetailTopAppBar(nav) },
        content = { paddingValues ->
            item.value?.let { item ->
                DetailContent(item, paddingValues, onFavoriteClick = {
                    vm.toggleFavorite(it.id)
                })
            }
        }
    )
}


