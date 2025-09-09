package com.ica.lb_dice.ui

object ResultImage {
    fun iconForResult(result: String) : Int {
        if (result == "Pass") return com.ica.lb_dice.R.drawable.pass
        if (result == "Fail") return com.ica.lb_dice.R.drawable.fail
        if (result == "Head") return com.ica.lb_dice.R.drawable.head
        if (result == "Chest") return com.ica.lb_dice.R.drawable.chest
        if (result == "Arm") return com.ica.lb_dice.R.drawable.arm
        if (result == "Leg") return com.ica.lb_dice.R.drawable.leg
        if (result == "Stun") return com.ica.lb_dice.R.drawable.stun
        if (result == "Flesh") return com.ica.lb_dice.R.drawable.flesh
        return 0
    }
}