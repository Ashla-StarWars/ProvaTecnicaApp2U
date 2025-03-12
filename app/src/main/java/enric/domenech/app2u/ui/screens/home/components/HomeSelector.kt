package enric.domenech.app2u.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import enric.domenech.app2u.R

@Composable
fun HomeSelector() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFD99079))
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