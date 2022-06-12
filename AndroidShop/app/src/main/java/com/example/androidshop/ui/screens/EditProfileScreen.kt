package com.example.androidshop.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.navigation.NavHostController
import com.example.androidshop.db.viewmodels.UserEntityViewModel
import com.example.androidshop.models.cutomers.UserVM
import com.example.androidshop.models.invoices.InvoiceItems
import com.example.androidshop.models.invoices.PaymentTransction
import com.example.androidshop.utils.ThisApp
import com.example.androidshop.viewmoels.costomers.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun EditProfileScreen(
    navController: NavHostController,
    userEntityViewModel: UserEntityViewModel,
    userViewModel: UserViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val currentUser by remember { mutableStateOf(userEntityViewModel.currentUser) }
    val isLoagedIn by remember { mutableStateOf(userEntityViewModel.currentUser.value != null) }
    var firstName by remember { mutableStateOf(TextFieldValue(if (isLoagedIn) currentUser.value!!.firstName!! else "")) }
    var firstNameError by remember { mutableStateOf(false) }
    var lastName by remember { mutableStateOf(TextFieldValue(if (isLoagedIn) currentUser.value!!.lastName!! else "")) }
    var lastNameError by remember { mutableStateOf(false) }
    var username by remember { mutableStateOf(TextFieldValue(if (isLoagedIn) currentUser.value!!.username!! else "")) }
    var usernameError by remember { mutableStateOf(false) }
    var address by remember { mutableStateOf(TextFieldValue(if (isLoagedIn) currentUser.value!!.phone!! else "")) }
    var addressError by remember { mutableStateOf(false) }
    var phone by remember { mutableStateOf(TextFieldValue(if (isLoagedIn) currentUser.value!!.address!! else "")) }
    var phoneError by remember { mutableStateOf(false) }
    var postalCode by remember { mutableStateOf(TextFieldValue(if (isLoagedIn) currentUser.value!!.postalCode!! else "")) }
    var postalCodeError by remember { mutableStateOf(false) }

    var isLoading by remember { mutableStateOf(false) }

    Row {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = "Edit Information",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
        )
    }

    Column {
        Spacer(modifier = Modifier.height(50.dp))
        LazyColumn(modifier = Modifier.padding(8.dp)) {
            item {
                OutlinedTextField(
                    value = firstName,
                    onValueChange = {
                        firstName = it
                        firstNameError = false
                    },
                    label = { Text(text = "First Name") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    trailingIcon = {
                        if (firstNameError) {
                            Icon(
                                imageVector = Icons.Filled.Warning,
                                contentDescription = "error",
                                tint = Color.Red
                            )
                        }
                    },
                    isError = firstNameError

                )
                if (firstNameError) {
                    Text(text = "Please enter firstname", color = Color.Red)
                }
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = lastName,
                    onValueChange = {
                        lastName = it
                        lastNameError = false
                    },
                    label = { Text(text = "Last Name") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    trailingIcon = {
                        if (lastNameError) {
                            Icon(
                                imageVector = Icons.Filled.Warning,
                                contentDescription = "error",
                                tint = Color.Red
                            )
                        }
                    },
                    isError = lastNameError

                )
                if (lastNameError) {
                    Text(text = "Please enter lastName", color = Color.Red)
                }
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = username,
                    onValueChange = {
                        username = it
                        usernameError = false
                    },
                    enabled = currentUser.value == null ,
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
                    value = address,
                    onValueChange = {
                        address = it
                        addressError = false
                    },
                    label = { Text(text = "Address") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    trailingIcon = {
                        if (addressError) {
                            Icon(
                                imageVector = Icons.Filled.Warning,
                                contentDescription = "error",
                                tint = Color.Red
                            )
                        }
                    },
                    isError = addressError

                )
                if (addressError) {
                    Text(text = "Please enter address", color = Color.Red)
                }
                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = phone,
                    onValueChange = {
                        phone = it
                        phoneError = false
                    },
                    label = { Text(text = "Phone") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    ),
                    trailingIcon = {
                        if (phoneError) {
                            Icon(
                                imageVector = Icons.Filled.Warning,
                                contentDescription = "error",
                                tint = Color.Red
                            )
                        }
                    },
                    isError = phoneError

                )
                if (phoneError) {
                    Text(text = "Please enter phone", color = Color.Red)
                }
                Spacer(modifier = Modifier.height(10.dp))


                OutlinedTextField(
                    value = postalCode,
                    onValueChange = {
                        postalCode = it
                        postalCodeError = false
                    },
                    label = { Text(text = "Postal Code") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text
                    ),
                    trailingIcon = {
                        if (postalCodeError) {
                            Icon(
                                imageVector = Icons.Filled.Warning,
                                contentDescription = "error",
                                tint = Color.Red
                            )
                        }
                    },
                    isError = postalCodeError

                )
                if (postalCodeError) {
                    Text(text = "Please enter postal code", color = Color.Red)
                }
                Spacer(modifier = Modifier.height(10.dp))
                if (!isLoading) {
                    Column(Modifier.padding(8.dp)) {
                        Button(
                            onClick = {
                                if (firstName.text.isEmpty()) {
                                    firstNameError = true
                                }
                                if (lastName.text.isEmpty()) {
                                    lastNameError = true
                                }
                                if (username.text.isEmpty()) {
                                    usernameError = true
                                }
                                if (address.text.isEmpty()) {
                                    addressError = true
                                }
                                if (phone.text.isEmpty()) {
                                    phoneError = true
                                }
                                if (postalCode.text.isEmpty()) {
                                    postalCodeError = true
                                }
                                if (firstNameError || lastNameError || usernameError || addressError || phoneError || postalCodeError) {
                                    return@Button
                                }

                                var userInfo = UserVM(
                                    id = currentUser.value!!.userId,
                                    customerId = currentUser.value!!.customerId,
                                    username = username.text,
                                    firstName = firstName.text,
                                    lastName = lastName.text,
                                    phone = phone.text,
                                    address = address.text,
                                    postalCode = postalCode.text,
                                )
                                isLoading = true
                                //ThisApp.token = currentUser.value!!.token!!
                                userViewModel.update(userInfo) { response ->
                                    if (response.status == "OK") {
                                        CoroutineScope(Dispatchers.IO).launch {
                                            val userEntity = userInfo.converToEntity()
                                            userEntity.id = currentUser.value!!.id
                                            userEntity.token = currentUser.value!!.token!!
                                            userEntityViewModel.update(userEntity)
                                        }
                                        navController.popBackStack()
                                    }
                                    isLoading = false
                                }
                            },
                            shape = RoundedCornerShape(20.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                        ) {
                            Text(
                                text = "Update Info",
                                color = Color.White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                } else {
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
}