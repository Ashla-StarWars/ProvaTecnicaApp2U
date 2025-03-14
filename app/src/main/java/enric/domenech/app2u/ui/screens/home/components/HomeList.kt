package enric.domenech.app2u.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import enric.domenech.app2u.domain.models.Result

@Composable
fun HomeList(
    data: List<Result>,
    nav: NavHostController,
    onFavoriteClick: (Int) -> Unit,
) {
    if (data.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(WindowInsets.statusBars.asPaddingValues()),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator(
                color = Color(0xFFD99079)
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        data.forEach { item ->
            HomeItemList(nav, item, onFavoriteClick = { onFavoriteClick(item.id) })
        }
    }
}