package com.ica.lb_dice.ui

object ResultImage {
    fun iconForResult(result: String) : Int {
        if (result == "Pass") return com.ica.lb_dice.R.drawable.pass
        if (result == "Fail") return com.ica.lb_dice.R.drawable.fail
        if (result == "Arm") return com.ica.lb_dice.R.drawable.arm_2
        if (result == "Leg") return com.ica.lb_dice.R.drawable.leg_2
        if (result == "Stun") return com.ica.lb_dice.R.drawable.stun
        if (result == "Flesh") return com.ica.lb_dice.R.drawable.flesh
        if (result == "Mortal") return com.ica.lb_dice.R.drawable.tombstone_2
        if (result == "A") return com.ica.lb_dice.R.drawable.attacker
        if (result == "D") return com.ica.lb_dice.R.drawable.defender
        return 0
    }
}