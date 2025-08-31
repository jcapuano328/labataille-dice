package com.ica.lb_dice.features.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

import com.ica.lb_dice.features.morale.MoraleService

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
                //.background(Color.Gray)
                .background(MaterialTheme.colorScheme.secondary)
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
                        .padding(end = 8.dp)
                        //.padding(2.dp)
                    ,horizontalArrangement = Arrangement.Center
                    ,verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = item.first, modifier = Modifier.weight(3f), fontSize = 18.sp, textAlign = TextAlign.Center)
                    Text(text = item.second, modifier = Modifier.weight(1f), fontSize = 14.sp, textAlign = TextAlign.Center)
                    PngIcon(item.third, "", modifier = Modifier
                        .weight(1f)
                        .padding(4.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun MoraleResultsAlt(modifier: Modifier = Modifier, title: String = "", results: List<MoraleResultAlt>) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp)) // Rounded corners for the whole table
            .border(2.dp, Color.Black, RoundedCornerShape(16.dp)) // Black border around the entire table
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0xFFFFFAE5)) // Light Yellow
    ) {
        val data = results.map { Triple(it.moralePass, it.modifier, it.moraleFail) }

        // Header Row (Gray)
        if (title.isNotEmpty()) {
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
        }
        // this the header row for the columns
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .background(Color.LightGray)
                .padding(2.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            //Text(text = "Pass", modifier = Modifier.weight(1f), fontSize = 12.sp, textAlign = TextAlign.Center)
            PngIcon(ResultImage.iconForResult("Pass"), "", modifier = Modifier
                .weight(1f)
                .padding(4.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            //Text(text = "Fail", modifier = Modifier.weight(1f), fontSize = 12.sp, textAlign = TextAlign.Center)
            PngIcon(ResultImage.iconForResult("Fail"), "", modifier = Modifier
                .weight(1f)
                .padding(4.dp)
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
                    Text(text = item.first, modifier = Modifier.weight(1f), fontSize = 18.sp, textAlign = TextAlign.Center)
                    Text(text = item.second, modifier = Modifier.weight(1f), fontSize = 14.sp, textAlign = TextAlign.Center)
                    Text(text = item.third, modifier = Modifier.weight(1f), fontSize = 18.sp, textAlign = TextAlign.Center)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMoraleResults() {
    val service = MoraleService()
    val results = service.range(52)

    MoraleResultsAlt(
        modifier = Modifier
            .fillMaxSize()
        ,
        results = results.map { MoraleResultAlt(it.pass, it.fail, it.modifier) }
    )
}