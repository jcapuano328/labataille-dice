package com.ica.lb_dice.ui

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.ica.lb_dice.R


val fontAwesomeFamily = FontFamily(
    Font(R.font.fa_solid_900, FontWeight.Normal)
)

@Composable
fun FontAwesomeIcon(
    unicode: String,
    description: String = "",
    modifier: Modifier = Modifier
) {
    Text(
        text = unicode,
        fontFamily = fontAwesomeFamily,
        style = TextStyle(fontSize = 24.sp),
        modifier = modifier,
        color = Color.Blue
    )
}