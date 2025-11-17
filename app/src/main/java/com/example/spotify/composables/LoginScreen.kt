package com.example.spotify.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicSecureTextField
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.spotify.R
import com.example.spotify.navigation.Dashboard
import com.example.spotify.navigation.Login
import com.example.spotify.navigation.Register

@Composable
fun LoginScreen(navController: NavController) {
    var userName by remember { mutableStateOf("") }
    val password = remember { TextFieldState() }
    var showPassword by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Header(navController = navController, title ="Get Started")
        Text(text = "Please fill your details to login.", fontSize = 16.sp,
            modifier = Modifier.fillMaxWidth(0.88f).padding(top = 72.dp))
        BasicTextField(
            value = "userName",
            onValueChange = {
//                userName = it
            },

            modifier = Modifier
                .padding(top = 32.dp)
                .fillMaxWidth(0.88f)
                .height(56.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(Color(0xffDEDEDE))
                .padding(horizontal = 16.dp, vertical = 18.dp),
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            ),
            singleLine = true,

            )

        BasicSecureTextField(
            state = password,
            textObfuscationMode =
                if (showPassword) {
                    TextObfuscationMode.Visible
                } else {
                    TextObfuscationMode.RevealLastTyped
                },
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth(0.88f)
                .height(56.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(Color(0xffDEDEDE))
                .padding(6.dp),

        decorator = { innerTextField ->
                Box(modifier = Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 16.dp, end = 48.dp)
                    ) {
                        innerTextField()
                    }
                    Icon(
                        if (showPassword) {
                            Icons.Filled.Visibility
                        } else {
                            Icons.Filled.VisibilityOff
                        },
                        contentDescription = "Toggle password visibility",
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .requiredSize(54.dp).padding(16.dp)
                            .clickable { showPassword = !showPassword }
                    )
                }
            }
        )

        Button(onClick = {}, modifier = Modifier.fillMaxWidth(0.88f).padding(top = 32.dp).clip(RoundedCornerShape(5.dp))
            .background(Color(0xff000000)
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(0.dp),
            elevation = null) {
            Text(text = "Get Started", color = Color(0xffffffff), modifier = Modifier.clickable{
                navController.navigate(Dashboard)
            })
        }

        Button(onClick = {}, modifier = Modifier.fillMaxWidth(0.88f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(0.dp),
            elevation = null) {
            Text(text = "forgot password?", color = Color(0xff000000), style = TextStyle(textDecoration = TextDecoration.Underline))
        }

        Box(modifier = Modifier.fillMaxSize(), ) {
            Row(modifier = Modifier.fillMaxWidth().align(Alignment.BottomEnd).padding(bottom = 40.dp), horizontalArrangement = Arrangement.Center) {
                Text(text = "New member? ", fontSize = 14.sp)
                Text(text = "Register", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.clickable{
                    navController.navigate(Register)
                })
            }
        }
    }



}
//
//@Preview
//@Composable
//fun LoginScreenPreview() {
//    LoginScreen()
//}

@Composable
fun Header(navController : NavController, title : String) {
    Row(modifier = Modifier.fillMaxWidth(0.88f).padding(top = 65.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically ) {
        Image(painter = painterResource(R.drawable.gobackicon), contentDescription = "back icon", modifier = Modifier.size(25.dp).clickable{
            navController.popBackStack()
        }, )
        Text(text = title, fontSize = 24.sp)
        Text(text="", modifier = Modifier.size(25.dp))
    }
}