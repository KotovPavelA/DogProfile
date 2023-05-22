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
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Composable
fun ProfilePage() {
    Card(
        elevation = CardDefaults.elevatedCardElevation(),
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 100.dp, bottom = 100.dp, start = 16.dp, end = 16.dp
            )
            .border(
                width = 2.dp, Color.White, shape = RoundedCornerShape(
                    30.dp
                )
            )
    ) {
        // Content of our card - Including Dog Image, description, followers etc.
        Column(
            Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.doge),
                contentDescription = "doge",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .border(width = 2.dp, Color.Red, shape = CircleShape),
                contentScale = ContentScale.Crop
            )
            Text(text = "Meme doge")
            Text(text = "Webs")

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                ProfileStats(count = "150M", title = "Followers")
                ProfileStats(count = "100", title = "Following")
                ProfileStats(count = "12", title = "Posts")
            }

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Follow user")
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Direct message")
                }
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