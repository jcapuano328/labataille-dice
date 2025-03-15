package com.ica.lb_dice.navigation

import com.ica.lb_dice.ui.FontAwesomeIcons

sealed class NavigationDestinations(val route: String, val icon: String) {
    object FireCombat : NavigationDestinations("Fire", FontAwesomeIcons.FIRE)
    object MeleeCombat : NavigationDestinations("Melee", FontAwesomeIcons.SHIELD)
    object MoraleCheck : NavigationDestinations("Morale", FontAwesomeIcons.GROUP)
    object General : NavigationDestinations("General", FontAwesomeIcons.DICE)
}