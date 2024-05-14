package com.krillinator.test_19

import android.os.Bundle
import android.os.CountDownTimer
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.krillinator.test_19.ui.theme.Test_19Theme
import com.krillinator.test_19.viewmodel.CountdownVM
import kotlin.math.absoluteValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Test_19Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        LaunchCountdowns()
                    }
                }
            }
        }
    }
}


@Composable
fun LaunchCountdowns() {

    // viewModel() <--- sharedViewModel
    val countdownViewModelONE : CountdownVM = viewModel()    // viewModel() <-- fetch an instance of VM
    val countdownViewModelTWO : CountdownVM = viewModel()

    var text: String by remember {
        mutableStateOf("")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CountdownScreen(seconds = 5, countdownViewModelTWO)
        CountdownScreen(seconds = 10, countdownViewModelONE)
        TextField(value = text, onValueChange = { text = it })
    }

}

@Composable
fun CountdownScreen(seconds: Int, countdownViewModel: CountdownVM) {

    // Repeat on lifecycle
    val uiState by countdownViewModel.remainingSeconds.collectAsStateWithLifecycle()

    // Run Coroutine Functions
    LaunchedEffect(key1 = seconds) {
        countdownViewModel.startCountdown(seconds)
    }

    Column{
        Text(
            text = "Hello ${uiState}!",
            Modifier.size(44.dp)
        )
    }

}



