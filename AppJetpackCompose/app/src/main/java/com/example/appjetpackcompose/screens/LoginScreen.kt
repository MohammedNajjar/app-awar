import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel = viewModel()) {
    val username by viewModel.username.collectAsState()
    val password by viewModel.password.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Logo section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "AWAR",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2E8B57),
                textAlign = TextAlign.Center
            )
        }

        // Login form section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.7f)
                .background(
                    color = Color(0xFF333B42),
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                )
                .padding(horizontal = 24.dp, vertical = 32.dp)
        ) {
            Text(
                text = "New Account",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Full name field
            Text(
                text = "Full Name",
                color = Color.White,
                fontSize = 14.sp
            )

            OutlinedTextField(
                value = username,
                onValueChange = { viewModel.updateUsername(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color.White
                ),
                textStyle = TextStyle(color = Color.White),
                placeholder = { Text("Full Name", color = Color.Gray) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Password field
            Text(
                text = "Password",
                color = Color.White,
                fontSize = 14.sp
            )

            OutlinedTextField(
                value = password,
                onValueChange = { viewModel.updatePassword(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color.White
                ),
                textStyle = TextStyle(color = Color.White),
                visualTransformation = PasswordVisualTransformation(),
                placeholder = { Text("********", color = Color.Gray) }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "FORGOT PASSWORD?",
                    color = Color(0xFFE17945),
                    fontSize = 12.sp,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .clickable { /* Handle forgot password */ }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (viewModel.login()) {
                        navController.navigate("home") {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2E8B57)
                )
            ) {
                Text(
                    text = "LOGIN",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "New Member?",
                    color = Color.White,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "Sign up",
                    color = Color(0xFFE17945),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { /* Handle sign up */ }
                )
            }
        }
    }
}
