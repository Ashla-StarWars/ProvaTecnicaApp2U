package enric.domenech.app2u.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val avatar: String?,
    val description: String,
    val email: String,
    val facebook: String?,
    @SerialName("first_name") val firstName: String,
    val guid: String,
    val id: Int,
    val image: String,
    val instagram: String?,
    @SerialName("is_removed") val isRemoved: Boolean,
    @SerialName("last_name") val lastName: String,
    val webpage: String?
)