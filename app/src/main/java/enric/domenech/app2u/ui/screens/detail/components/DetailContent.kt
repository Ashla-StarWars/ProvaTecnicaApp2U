package enric.domenech.app2u.ui.screens.detail.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import enric.domenech.app2u.domain.models.Result

@Composable
fun DetailContent(
    item: Result,
    paddingValues: PaddingValues,
    onFavoriteClick: (Result) -> Unit,
) {

    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        item {
            DetailImage(item)
        }
        item {
            DetailTitle(item, onFavoriteClick = { onFavoriteClick(item)} )
        }
        item {
            DetailDescription(item)
        }
    }
}