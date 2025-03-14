package enric.domenech.app2u.ui.screens.detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import enric.domenech.app2u.R
import enric.domenech.app2u.domain.models.Result

@Composable
fun DetailTitle(
    item: Result,
    onFavoriteClick: () -> Unit,
) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${item.firstName} ${item.lastName}",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.weight(1f))
        IconButton(
            onClick = {
                onFavoriteClick()
            },
        ) {
            Icon(
                modifier = Modifier.size(52.dp),
                painter = if (item.isFavorite == true) painterResource(R.drawable.ic_heart_fill)
                else painterResource(R.drawable.ic_heart),
                contentDescription = "Favorite",
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}