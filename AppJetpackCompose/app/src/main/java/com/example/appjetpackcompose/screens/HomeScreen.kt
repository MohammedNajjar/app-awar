import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appjetpackcompose.data.network.model.Order
import com.example.appjetpackcompose.data.network.model.OrderStatus
import com.example.appjetpackcompose.viewModel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = viewModel<HomeViewModel>()) { // ✅ تصحيح الاستدعاء
    val orders by viewModel.orders.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Orders") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle back */ }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = true,
                    onClick = { /* Navigate to home */ },
                    icon = {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_menu_compass),
                            contentDescription = "Home"
                        )
                    },
                    label = { Text("Home") }
                )

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("orders") },
                    icon = {
                        Icon(
                            painter = painterResource(id = android.R.drawable.ic_menu_recent_history),
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
                            painter = painterResource(id = android.R.drawable.ic_menu_myplaces),
                            contentDescription = "Account"
                        )
                    },
                    label = { Text("Account") }
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            items(orders) { order ->
                OrderItem(
                    order = order,
                    onDeleteClick = { viewModel.deleteOrder(order) }
                )
            }
        }
    }
}

@Composable
fun OrderItem(order: Order, onDeleteClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = order.id,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )

            Button(
                onClick = onDeleteClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5252)),
                shape = RoundedCornerShape(16.dp),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "Remove",
                    color = Color.White,
                    fontSize = 12.sp
                )
            }
        }

        Text(
            text = order.date,
            color = Color.Gray,
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CircleImage(size = 24.dp, backgroundColor = Color(0xFF4CAF50))

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = order.restaurant,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.width(16.dp))

            CircleImage(size = 24.dp, backgroundColor = Color(0xFF2196F3))

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = order.customerName,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val statuses = listOf(
                OrderStatus.PENDING,
                OrderStatus.PREPARE,
                OrderStatus.PICKUP,
                OrderStatus.DELIVER
            )

            statuses.forEach { status ->
                val isActive = order.status.ordinal >= status.ordinal
                val statusColor = when {
                    isActive -> when (status) {
                        OrderStatus.PENDING -> Color(0xFF4CAF50)
                        OrderStatus.PREPARE -> Color(0xFF2196F3)
                        OrderStatus.PICKUP -> Color(0xFF4CAF50)
                        OrderStatus.DELIVER -> Color(0xFF9E9E9E)
                    }
                    else -> Color.LightGray
                }

                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(statusColor, CircleShape)
                        .padding(end = 4.dp)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = status.name.replaceFirstChar { it.uppercaseChar() },
                    fontSize = 12.sp,
                    color = if (isActive) Color.DarkGray else Color.LightGray
                )

                Spacer(modifier = Modifier.width(8.dp))
            }
        }

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            color = Color.LightGray
        )
    }
}

@Composable
fun CircleImage(size: Dp, backgroundColor: Color) {
    Box(
        modifier = Modifier
            .size(size)
            .background(backgroundColor, CircleShape),

        //contentAlignment = Alignment.Center

    )
}
