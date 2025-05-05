package com.ica.lb_dice.screens.results

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ica.lb_dice.services.FireCombatService
import com.ica.lb_dice.viewmodels.results.CombatResult
import com.ica.lb_dice.viewmodels.results.CombatResultAlt

@Composable
fun CombatResults(modifier: Modifier = Modifier, title: String = "", results: List<CombatResult>) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp)) // Rounded corners for the whole table
            .border(2.dp, Color.Black, RoundedCornerShape(16.dp)) // Black border around the entire table
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0xFFFFFAE5)) // Light Yellow
    ) {
        val data = results.map { Pair(it.odds, it.result) }
        if (title.isNotEmpty()) {
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
                    text = "Combat",
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier
                        .weight(2f)
                )
            }
        }

        // Body (Light Yellow)
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFFAE5)) // Light Yellow
        ) {
            items(data) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp, bottom = 4.dp, end = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = item.first, modifier = Modifier.weight(2f), textAlign = TextAlign.Center, fontSize = 18.sp, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic)
                    Text(text = item.second, modifier = Modifier
                        .weight(1f)
                        .background(color = getBackgroundColorForResult(item.second)), textAlign = TextAlign.Center, fontSize = 18.sp, style = TextStyle(color = getForegroundColorForResult(item.second)))
                }
            }
        }
    }
}

private fun getForegroundColorForResult(result: String): Color {
    return when (result) {
        "1" -> Color.Black
        "2" -> Color.Black
        "3" -> Color.Black
        "4" -> Color.Black
        "5" -> Color.Black
        "AS" -> Color.White
        "AR" -> Color.White
        "AD" -> Color.Black
        "DD" -> Color.Black
        "DR" -> Color.White
        "DS" -> Color.White
        "NE" -> Color.Black
        else -> Color.Black
    }
}
private fun getBackgroundColorForResult(result: String): Color {
    return when (result) {
        "1" -> Color(0xFFBBFFFF)
        "2" -> Color(0xFFD2FFBB)
        "3" -> Color(0xFFFFF5BB)
        "4" -> Color(0xFFFFD9BB)
        "5" -> Color(0xFFFFBBBB)
        "AS" -> Color(0xFF7F0000)
        "AR" -> Color.Red
        "AD" -> Color.Yellow
        "DD" -> Color.Yellow
        "DR" -> Color.Red
        "DS" -> Color(0xFF7F0000)
        "NE" -> Color.Transparent
        else -> Color(0xFFD9ECF2)
    }
}

@Composable
fun CombatResultsAlt(modifier: Modifier = Modifier, title: String = "", results: List<CombatResultAlt>) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp)) // Rounded corners for the whole table
            .border(2.dp, Color.Black, RoundedCornerShape(16.dp)) // Black border around the entire table
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0xFFFFFAE5)) // Light Yellow
    ) {
        if (title.isNotEmpty()) {
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
                    text = "Combat",
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        textAlign = TextAlign.Center
                    ),
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
                //.background(Color.LightGray)
                .padding(2.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "1", modifier = Modifier
                .weight(1f)
                .background(Color(0xFFBBFFFF)), fontSize = 18.sp, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic, textAlign = TextAlign.Center)
            Text(text = "2", modifier = Modifier
                .weight(1f)
                .background(Color(0xFFD2FFBB)), fontSize = 18.sp, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic,textAlign = TextAlign.Center)
            Text(text = "3", modifier = Modifier
                .weight(1f)
                .background(Color(0xFFFFF5BB)), fontSize = 18.sp, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic,textAlign = TextAlign.Center)
            Text(text = "4", modifier = Modifier
                .weight(1f)
                .background(Color(0xFFFFD9BB)), fontSize = 18.sp, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic,textAlign = TextAlign.Center)
            Text(text = "5", modifier = Modifier
                .weight(1f)
                .background(Color(0xFFFFBBBB)), fontSize = 18.sp, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic,textAlign = TextAlign.Center)
        }

        // Body (Light Yellow)
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFFFFAE5)) // Light Yellow
        ) {
            items(results) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = item.odds, modifier = Modifier.weight(1f), textAlign = TextAlign.Center, fontSize = 18.sp, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic)
                    Text(text = item.result1, modifier = Modifier
                        .weight(1f)
                        .background(Color(0xFFBBFFFF)), fontSize = 18.sp, textAlign = TextAlign.Center)
                    Text(text = item.result2, modifier = Modifier
                        .weight(1f)
                        .background(Color(0xFFD2FFBB)), fontSize = 18.sp, textAlign = TextAlign.Center)
                    Text(text = item.result3, modifier = Modifier
                        .weight(1f)
                        .background(Color(0xFFFFF5BB)), fontSize = 18.sp, textAlign = TextAlign.Center)
                    Text(text = item.result4, modifier = Modifier
                        .weight(1f)
                        .background(Color(0xFFFFD9BB)), fontSize = 18.sp, textAlign = TextAlign.Center)
                    Text(text = item.result5, modifier = Modifier
                        .weight(1f)
                        .background(Color(0xFFFFBBBB)), fontSize = 18.sp, textAlign = TextAlign.Center)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCombatResults() {
    val service = FireCombatService()

    val results = service.resolve(52)

    CombatResults(
        modifier = Modifier
            .fillMaxSize()
        ,
        results = results.map { CombatResult(it.odds, it.result) }
    )


    /*
    val results = service.resolveAlt(52)

    CombatResultsAlt(
        modifier = Modifier
            .fillMaxSize()
        ,
        results = results.map { CombatResultAlt(it.odds, it.result1, it.result2, it.result3, it.result4, it.result5) }
    )
    */
}