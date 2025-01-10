package com.duyts.android.composepractice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ComposeApplication() {
	Surface(
		modifier = Modifier
			.fillMaxSize()
			.background(MaterialTheme.colorScheme.background),
	) {
		Scaffold(
			containerColor = Color.Transparent,
			contentColor = MaterialTheme.colorScheme.onBackground,
			contentWindowInsets = WindowInsets(0, 0, 0, 0)
		) { paddingValues ->
			Row(
				modifier = Modifier
					.fillMaxSize()
					.consumeWindowInsets(paddingValues)
			) {
				AppNavHost()
			}
		}
	}
}