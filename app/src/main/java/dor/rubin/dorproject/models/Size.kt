package dor.rubin.dorproject.models

data class Size(val size: String)

class SizeRepository {
    companion object {
        fun getShoesSize(): List<Size> {
            return listOf(
                Size("35"), Size("36"), Size("37"), Size("38"),
                Size("39"), Size("40"), Size("41"), Size("42"), Size("43"),
                Size("44"), Size("45")
            )

        }
        fun getClothesSize(): List<Size> {
            return listOf(
              Size("XS") ,Size("S"), Size("M"), Size("L"),
                Size("XL"), Size("XXL")
            )

        }
    }

}