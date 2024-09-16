package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                    ArtSpaceApp()
            }
        }
    }
}

data class ArtWork(
    val title: String,
    val artist: String,
    val year: String,
    val imageRes: Int
)

val artworks = listOf(
    ArtWork("A Symphony of Colors", "Fabrice M Mpozenzi", "2017", R.drawable.image1),
    ArtWork("Brushstrokes of Identity", "Fabrice M Luhangara", "1889", R.drawable.image2),
)

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    var currentIndex by remember { mutableStateOf(0) }
    val artwork = artworks[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Display the artwork image
        Image(
            painter = painterResource(id = artwork.imageRes),
            contentDescription = artwork.title,
            modifier = Modifier
                .fillMaxWidth()  // Restrict the image to fill only the width
                .height(300.dp)
                .border(
                    width = 4.dp,
                    color = Color.LightGray,
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
                )
                .padding(16.dp)
                .shadow(8.dp, shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)),  // Add shadow
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Display artwork title and artist
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(16.dp)
                .border(1.dp, Color.LightGray)
                .background(Color(0xFFF2F2F2))  // Light gray background
                .padding(16.dp),

        )
        {
            Column {
                Text(text = artwork.title, fontWeight = FontWeight.Bold)
                Text(text = "By ${artwork.artist} (${artwork.year})")
            }



        }

        Spacer(modifier = Modifier.height(16.dp))

        // Previous and Next button for navigation
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()  // Make the row fill the available width
                .padding(horizontal = 16.dp)  // Optional: Add some padding for aesthetics
        ) {
            Button(onClick = { if (currentIndex > 0) currentIndex-- }) {
                Text("Previous")
            }
            Button(onClick = { if (currentIndex < artworks.size - 1) currentIndex++ }) {
                Text("Next")
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}




