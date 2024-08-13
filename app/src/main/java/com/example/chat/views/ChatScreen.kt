package com.example.chat.views

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.chat.ui.viewModels.ChatViewModel

@Composable

fun ChatScreen(navController: NavHostController, viewnodel: ChatViewModel) {
        var message by rememberSaveable { mutableStateOf("") }

        Text("ChatGPT")
        Button(onClick = { navController.navigate("main") }) {
            Text("Go to main")

        }
        TextField(
        modifier = Modifier.fillMaxWidth(),
        value = message,
        onValueChange = { message = it },
        placeholder = { Text("Write your message here :)") }
    )

    }
