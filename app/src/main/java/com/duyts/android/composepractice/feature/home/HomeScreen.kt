package com.duyts.android.composepractice.feature.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.safeGestures
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.duyts.android.composepractice.R
import com.duyts.android.composepractice.components.PostImagesGrid
import com.duyts.android.composepractice.data.MockData
import com.duyts.android.composepractice.model.Post
import com.duyts.android.composepractice.model.Story
import com.duyts.android.composepractice.model.User


@Composable
fun HomeScreen() {
	val posts = MockData.getMockPosts(6)
	val stories = MockData.getMockStories()
	HomeContent(posts, stories)
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeContent(posts: List<Post>, stories: List<Story>) {
	LazyColumn(
		modifier = Modifier
			.fillMaxSize()
			.imePadding(),
		verticalArrangement = Arrangement.spacedBy(24.dp),
		contentPadding = WindowInsets.systemBars.asPaddingValues()
	) {
		item {
			Header()
		}
		item {
			Stories(stories)
		}
		items(posts) { item ->
			PostItem(item)
		}
	}
}

@Composable
private fun Stories(stories: List<Story>) {
	LazyRow(
		modifier = Modifier.fillMaxWidth(),
		horizontalArrangement = Arrangement.spacedBy(12.dp),
	) {
		items(stories) { story ->
			Column(
				modifier = Modifier.width(80.dp),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				Surface(
					shape = CircleShape,
					border = BorderStroke(
						1.dp,
						MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
					)
				) {
					AsyncImage(
						modifier = Modifier.size(72.dp),
						model = story.user.profileImageUrl,
						contentDescription = null
					)
				}
				Spacer(modifier = Modifier.height(12.dp))
				Text(
					text = story.user.name,
					style = MaterialTheme.typography.bodySmall,
					fontWeight = FontWeight.SemiBold,
					maxLines = 1,
					overflow = TextOverflow.Ellipsis
				)
			}
		}
	}
}

@Composable
private fun Header() {
	var input by remember { mutableStateOf("") }
	Row(modifier = Modifier.fillMaxWidth()) {
		TextField(
			input, onValueChange = { input = it },
			modifier = Modifier
				.weight(1f)
				.padding(end = 6.dp)
		)
		Image(imageVector = Icons.Filled.MoreVert, contentDescription = null)
		Image(imageVector = Icons.Filled.Email, contentDescription = null)
	}
}

@Composable
fun PostItem(item: Post) {
	val user = item.user
	var input by remember { mutableStateOf("") }
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.background(Color.Transparent)
	) {
		Row(
			modifier = Modifier.fillMaxWidth(),
			horizontalArrangement = Arrangement.spacedBy(10.dp),
			verticalAlignment = Alignment.CenterVertically,
		) {
			Surface(shape = CircleShape) {
				AsyncImage(
					modifier = Modifier.size(40.dp),
					contentScale = ContentScale.Crop,
					model = item.user.profileImageUrl,
					contentDescription = null,
					placeholder = painterResource(R.drawable.ic_launcher_background)
				)
			}
			Column(
				modifier = Modifier.fillMaxHeight(),
				verticalArrangement = Arrangement.SpaceBetween
			) {
				Text(
					user.name,
					style = MaterialTheme.typography.bodyMedium,
					fontWeight = FontWeight.Bold
				)
				Text(item.timeAgo, style = MaterialTheme.typography.bodySmall)
			}
			Spacer(modifier = Modifier.weight(1f))
			Icon(
				modifier = Modifier.size(36.dp),
				imageVector = Icons.Filled.MoreVert,
				contentDescription = null
			)
		}

		Text(
			modifier = Modifier.safeDrawingPadding(),
			text = item.contentText,
			style = MaterialTheme.typography.bodyLarge
		)

		PostImagesGrid(item.images)
		Spacer(modifier = Modifier.size(6.dp))
		LikeUsers(item.likedByUsers)
		TextField(
			input, onValueChange = { input = it },
			modifier = Modifier
				.weight(1f)
				.padding(end = 6.dp)
		)
	}
	Spacer(
		modifier = Modifier.windowInsetsBottomHeight(WindowInsets.systemBars)
	)
}

@Composable
private fun LikeUsers(likedByUsers: List<User>) {
	val maxUsers: List<User> = likedByUsers.take(3)
	val text = buildString {
		append("Liked by ${likedByUsers.first().name}")
		if (likedByUsers.size > maxUsers.size) {
			append(" and ${likedByUsers.size - maxUsers.size} others")
		}
	}
	Row(
		modifier = Modifier.fillMaxWidth(),
	) {
		LikeUserAvatars(
			modifier = Modifier.padding(end = 6.dp), likedByUsers = maxUsers
		)
		Text(text, style = MaterialTheme.typography.bodySmall)
	}
}

@Composable
private fun LikeUserAvatars(
	modifier: Modifier, likedByUsers: List<User>,
) {
	Row(
		modifier = modifier.wrapContentWidth(),
		horizontalArrangement = Arrangement.spacedBy((-6).dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		likedByUsers.map { user ->
			AsyncImage(
				modifier = Modifier
					.clip(CircleShape)
					.size(20.dp),
				model = user.profileImageUrl,
				contentDescription = null
			)
		}
	}
}

@Preview(
	showSystemUi = true,
	showBackground = true
)
@Composable
fun HomeContentPreview() {
	val stories = MockData.getMockStories()
	val posts = MockData.getMockPosts()
	HomeContent(posts, stories)
}


@Preview
@Composable
fun PostItemPreview() {
	val posts = MockData.getMockPosts()
	PostItem(posts.first())
}
