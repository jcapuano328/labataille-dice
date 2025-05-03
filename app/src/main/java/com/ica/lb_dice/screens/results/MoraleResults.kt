package com.ica.lb_dice.screens.results

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ica.lb_dice.ui.PngIcon
import com.ica.lb_dice.ui.ResultImage
import com.ica.lb_dice.viewmodels.MoraleResult

@Composable
fun MoraleResults(modifier: Modifier = Modifier, title: String = "Morale", results: List<MoraleResult>) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp)) // Rounded corners for the whole table
            .border(2.dp, Color.Black, RoundedCornerShape(16.dp)) // Black border around the entire table
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0xFFFFFAE5)) // Light Yellow
    ) {
        val data = results.map { Triple(it.result, it.modifier, ResultImage.iconForResult(it.icon)) }

        // Header Row (Gray)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .padding(4.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = TextStyle(color = Color.White, fontWeight = FontWeight.Bold, fontSize = 12.sp, textAlign = TextAlign.Center),
                modifier = Modifier
                    .weight(2f)
            )
        }

        // Body (Light Yellow)
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
            //.background(Color(0xFFFFFAE5)) // Light Yellow
        ) {
            items(data) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp)
                    ,horizontalArrangement = Arrangement.Center
                    ,verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = item.first, modifier = Modifier.weight(3/7f), fontSize = 18.sp, textAlign = TextAlign.Center)
                    Text(text = item.second, modifier = Modifier.weight(2/7f), fontSize = 14.sp, textAlign = TextAlign.Center)
                    PngIcon(item.third, "", modifier = Modifier
                        .weight(2/7f)
                        .padding(4.dp)
                    )
                }
            }
        }
    }
}