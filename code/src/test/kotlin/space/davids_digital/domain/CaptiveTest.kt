package space.davids_digital.domain

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CaptiveTest {
    private lateinit var captive: Captive

    @BeforeEach
    fun createCaptive() {
        captive = Captive("Пленник")
    }

    @Test
    fun `captive initial values`() {
        assertEquals(Captive.State.CALM, captive.state)
    }

    @Test
    fun `captive becomes scared if yelled at`() {
        assertEquals(Captive.State.CALM, captive.state)
        captive.onYelledAt()
        assertEquals(Captive.State.SCARED, captive.state)
    }
}