package com.example.dogprofile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import kotlinx.coroutines.NonDisposableHandle.parent

@Composable
fun ProfilePage() {
    Card(
        elevation = CardDefaults.elevatedCardElevation(), modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 50.dp, bottom = 50.dp, start = 16.dp, end = 16.dp
            )
    ) {
        // Content of our card - Including Dog Image, description, followers etc.

        BoxWithConstraints() {
            val constraints = if (maxWidth < 600.dp) {
                portraitConstraints(margin = 16.dp)
            } else {
                landscapeConstraints(margin = 16.dp)
            }
            ConstraintLayout(constraints) {
                Image(
                    painter = painterResource(id = R.drawable.doge),
                    contentDescription = "doge",
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .border(width = 2.dp, Color.Red, shape = CircleShape)
                        .layoutId("image"),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "Meme doge",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.layoutId("nameText")
                )
                Text(
                    text = "Webs",
                    Modifier.layoutId("detailsText")
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .layoutId("rowStats")
                ) {
                    ProfileStats(count = "150M", title = "Followers")
                    ProfileStats(count = "100", title = "Following")
                    ProfileStats(count = "12", title = "Posts")
                }

                Button(onClick = { /*TODO*/ }, Modifier.layoutId("buttonFollow")) {
                    Text(text = "Follow user")
                }
                Button(onClick = { /*TODO*/ }, Modifier.layoutId("messageButton")) {
                    Text(text = "Direct message")
                }

            }
        }
    }
}

private fun portraitConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val image = createRefFor("image")
        val nameText = createRefFor("nameText")
        val detailsText = createRefFor("detailsText")
        val buttonFollow = createRefFor("buttonFollow")
        val messageButton = createRefFor("messageButton")
        val rowStats = createRefFor("rowStats")

        val guideline = createGuidelineFromTop(0.3f)

        constrain(image) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(nameText) {
            top.linkTo(image.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(detailsText) {
            top.linkTo(nameText.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(rowStats) {
            top.linkTo(detailsText.bottom)
        }
        constrain(buttonFollow) {
            top.linkTo(rowStats.bottom, margin = margin)
            start.linkTo(parent.start)
            end.linkTo(messageButton.start)
            width = Dimension.wrapContent
        }
        constrain(messageButton) {
            top.linkTo(rowStats.bottom, margin = margin)
            start.linkTo(buttonFollow.end)
            end.linkTo(parent.end)
            width = Dimension.wrapContent
        }
    }
}

private fun landscapeConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val image = createRefFor("image")
        val nameText = createRefFor("nameText")
        val detailsText = createRefFor("detailsText")
        val buttonFollow = createRefFor("buttonFollow")
        val messageButton = createRefFor("messageButton")
        val rowStats = createRefFor("rowStats")

        val guideline = createGuidelineFromTop(0.3f)

        constrain(image) {
            top.linkTo(parent.top, margin = margin)
            start.linkTo(parent.start, margin = margin)
        }
        constrain(nameText) {
            start.linkTo(image.start)
            top.linkTo(image.bottom)
            end.linkTo(image.end)
        }
        constrain(detailsText) {
            top.linkTo(image.bottom)
            start.linkTo(nameText.start)
            end.linkTo(nameText.end)
        }
        constrain(rowStats) {
            top.linkTo(image.top)
            start.linkTo(image.end, margin = margin)
            end.linkTo(parent.end)
        }
        constrain(buttonFollow) {
            top.linkTo(rowStats.bottom, margin = margin)
            start.linkTo(rowStats.start)
            end.linkTo(messageButton.start)
            bottom.linkTo(detailsText.bottom)
            width = Dimension.wrapContent
        }
        constrain(messageButton) {
            top.linkTo(rowStats.bottom, margin = margin)
            start.linkTo(buttonFollow.end)
            end.linkTo(parent.end)
            bottom.linkTo(detailsText.bottom)
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