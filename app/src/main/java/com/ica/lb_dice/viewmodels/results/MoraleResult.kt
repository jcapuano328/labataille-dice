package com.ica.lb_dice.viewmodels.results

data class MoraleResult(
    val result: String,
    val modifier: String,
    val icon: String
)

data class MoraleResultAlt(
    val moralePass: String,
    val moraleFail: String,
    val modifier: String
)
