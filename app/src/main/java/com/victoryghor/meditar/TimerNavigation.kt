package com.victoryghor.meditar

import android.content.Context
import android.content.ContextWrapper
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.activity.ComponentActivity
import androidx.compose.runtime.clearCompositionErrors
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
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
import timber.log.Timber
import com.victoryghor.meditar.bell.HitBellScreen
import com.victoryghor.meditar.util.removeCurlyBrackets

object Destinations {
    const val SELECT_TIMER_SCREEN = "selectTimerScreen"
    const val TIMER_SCREEN = "timerScreen/{minutes}"
    const val HIT_BELL_SCREEN = "hitBellScreen/{minutes}/{quantityOfHits}"
    const val RING_BELL_SCREEN = "ringBellScreen?minutes={minutes}?quantityOfHits={quantityOfHits}"
}

var previousRoute: String = SELECT_TIMER_SCREEN

@Composable
fun TimerNavHost(
    navController: NavHostController = rememberNavController()
) {
    fun navigateWithoutTrace(destination: String) {
        navController.navigate(destination) {
            launchSingleTop = true
            popUpTo(0)
        }
    }
    NavHost(
        navController = navController,
        startDestination = SELECT_TIMER_SCREEN
    ) {
        composable(SELECT_TIMER_SCREEN) {
            val viewModel: TimerPickerViewModel = viewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            SelectTimerScreen(
                goToRingBellScreen = { minutes ->
                    previousRoute = SELECT_TIMER_SCREEN
                    navigateWithoutTrace(
                        HIT_BELL_SCREEN
                            .replace("minutes", minutes.toString())
                            .replace("quantityOfHits", "3")
                    )
                },
                selectMinutes = viewModel::selectMinutes,
                uiState = uiState
            )
        }
        composable(HIT_BELL_SCREEN) {
            val bellViewModel = viewModel<BellViewModel>()
            val bellUiState by bellViewModel.uiState.collectAsStateWithLifecycle()
            val minutes = it.arguments?.getString("minutes")?.removeCurlyBrackets() ?: "5"
            HitBellScreen(bellUiState) {
                bellViewModel.startHitBell {
                    previousRoute = HIT_BELL_SCREEN
                    navigateWithoutTrace(
                        "ringBellScreen?minutes=$minutes?quantityOfHits=${bellUiState.quantityOfHits}"
                    )
                }
            }
        }
        composable(
            RING_BELL_SCREEN,
            arguments = listOf(
                navArgument("minutes") { nullable = true },
                navArgument("quantityOfHits") { nullable = true }
            )
        ) {
            val bellViewModel: BellViewModel = viewModel()
            val bellUiState by bellViewModel.uiState.collectAsStateWithLifecycle()
            val minutes = it.arguments?.getString("minutes") ?: "5"
            RingBellScreen(
                bellUiState
            ) {
                val goToNextScreen: () -> Unit =
                    {
                        when (previousRoute) {
                            HIT_BELL_SCREEN -> {
                                val destination = if (bellUiState.quantityOfHits == 1) {
                                    TIMER_SCREEN
                                } else {
                                    HIT_BELL_SCREEN.replace(
                                        "quantityOfHits",
                                        (bellUiState.quantityOfHits - 1).toString()
                                    )
                                }
                                previousRoute = RING_BELL_SCREEN
                                navigateWithoutTrace(destination.replace("minutes", minutes))
                            }

                            TIMER_SCREEN -> {
                                previousRoute = RING_BELL_SCREEN
                                navigateWithoutTrace(
                                    SELECT_TIMER_SCREEN.replace(
                                    "minutes",
                                    minutes))
                            }
                            else -> {
                                Timber.d(
                                    "reach in a not covered case destination =" +
                                            " $previousRoute"
                                )
                            }
                        }
                    }
                bellViewModel.startRingBell(goToNextScreen)
            }
        }
        composable(
            TIMER_SCREEN
        ) {
            val timerViewModel: TimerViewModel = viewModel()
            val timerUIState by timerViewModel.uiState.collectAsStateWithLifecycle()
            TimerScreen(
                goToBellRingScreen = {
                    previousRoute = TIMER_SCREEN
                    navigateWithoutTrace(RING_BELL_SCREEN)
                },
                startTimer = timerViewModel::startTimer,
                uiState = timerUIState,
                selectMinutes = timerViewModel.selectTimerInMinutes,
                onStop = {
                    previousRoute = TIMER_SCREEN
                    timerViewModel.stopTimer {
                        navigateWithoutTrace(SELECT_TIMER_SCREEN)
                    }
                }
            )
        }
    }
}
