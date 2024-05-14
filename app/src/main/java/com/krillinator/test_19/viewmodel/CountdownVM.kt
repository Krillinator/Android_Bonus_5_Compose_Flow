package com.krillinator.test_19.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CountdownVM : ViewModel() {

    private val _remainingSeconds = MutableStateFlow(0)
    val remainingSeconds: StateFlow<Int> = _remainingSeconds.asStateFlow()

    fun startCountdown(seconds: Int) {
        viewModelScope.launch {

            // Time
            var remainingSeconds = seconds

            while (remainingSeconds >= 0) {
                _remainingSeconds.value = remainingSeconds
                delay(1000L)
                remainingSeconds--
            }

        }
    }

}