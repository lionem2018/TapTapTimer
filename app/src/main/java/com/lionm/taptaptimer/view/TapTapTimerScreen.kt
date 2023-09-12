package com.lionm.taptaptimer.view

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lionm.taptaptimer.data.WatchMode
import com.lionm.taptaptimer.data.WatchState
import com.lionm.taptaptimer.ui.theme.TapTapTimerTheme


@Composable
fun TapTapTimerScreen(
    viewModel: MainViewModel?,
    modifier: Modifier = Modifier
) {
    StopWatchContent(
        onChangeWatchMode = { watchMode ->
            viewModel?.changeWatchMode(watchMode)
        },
        onChangeTimerState = { watchState ->
            viewModel?.changeTimerState(watchState)
        },
        modifier = modifier
    )
}

@Composable
fun StopWatchContent(
    onChangeWatchMode: (WatchMode?) -> Unit,
    onChangeTimerState: (WatchState) -> Unit,
    modifier: Modifier = Modifier
) {
    var declarationDialogState by remember { mutableStateOf(false) }

    if (declarationDialogState) {
        CountUpSettingDialog(
            onClickPositiveButton = {
                Log.d("Dialog>>", it.toString())
            },
            onDismissRequest = {
                declarationDialogState = false
            }
        )
    }
    Column(
        modifier = modifier
            .padding(PaddingValues(horizontal = 16.dp))
    ) {

        // RadioButtonGroup

        Row {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Cyan,
                    contentColor = Color.White,
                    disabledContainerColor = Color.Gray,
                    disabledContentColor = Color.White
                ),
                onClick = {
                    declarationDialogState = true
                },
                modifier = Modifier.wrapContentSize()
            ) {
                Text(text = "M")
            }
        }
    }
}

@Preview
@Composable
fun StopWatchScreenPreview() {
    TapTapTimerTheme() {
        TapTapTimerScreen(null)
    }
}
