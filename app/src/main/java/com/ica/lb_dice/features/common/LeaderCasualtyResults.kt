package com.ica.lb_dice.features.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ica.lb_dice.ui.PngIcon
import com.ica.lb_dice.ui.ResultImage

@Composable
fun LeaderCasualtyResults(modifier: Modifier = Modifier, data: LeaderCasualtyResult) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp)) // Rounded corners for the whole table
            .border(2.dp, Color.Black, RoundedCornerShape(16.dp)) // Black border around the entire table
            .fillMaxWidth()
        //.background(Color.Blue)
    ) {
        // Header Row (Gray)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                //.background(Color.Gray)
                .background(MaterialTheme.colorScheme.secondary)
                .padding(4.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Leader Casualty",
                style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp, textAlign = TextAlign.Center),
                modifier = Modifier
                    .weight(2f)
            )
        }

        // Body (Light Yellow)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFFAE5)) // Light Yellow
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (data.icon != "") {
                    if (data.side != "") {
                        Row(modifier = Modifier.weight(1f),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically) {
                            PngIcon(
                                ResultImage.iconForResult(data.side),
                                data.side,
                                modifier = Modifier.height(40.dp)
                            )
                        }
                    }
                    PngIcon(ResultImage.iconForResult(data.icon), data.icon, modifier = Modifier.weight(2f))
                }
                Text(text = data.result, modifier = Modifier.weight(2f), textAlign = TextAlign.Center)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLeaderCasualtyResults() {
    val service = LeaderCasualtyService()
    //val result = service.resolveFireCombat(65, 3, 2, 4)
    val result = service.resolveMeleeCombat(65, 3, 2, 4)

    LeaderCasualtyResults(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
        ,
        data = LeaderCasualtyResult(result.side, result.result, result.icon)
    )
}