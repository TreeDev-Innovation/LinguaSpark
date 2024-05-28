@file:OptIn(ExperimentalSerializationApi::class)

package country.data.model

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames

@Serializable
data class LanguageDto(
    @JsonNames("name")
    val name: String,
    @JsonNames("nativeName")
    val nativeName: String
)
