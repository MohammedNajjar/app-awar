import com.example.appjetpackcompose.data.network.ApiService
import com.example.appjetpackcompose.data.network.model.Post

class OrderRepository private constructor() {
    private val apiService = ApiService.instance

    suspend fun getOrders(): List<Post> {
        return apiService.getPosts()
    }

    suspend fun getOrderById(id: Int): Post {
        return apiService.getPostById(id)
    }

    companion object {
        // Singleton pattern
        val instance: OrderRepository by lazy { OrderRepository() }
    }
}