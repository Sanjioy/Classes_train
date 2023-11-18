import kotlin.random.Random

// Города для создания направлений
val cities = listOf("Санкт-Петербург", "Москва", "Краснодар", "Уфа", "Тюмень", "Владимир",
    "Казань", "Екатеринбург", "Мурманск", "Сочи", "Рязань", "Пермь", "Тверь", "Псков", "Калуга")

// Переменные для хранения текущего направления, количества пассажиров и поездов
var currentDirection: Pair<String, String>? = null
var passengersCount: Int = 0
var train: MutableList<Wagon> = mutableListOf()

// Функция создания направления
fun createDirection() {
    val startCity = cities.random()
    var endCity = cities.random()

    while (endCity == startCity) {
        endCity = cities.random()
    }

    currentDirection = Pair(startCity, endCity)
    println("Создано направление: ${currentDirection?.first} - ${currentDirection?.second}")
}

// Функция продажи билетов
fun sellTickets() {
    passengersCount = Random.nextInt(5, 202)
    println("Продано билетов: $passengersCount")
}

// Функция создания поезда
fun createTrain() {
    train.clear()
    var remainingPassengers = passengersCount

    while (remainingPassengers > 0) {
        val capacity = Random.nextInt(5, 26)
        val passengersInWagon = minOf(capacity, remainingPassengers)
        train.add(Wagon(capacity, passengersInWagon))
        remainingPassengers -= passengersInWagon
    }

    println("Сформирован поезд:")
    for ((index, wagon) in train.withIndex()) {
        println("Вагон $index: Вместимость - ${wagon.capacity}, Пассажиров - ${wagon.passengers}")
    }
}

// Функция отправки поезда
fun sendTrain() {
    if (currentDirection != null && train.isNotEmpty()) {
        println("Поезд ${currentDirection?.first} - ${currentDirection?.second}, состоящий из ${train.size} вагонов, отправлен.")
        for ((index, wagon) in train.withIndex()) {
            println("Вагон $index: Вместимость - ${wagon.capacity}, Пассажиров - ${wagon.passengers}")
        }
    } else {
        println("Направление или поезд не созданы.")
    }
}

// Класс для представления вагона
data class Wagon(val capacity: Int, val passengers: Int)

fun main() {
    var exitFlag = false

    while (!exitFlag) {
        println("Выберите действие:")
        println("1. Создать направление")
        println("2. Продать билеты")
        println("3. Сформировать поезд")
        println("4. Отправить поезд")
        println("EXIT. Завершить программу")

        val userInput = readLine()

        when (userInput?.toUpperCase()) {
            "1" -> createDirection()
            "2" -> sellTickets()
            "3" -> createTrain()
            "4" -> sendTrain()
            "EXIT" -> exitFlag = true
            else -> println("Некорректный ввод. Повторите попытку.")
        }
    }
}
