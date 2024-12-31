package com.duyts.android.composepractice.feature.onboarding

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
object Onboarding

fun NavGraphBuilder.onboardingScreen(onFinish: () -> Unit) {
	composable<Onboarding> {
		OnboardingScreen(onFinish = onFinish)
	}
}

fun NavController.navigateToOnboardingScreen() {
	navigate(Onboarding)
}