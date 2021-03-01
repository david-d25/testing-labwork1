package main.kotlin.domain

open class Creature(var name: String): CanSmile {
    override fun smile(durationMs: Long) {
        println("$name начал улыбаться...")
        try {
            Thread.sleep(durationMs)
            println("$name закончил улыбаться")
        } catch (ex: InterruptedException) {
            println("Что-то прервало улыбку")
        }
    }
}