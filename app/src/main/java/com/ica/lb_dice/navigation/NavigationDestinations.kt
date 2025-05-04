package com.ica.lb_dice.navigation

import com.ica.lb_dice.ui.FontAwesomeIcons
import com.ica.lb_dice.ui.PngIcons

sealed class NavigationDestinations(val route: String, val icon: String) {
    object FireCombat : NavigationDestinations("Fire", FontAwesomeIcons.FIRE)
    object MeleeCombat : NavigationDestinations("Melee", FontAwesomeIcons.SHIELD)
    object MoraleCheck : NavigationDestinations("Morale", FontAwesomeIcons.GROUP)
    object General : NavigationDestinations("General", FontAwesomeIcons.DICE)
}

sealed class NavigationDestinationsAlt(val route: String, val icon: Int) {
    object FireCombat : NavigationDestinationsAlt("Fire", PngIcons.FIRE)
    object MeleeCombat : NavigationDestinationsAlt("Melee", PngIcons.MELEE)
    object MoraleCheck : NavigationDestinationsAlt("Morale", PngIcons.MORALE)
    object General : NavigationDestinationsAlt("General", PngIcons.DICE)
}