package com.example.chat.ui.navigator

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chat.ui.viewModels.ChatViewModel
import com.example.chat.views.MainScreen
import com.example.chat.views.ChatScreen
@Composable
fun Navigator(){
    val navController= rememberNavController()
    val viewModel: ChatViewModel = ChatViewModel()

    NavHost(navController = navController, startDestination = "main") {
        composable("main"){
            MainScreen(navController)
        }
        composable("chat"){
            ChatScreen(navController,viewModel)
        }
    }
}

