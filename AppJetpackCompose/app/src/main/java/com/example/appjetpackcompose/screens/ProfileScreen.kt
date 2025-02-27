package com.example.appjetpackcompose.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appjetpackcompose.viewModel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController, viewModel: UserViewModel = viewModel()) {
    val user by viewModel.user.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Profile", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color(0xFF059372)),
                actions = {
                    IconButton(onClick = { /* Settings */ }) {
                        Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings", tint = Color.White)
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF20252B),
                contentColor = Color.White
            ) {
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("home") },
                    icon = { Icon(painter = painterResource(id = android.R.drawable.ic_menu_compass), contentDescription = "Home") },
                    label = { Text("Home") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("orders") },
                    icon = { Icon(painter = painterResource(id = android.R.drawable.ic_menu_recent_history), contentDescription = "Orders") },
                    label = { Text("Orders") }
                )
                NavigationBarItem(
                    selected = true,
                    onClick = { /* Already on profile */ },
                    icon = { Icon(painter = painterResource(id = android.R.drawable.ic_menu_myplaces), contentDescription = "Account") },
                    label = { Text("Account") }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
        ) {
            // Profile header section
            Column(
                modifier = Modifier.fillMaxWidth().background(Color(0xFF059372)).padding(bottom = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Profile image
                Box(
                    modifier = Modifier.padding(top = 16.dp).size(80.dp).clip(RoundedCornerShape(12.dp)).background(Color.White)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(text = user.name, fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color.White)
                Text(text = user.location, fontSize = 14.sp, color = Color.White)
            }

            Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                ProfileOption(title = "Favorite", value = "${user.favorites}", showDivider = true)
                ProfileOption(title = "Language", value = user.language, showDivider = true)
                ProfileOption(title = "My Rates", value = "", showDivider = true)
                ProfileOption(title = "Contact us", value = "", showDivider = true)
                ProfileOption(title = "Share App", value = "", showDivider = false)

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        navController.navigate("login") { popUpTo(0) { inclusive = true } }
                    },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color(0xFFE74C3C)),
                    elevation = ButtonDefaults.buttonElevation(0.dp)
                ) {
                    Icon(painter = painterResource(id = android.R.drawable.ic_lock_power_off), contentDescription = "Sign Out", tint = Color(0xFFE74C3C))
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "SIGN OUT", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun ProfileOption(title: String, value: String, showDivider: Boolean) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (value.isNotEmpty()) {
                    Text(text = value, fontSize = 16.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Icon(painter = painterResource(id = android.R.drawable.ic_media_play), contentDescription = "Arrow", modifier = Modifier.size(16.dp), tint = Color.Gray)
            }
        }
        if (showDivider) {
            Divider(color = Color.LightGray)
        }
    }
}
