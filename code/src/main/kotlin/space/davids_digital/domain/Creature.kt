package space.davids_digital.domain

open class Creature(var name: String): CanSmile {
    override fun trySmile(durationMs: Long) {
        if (durationMs < 0)
            throw IllegalArgumentException()
        println("$name начал улыбаться...")
        try {
            Thread.sleep(durationMs)
            println("$name закончил улыбаться")
        } catch (ex: InterruptedException) {
            println("Что-то прервало улыбку")
            throw InterruptedSmilingException()
        }
    }
}