package com.ica.lb_dice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.ica.lb_dice.ui.FireScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    var selectedTab by remember { mutableStateOf(Tab.Fire) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("La Bataille Dice") })
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            TabRow(selectedTab = selectedTab, onTabSelected = { selectedTab = it })
            when (selectedTab) {
                Tab.Fire -> FireScreen()
                Tab.Melee -> Text("Melee Screen") // Placeholder
                Tab.Morale -> Text("Morale Screen") // Placeholder
                Tab.General -> Text("General Screen") // Placeholder
            }
        }
    }
}

enum class Tab {
    Fire, Melee, Morale, General
}

@Composable
fun TabRow(selectedTab: Tab, onTabSelected: (Tab) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        TabButton(text = "Fire", isSelected = selectedTab == Tab.Fire, onClick = { onTabSelected(Tab.Fire) })
        TabButton(text = "Melee", isSelected = selectedTab == Tab.Melee, onClick = { onTabSelected(Tab.Melee) })
        TabButton(text = "Morale", isSelected = selectedTab == Tab.Morale, onClick = { onTabSelected(Tab.Morale) })
        TabButton(text = "General", isSelected = selectedTab == Tab.General, onClick = { onTabSelected(Tab.General) })
    }
}

@Composable
fun TabButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(8.dp),
        colors = if (isSelected) {
            ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
        } else {
            ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        }
    ) {
        Text(text = text)
    }
}

