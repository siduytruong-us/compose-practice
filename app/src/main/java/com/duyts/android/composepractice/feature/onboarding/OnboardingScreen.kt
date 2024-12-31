package com.duyts.android.composepractice.feature.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.duyts.android.composepractice.feature.onboarding.component.FirstPage
import com.duyts.android.composepractice.feature.onboarding.component.SecondPage
import kotlinx.coroutines.launch

private const val PAGE_SIZE: Int = 3

@Composable
fun OnboardingScreen(onFinish: () -> Unit) {
	val coroutineScope = rememberCoroutineScope()
	val pagerState = rememberPagerState(0, pageCount = { PAGE_SIZE })

	Box(
		modifier = Modifier
			.fillMaxSize()
			.padding(16.dp)
	) {
		// Pager for onboarding pages
		HorizontalPager(
			state = pagerState,
			modifier = Modifier.fillMaxWidth()
		) { page ->
			when (page) {
				0 -> FirstPage()
				1 -> SecondPage()
				2 -> FirstPage()
			}
		}

		// Pager Indicator
		Column(
			modifier = Modifier
				.wrapContentHeight()
				.fillMaxWidth()
				.align(Alignment.BottomCenter)
		) {
			Row(
				Modifier
					.wrapContentHeight()
					.fillMaxWidth()
					.padding(bottom = 8.dp),
				horizontalArrangement = Arrangement.Center
			) {
				repeat(pagerState.pageCount) { iteration ->
					val color =
						if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
					Box(
						modifier = Modifier
							.padding(2.dp)
							.clip(CircleShape)
							.background(color)
							.size(16.dp)
					)
				}
			}

			// Buttons for Navigation
			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceBetween
			) {
				TextButton(
					onClick = { onFinish() },
					enabled = pagerState.currentPage == PAGE_SIZE - 1
				) {
					Text("Skip")
				}

				Button(
					onClick = {
						if (pagerState.currentPage == PAGE_SIZE - 1) {
							onFinish()
						} else {
							coroutineScope.launch {
								pagerState.animateScrollToPage(pagerState.currentPage + 1)
							}
						}
					}
				) {
					Text(if (pagerState.currentPage == PAGE_SIZE - 1) "Finish" else "Next")
				}
			}
		}
	}
}
