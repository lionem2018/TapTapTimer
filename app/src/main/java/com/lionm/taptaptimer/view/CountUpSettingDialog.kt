package com.lionm.taptaptimer.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CountUpSettingDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: (() -> Unit)? = null,
    onClickPositiveButton: ((Long) -> Unit)? = null,
    onCheckedChange: ((Boolean) -> Unit)? = null,
) {
    var time by remember { mutableStateOf(0L) }
    AlertDialog(
        onDismissRequest = {
            if (onDismissRequest != null) {
                onDismissRequest()
            }
        },
        confirmButton = {
            TextButton(
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.primary
                ),
                onClick = {
                    if (onDismissRequest != null) {
                        onDismissRequest()
                    }
                }
            ) {
                Text(text = "Dismiss")
            }

            TextButton(
                colors = ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.primary
                ),
                onClick = {
                    if (onClickPositiveButton != null) {
                        onClickPositiveButton(time)
                    }
                }
            ) {
                Text(text ="Confirm")
            }
        },
        text = {
            Column(
                modifier = modifier
                    .background(Color.White)
                    .padding(4.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = false,
                        onCheckedChange = onCheckedChange
                    )
                    Text(
                        text = "Recursive",
                        Modifier.padding(PaddingValues(start = 4.dp))
                    )
                }
                Spacer(Modifier.height(12.dp))
                TimePicker(
                    onValueChanged = { hour, minute, second ->
                        time = calculateTime(hour, minute, second)
                    }
                )
            }
        },
        containerColor = Color.White,
        modifier = modifier
    )
}

private fun calculateTime(hh: Int, mm: Int, ss: Int): Long =
    hh * 360L + mm * 60L + ss


@Composable
fun TimePicker (
    modifier: Modifier = Modifier,
    onValueChanged: ((Int, Int, Int) -> Unit)? = null
) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        var hour by remember { mutableStateOf(0) }
        var minute by remember { mutableStateOf(0) }
        var second by remember { mutableStateOf(0) }

        NumberPicker(
            range = 0..23,
            onValueChanged = { old, new ->
                hour = new

                if (onValueChanged != null) {
                    onValueChanged(hour, minute, second)
                }
            }
        )
        Text(text = ":")
        NumberPicker(
            range = 0..59,
            onValueChanged = { old, new ->
                minute = new

                if (onValueChanged != null) {
                    onValueChanged(hour, minute, second)
                }
            }
        )
        Text(text = ":")
        NumberPicker(
            range = 0..59,
            onValueChanged = { old, new ->
                second = new
                if (onValueChanged != null) {
                    onValueChanged(hour, minute, second)
                }
            }
        )
    }
}

@Composable
fun NumberPicker(
    range: IntRange,
    onValueChanged: ((Int, Int) -> Unit)?,
    modifier: Modifier = Modifier
) {
    var currentValue by remember { mutableStateOf(range.first) }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Black,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.White
            ),
            onClick = {
                val newValue = currentValue + 1
                if (newValue in range) {
                    if (onValueChanged != null) {
                        onValueChanged(currentValue, newValue)
                    }
                    currentValue = newValue
                }
            }
        ) {
            Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = null)
        }
        Text(text = currentValue.toString())
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.Black,
                disabledContainerColor = Color.Gray,
                disabledContentColor = Color.White
            ),
            onClick = {
                val newValue = currentValue - 1
                if (newValue in range) {
                    if (onValueChanged != null) {
                        onValueChanged(currentValue, newValue)
                    }
                    currentValue = newValue
                }
            }
        ) {
            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = null)
        }
    }
}

@Preview
@Composable
fun CountUpSettingDialogPreview() {
    CountUpSettingDialog()
}

@Preview
@Composable
fun TimePickerPreview() {
    TimePicker(Modifier.background(Color.White))
}

@Preview
@Composable
fun NumberPickerPreview() {
    NumberPicker(
        range = 0..3,
        null,
        Modifier.background(Color.White)
    )
}