package com.duyts.android.composepractice

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.duyts.android.composepractice.feature.home.Home
import com.duyts.android.composepractice.feature.home.homeScreen
import com.duyts.android.composepractice.feature.home.navigateToHome
import com.duyts.android.composepractice.feature.onboarding.Onboarding
import com.duyts.android.composepractice.feature.onboarding.onboardingScreen

@Composable
fun AppNavHost() {
	val navController = rememberNavController()
	NavHost(navController = navController, startDestination = Home) {
		onboardingScreen(navController::navigateToHome)
		homeScreen()
	}
}