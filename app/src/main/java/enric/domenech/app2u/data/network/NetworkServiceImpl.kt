package enric.domenech.app2u.data.network

import enric.domenech.app2u.domain.models.Response
import enric.domenech.app2u.domain.models.Result
import enric.domenech.app2u.domain.repositories.NetworkService
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.client.statement.request
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class NetworkServiceImpl(
    private val client: HttpClient,
    private val json: Json
) : NetworkService {

    override suspend fun fetchDataFromServer(): List<Result> {
        return try {
            withContext(Dispatchers.IO) {
                val response: HttpResponse = client.get("/api/photographer/")
                println("${response.request.method.value} ${response.request.url}")

                if (response.status.value !in 200..299) {
                    println("Error: HTTP ${response.status}")
                    return@withContext emptyList()
                }

                val responseData: Response = json.decodeFromString(response.bodyAsText())

//                println("Data: $responseData")
                responseData.results
            }
        } catch (e: Exception) {
            println("Error fetching first page: ${e.message}")
            emptyList()
        }
    }

    override suspend fun fetchNextPage(page: Int): List<Result> {
        TODO("Not yet implemented")
    }

}