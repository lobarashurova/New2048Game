package uz.mlsoft.new2048game.utils

import uz.mlsoft.new2048game.R

class MyBackgroundGenerator {
    private val map = hashMapOf(
        0 to R.drawable.back_0,
        2 to R.drawable.back_2,
        4 to R.drawable.back_4,
        8 to R.drawable.back_8,
        16 to R.drawable.back_16,
        32 to R.drawable.back_32,
        64 to R.drawable.back_64,
        128 to R.drawable.back_128,
        256 to R.drawable.back_256,
        512 to R.drawable.back_512,
        1024 to R.drawable.back_1024,
        2048 to R.drawable.back_4096
    )

    fun backgroundByAmount(amount: Int) = map.getOrDefault(amount, R.drawable.back_2)
}