package com.example.spotify.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.spotify.R
import com.example.spotify.navigation.Login

@Composable
fun MainScreen(navController: NavController) {
    val imageSize = 136.87.dp
    val screenSize = LocalConfiguration.current.screenHeightDp.dp

    Box{
        Image(painter = painterResource(R.drawable.maincover), contentDescription = "mainscreencover",
            modifier = Modifier.fillMaxSize(1f).offset(y=-screenSize /2+40.dp),
            alignment = Alignment.BottomEnd)
    }



    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Image(painter = painterResource(R.drawable.applogo), contentDescription = "applogo",
        modifier = Modifier.size(imageSize)
        )


    }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(top = screenSize/2 + imageSize/2 ).fillMaxWidth(0.83f),
            ) {
            Text(text="Read more and stress less with our online book shopping app. Shop from anywhere you are and discover titles that you love. Happy reading!",
                color = Color(0xff252525),
                textAlign = TextAlign.Center,
                lineHeight = 24.5.sp,
                modifier = Modifier.padding(top=22.dp)

            )
        }

        Button(onClick = {navController.navigate(Login)}, modifier = Modifier.fillMaxWidth(0.83f).padding(top = 88.dp).clip(RoundedCornerShape(5.dp)).background(Color(0xff000000)),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(0.dp),
            elevation = null) {
            Text(text = "Get Started", color = Color(0xffffffff))
        }
        Button(onClick = {}, modifier = Modifier.fillMaxWidth(0.83f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(0.dp),
            elevation = null) {
            Text(text = "Register", color = Color(0xff000000))
        }
    }




}


//@Preview
//@Composable
//fun MainScreenPreview() {
//    MainScreen()
//}