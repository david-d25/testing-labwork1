package space.davids_digital.domain

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CreatureTest {
    private lateinit var creature: Creature

    @BeforeEach
    fun createCreature() {
        creature = Creature("Существо")
    }

    @Test
    fun `creature smiling with negative argument throws exception`() {
        Assertions.assertThrows(IllegalArgumentException::class.java) { creature.trySmile(-1) }
    }

    @Test
    fun `interrupting creature smiling throws exception`() {
        val thisThread = Thread.currentThread()
        val thread = Thread { Thread.sleep(200); thisThread.interrupt() }
        thread.start()
        Assertions.assertThrows(InterruptedSmilingException::class.java) { creature.trySmile(1000) }
        thread.join()
    }

    @Test
    fun `creature smiles without exceptions when not interrupted`() {
        Assertions.assertDoesNotThrow { creature.trySmile(1000) }
    }
}