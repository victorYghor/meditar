package com.victoryghor.meditar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import android.util.Log
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.victoryghor.meditar.Destinations.BELLSCREEN
import com.victoryghor.meditar.Destinations.SELECTTIMER
import com.victoryghor.meditar.Destinations.TIMER
import com.victoryghor.meditar.bell.BellScreen
import com.victoryghor.meditar.timer.TimerScreen
import com.victoryghor.meditar.timerPicker.SelectTimerScreen

object Destinations {
    const val SELECTTIMER = "selectTimer"
    const val TIMER = "timer"
    const val BELLSCREEN = "bellScreen/{minutes}"
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
            SelectTimerScreen(
                goToRingBellScreen = { minutes -> navController.navigate("bellScreen/${minutes}") }
            )
        }
        composable(BELLSCREEN) { NavBackStackEntry ->
            val minutes = NavBackStackEntry.arguments?.getString("minutes")?.toInt()
            if(minutes == null)
                Log.e("TimerNavigation", "the miuntes passed to the argument are null")
            BellScreen(minutes ?: 15)
        }
        composable(TIMER) {
//            TimerScreen()
        }
    }
}