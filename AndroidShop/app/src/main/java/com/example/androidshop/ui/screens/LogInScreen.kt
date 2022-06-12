package com.example.androidshop.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.androidshop.db.viewmodels.UserEntityViewModel
import com.example.androidshop.models.cutomers.UserVM
import com.example.androidshop.viewmoels.costomers.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LogInScreen(
    navController: NavController,
    userEntityViewModel: UserEntityViewModel,
    userViewModel: UserViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var username by remember { mutableStateOf(TextFieldValue()) }
    var usernameError by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf(TextFieldValue()) }
    var passwordError by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    Column(
        Modifier
            .padding(8.dp, 0.dp)
            .fillMaxSize()
    ) {
        Row {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
            }
            Spacer(modifier = Modifier.width(10.dp))
        }

        Spacer(modifier = Modifier.height(20.dp))

        Column {
            Text(text = "let's sing you in.", fontSize = 28.sp)
            Text(text = "Welcome back ...", fontSize = 24.sp)
            Text(text = "You've been missed!", fontSize = 20.sp)

            Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = username,
                onValueChange = {
                    username = it
                    usernameError = false
                },
                label = { Text(text = "Username") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                trailingIcon = {
                    if (usernameError) {
                        Icon(
                            imageVector = Icons.Filled.Warning,
                            contentDescription = "error",
                            tint = Color.Red
                        )
                    }
                },
                isError = usernameError

            )
            if (usernameError) {
                Text(text = "Please enter usrname", color = Color.Red)
            }
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = false
                },
                label = { Text(text = "Password") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    if (passwordError) {
                        Icon(
                            imageVector = Icons.Filled.Warning,
                            contentDescription = "error",
                            tint = Color.Red
                        )
                    }
                },
                isError = passwordError

            )
            if (passwordError) {
                Text(text = "Please enter password", color = Color.Red)
            }
            Spacer(modifier = Modifier.height(20.dp))
            if (!isLoading) {
                Button(
                    onClick = {
                        if (username.text.isEmpty()) {
                            usernameError = true
                        }
                        if (password.text.isEmpty()) {
                            passwordError = true
                        }
                        if (usernameError || passwordError) return@Button
                        isLoading = true
                        userViewModel.loginUser(
                            UserVM(
                                username = username.text,
                                password = password.text
                            )
                        ) { response ->
                            if (response.status == "OK") {
                                val user = response.data!![0]
                                CoroutineScope(Dispatchers.IO).launch {
                                    userEntityViewModel.insert(user.converToEntity())
                                }
                                Toast.makeText(
                                    context,
                                    "Welcome back dear ${user.firstName}",
                                    Toast.LENGTH_LONG
                                ).show()
                                isLoading = false
                                navController.navigate("home")
                            }else{
                                Toast.makeText(
                                    context,
                                    "User not found!",
                                    Toast.LENGTH_LONG
                                ).show()
                                isLoading = false
                            }
                        }
                    },
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                ) {
                    Text(
                        text = "Login",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            if (isLoading) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
            }
        }

    }
}