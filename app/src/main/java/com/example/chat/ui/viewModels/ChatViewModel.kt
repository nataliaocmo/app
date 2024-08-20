package com.example.chat.ui.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chat.data.model.Content
import com.example.chat.data.model.Part
import com.example.chat.data.model.PostRequest
import com.example.chat.data.network.ChatApi
import com.google.ai.client.generativeai.type.Candidate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Date

data class Message(
    val text: String,
    val sender: String,
    val date: Date,
    val isSeen: Boolean
    )
data class ChatUIState(
    var messages: List<Any> = emptyList()
)
class ChatViewModel :ViewModel(){

    private val _uiState = MutableStateFlow(ChatUIState())
    val uiState: StateFlow<ChatUIState> = _uiState

    var currentMessage by mutableStateOf("")
    private set

    fun updateCurrentMessage(text: String){
        currentMessage=text

    }
    fun sendMessage(message: String){
        viewModelScope.launch {
            //la función para enviar mensaje que sea suspendida
            val newMessage = Message(
                text = currentMessage,
                sender= "me",
                date=Date(),
                isSeen=false
            )
            // Update UI with the new message
            _uiState.value=_uiState.value.copy(
                messages = _uiState.value.messages + newMessage
            )
            // clear current messages
            currentMessage=""
            val requestBody = PostRequest(
                contents = listOf(
                    Content(
                        parts = listOf(
                            Part(
                                text= message
                            )
                        )
                    )
                )
            )

                try {
                    val response = ChatApi.retrofitService.getMessage(requestBody)
                    if (response.isSuccessful){
                        val reply = response.body()?.candidates?.get(0)?.content?.parts?.get(0)?.text
                        if(reply!=null){
                            val botMessage = Message(
                                text = reply,
                                sender= "bot",
                                date= Date(),
                                isSeen = false
                            )
                            // Update UI with the new message
                            _uiState.value=_uiState.value.copy(
                                messages = _uiState.value.messages + botMessage)
                        } else {
                            println("Sorry there was no reply")
                        }

                    }
                }
                catch (e:Exception){
                    println("Failed to connect:${e.message}")
                } finally {
                    currentMessage=""
                }



        }
    }

    fun clearMessages() {
        _uiState.value = _uiState.value.copy(messages = emptyList())
    }

}