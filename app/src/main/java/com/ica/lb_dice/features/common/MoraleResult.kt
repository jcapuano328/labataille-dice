package com.ica.lb_dice.features.common

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
