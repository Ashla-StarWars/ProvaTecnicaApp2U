package enric.domenech.app2u.domain.repositories

import androidx.compose.ui.graphics.ImageBitmap
import enric.domenech.app2u.domain.models.Result

interface NetworkService {
    suspend fun fetchDataFromServer() : List<Result>
    suspend fun fetchNextPage(page: Int) : List<Result>
    suspend fun fetchImage(imageUrl: String) : ByteArray
    suspend fun fetchImageAsBitmap(imageUrl: String): ImageBitmap?
}