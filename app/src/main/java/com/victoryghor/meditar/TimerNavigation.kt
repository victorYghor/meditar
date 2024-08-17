package com.victoryghor.meditar

import android.content.Context
import android.content.ContextWrapper
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.savedstate.SavedStateRegistryOwner
import com.victoryghor.meditar.Destinations.HIT_BELL_SCREEN
import com.victoryghor.meditar.Destinations.RING_BELL_SCREEN
import com.victoryghor.meditar.Destinations.SELECT_TIMER_SCREEN
import com.victoryghor.meditar.Destinations.TIMER_SCREEN
import com.victoryghor.meditar.bell.BellViewModel
import com.victoryghor.meditar.bell.HitBellScreen
import com.victoryghor.meditar.bell.RingBellScreen
import com.victoryghor.meditar.timer.TimerScreen
import com.victoryghor.meditar.timer.TimerViewModel
import com.victoryghor.meditar.timerPicker.SelectTimerScreen
import com.victoryghor.meditar.timerPicker.TimerPickerViewModel

object Destinations {
    const val SELECT_TIMER_SCREEN = "selectTimerScreen"
    const val TIMER_SCREEN = "timerScreen/{%s}"
    const val HIT_BELL_SCREEN = "hitBellScreen/{%s}"
    const val RING_BELL_SCREEN = "ringBellScreen?minutes={%s}"
}
@Composable
fun TimerNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = SELECT_TIMER_SCREEN
    ) {
        composable(SELECT_TIMER_SCREEN) {
            val viewModel: TimerPickerViewModel = viewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            SelectTimerScreen(
                goToRingBellScreen = { minutes -> navController.navigate(HIT_BELL_SCREEN.format(minutes)) },
                selectMinutes = viewModel::selectMinutes,
                uiState = uiState
            )
        }
        composable(HIT_BELL_SCREEN) { NavBackStackEntry ->
            val bellViewModel = viewModel<BellViewModel>()
            val bellUiState by bellViewModel.uiState.collectAsStateWithLifecycle()
            HitBellScreen(bellUiState)
        }
        composable(
            RING_BELL_SCREEN,
            arguments = listOf(navArgument("minutes"){ nullable = true })
        ) {
            val bellViewModel: BellViewModel = viewModel()
            val bellUiState by bellViewModel.uiState.collectAsStateWithLifecycle()
            RingBellScreen(bellUiState, { navController.navigate(SELECT_TIMER_SCREEN) })
        }
        composable(
            TIMER_SCREEN
            ) {
            val timerViewModel: TimerViewModel = viewModel()
            val timerUIState by timerViewModel.uiState.collectAsStateWithLifecycle()
            TimerScreen(
                goToBellRingScreen = { navController.navigate(RING_BELL_SCREEN) },
                startTimer = timerViewModel::startTimer,
                uiState = timerUIState,
                selectMinutes = timerViewModel.selectTimerInMinutes
            )
        }
    }
}
