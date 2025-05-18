package com.example.testnoteapp.components

import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun SaveButton (
    onClick: () -> Unit = {},

) {
    OutlinedButton(
        onClick = onClick,
    ) {
        Text("Save")
    }
}