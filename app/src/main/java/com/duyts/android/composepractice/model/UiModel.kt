package com.duyts.android.composepractice.model

data class Story(
	val id: String,
	val user: User,
)

data class Post(
	val id: String,
	val user: User, // Associated user
	val timeAgo: String,
	val contentText: String,
	val images: List<String>, // List of image URLs
	val likeCount: Int,
	val commentCount: Int,
	val likedByUsers: List<String>, // Names of users who liked
)

data class User(
	val id: String,
	val name: String,
	val profileImageUrl: String,
)
