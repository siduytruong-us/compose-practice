package com.duyts.android.composepractice.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.duyts.android.composepractice.data.MockData

@Composable
fun PostImagesGrid(list: List<String>) {
	LazyVerticalStaggeredGrid(
		modifier = Modifier.fillMaxWidth(),
		columns = StaggeredGridCells.Fixed(2),
	) {
		items(list) { item ->
			AsyncImage(item, contentDescription = null)
		}
	}
}


@Preview
@Composable
fun PostImagesGridPreview() {
	val images = MockData.getMockPosts().first().images
	PostImagesGrid(images)
}