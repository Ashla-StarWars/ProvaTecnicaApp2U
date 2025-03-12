package enric.domenech.app2u.ui.screens.detail.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import enric.domenech.app2u.domain.models.Result

@Composable
fun DetailDescription(item: Result?) {
    Text(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(WindowInsets.navigationBars.asPaddingValues()),
        text = item?.description ?: "No description available",
        style = MaterialTheme.typography.bodyLarge,
        fontSize = 18.sp,
        color = MaterialTheme.colorScheme.onBackground
    )
}