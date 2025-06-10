package com.example.app

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.app.ui.theme.MyApplicationTheme // Adjusted for potential package change in Theme
import com.example.myapplication.presentation.main.MainViewModel
import com.example.myapplication.presentation.main.MainScreenEvent
import com.example.myapplication.presentation.main.MainScreenEffect
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val state by viewModel.uiState.collectAsState()
    val context = LocalContext.current
    var textFieldValue by remember { mutableStateOf("") }

    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is MainScreenEffect.ShowToastMessage -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
                is MainScreenEffect.LogErrorMessage -> {
                    // Log to console or analytics
                    println("Error: \${effect.error}")
                    Toast.makeText(context, "Error: \${effect.error}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (state.isLoading) {
                CircularProgressIndicator()
            } else {
                Text(text = "Message: \${state.currentMessage}")
            }
            Text(text = "Count: \${state.counter}")
            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = textFieldValue,
                onValueChange = { textFieldValue = it },
                label = { Text("New Message") }
            )
            Button(onClick = { viewModel.onEvent(MainScreenEvent.UpdateMessageViaInput(textFieldValue)) }) {
                Text("Update Message")
            }
            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = { viewModel.onEvent(MainScreenEvent.IncrementCounter) }) {
                Text("Increment Counter")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { viewModel.onEvent(MainScreenEvent.ShowToastWithCurrentMessage) }) {
                Text("Show Toast")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { viewModel.onEvent(MainScreenEvent.RequestInitialMessage) }) {
                Text("Reload Initial Message")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
       MainScreen() // Preview might show "Loading..." or require a mock ViewModel setup for full interaction
    }
}
