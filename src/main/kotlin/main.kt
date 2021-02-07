package ru.netology

fun main() {
    transferMoney("vkPay", 10_000, 5_000)
    transferMoney("vkPay", 45_000, 50_000)
    transferMoney("MasterCard", 50_000, 25_000)
    transferMoney("Maestro", 50_000, 250)
    transferMoney("Mir", 500_000, 150_000)
    transferMoney("Visa", 550_000, 20_000)
}

fun transferMoney(card: String, transferredThisMonth: Int, amountToTransfer: Int) {
    val dayLimit: Int
    val monthLimit: Int
    var interestRatePerCent: Double
    var interestRateMin: Int
    when (card) {
        "MasterCard", "Maestro" -> {
            dayLimit = 150000
            monthLimit = 600000
            interestRatePerCent = 0.006
            interestRateMin = 20

            if ((amountToTransfer + transferredThisMonth > monthLimit) || (amountToTransfer > dayLimit)) {
                println("Превышен лимит перевода по карте $card")
            } else {
                if ((amountToTransfer >= 300) && (amountToTransfer <= 75000)) {
                    interestRatePerCent = 0.00
                    interestRateMin = 0
                    print("Дейтвует акция. ")
                }
            }
            calculateInterest(card, amountToTransfer, interestRatePerCent, interestRateMin)
        }

        "Visa", "Mir" -> {
            dayLimit = 150000
            monthLimit = 600000
            interestRatePerCent = 0.0075
            interestRateMin = 35

            if ((amountToTransfer + transferredThisMonth > monthLimit) || (amountToTransfer > dayLimit)) {
                println("Превышен лимит перевода по карте $card")
            } else {
                calculateInterest(card, amountToTransfer, interestRatePerCent, interestRateMin)
            }
        }

        "vkPay" -> {
            dayLimit = 15000
            monthLimit = 40000
            interestRatePerCent = 0.0
            interestRateMin = 0

            if ((amountToTransfer + transferredThisMonth > monthLimit) || (amountToTransfer > dayLimit)) {
                println("Превышен лимит перевода по карте $card")
            } else {
                calculateInterest(card, amountToTransfer, interestRatePerCent, interestRateMin)
            }
        }
    }
}

fun calculateInterest(card: String, amountToTransfer: Int, interestRatePerCent: Double, interestRateMin: Int) {
    val interest: Int = ((amountToTransfer * interestRatePerCent + interestRateMin) * 100).toInt()
    println("Выполнен перевод с карты $card в сумме $amountToTransfer руб. Комиссия составила $interest коп.")
}
