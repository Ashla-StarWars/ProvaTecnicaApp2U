package enric.domenech.app2u.ui.screens.detail.components

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults.IconSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import enric.domenech.app2u.R

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DetailTopAppBar(nav: NavHostController) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(
                onClick = { nav.popBackStack() }, modifier = Modifier.clip(CircleShape)
            ) {
                Icon(
                    modifier = Modifier.size(IconSize),
                    painter = painterResource(R.drawable.ic_back_arrow),
                    contentDescription = "Button back",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        },
        actions = {},
        windowInsets = WindowInsets.statusBars
    )
}