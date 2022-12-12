package org.chiachat.app.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import org.chiachat.app.ui.composables.chatgpt.*
import org.chiachat.app.ui.composables.chatgpt.HomeFeed
import org.chiachat.app.ui.services.ResourceService
import org.koin.core.component.inject

internal class LoginComponent : Component {

    override val vm: ILoginViewModel = LoginViewModel()

    val resources: ResourceService by inject()

    val posts = listOf(
        Post(
            user = User(
                name = "Jane Doe",
                handle = "@janedoe"
            ),
            text = "Just learned about Jetpack Compose! It looks like a great way to build Android UIs.",
            timestamp = "5m",
            likes = 23,
            replies = 4,
            retweets = 12
        ),
        Post(
            user = User(
                name = "John Doe",
                handle = "@johndoe"
            ),
            text = "I've been using Jetpack Compose for a while now and it's really changed the way I approach Android development.",
            timestamp = "23m",
            likes = 45,
            replies = 9,
            retweets = 19
        ),
        Post(
            user = User(
                name = "Jetpack Compose",
                handle = "@jetpackcompose"
            ),
            text = "Jetpack Compose is a modern toolkit for building native Android UIs.",
            timestamp = "1h",
            likes = 103,
            replies = 20,
            retweets = 37
        ),
        Post(
            user = User(
                name = "Jane Doe",
                handle = "@janedoe"
            ),
            text = "Just learned about Jetpack Compose! It looks like a great way to build Android UIs.",
            timestamp = "5m",
            likes = 23,
            replies = 4,
            retweets = 12
        ),
        Post(
            user = User(
                name = "John Doe",
                handle = "@johndoe"
            ),
            text = "I've been using Jetpack Compose for a while now and it's really changed the way I approach Android development.",
            timestamp = "23m",
            likes = 45,
            replies = 9,
            retweets = 19
        ),
        Post(
            user = User(
                name = "Jetpack Compose",
                handle = "@jetpackcompose"
            ),
            text = "Jetpack Compose is a modern toolkit for building native Android UIs.",
            timestamp = "1h",
            likes = 103,
            replies = 20,
            retweets = 37
        ),
        Post(
            user = User(
                name = "Jane Doe",
                handle = "@janedoe"
            ),
            text = "Just learned about Jetpack Compose! It looks like a great way to build Android UIs.",
            timestamp = "5m",
            likes = 23,
            replies = 4,
            retweets = 12
        ),
        Post(
            user = User(
                name = "John Doe",
                handle = "@johndoe"
            ),
            text = "I've been using Jetpack Compose for a while now and it's really changed the way I approach Android development.",
            timestamp = "23m",
            likes = 45,
            replies = 9,
            retweets = 19
        ),
        Post(
            user = User(
                name = "Jetpack Compose",
                handle = "@jetpackcompose"
            ),
            text = "Jetpack Compose is a modern toolkit for building native Android UIs.",
            timestamp = "1h",
            likes = 103,
            replies = 20,
            retweets = 37
        ),
    )

    @Composable
    override fun View() {
        Column {
            HomeFeed.HomeFeedScreen(posts)
        }
//        var register by remember { mutableStateOf(true) }
//        if (register) {
//            RegistrationComposables.RegistrationScreen(resources) { register = false }
//        } else {
//            LoginComposables.LoginScreen(resources)
//        }
        /*Box(modifier = Modifier.padding(40.dp).fillMaxSize()) {
            ToggleDarkModeButton(vm.themeService, vm.resourceService, Modifier.align(Alignment.TopEnd))
            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.align(Alignment.Center).padding(40.dp).width(320.dp)
            ) {
                CchTextField(vm.server, "server", "chiachat.org")
                CchTextField(vm.username, "username", "@username")
                CchTextField(vm.password, "password")
                CchActionButton("Login", onClick = vm::onLogin)
            }
        }*/
    }
}
