package enric.domenech.app2u.ui.screens.home.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import enric.domenech.app2u.domain.models.Result
import enric.domenech.app2u.ui.screens.home.HomeViewModel

@Composable
fun HomeContent(
    paddingValues: PaddingValues,
    nav: NavHostController,
    data: List<Result>,
    vm: HomeViewModel
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
    ) {
        item {
            HomeTitle()
        }
        item {
            HomeSelector()
        }
        item {
            HomeList(data, nav, vm)
        }
    }
}