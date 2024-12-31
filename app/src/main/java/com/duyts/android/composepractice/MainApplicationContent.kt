package com.duyts.android.composepractice

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainApplicationContent() {
	Scaffold(
		modifier = Modifier
			.fillMaxSize()
			.background(MaterialTheme.colorScheme.background)
	) { paddingValues ->
		Surface(
			modifier = Modifier
				.padding(paddingValues)
				.fillMaxSize(),
		) {
			AppNavHost()
		}
	}
}