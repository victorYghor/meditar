package com.victoryghor.meditar

import android.content.Context
import android.content.ContextWrapper
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.savedstate.SavedStateRegistryOwner
import com.victoryghor.meditar.Destinations.BELLHITSCREEN
import com.victoryghor.meditar.Destinations.BELLRINGSCREEN
import com.victoryghor.meditar.Destinations.SELECTTIMER
import com.victoryghor.meditar.Destinations.TIMER
import com.victoryghor.meditar.bell.BellViewModel
import com.victoryghor.meditar.bell.HitBellScreen
import com.victoryghor.meditar.bell.RingBellScreen
import com.victoryghor.meditar.timer.TimerScreen
import com.victoryghor.meditar.timer.TimerViewModel
import com.victoryghor.meditar.timerPicker.SelectTimerScreen

object Destinations {
    const val SELECTTIMER = "selectTimer"
    const val TIMER = "timer"
    const val BELLHITSCREEN = "bellHitScreen/{minutes}"
    const val BELLRINGSCREEN = "bellRingScreen?minutes={minutes}"
}
@Composable
fun TimerNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = TIMER
    ) {
        composable(SELECTTIMER) {
            SelectTimerScreen(
                goToRingBellScreen = { minutes -> navController.navigate("$BELLHITSCREEN/${minutes}") }
            )
        }
        composable(BELLHITSCREEN) { NavBackStackEntry ->
            val minutes = NavBackStackEntry.arguments?.getString("minutes")?.toInt()
            if(minutes == null)
                Log.e("TimerNavigation", "the miuntes passed to the argument are null")
            HitBellScreen(minutes ?: 15)
        }
        composable(
            BELLRINGSCREEN,
            arguments = listOf(navArgument("minutes"){ nullable = true })
        ) {
            val minutes = it.arguments?.getString("minutes")?.toInt()
            RingBellScreen(minutes, { navController.navigate(SELECTTIMER) })
        }
        composable(TIMER) {
            TimerScreen(
                goToBellRingScreen = { navController.navigate("$BELLRINGSCREEN") },
                viewModel<TimerViewModel>(
                    factory = TimerViewModel.providedFactory(1, LocalContext.current.getActivity() as SavedStateRegistryOwner)
                )
            )
        }
    }
}
fun Context.getActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}
