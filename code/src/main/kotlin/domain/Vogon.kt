package main.kotlin.domain

// https://thehitchhikersguidetothegalaxy.fandom.com/ru/wiki/%D0%9F%D1%80%D0%BE%D1%81%D1%82%D0%B0%D1%82%D0%BD%D0%B8%D0%BA_%D0%94%D0%B6%D0%B5%D0%BB%D1%8C%D1%86
open class Vogon(
    name: String,
    var captives: MutableCollection<Captive> = mutableSetOf(),
): Creature(name) {
    var rested = false
        set(v) {
            field = v
            if (v)
                println("$name теперь чувствует себя отдохнувшим")
            else
                println("$name больше не чувствует себя отдохнувшим")
        }

    var readyForVileness = false
        set(v) {
            field = v
            if (v)
                println("$name теперь готов к гнусности")
            else
                println("$name больше не готов к гнусности")
        }

    override fun smile(durationMs: Long) {
        println("$name пытается вспомнить, как улыбаться")
        try {
            Thread.sleep(durationMs)
            println("$name вспомнил, как улыбаться")
            super.smile(durationMs)
        } catch (ex: InterruptedException) {
            println("Что-то прервало размышления и $name не вспомнил, как улыбаться")
        }
    }

    fun yellAtCaptives() {
        captives.forEach {
            println("$name накричал на пленника '${it.name}'")
            Thread.sleep(250)
        }
        rested = true
        readyForVileness = true
        smile(2000)
    }
}