package com.example.appjetpackcompose.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.appjetpackcompose.viewModel.OrderViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDetailsScreen(navController: NavController, orderId: Int, viewModel: OrderViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val post by viewModel.selectedPost.collectAsState()

    LaunchedEffect(orderId) {
        viewModel.fetchPostDetails(orderId)
    }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Id #$orderId") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFF4CAF50),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
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
                    icon = {
                        Icon(
                            Icons.Default.ArrowBack, // استبدال الأيقونة بأخرى حديثة
                            contentDescription = "Home"
                        )
                    },
                    label = { Text("Home") }
                )

                NavigationBarItem(
                    selected = true,
                    onClick = { navController.navigate("orders") },
                    icon = {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Orders"
                        )
                    },
                    label = { Text("Orders") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("profile") },
                    icon = {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Account"
                        )
                    },
                    label = { Text("Account") }
                )
            }
        }
    ) { paddingValues ->
        post?.let { selectedPost ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Title: ${selectedPost.title}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Body:",
                    fontWeight = FontWeight.Bold,
                    color = Color.Red,
                    fontSize = 16.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = selectedPost.body,
                    fontSize = 14.sp
                )
            }
        } ?: Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}
