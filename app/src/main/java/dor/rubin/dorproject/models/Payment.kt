package dor.rubin.dorproject.models

data class Payment(
    val fullName: String = "",
    val price: Double = 0.0,
    val cardNumber: String = "",
    val yearAndMonth: String = "",
    val cvv:String = ""

)
