package com.ica.lb_dice

import androidx.compose.runtime.Composable
import com.ica.lb_dice.navigation.MainNavigation
import com.ica.lb_dice.ui.theme.LbdiceTheme

@Composable
fun App() {
    LbdiceTheme {
        MainNavigation()
    }
}