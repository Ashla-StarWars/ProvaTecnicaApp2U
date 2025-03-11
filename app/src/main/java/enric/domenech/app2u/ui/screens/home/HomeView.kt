package enric.domenech.app2u.ui.screens.home

import androidx.compose.animation.core.AnimationState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import enric.domenech.app2u.R
import enric.domenech.app2u.domain.models.Result
import enric.domenech.app2u.ui.navigation.DETAIL

@Composable
fun HomeView(
    nav: NavHostController,
    vm: HomeViewModel
) {
    val data = vm.dataState.collectAsState(initial = emptyList()).value

    Scaffold(
        topBar = { TopCustomAppBar() },
        bottomBar = { BottomAppBar() },
        content = { paddingValues ->
            HomeViewContent(paddingValues, nav, data)
        },
        containerColor = Color(0xFFD99079)
    )
}

@Composable
private fun HomeViewContent(
    paddingValues: PaddingValues,
    nav: NavHostController,
    data: List<Result>
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp)
            .padding(paddingValues),
    ) {
        item {
            TitleSection()
        }
        item {
            IconSelector()
        }
        item {
            ItemList(data, nav)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopCustomAppBar() {
    TopAppBar(
        navigationIcon = {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_camera),
                    contentDescription = "Scan",
                    modifier = Modifier.size(50.dp),
                )
                Text("Scan")
            }
        },
        windowInsets = WindowInsets.statusBars,
        title = {},
        actions = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.padding(horizontal = 24.dp)
            ) {
                IconButton(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.LightGray),
                    onClick = {

                    }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.AccountCircle,
                        contentDescription = "Favorite",
                        tint = Color.White
                    )
                }
            }
        },
        expandedHeight = 80.dp,
        colors = TopAppBarColors(
            scrolledContainerColor = Color.Transparent,
            containerColor = Color.Transparent,
            navigationIconContentColor = MaterialTheme.colorScheme.onBackground,
            titleContentColor = MaterialTheme.colorScheme.onBackground,
            actionIconContentColor = MaterialTheme.colorScheme.onBackground,
        ),
        scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    )
}

@Composable
private fun TitleSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(
                "INCADAQUÉS 2020",
                style = TextStyle(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 26.sp,
                ),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "15 -25 Octubre 2020",
                color = Color.DarkGray,
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
        Spacer(modifier = Modifier.weight(1f))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            IconButton(
                onClick = { },
                modifier = Modifier.size(56.dp)
            ) {
                Icon(
                    modifier = Modifier.size(32.dp),
                    painter = painterResource(R.drawable.ic_heart_fill),
                    contentDescription = "Festival",
                    tint = lerp(Color.Black, Color.Transparent, 0.5f)
                )
            }
        }
    }
}


@Composable
private fun IconSelector() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 16.dp, start = 16.dp, bottom = 16.dp)
        ) {
            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFD99079))
            ) {
                Icon(
                    modifier = Modifier.size(40.dp),
                    painter = painterResource(R.drawable.ic_calendar),
                    contentDescription = "Program",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            Text("Program", color = MaterialTheme.colorScheme.onBackground)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 16.dp, horizontal = 12.dp)
        ) {
            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF989E9C))
            ) {
                Icon(
                    modifier = Modifier.size(40.dp),
                    painter = painterResource(R.drawable.ic_frame),
                    contentDescription = "Artworks",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            Text("Artworks", color = MaterialTheme.colorScheme.onBackground)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 16.dp, bottom = 16.dp)
        ) {
            IconButton(
                onClick = { },
                modifier = Modifier
                    .size(64.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFC2B782))
            ) {
                Icon(
                    modifier = Modifier.size(40.dp),
                    painter = painterResource(R.drawable.ic_pin),
                    contentDescription = "Map",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }

            Text("Map", color = MaterialTheme.colorScheme.onBackground)
        }


    }
}


@Composable
private fun ItemList(
    data: List<Result>,
    nav: NavHostController
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        data.forEach {
            var isFavorite by remember { mutableStateOf(false) }
            Column(
                modifier = Modifier.clickable {
                    nav.navigate(DETAIL(it.id))
                }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        it.firstName + " " + it.lastName,
                        style = TextStyle(
                            fontSize = 28.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    )
                    Spacer(Modifier.weight(1f))
                    IconButton(
                        onClick = {
                            isFavorite = !isFavorite
                        }
                    ) {
                        Icon(
                            modifier = Modifier.size(32.dp),
                            painter = painterResource(R.drawable.ic_heart),
                            contentDescription = "Favorite",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
                Image(
                    modifier = Modifier
                        .background(Color.Gray)
                        .aspectRatio(16 / 9f),
                    painter = painterResource(R.drawable.ic_frame),
                    contentDescription = it.firstName
                )
//                        AsyncImage(
//                            model = it.image,
//                            contentDescription = it.firstName,
//                        )
            }
        }
    }
}

@Composable
private fun BottomAppBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(WindowInsets.navigationBars.asPaddingValues())
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.Bottom
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clickable(
                    onClick = {
                        // TODO: Implement action
                    }
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_camera),
                contentDescription = "Scan",
                modifier = Modifier.size(30.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
            )
            Text("Scan", color = MaterialTheme.colorScheme.onBackground)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clickable(
                    onClick = {
                        // TODO: Implement action
                    }
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_eye),
                contentDescription = "Festival",
                modifier = Modifier.size(30.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
            )
            Text("Festivals", color = MaterialTheme.colorScheme.onBackground)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clickable(
                    onClick = {
                        // TODO: Implement action
                    }
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_megaphone),
                contentDescription = "News",
                modifier = Modifier.size(30.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
            )
            Text("News", color = MaterialTheme.colorScheme.onBackground)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clickable(
                    onClick = {
                        // TODO: Implement action
                    }
                )
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_heart),
                contentDescription = "Favs",
                modifier = Modifier.size(30.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
            )
            Text("Favs", color = MaterialTheme.colorScheme.onBackground)
        }
    }
}