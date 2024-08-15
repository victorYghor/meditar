package com.victoryghor.meditar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.victoryghor.meditar.Destinations.SELECTTIMER
import com.victoryghor.meditar.Destinations.TIMER
import com.victoryghor.meditar.timer.TimerScreen
import com.victoryghor.meditar.timerPicker.SelectTimerScreen

object Destinations {
    const val SELECTTIMER = "selectTimer"
    const val TIMER = "timer"
}
@Composable
fun TimerNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = SELECTTIMER
    ) {
        composable(SELECTTIMER) {
            SelectTimerScreen()
        }
        composable(TIMER) {
            TimerScreen()
        }
    }
}