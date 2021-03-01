package main.kotlin

import java.lang.IllegalArgumentException
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.sign

class ScrapMath {
    companion object {
        const val MIN_ERROR = 10e-8

        fun scrapAtan(x: Double, maxError: Double): Double {
            if (maxError.isNaN())
                throw IllegalArgumentException("maxError should be a number")

            if (abs(maxError) < MIN_ERROR)
                throw IllegalArgumentException("maxError should be <= $MIN_ERROR, actual value: $maxError")

            if (x.isNaN())
                return Double.NaN

            return atanDecomposed(x, getOptimalDecomposingLength(x, maxError))
        }

        private fun atanDecomposed(x: Double, repeats: Int): Double {
            return if (abs(x) > 1) {
                sign(x) * Math.PI/2 - atanDecomposed(1/x, repeats)
            } else {
                var result = 0.0
                for (n in 0..repeats) {
                    result += (-1.0).pow(n) * x.pow(2 * n + 1) / (2 * n + 1)
                }
                result
            }
        }

        private fun getOptimalDecomposingLength(x: Double, maxError: Double): Int {
            if (abs(x) > 1)
                return getOptimalDecomposingLength(1/x, maxError)

            var min = 0
            var max = Integer.MAX_VALUE
            var n = 0
            while (min < max) {
                n = min/2 + max/2
                val probe = x.pow(2 * n + 1) / (2 * n + 1)
                if (abs(probe) > abs(maxError))
                    min = n + 1
                else
                    max = n
            }
            return n
        }
    }
}