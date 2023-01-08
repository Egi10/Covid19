package id.buaja.covid19.domain.usecase.model

data class ProvinceCovid(
    val lastUpdate: String,
    val provinceCases: List<ProvinceCases>
)

data class ProvinceCases(
    val name: String,
    val totalPositiveCases: Int,
    val totalCasesRecovery: Int,
    val totalDeathCases: Int
)
