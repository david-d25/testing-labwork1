package main.kotlin.domain

fun main() {
    val vogon = Vogon("Простатник Джельц")
    vogon.captives.add(Captive("Пленник 1"))
    vogon.captives.add(Captive("Пленник 2"))
    vogon.captives.add(Captive("Пленник 3"))
    vogon.yellAtCaptives()
}

