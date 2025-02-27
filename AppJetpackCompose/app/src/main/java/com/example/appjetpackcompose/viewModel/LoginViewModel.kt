import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel() {
    private val _username = MutableStateFlow("")
    val username: StateFlow<String> = _username.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    fun updateUsername(name: String) {
        _username.value = name
    }

    fun updatePassword(pass: String) {
        _password.value = pass
    }

    fun login(): Boolean {
        // In a real app, this would validate credentials
        return _username.value.isNotEmpty() && _password.value.isNotEmpty()
    }
}