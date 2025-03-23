package com.ica.lb_dice.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ModifierButtonsRow(
    label: String = "",
    foregroundColor: Color = Color.Black,
    backgroundColor: Color = Color.White,
    onModifierButtonClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            //.wrapContentHeight()
            .padding(1.dp)
            //.border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(1.dp))
            ,
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        ModifierButton(
            value = "-6",
            modifier = Modifier.weight(1f),
            foregroundColor = foregroundColor,
            backgroundColor = backgroundColor,
            onModifierButtonClicked = { onModifierButtonClicked(-6) }
        )
        ModifierButton(
            value = "-3",
            modifier = Modifier.weight(1f),
            foregroundColor = foregroundColor,
            backgroundColor = backgroundColor,
            onModifierButtonClicked = { onModifierButtonClicked(-3) }
        )
        ModifierButton(
            value = "-1",
            modifier = Modifier.weight(1f),
            foregroundColor = foregroundColor,
            backgroundColor = backgroundColor,
            onModifierButtonClicked = { onModifierButtonClicked(-1) }
        )
        ModifierButton(
            value = "+1",
            modifier = Modifier.weight(1f),
            foregroundColor = foregroundColor,
            backgroundColor = backgroundColor,
            onModifierButtonClicked = { onModifierButtonClicked(1) }
        )
        ModifierButton(
            value = "+3",
            modifier = Modifier.weight(1f),
            foregroundColor = foregroundColor,
            backgroundColor = backgroundColor,
            onModifierButtonClicked = { onModifierButtonClicked(3) }
        )
        ModifierButton(
            value = "+6",
            modifier = Modifier.weight(1f),
            foregroundColor = foregroundColor,
            backgroundColor = backgroundColor,
            onModifierButtonClicked = { onModifierButtonClicked(6) }
        )
    }
}

@Composable
fun ModifierButton(
    value: String,
    foregroundColor: Color,
    backgroundColor: Color,
    onModifierButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(onClick = onModifierButtonClicked,
        modifier = modifier,
        //modifier = Modifier.size(10.dp),
        shape = RoundedCornerShape(8.dp),
        colors = androidx.compose.material3.ButtonDefaults.buttonColors(containerColor = backgroundColor),
        contentPadding = PaddingValues(0.dp)

    ) {
        Text(
            value,
            color = foregroundColor,
            //fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}