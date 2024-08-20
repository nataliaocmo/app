package com.example.chat.views


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.* // For Row, Column, Box, Spacer, etc.
import androidx.compose.material.* // For Button, Text, TextField, etc.
import androidx.compose.runtime.* // For @Composable, mutableStateOf, etc.
import androidx.compose.ui.unit.dp // For dp units
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.chat.ui.viewModels.ChatViewModel
import com.example.chat.ui.viewModels.Message
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults


@Composable

fun ChatScreen(navController: NavHostController, viewmodel: ChatViewModel) {
    val chatViewModel: ChatViewModel= viewModel()
    val uiState by chatViewModel.uiState.collectAsState()
    var message by remember {
        mutableStateOf(chatViewModel.currentMessage)
    }
    LaunchedEffect(key1 = "ChatScreen") {
        chatViewModel.clearMessages()
    }
    // Clear messages when the screen is first displayed
    LaunchedEffect(chatViewModel.currentMessage) {
        message = chatViewModel.currentMessage
    }


    Box(
        modifier = Modifier
            .fillMaxSize() // This ensures the Box takes up the whole space of its parent
            .padding(16.dp) // Optional padding around the Box
        // Aligns the Box to the bottom-end of its parent

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 80.dp) // Space for the input and button
        ) {
            LazyColumn(
                reverseLayout = false,
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(uiState.messages) { message ->
                    MessageItem(message = message as Message)
                }

            }
        }

        Text(
            "Hello I am ChatBot, What can I help you with today?",
            modifier = Modifier
                .align(Alignment.TopCenter)
        )
        Button(onClick = { navController.navigate("main") },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(237, 223, 255)
            )
        ) {
            Text("Go to main \uD83C\uDFE0")

        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter) // Aligns the Row to the bottom-center of the Box
                .fillMaxWidth(), // Make the Row take up the full width
            verticalAlignment = Alignment.CenterVertically // Vertically center the contents
        ) {
            TextField(
                modifier = Modifier
                    .weight(1f) // The TextField takes up the remaining space
                    .padding(end = 16.dp, bottom = 30.dp), // Add some space between the TextField and the Button
                value = message,
                onValueChange = { newMessage ->
                    message= newMessage
                    chatViewModel.updateCurrentMessage(newMessage) },
                placeholder = { Text("Write your message here :)") }
            )

            Button(
                onClick = {
                    chatViewModel.sendMessage(message)
                },
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .padding(bottom = 30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(237, 223, 255)
                )
            ) {
                Text("Send ➤")
            }
        }
    }
}

@Composable
fun MessageItem(message: Message) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp),
        horizontalArrangement = if(message.sender=="me") Arrangement.End else Arrangement.Start

    ){
        Text(
            text = message.text,
            modifier = Modifier
                .background(
                    color = if (message.sender == "me") Color(237, 223, 255) else Color.Gray,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(8.dp),
            color=Color.Black
            )

    }


}





