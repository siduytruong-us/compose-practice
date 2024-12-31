package com.duyts.android.composepractice.feature.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable


@Serializable
object Home

fun NavController.navigateToHome() {
	navigate(Home)
}

fun NavGraphBuilder.homeScreen() {
	composable<Home> {
		HomeScreen()
	}
}
