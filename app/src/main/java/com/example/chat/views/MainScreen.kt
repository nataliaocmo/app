package com.example.chat.views

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.chat.R


@Composable
fun MainScreen(navController: NavHostController) {

    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.bot))
    Column {
        LottieAnimation(
            composition = composition, iterations = LottieConstants.IterateForever
        )
        Text("Chat BOT")
        Button(onClick = { navController.navigate("chat") }) {
            Text("Go to chat")

        }
    }

}