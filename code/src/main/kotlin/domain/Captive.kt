package main.kotlin.domain

import java.text.MessageFormat

open class Captive(name: String): Creature(name) {
    var state: State = State.CALM
        private set(v) {
            field = v
            println(MessageFormat.format(v.message, name))
        }

    fun onYelledAt() {
        state = State.SCARED
    }

    enum class State(val message: String) {
        CALM("Теперь {0} спокоен"),
        SCARED("Теперь {0} спуган");
    }
}