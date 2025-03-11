package enric.domenech.app2u.ui.screens.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import enric.domenech.app2u.R

@Composable
fun DetailView(
    detailId: Int,
    nav: NavHostController
) {
    Scaffold(
        topBar = {
            Row {
                IconButton(
                    onClick = { nav.popBackStack() },
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF989E9C))
                ) {
                    Icon(
                        modifier = Modifier.size(40.dp),
                        painter = painterResource(R.drawable.ic_back_arrow),
                        contentDescription = "Button back",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        }
    ) { paddingValues ->
       Column (modifier = Modifier.padding(paddingValues)) {
              Text(text = "Detail $detailId")
       }
    }
}