package com.ica.lb_dice.features.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

class DiceRollViewModel  : ViewModel() {
    private val _fabEvent = MutableSharedFlow<Unit>()
    val fabEvent = _fabEvent.asSharedFlow()
    suspend fun onFabClicked() {
        _fabEvent.emit(Unit)
    }
}