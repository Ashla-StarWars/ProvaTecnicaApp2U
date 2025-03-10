package enric.domenech.app2u.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import enric.domenech.app2u.R

@Composable
fun HomeView(
//    nav: NavHostController,
//    vm: HomeViewModel
) {

    Scaffold(
        topBar = {
            Row (
                modifier = Modifier.fillMaxWidth()
                    .padding(WindowInsets.systemBars.asPaddingValues())
                    .background(
                        Color.Green
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top,
            ){
                Column (
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(8.dp).padding(top = 20.dp, start = 20.dp)
                ){
                    Image(
                        painter = painterResource(id = R.drawable.ic_camera),
                        contentDescription = "Logo",
                        modifier = Modifier.size(50.dp)
                    )
                    Text("Scan" )
                }

                Column (
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(8.dp).padding(top = 20.dp, end = 20.dp)
                ){
                    IconButton(
                        modifier = Modifier.clip(CircleShape).background(Color.LightGray),
                        onClick = {

                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Face,
                            contentDescription = "Favorite",
                            tint = Color.White
                        )
                    }
                }
            }
        },
        bottomBar = {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(WindowInsets.systemBars.asPaddingValues()),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.Bottom
            ) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(8.dp).clickable(
                        onClick = {
                            // TODO: Implement action
                        }
                    )
                ){
                    Image(
                        painter = painterResource(id = R.drawable.ic_camera),
                        contentDescription = "Scan",
                        modifier = Modifier.size(30.dp)
                    )
                    Text("Scan")
                }
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(8.dp).clickable(
                        onClick = {
                            // TODO: Implement action
                        }
                    )
                ){
                    Image(
                        painter = painterResource(id = R.drawable.ic_eye),
                        contentDescription = "Festival",
                        modifier = Modifier.size(30.dp)
                    )
                    Text("Festival")
                }
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(8.dp).clickable(
                        onClick = {
                            // TODO: Implement action
                        }
                    )
                ){
                    Image(
                        painter = painterResource(id = R.drawable.ic_megaphone),
                        contentDescription = "News",
                        modifier = Modifier.size(30.dp)
                    )
                    Text("News")
                }
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(8.dp).clickable(
                        onClick = {
                            // TODO: Implement action
                        }
                    )
                ){
                    Image(
                        painter = painterResource(id = R.drawable.ic_heart),
                        contentDescription = "Favs",
                        modifier = Modifier.size(30.dp),
                        colorFilter = ColorFilter.tint(Color.LightGray)
                    )
                    Text("Favs")
                }
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("HomeView")
        }

    }

}