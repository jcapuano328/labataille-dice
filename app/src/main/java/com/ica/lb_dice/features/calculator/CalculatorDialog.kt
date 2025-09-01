package com.ica.lb_dice.features.calculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ica.lb_dice.features.help.HelpDialog

data class CalculatorDialogRequest(
    val initialValue: Float,
    val onSetAttack: (Float) -> Unit,
    val onSetDefend: (Float) -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculatorDialog(
    modifier: Modifier = Modifier,
    onSetAttack: (Float) -> Unit,
    onSetDefend: (Float) -> Unit,
    onDismissRequest: () -> Unit
) {
    var showHelp by remember { mutableStateOf(false) }

    Card(modifier = modifier.padding(2.dp)) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = androidx.compose.ui.Alignment.Start
        ) {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onSecondary
                ),
                //title = { Text("Calculator") },
                title = { },
                navigationIcon = {
                    IconButton(onClick = onDismissRequest) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { showHelp = true }) {
                        Icon(Icons.Default.HelpOutline, contentDescription = "Help")
                    }
                }
            )

            ProportionalStrengthCalculator(
                onSetAttack = onSetAttack,
                onSetDefend = onSetDefend
            )
        }
    }

    if (showHelp) {
        HelpDialog(
            onDismiss = {showHelp = false},
            currentTopic = "Calculator"
        ) {
            CalculatorHelpContent()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCalculatorDialog() {
    CalculatorDialog(
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
