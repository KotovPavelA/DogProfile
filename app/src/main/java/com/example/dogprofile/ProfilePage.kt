package com.example.dogprofile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun ProfilePage() {
    Card(
        elevation = CardDefaults.elevatedCardElevation(), modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 100.dp, bottom = 100.dp, start = 16.dp, end = 16.dp
            )

    ) {
        // Content of our card - Including Dog Image, description, followers etc.
        ConstraintLayout() {

            val (image, nameText, detailText, rowStats, buttonFollow, messageButton) = createRefs()
            Image(
                painter = painterResource(id = R.drawable.doge),
                contentDescription = "doge",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .border(width = 2.dp, Color.Red, shape = CircleShape)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                contentScale = ContentScale.Crop
            )
            Text(text = "Meme doge", modifier = Modifier.constrainAs(nameText) {
                top.linkTo(image.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })
            Text(text = "Webs", modifier = Modifier.constrainAs(detailText) {
                top.linkTo(nameText.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

            Row(horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .constrainAs(rowStats) {
                        top.linkTo(detailText.bottom)
                    }) {
                ProfileStats(count = "150M", title = "Followers")
                ProfileStats(count = "100", title = "Following")
                ProfileStats(count = "12", title = "Posts")
            }

            Button(onClick = { /*TODO*/ }, Modifier.constrainAs(buttonFollow) {
                top.linkTo(rowStats.bottom, margin = 16.dp)
                start.linkTo(parent.start)
                end.linkTo(messageButton.start)
                width = Dimension.wrapContent
            }) {
                Text(text = "Follow user")
            }
            Button(onClick = { /*TODO*/ }, Modifier.constrainAs(messageButton) {
                top.linkTo(rowStats.bottom, margin = 16.dp)
                start.linkTo(buttonFollow.end)
                end.linkTo(parent.end)
                width = Dimension.wrapContent
            }) {
                Text(text = "Direct message")
            }

        }
    }
}

@Composable
fun ProfileStats(count: String, title: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = count, fontWeight = FontWeight.Bold)
        Text(text = title)
    }
}

@Preview
@Composable
fun ProfilePagePreview() {
    ProfilePage()
}