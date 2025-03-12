package enric.domenech.app2u.ui.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import enric.domenech.app2u.R
import enric.domenech.app2u.domain.models.Result
import enric.domenech.app2u.ui.navigation.DETAIL
import enric.domenech.app2u.ui.screens.home.HomeViewModel

@Composable
fun HomeItemList(
    nav: NavHostController,
    item: Result,
    isFavorite: Boolean?,
    vm: HomeViewModel
) {
    var isFavorite1 = isFavorite
    Column(
        modifier = Modifier.clickable {
            nav.navigate(DETAIL(item.id))
        }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                item.firstName + " " + item.lastName,
                style = TextStyle(
                    fontSize = 28.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
            Spacer(Modifier.weight(1f))
            IconButton(
                onClick = {
                    isFavorite1 = !isFavorite1!!
                    vm.toggleFavorite(item.id)
                    item.isFavorite = isFavorite1
                }
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    painter = if (!isFavorite1!!) painterResource(R.drawable.ic_heart) else painterResource(
                        R.drawable.ic_heart_fill
                    ),
                    contentDescription = "Favorite",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }

        if (item.imageBitmap != null) {
            Image(
                modifier = Modifier
                    .background(Color.Gray)
                    .aspectRatio(16 / 9f),
                painter = BitmapPainter(item.imageBitmap!!),
                contentDescription = item.firstName,
                contentScale = ContentScale.FillWidth
            )
        } else {
            // Placeholder or loading state
            Image(
                modifier = Modifier
                    .background(Color.Gray)
                    .aspectRatio(16 / 9f),
                painter = painterResource(R.drawable.ic_frame),
                contentDescription = item.firstName
            )
        }
    }
}