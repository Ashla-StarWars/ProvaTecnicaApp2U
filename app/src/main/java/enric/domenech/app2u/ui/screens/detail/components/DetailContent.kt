package enric.domenech.app2u.ui.screens.detail.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import enric.domenech.app2u.domain.models.Result
import enric.domenech.app2u.ui.screens.detail.DetailViewModel

@Composable
fun DetailContent(
    item: Result?,
    paddingValues: PaddingValues,
    vm: DetailViewModel
) {
    val isFavorite by remember { mutableStateOf(item?.isFavorite) }
    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        item {
            DetailImage(item)
        }
        item {
            DetailTitle(item, isFavorite, vm)
        }
        item {
            DetailDescription(item)
        }
    }
}