package enric.domenech.app2u.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Result>,
    val timestamp: String
)