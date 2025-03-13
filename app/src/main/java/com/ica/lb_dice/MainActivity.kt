package com.ica.lb_dice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.Alignment
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameScreen()
        }
    }
}


@Composable
fun GameScreen() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.padding(8.dp)) {
                // Navigation Tabs
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    listOf("Fire", "Melee", "Morale", "General").forEach { tab ->
                        Button(onClick = { /* Handle Tab Click */ }) {
                            Text(text = tab, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Dice Row
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    listOf("Red", "White", "Blue", "Yellow", "Green", "Black").forEach { color ->
                        Box(modifier = Modifier.size(40.dp).background(Color.LightGray)) {
                            // Placeholder for Dice Images
                            Text(text = color.first().toString(), modifier = Modifier.align(Alignment.Center))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Modifiers Row
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    listOf(-6, -3, -1, 1, 3, 6).forEach { mod ->
                        Button(onClick = { /* Apply Modifier */ }) {
                            Text(text = mod.toString())
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Results Table
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(listOf(
                        Triple("1:1", "0", "✅"),
                        Triple("2:1", "1", "❌"),
                        Triple("3:1", "2", "❌")
                    )) { (odds, result, morale) ->
                        Row(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
                            Text(text = odds, modifier = Modifier.weight(1f))
                            Text(text = result, modifier = Modifier.weight(1f))
                            Text(text = morale, modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}