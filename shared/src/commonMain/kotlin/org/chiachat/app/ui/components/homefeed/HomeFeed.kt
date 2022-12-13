package org.chiachat.app.ui.components.homefeed

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.chiachat.app.ui.composables.Graphics.GraphicButton
import org.chiachat.app.ui.theme.CchGraphics

data class Post(
    val user: User,
    val text: String,
    val timestamp: String,
    val likes: Int,
    val replies: Int,
    val retweets: Int
)

data class User(
    val name: String,
    val handle: String,
)


internal object HomeFeed {
    @Composable
    fun HomeFeedScreen(posts: List<Post>) {
        TopNavBar(menuClick = {}, homeClick = {}, notificationsClick = {})
        LazyColumn {
            posts.forEach { tweet ->
                item {
                    TweetCard(tweet)
                }
            }
        }
    }

    @Composable
    fun TopNavBar(
        modifier: Modifier = Modifier,
        menuClick: () -> Unit,
        homeClick: () -> Unit,
        notificationsClick: () -> Unit
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            MenuButton(menuClick)
            HomeButton(homeClick)
            NotificationButton(notificationsClick)
        }
    }

    @Composable
    fun MenuButton(onClick: () -> Unit) {
        GraphicButton(graphic = CchGraphics.MENU, contentDescription = "menu", onClick = onClick)
    }

    @Composable
    fun HomeButton(onClick: () -> Unit) {
        GraphicButton(graphic = CchGraphics.CHIACHAT_ICON, contentDescription = "home", onClick = onClick)
    }

    @Composable
    fun NotificationButton(onClick: () -> Unit) {
        GraphicButton(graphic = CchGraphics.NOTIFICATION_BELL, contentDescription = "notifications", onClick = onClick)
    }


    // create a side drawer that pops open when you click the hamburger menu
    @Composable
    fun SideDrawer() {
        Column {
            // profile picture
            // name
            // handle
            // bio
            // location
            // website
            // join date
            // number of tweets
            // number of following
            // number of followers
        }
    }

    @Composable
    fun TweetCard(post: Post) {
        Surface(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.padding(16.dp)) {
                /* Avatar image */
                Column(modifier = Modifier.padding(start = 16.dp)) {
                    /* User name and handle */
                    Row {
                        Text(
                            text = post.user.name,
                            style = TextStyle(fontWeight = FontWeight.Bold)
                        )
                        Text(
                            text = post.user.handle,
                            style = TextStyle(color = Color.Gray)
                        )
                    }
                    /* Tweet text */
                    Text(text = post.text)
                    /* Tweet metadata (time, likes, replies, etc.) */
                    Row {
                        Text(
                            text = post.timestamp,
                            style = TextStyle(color = Color.Gray)
                        )
                        /* Other tweet metadata */
                    }
                }
            }
        }
    }
}