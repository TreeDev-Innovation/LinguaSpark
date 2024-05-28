package country.domain.model

data class Country(
    val id: String,
    val name: String,
    val nativeName: String,
    val icon: String? = null,
)