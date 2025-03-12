package enric.domenech.app2u.ui.screens.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import enric.domenech.app2u.R
import enric.domenech.app2u.domain.models.Result


@Composable
fun DetailImage(item: Result?) {
    if (item?.imageBitmap != null) {
        Image(
            bitmap = item.imageBitmap!!,
            contentDescription = "Image detail",
            modifier = Modifier
                .aspectRatio(16 / 9f)
                .background(
                    color = Color.Gray
                ),
            contentScale = ContentScale.FillWidth
        )
    } else {
        Image(
            painter = painterResource(id = R.drawable.ic_frame),
            contentDescription = "Image detail",
            modifier = Modifier
                .aspectRatio(16 / 9f)
                .background(
                    color = Color.Gray
                )
        )
    }
}