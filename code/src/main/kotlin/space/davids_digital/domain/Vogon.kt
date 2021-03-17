package space.davids_digital.domain

// https://thehitchhikersguidetothegalaxy.fandom.com/ru/wiki/%D0%9F%D1%80%D0%BE%D1%81%D1%82%D0%B0%D1%82%D0%BD%D0%B8%D0%BA_%D0%94%D0%B6%D0%B5%D0%BB%D1%8C%D1%86
open class Vogon(
    name: String
): Creature(name) {
    companion object {
        const val SLOW_SMILE_THRESHOLD_MS = 500
    }

    var rested = false
        set(v) {
            if (field != v) {
                field = v
                if (v)
                    println("$name теперь чувствует себя отдохнувшим")
                else
                    println("$name больше не чувствует себя отдохнувшим")
            }
        }

    var readyForVileness = false
        set(v) {
            if (field != v) {
                field = v
                if (v)
                    println("$name теперь готов к гнусности")
                else
                    println("$name больше не готов к гнусности")
            }
        }


    override fun trySmile(durationMs: Long) {
        if (durationMs < 0)
            throw IllegalArgumentException()
        if (readyForVileness) {
            if (durationMs >= SLOW_SMILE_THRESHOLD_MS)
                println("$name пытается вспомнить, как улыбаться")
            try {
                Thread.sleep(durationMs)
                if (durationMs >= SLOW_SMILE_THRESHOLD_MS)
                    println("$name вспомнил, как улыбаться")
                super.trySmile(durationMs)
            } catch (ex: Exception) {
                when (ex) {
                    is InterruptedSmilingException, is InterruptedException -> {
                        println("Что-то прервало размышления и $name не вспомнил, как улыбаться")
                        throw InterruptedSmilingException()
                    }
                }
            }
        } else {
            println("$name не улыбнулся, потому что не готов к гнусности")
            throw SmilingException()
        }
    }

    fun yellAt(captive: Captive) {
        println("$name накричал на пленника '${captive.name}'")
        captive.onYelledAt()
        Thread.sleep(250)
        rested = true
        readyForVileness = true
    }
}