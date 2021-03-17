package space.davids_digital.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock

class VogonTest {
    private lateinit var vogon: Vogon

    @BeforeEach
    fun createVogon() {
        vogon = Vogon("Простатник Джельц")
    }

    @Test
    fun `vogon initial values test`() {
        assertFalse(vogon.rested)
        assertFalse(vogon.readyForVileness)
    }

    @Test
    fun `standard scenario test`() {
        vogon.yellAt(mock(Captive::class.java))
        assertTrue(vogon.rested)
        assertTrue(vogon.readyForVileness)
        vogon.yellAt(mock(Captive::class.java))
        assertTrue(vogon.rested)
        assertTrue(vogon.readyForVileness)
        vogon.trySmile(0)
    }

    @Test
    fun `smiling without being ready for vileness throws exception`() {
        assertThrows(SmilingException::class.java) { vogon.trySmile(0) }
    }

    @Test
    fun `vogon smiling with negative argument throws exception`() {
        assertThrows(IllegalArgumentException::class.java) { vogon.trySmile(-1) }
    }

    @Test
    fun `smiling with positive argument`() {
        vogon.yellAt(mock(Captive::class.java))
        vogon.trySmile(100)
    }

    @Test
    fun `vogon can smile slowly`() {
        vogon.yellAt(mock(Captive::class.java))
        vogon.trySmile(Vogon.SLOW_SMILE_THRESHOLD_MS.toLong())
    }

    @Test
    fun `interrupting vogon smiling throws exception`() {
        val thisThread = Thread.currentThread()
        val thread = Thread { Thread.sleep(200); thisThread.interrupt() }
        vogon.yellAt(mock(Captive::class.java))
        thread.start()
        assertThrows(InterruptedSmilingException::class.java) { vogon.trySmile(1000) }
        thread.join()
    }
}