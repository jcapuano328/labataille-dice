package com.ica.lb_dice.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProportionalStrengthCalculator(
    modifier: Modifier = Modifier,
    onSetAttack: (Float) -> Unit,
    onSetDefend: (Float) -> Unit,
    onDismissRequest: () -> Unit
) {
    var display by remember { mutableStateOf("0") }
    var result by remember { mutableStateOf(0f) }
    var size by remember { mutableStateOf(0f) }
    var loss by remember { mutableStateOf(0f) }
    var strength by remember { mutableStateOf(0f) }
    var attack by remember { mutableStateOf(0f) }
    var defend by remember { mutableStateOf(0f) }

    fun calculate(s: Float, l: Float, str: Float): Float? {
        return if (s > 0 && l < s && str > 0) {
            ((s - l) / s) * str
        } else null
    }

    fun processKey(key: Any) {
        when (key) {
            is Int -> {
                if (display == "0") display = ""
                display += key.toString()
            }
            "bksp" -> {
                display = display.dropLast(1).ifEmpty { "0" }
            }
            "size" -> {
                size = display.toFloatOrNull() ?: 0f
                display = "0"
                result = calculate(size, loss, strength) ?: 0f
            }
            "loss" -> {
                loss = display.toFloatOrNull() ?: 0f
                display = "0"
                result = calculate(size, loss, strength) ?: 0f
            }
            "strength" -> {
                strength = display.toFloatOrNull() ?: 0f
                display = "0"
                result = calculate(size, loss, strength) ?: 0f
            }
            "1/3" -> result /= 3f
            "1/2" -> result /= 2f
            "3/2" -> result *= 1.5f
            "2" -> result *= 2f
            "clear" -> {
                display = "0"
                size = 0f; loss = 0f; strength = 0f
                result = 0f; attack = 0f; defend = 0f
            }
            "add" -> {
                result += display.toFloatOrNull() ?: 0f
                display = "0"
            }
            "equals" -> {
                result = calculate(size, loss, strength) ?: 0f
                display = "0"
            }
            "att" -> {
                attack += result
                display = "0"
                result = 0f
                onSetAttack(attack)
            }
            "def" -> {
                defend += result
                display = "0"
                result = 0f
                onSetDefend(defend)
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            //.padding(4.dp)
        ,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        // First row: 3 cells with custom widths
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(4f) // 1 small + 1 standard (1 + 3)
                    .fillMaxHeight()
                    .background(CalculatorDisplay.getCellColor(0, 0))
                ,
                contentAlignment = Alignment.Center
            ) {
                //Text("R0C0")
                //CalculatorDisplay.getCellContent(0, 0, modifier)
                Text(
                    text = if (result > 0f) "%.1f".format(result) else "",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Box(
                modifier = Modifier
                    .weight(6f) // 2 standard (3 + 3)
                    .fillMaxHeight()
                    //.background(CalculatorDisplay.getCellColor(0, 1))
                ,
                contentAlignment = Alignment.CenterEnd
            ) {
                //Text("R0C1")
                //CalculatorDisplay.getCellContent(0, 1, modifier)
                Text(
                    text = display,
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Box(
                modifier = Modifier
                    .weight(3f) // 1 standard
                    .fillMaxHeight()
                    //.background(CalculatorDisplay.getCellColor(0, 2))
                ,
                contentAlignment = Alignment.Center
            ) {
                //Text("R0C2")
                //CalculatorDisplay.getCellContent(0, 2, modifier)
                CalculatorIconButton(
                    CalculatorDisplay.iconForKey("calc-bksp"),
                    onClick = { processKey("bksp") }
                )
            }
        }

        // Rows 2–7: 5 cells (1 small, 4 standard)
        for (row in 1..6) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(CalculatorDisplay.getCellColor(row, 0))
                    ,
                    contentAlignment = Alignment.Center
                ) {
                    //Text("R${row}C0")
                    //CalculatorDisplay.getCellContent(row, 0, modifier)

                    if (row == 3) {
                        Text(text = "%.0f".format(size), fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                    } else if (row == 4) {
                        Text(text = "%.0f".format(loss), fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                    } else if (row == 5) {
                        Text(text = "%.0f".format(strength), fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
                    } else {
                        Spacer(modifier = modifier)
                    }
                }
                for (col in 1..4) {
                    Box(
                        modifier = Modifier
                            .weight(3f)
                            .fillMaxHeight()
                            //.background(getCellColor(row, col))
                        ,
                        contentAlignment = Alignment.Center
                    ) {
                        //Text("R${row}C$col")
                        //CalculatorDisplay.getCellContent(row, col, modifier)
                        if (row == 6 && col == 1) {
                            IconButton(onClick = onDismissRequest) {
                                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                            }
                        } else if (row == 6 && (col == 2 || col == 4)) {
                            Spacer(modifier = modifier)
                            //Spacer(modifier = Modifier.height(16.dp))
                        } else {
                            CalculatorIconButton(
                                CalculatorDisplay.iconForCell(row, col),
                                onClick = { processKey(CalculatorDisplay.keyForCell(row, col)) }
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

object CalculatorDisplay {

    fun iconForCell(row: Int, column: Int) : Int {
        val icon = when {
            row == 1 && column == 1 -> "calc-add"
            row == 1 && column == 2 -> "calc-att"
            row == 1 && column == 3 -> "calc-def"
            row == 1 && column == 4 -> "calc-clear"

            row == 2 && column == 1 -> "calc-mod-1-3"
            row == 2 && column == 2 -> "calc-mod-1-2"
            row == 2 && column == 3 -> "calc-mod-3-2"
            row == 2 && column == 4 -> "calc-mod-2"

            row == 3 && column == 1 -> "calc-size"
            row == 3 && column == 2 -> "calc-7"
            row == 3 && column == 3 -> "calc-8"
            row == 3 && column == 4 -> "calc-9"

            row == 4 && column == 1 -> "calc-loss"
            row == 4 && column == 2 -> "calc-4"
            row == 4 && column == 3 -> "calc-5"
            row == 4 && column == 4 -> "calc-6"

            row == 5 && column == 1 -> "calc-strength"
            row == 5 && column == 2 -> "calc-1"
            row == 5 && column == 3 -> "calc-2"
            row == 5 && column == 4 -> "calc-3"

            row == 6 && column == 3 -> "calc-0"

            else -> ""
        }

        return iconForKey(icon)
    }

    fun keyForCell(row: Int, column: Int) : Any {

        return when {
            row == 1 && column == 1 -> "add"
            row == 1 && column == 2 -> "att"
            row == 1 && column == 3 -> "def"
            row == 1 && column == 4 -> "clear"

            row == 2 && column == 1 -> "1/3"
            row == 2 && column == 2 -> "1/2"
            row == 2 && column == 3 -> "3/2"
            row == 2 && column == 4 -> "2"

            row == 3 && column == 1 -> "size"
            row == 3 && column == 2 -> 7
            row == 3 && column == 3 -> 8
            row == 3 && column == 4 -> 9

            row == 4 && column == 1 -> "loss"
            row == 4 && column == 2 -> 4
            row == 4 && column == 3 -> 5
            row == 4 && column == 4 -> 6

            row == 5 && column == 1 -> "strength"
            row == 5 && column == 2 -> 1
            row == 5 && column == 3 -> 2
            row == 5 && column == 4 -> 3

            row == 6 && column == 3 -> 0

            else -> ""
        }
    }
    fun iconForKey(result: String) : Int {
        if (result == "calc-0") return com.ica.lb_dice.R.drawable.calc_0
        if (result == "calc-1") return com.ica.lb_dice.R.drawable.calc_1
        if (result == "calc-2") return com.ica.lb_dice.R.drawable.calc_2
        if (result == "calc-3") return com.ica.lb_dice.R.drawable.calc_3
        if (result == "calc-4") return com.ica.lb_dice.R.drawable.calc_4
        if (result == "calc-5") return com.ica.lb_dice.R.drawable.calc_5
        if (result == "calc-6") return com.ica.lb_dice.R.drawable.calc_6
        if (result == "calc-7") return com.ica.lb_dice.R.drawable.calc_7
        if (result == "calc-8") return com.ica.lb_dice.R.drawable.calc_8
        if (result == "calc-9") return com.ica.lb_dice.R.drawable.calc_9
        if (result == "calc-add") return com.ica.lb_dice.R.drawable.calc_add
        if (result == "calc-att") return com.ica.lb_dice.R.drawable.calc_att
        if (result == "calc-bksp") return com.ica.lb_dice.R.drawable.calc_backspace
        if (result == "calc-clear") return com.ica.lb_dice.R.drawable.calc_clear
        if (result == "calc-def") return com.ica.lb_dice.R.drawable.calc_def
        if (result == "calc-loss") return com.ica.lb_dice.R.drawable.calc_loss
        if (result == "calc-size") return com.ica.lb_dice.R.drawable.calc_size
        if (result == "calc-strength") return com.ica.lb_dice.R.drawable.calc_strength
        if (result == "calc-mod-1-3") return com.ica.lb_dice.R.drawable.calc_third
        if (result == "calc-mod-1-2") return com.ica.lb_dice.R.drawable.calc_half
        if (result == "calc-mod-3-2") return com.ica.lb_dice.R.drawable.calc_three_halves
        if (result == "calc-mod-2") return com.ica.lb_dice.R.drawable.calc_twice
        return 0
    }

    fun getCellColor(row: Int, column: Int): Color {
        return when {
            row == 0 && column == 0 -> Color(0xffA5FF7F)
            row == 0 && column == 1 -> Color.Transparent
            row == 0 && column == 2 -> Color.DarkGray

            row == 1 && column == 0 -> Color.Transparent
            row == 1 && column == 1 -> Color.DarkGray
            row == 1 && (column == 2 || column == 3 || column == 4) -> Color.LightGray

            row == 2 && column == 0 -> Color.Transparent
            row == 2 && (column == 1 || column == 2 || column == 3 || column == 4) -> Color(0xFFB8860B) // Dark goldenrod

            row == 3 && (column == 0 || column == 1) -> Color(0xffFFE97F) // lemon yellow
            row == 3 && (column == 2 || column == 3 || column == 4) -> Color.Black

            row == 4 && (column == 0 || column == 1) -> Color(0xffFFE97F) // lemon yellow
            row == 4 && (column == 2 || column == 3 || column == 4) -> Color.Black

            row == 5 && (column == 0 || column == 1) -> Color(0xffFFE97F) // lemon yellow
            row == 5 && (column == 2 || column == 3 || column == 4) -> Color.Black

            row == 6 && (column == 0 || column == 1 || column == 2 || column == 4) -> Color.Transparent
            row == 6 && column == 3 -> Color.Black

            else -> Color(
                red = (0..255).random(),
                green = (0..255).random(),
                blue = (0..255).random()
            )
        }
    }
    fun getCellContent(row: Int, column: Int, modifier: Modifier = Modifier): @Composable () -> Unit {
        return when {
            row == 0 && column == 0 -> {
                { Spacer(modifier = modifier) }
            }
            row == 0 && column == 1 -> {
                { Spacer(modifier = modifier) }
            }
            row == 0 && column == 2 -> {
                { Spacer(modifier = modifier) }
            }

            row == 1 && column == 0 -> {
                { Spacer(modifier = modifier) }
            }
            row == 1 && column == 1 -> {
                { Spacer(modifier = modifier) }
            }
            row == 1 && (column == 2 || column == 3 || column == 4) -> {
                { Spacer(modifier = modifier) }
            }

            row == 2 && column == 0 -> {
                { Spacer(modifier = modifier) }
            }
            row == 2 && (column == 1 || column == 2 || column == 3 || column == 4) -> {
                { Spacer(modifier = modifier) }
            }

            row == 3 && (column == 0 || column == 1) -> {
                { Spacer(modifier = modifier) }
            }
            row == 3 && (column == 2 || column == 3 || column == 4) -> {
                { Spacer(modifier = modifier) }
            }

            row == 4 && (column == 0 || column == 1) -> {
                { Spacer(modifier = modifier) }
            }
            row == 4 && (column == 2 || column == 3 || column == 4) -> {
                { Spacer(modifier = modifier) }
            }

            row == 5 && (column == 0 || column == 1) -> {
                { Spacer(modifier = modifier) }
            }
            row == 5 && (column == 2 || column == 3 || column == 4) -> {
                { Spacer(modifier = modifier) }
            }

            row == 6 && (column == 0 || column == 1 || column == 2 || column == 4) -> {
                { Spacer(modifier = modifier) }
            }
            row == 6 && column == 3 -> {
                { Spacer(modifier = modifier) }
            }

            else -> {
                { Spacer(modifier = modifier) }
            }
        }
    }
}

@Composable
fun CalculatorIconButton(
    imageResId: Int, // drawable resource ID
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    width: Dp? = null,
    height: Dp? = null,
    contentDescription: String? = null,
    scale: Float = 1f,
    resizeMode: ContentScale = ContentScale.FillBounds // equivalent to 'contain'
) {
    var layoutSize by remember { mutableStateOf(IntSize.Zero) }

    val finalWidth = width ?: (layoutSize.width.takeIf { it != 0 }?.dp?.times(scale) ?: 32.dp)
    val finalHeight = height ?: (layoutSize.height.takeIf { it != 0 }?.dp?.times(scale) ?: 32.dp)

    Box(
        modifier = modifier
            .onGloballyPositioned { coordinates ->
                layoutSize = coordinates.size
            }
            .clickable { onClick() }
            .size(width = finalWidth, height = finalHeight)
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = contentDescription,
            contentScale = resizeMode,
            modifier = Modifier.fillMaxSize()
        )
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewProportionalStrengthCalculator() {
    ProportionalStrengthCalculator(
        modifier = Modifier
            .fillMaxSize(),
        onSetAttack = { value ->
            println("Attack value: $value")
        },
        onSetDefend = { value ->
            println("Defend value: $value")
        },
        onDismissRequest = {
            println("Dialog dismissed")
        }
    )
}
