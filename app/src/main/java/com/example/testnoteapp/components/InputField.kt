@file:Suppress("DEPRECATION")

package com.example.testnoteapp.components

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import org.w3c.dom.Text

@Composable
fun InputField (
    value: MutableState<String>,
    onValueChange: (String) -> Unit,
    label: String = "",
    onImeAction: () -> Unit = {},

) {
    val kbController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = value.value,
        onValueChange = onValueChange,
        label = { Text(label) },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            kbController?.hide()
        }),
        maxLines = 1,
        modifier = Modifier
            .widthIn(min = 250.dp, max = 350.dp)
    )
}