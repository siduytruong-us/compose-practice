package com.duyts.android.composepractice.feature.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.Composable
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
import okhttp3.internal.userAgent


@Composable
fun HomeScreen() {
	val posts = MockData.getMockPosts()
	val stories = MockData.getMockStories()
	HomeContent(posts, stories)
}


@Composable
fun HomeContent(posts: List<Post>, stories: List<Story>) {
	LazyColumn(
		modifier = Modifier
			.fillMaxSize()
			.background(MaterialTheme.colorScheme.background),
		contentPadding = PaddingValues(18.dp),
		verticalArrangement = Arrangement.spacedBy(24.dp)
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
					border = BorderStroke(1.dp, Color.Black)
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
	Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
		Image(imageVector = Icons.Filled.Home, contentDescription = null)
		Spacer(modifier = Modifier.weight(1f))
		Image(imageVector = Icons.Filled.MoreVert, contentDescription = null)
		Image(imageVector = Icons.Filled.Email, contentDescription = null)
	}
}

@Composable
fun PostItem(item: Post) {
	val user = item.user
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.background(Color.Transparent)
			.padding(bottom = 6.dp)
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
			modifier = Modifier.padding(vertical = 8.dp),
			text = item.contentText,
			style = MaterialTheme.typography.bodyLarge
		)

		PostImagesGrid(item.images)
		Spacer(modifier = Modifier.size(6.dp))
		LikeUsers(item.likedByUsers)
	}
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

@Preview
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
