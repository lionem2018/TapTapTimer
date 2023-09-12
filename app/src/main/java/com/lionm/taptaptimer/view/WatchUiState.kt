package com.lionm.taptaptimer.view

import com.lionm.taptaptimer.data.WatchMode
import com.lionm.taptaptimer.data.WatchState

data class WatchUiState(
    val watchMode: WatchMode = WatchMode.STOP_WATCH,
    val watchState: WatchState = WatchState.RESET
)