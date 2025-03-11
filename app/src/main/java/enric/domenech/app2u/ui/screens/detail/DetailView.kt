package enric.domenech.app2u.ui.screens.detail


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults.IconSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import enric.domenech.app2u.R
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun DetailView(
    dataId: Int,
    nav: NavHostController,
    vm: DetailViewModel = koinViewModel { parametersOf(dataId) }
) {
    var isFavorite by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.padding(WindowInsets.statusBars.asPaddingValues())
            ) {
                IconButton(
                    onClick = { nav.popBackStack() },
                    modifier = Modifier
                        .clip(CircleShape)
                ) {
                    Icon(
                        modifier = Modifier.size(IconSize),
                        painter = painterResource(R.drawable.ic_back_arrow),
                        contentDescription = "Button back",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        },
        content = { paddingValues ->
            vm.data.let {
                LazyColumn(modifier = Modifier.padding(paddingValues)) {
                    item {
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
                    item {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.padding(24.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "${it.data?.firstName} ${it.data?.lastName}",
                                style = MaterialTheme.typography.headlineMedium
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            IconButton(
                                onClick = { isFavorite = !isFavorite },
                            ) {
                                Icon(
                                    modifier = Modifier.size(52.dp),
                                    painter =
                                    if (isFavorite)
                                        painterResource(R.drawable.ic_heart_fill)
                                    else
                                        painterResource(R.drawable.ic_heart),
                                    contentDescription = "Favorite",
                                    tint = MaterialTheme.colorScheme.onBackground
                                )
                            }
                        }
                    }
                    item {
                        Text(
                            modifier = Modifier
                                .padding(horizontal = 16.dp)
                                .padding(WindowInsets.navigationBars.asPaddingValues()),
                            text = it.data?.description ?: "No description available",
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        }
    )
}