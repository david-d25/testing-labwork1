package space.davids_digital.domain

import main.kotlin.domain.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DomainTest {
    @Test
    fun `initial values test`() {
        val vogon = Vogon("Простатник Джельц")
        assertFalse(vogon.rested)
        assertFalse(vogon.readyForVileness)
        val captive = Captive("Пленник")
        assertEquals(Captive.State.CALM, captive.state)
    }

    @Test
    fun `standard scenario test`() {
        val vogon = Vogon("Простатник Джельц")
        val captive1 = Captive("Пленник 1")
        val captive2 = Captive("Пленник 2")
        vogon.yellAt(captive1)
        assertEquals(Captive.State.SCARED, captive1.state)
        assertEquals(Captive.State.CALM, captive2.state)
        assertTrue(vogon.rested)
        assertTrue(vogon.readyForVileness)
        vogon.yellAt(captive2)
        assertEquals(Captive.State.SCARED, captive1.state)
        assertEquals(Captive.State.SCARED, captive2.state)
        assertTrue(vogon.rested)
        assertTrue(vogon.readyForVileness)
        vogon.trySmile(0)
    }

    @Test
    fun `smiling without being ready for vileness throws exception`() {
        val vogon = Vogon("Простатник Джельц")
        assertThrows(SmilingException::class.java) { vogon.trySmile(0) }
    }

    @Test
    fun `smiling with negative argument throws exception`() {
        val vogon = Vogon("Простатник Джельц")
        assertThrows(IllegalArgumentException::class.java) { vogon.trySmile(-1) }
    }

    @Test
    fun `smiling with positive argument`() {
        val vogon = Vogon("Простатник Джельц")
        val captive1 = Captive("Пленник 1")
        vogon.yellAt(captive1)
        vogon.trySmile(100)
    }

    @Test
    fun `interrupting creature smiling throws exception`() {
        val thisThread = Thread.currentThread()
        val thread = Thread { thisThread.interrupt() }
        val creature = Creature("Любое существо")
        thread.start()
        assertThrows(InterruptedSmilingException::class.java) { creature.trySmile(1000) }
        thread.join()
    }

    @Test
    fun `interrupting vogon smiling throws exception`() {
        val thisThread = Thread.currentThread()
        val thread = Thread { thisThread.interrupt() }
        val vogon = Vogon("Спидвагон")
        vogon.yellAt(Captive("Пленник Спидвагона"))
        thread.start()
        assertThrows(InterruptedSmilingException::class.java) { vogon.trySmile(1000) }
        thread.join()
    }

    @Test
    fun `creature smiles without exceptions when not interrupted`() {
        val creature = Creature("Любое существо")
        assertDoesNotThrow { creature.trySmile(1000) }
    }

    @Test
    fun `captive becomes scared if yelled at`() {
        val captive = Captive("АаАаАаАаАа")
        assertEquals(Captive.State.CALM, captive.state)
        captive.onYelledAt()
        assertEquals(Captive.State.SCARED, captive.state)
    }
}