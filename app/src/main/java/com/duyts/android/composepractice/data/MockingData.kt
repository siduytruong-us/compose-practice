package com.duyts.android.composepractice.data

import com.duyts.android.composepractice.model.Post
import com.duyts.android.composepractice.model.Story
import com.duyts.android.composepractice.model.User

object MockData {
	val images = listOf(
		"https://images.unsplash.com/photo-1507525428034-b723cf961d3e",
		"https://images.unsplash.com/photo-1506748686214-e9df14d4d9d0",
		"https://images.unsplash.com/photo-1483683804023-6ccdb62f86ef",
		"https://images.unsplash.com/photo-1517816743773-6e0fd518b4a6",
		"https://images.unsplash.com/photo-1472214103451-9374bd1c798e",
		"https://images.unsplash.com/photo-1469474968028-56623f02e42e",
		"https://images.unsplash.com/photo-1470770841072-f978cf4d019e",
		"https://images.unsplash.com/photo-1541167760496-1628856ab772",
		"https://images.unsplash.com/photo-1506377247377-2a5b3b417ebb",
		"https://images.unsplash.com/photo-1509042239860-f550ce710b93"
	)

	private fun getMockUsers(): List<User> {
		return listOf(
			User(
				id = "1",
				name = "Sarah Jones",
				profileImageUrl = "https://randomuser.me/api/portraits/women/5.jpg"
			),
			User(
				id = "2",
				name = "Emma Rodrigues",
				profileImageUrl = "https://randomuser.me/api/portraits/women/6.jpg"
			),
			User(
				id = "3",
				name = "John Doe",
				profileImageUrl = "https://randomuser.me/api/portraits/men/7.jpg"
			),
			User(
				id = "4",
				name = "Kate Mary",
				profileImageUrl = "https://randomuser.me/api/portraits/women/1.jpg"
			),
			User(
				id = "5",
				name = "Jack Hall",
				profileImageUrl = "https://randomuser.me/api/portraits/men/2.jpg"
			),
			User(
				id = "6",
				name = "Sophia Brown",
				profileImageUrl = "https://randomuser.me/api/portraits/women/8.jpg"
			),
			User(
				id = "7",
				name = "Mark Lee",
				profileImageUrl = "https://randomuser.me/api/portraits/men/3.jpg"
			),
			User(
				id = "8",
				name = "Alice Smith",
				profileImageUrl = "https://randomuser.me/api/portraits/women/9.jpg"
			),
			User(
				id = "9",
				name = "Paul Adams",
				profileImageUrl = "https://randomuser.me/api/portraits/men/4.jpg"
			),
			User(
				id = "10",
				name = "Sophia Taylor",
				profileImageUrl = "https://randomuser.me/api/portraits/women/10.jpg"
			)
		)
	}

	fun getMockStories(): List<Story> {
		val users: List<User> = getMockUsers()
		return users.map { user ->
			Story(
				id = user.id,
				user = user
			)
		}
	}

	fun getMockPosts(size:Int = 20): List<Post> {
		val users = getMockUsers()


		return List(size) { index ->
			val user = users[index % users.size] // Rotate through users
			Post(
				id = (index + 1).toString(),
				user = user,
				timeAgo = "${index + 1}h ago",
				contentText = "This is post number ${index + 1} by ${user.name}. Sharing thoughts and updates!",
				images = images.shuffled().take((3..6).random()), // Random 3-6 images
				likeCount = (100..3000).random(),
				commentCount = (10..500).random(),
				likedByUsers = users.shuffled().take((1..users.size).random())
			)
		}
	}
}
