import main.kotlin.ScrapMath.Companion.scrapAtan
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Timeout
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException
import java.lang.Math.PI
import java.util.concurrent.TimeUnit.SECONDS

class ScrapMathTest {
    companion object {
        private const val DELTA = 0.0000001
    }

    @Test
    @Timeout(value = 2, unit = SECONDS)
    fun `x = 1 and delta = 10e-8 returns in less than 2s`() {
        assertEquals(PI/4, scrapAtan(1.0, 10e-8), 10e-8)
    }

    @Test
    @Timeout(value = 1, unit = SECONDS)
    fun `delta less than 10e-8 throws exception`() {
        assertThrows<IllegalArgumentException> { scrapAtan(0.0, 10e-9) }
    }

    @Test
    fun `x = NaN returns NaN`() {
        assertEquals(Double.NaN, scrapAtan(Double.NaN, DELTA), DELTA)
    }

    @Test
    fun `delta = NaN throws exception`() {
        assertThrows<IllegalArgumentException> { scrapAtan(0.0, Double.NaN) }
    }

    @Test
    fun `delta = 0 throws exception`() {
        assertThrows<IllegalArgumentException> { scrapAtan(0.0, 0.0) }
    }

    @Test
    fun `x = 0`() {
        assertEquals(0.0, scrapAtan(0.0, DELTA), DELTA)
    }

    @Test
    fun `x = 1`() {
        assertEquals(PI /4, scrapAtan(1.0, DELTA), DELTA)
    }

    @Test
    fun `x = -1`() {
        assertEquals(-PI /4, scrapAtan(-1.0, DELTA), DELTA)
    }
    
    @Test
    fun `x = +INF`() {
        assertEquals(PI /2, scrapAtan(Double.POSITIVE_INFINITY, DELTA), DELTA)
    }

    @Test
    fun `x = -INF`() {
        assertEquals(-PI /2, scrapAtan(Double.NEGATIVE_INFINITY, DELTA), DELTA)
    }

    @Test
    fun `x between 0 and 1`() {
        assertEquals(0.463647609, scrapAtan(0.5, DELTA), DELTA)
    }

    @Test
    fun `x between -1 and 0`() {
        assertEquals(-0.463647609, scrapAtan(-0.5, DELTA), DELTA)
    }

    @Test
    fun `x greater than 1`() {
        assertEquals(0.982793723, scrapAtan(1.5, DELTA), DELTA)
    }

    @Test
    fun `x less than -1`() {
        assertEquals(-0.982793723, scrapAtan(-1.5, DELTA), DELTA)
    }
}