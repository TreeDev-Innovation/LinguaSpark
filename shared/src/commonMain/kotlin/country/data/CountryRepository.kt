package country.data

import country.countryNames

class CountryRepository {
    suspend fun getCountriesWithNames(): List<Country> {
        return countryNames.map {
            Country(
                id = it.key,
                name = it.value,
                ""
            )
        }
    }

}


data class Country(
    val id: String,
    val name: String,
    val icon: String,
)