package com.lionm.taptaptimer.view

import androidx.lifecycle.ViewModel
import com.lionm.taptaptimer.data.WatchMode
import com.lionm.taptaptimer.data.WatchState
import com.lionm.taptaptimer.view.WatchUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {
    private var _watchUiState = MutableStateFlow(WatchUiState())
    val watchUiState: StateFlow<WatchUiState> = _watchUiState.asStateFlow()

    fun changeWatchMode(newWatchMode: WatchMode?) {
        newWatchMode ?: return

        _watchUiState.update { currentState ->
            currentState.copy(
                watchMode = newWatchMode,
                watchState = WatchState.RESET
            )
        }
    }

    fun changeTimerState(state: WatchState) {
        _watchUiState.update { currentState ->
            currentState.copy(
                watchState = state
            )
        }
    }
}