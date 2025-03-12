package enric.domenech.app2u.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import enric.domenech.app2u.R

@Composable
fun HomeTitle() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFD99079))
            .padding(top = 24.dp)
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
                    color = Color.Black
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