package com.duyts.android.composepractice.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.duyts.android.composepractice.data.MockData

@Composable
fun PostImagesGrid(list: List<String>) {
	val pagerState = rememberPagerState(pageCount = { list.size })
	val modifier = Modifier.fillMaxWidth()
	Box {
		HorizontalPager(
			modifier = modifier.aspectRatio(4f / 5f), state = pagerState
		) { page ->
			AsyncImage(
				modifier = modifier, model = list[page], contentDescription = null
			)
		}
		PagerIndicator(
			modifier = modifier
				.align(Alignment.BottomCenter)
				.padding(bottom = 8.dp),
			pagerState = pagerState
		)
	}

}


@Composable
private fun PagerIndicator(
	modifier: Modifier = Modifier,
	pagerState: PagerState,
) {
	Row(
		modifier = modifier.wrapContentWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)
	) {
		repeat(pagerState.pageCount) { index ->
			val color =
				if (pagerState.currentPage == index)
					Color.White.copy(alpha = .8f)
				else
					Color.Gray.copy(alpha = .8f)
			Box(
				modifier = Modifier
					.clip(CircleShape)
					.background(color)
					.size(12.dp)
			) {

			}
		}
	}
}

@Preview
@Composable
fun PostImagesGridPreview() {
	val images = MockData.images
	PostImagesGrid(images)
}