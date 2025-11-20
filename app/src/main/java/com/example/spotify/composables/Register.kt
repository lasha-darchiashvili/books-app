package com.example.spotify.composables

import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.spotify.navigation.Login
import com.example.spotify.navigation.Register
import com.example.spotify.viewmodels.UsersDatabaseViewModel
import com.toxicbakery.bcrypt.Bcrypt

@Composable
fun Register(navController: NavController) {
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val password = remember { TextFieldState() }
    val confirmPassword = remember { TextFieldState() }
    var showPassword by remember { mutableStateOf(false) }
    var showConfirmPassword by remember { mutableStateOf(false) }
    var isError by remember { mutableStateOf(false) }
    var errorText by remember { mutableStateOf("") }


    val context = LocalContext.current
    context.deleteDatabase("users_database")

    val viewModel: UsersDatabaseViewModel = hiltViewModel()
    val viewState by viewModel.viewState.collectAsState()
    val users = viewState.allUsers

    println(users)

    println(users)
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Header(navController = navController, title = "Register")
        Text(text = "Please fill your details to signup.", fontSize = 16.sp,
            modifier = Modifier.fillMaxWidth(0.88f).padding(top = 72.dp))

        Box{
                Text(
                    text = "Enter your name",
                )

            BasicTextField(
                value = userName,
                onValueChange = {
                    userName = it
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

        }

        Box {

                Text(
                    text = "Enter your email",
                )

            BasicTextField(
                value = email,
                onValueChange = {
                    email = it
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
        }


        Box {

            Text(
                text = "Password",
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
                    .padding(top = 32.dp)
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
        }

        Box {

            Text(
                text = "Confirm password",
            )

            BasicSecureTextField(
                state = confirmPassword,
                textObfuscationMode =
                    if (showConfirmPassword) {
                        TextObfuscationMode.Visible
                    } else {
                        TextObfuscationMode.RevealLastTyped
                    },
                modifier = Modifier
                    .padding(top = 32.dp)
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
                            if (showConfirmPassword) {
                                Icons.Filled.Visibility
                            } else {
                                Icons.Filled.VisibilityOff
                            },
                            contentDescription = "Toggle password visibility",
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .requiredSize(54.dp).padding(16.dp)
                                .clickable { showConfirmPassword = !showConfirmPassword }
                        )
                    }
                }
            )
        }

        Button(onClick = {
            val userNameExists = viewState.allUsers.map { it.userName }.contains(userName)
            val passwordMatch = password.text.toString() == confirmPassword.text.toString()
            if(userNameExists) {
                isError = true
                errorText = "User already exists"
            }
            if(!userNameExists && !passwordMatch) {
                isError = true
                errorText = "passwords does not match"
            }
            if(!viewState.allUsers.map { it.userName }.contains(userName) && password.text.toString() == confirmPassword.text.toString()) {
                isError = false
                viewModel.insertIntoDb(userName = userName, email = email, password = String(Bcrypt.hash(password.text.toString(), 12), Charsets.UTF_8))
            }
        }, modifier = Modifier.fillMaxWidth(0.88f).padding(top = 32.dp).clip(RoundedCornerShape(5.dp))
            .background(Color(0xff000000)
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(0.dp),
            elevation = null) {
            Text(text = "Register", color = Color(0xffffffff))
        }

        Box(modifier = Modifier.fillMaxSize(), ) {
            Row(modifier = Modifier.fillMaxWidth().align(Alignment.BottomEnd).padding(bottom = 40.dp), horizontalArrangement = Arrangement.Center) {
                Text(text = "Already a member? ", fontSize = 14.sp)
                Text(text = "Signin", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.clickable{
                    navController.navigate(Login)
                })
            }
        }
        if(isError) {
            Toast.makeText(context, errorText , Toast.LENGTH_SHORT).show()
        }
    }

}

//@Preview
//@Composable
//fun RegisterPreview() {
//    Register(nav)
//}