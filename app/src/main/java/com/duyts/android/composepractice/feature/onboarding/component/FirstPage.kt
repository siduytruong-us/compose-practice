package com.duyts.android.composepractice.feature.onboarding.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.duyts.android.composepractice.R

@Composable
fun FirstPage() {
	Content()
}

@Composable
private fun Content() {
	Box(
		modifier = Modifier
			.fillMaxSize()
			.padding(24.dp)
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.align(Alignment.Center),
			verticalArrangement = Arrangement.spacedBy(16.dp)
		) {
			Image(painter = painterResource(R.drawable.first_screen), contentDescription = null)
			Text(
				text = "This is content onboarding screen",
				style = MaterialTheme.typography.titleLarge,
				color = MaterialTheme.colorScheme.onSurface,
				textAlign = TextAlign.Center

			)
			Text(
				text = "Create a stunning tutorial or onboarding page design for free! Access a comprehensive range of customizable templates and design elements to craft an engaging.",
				style = MaterialTheme.typography.bodyMedium,
				color = MaterialTheme.colorScheme.onSurface,
				textAlign = TextAlign.Center
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
private fun ContentPreview() {
	Content()
}