package com.hussein.glance

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProfileCard(
    modifier: Modifier = Modifier,
    profilePicture: Int,
    name: String,
    bio: String,
    socialMediaIcons: @Composable () -> Unit,
    actionButtons: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        //elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
        ) {
            Row {
                Image(
                    painter = painterResource(id = profilePicture),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column {
                    Text(
                        text = name,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(text = bio)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            socialMediaIcons()

            Spacer(modifier = Modifier.height(16.dp))

            actionButtons()
        }
    }
}

@Composable
fun SocialMediaIcons() {
    Row {
        // Add your social media icons here
    }
}

@Composable
fun ActionButtons() {
    Row {
        // Add your action buttons here
    }
}

@Preview
@Composable
fun ProfileCardPreview() {
    ProfileCard(
        profilePicture = R.drawable.ic_launcher_background,
        name = "John Doe",
        bio = "Software Engineer",
        socialMediaIcons = { SocialMediaIcons() },
        actionButtons = { ActionButtons() }
    )
}