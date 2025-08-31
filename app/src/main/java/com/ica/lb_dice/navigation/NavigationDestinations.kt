package com.ica.lb_dice.navigation

import com.ica.lb_dice.ui.PngIcons

sealed class NavigationDestinations(val route: String, val icon: Int) {
    object FireCombat : NavigationDestinations("Fire", PngIcons.FIRE)
    object MeleeCombat : NavigationDestinations("Melee", PngIcons.MELEE)
    object MoraleCheck : NavigationDestinations("Morale", PngIcons.MORALE)
    object General : NavigationDestinations("General", PngIcons.DICE)
}