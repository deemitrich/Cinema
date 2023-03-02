import java.util.*

val scan = Scanner(System.`in`)
var count = 0
const val fullPrice = 10
const val smallPrice = 8
const val placeSeatCinema = 60
var currentIncome = 0

fun showTheSeats(rows: Int, seat: Int, placeCinema: MutableList<MutableList<String>>) {
    println("Cinema:")
    print(" ")
    for (i in 1..seat) {
        print(" $i")
    }
    println()

    for (i in 0 until rows) {
        print("${i + 1}")
        for (j in 0 until seat) {
            print(" ${placeCinema[i][j]}")
        }
        println()
    }
    println()
}


fun checkPlace(a: Int, b: Int, placeCinema: MutableList<MutableList<String>>): Boolean {

    if (placeCinema[a - 1][b - 1] == "B") {
        println("\nThat ticket has already been purchased!\n")
        return false
    } else if (placeCinema[a - 1][b - 1] > placeCinema.toString()) {
        throw IndexOutOfBoundsException()

    } else return true
}


fun buyTicket(rows: Int, seat: Int, placeCinema: MutableList<MutableList<String>>) {
    try {

        println("Enter a row number:")
        val a = readln().toInt()
        println("Enter a seat number in that row:")
        val b = readln().toInt()

        val check = checkPlace(a, b, placeCinema)

        if (check) {
            val priceTicket = when {
                rows * seat <= placeSeatCinema -> {
                    fullPrice
                }
                rows / 2 >= a -> {
                    fullPrice
                }
                else -> smallPrice
            }
            currentIncome += priceTicket
            placeCinema[a - 1][b - 1] = "B"
            count++
            println("\nTicket price: $$priceTicket\n")
            println(
                """
1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit
""".trimIndent()
            )
        } else {
            buyTicket(rows, seat, placeCinema)
        }
    } catch (e: IndexOutOfBoundsException) {
        println("\nWrong input!")
        buyTicket(rows, seat, placeCinema)
    }
}


fun sumTicket(rows: Int, seat: Int): Int {

    val factSeats = rows * seat
    return if (factSeats <= placeSeatCinema) {
        factSeats * fullPrice
    } else {
        (rows / 2 * seat) * fullPrice + ((rows - rows / 2) * seat) * smallPrice
    }
}

fun statistics(rows: Int, seat: Int) {
    println("Number of purchased tickets: $count")

    try {
        if (count == 0) {
            throw ArithmeticException()
        }
        val percent = count * 100.0 / (rows * seat)

        println("Percentage: ${"%.2f".format(percent)}%")

    } catch (e: java.lang.ArithmeticException) {
        println("Percentage: 0.00%")
    }

    try {
        if (count == 0) {
            throw ArithmeticException()
        }
        println("Current income: $$currentIncome")
    } catch (e: java.lang.ArithmeticException) {
        println("Current income: $0")
    }

    println("Total income: $${sumTicket(rows, seat)}")
    println(
        "\n" + """
1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit""".trimIndent()
    )
}

fun main() {
    println("Enter the number of rows:")
    val rows = scan.nextInt()
    println("Enter the number of seats in each row:")
    val seat = scan.nextInt()

    val placeCinema = MutableList(rows) {
        MutableList(seat) { "S" }
    }
    println(
        "\n" + """
1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit""".trimIndent()
    )

    do {
        val customerPerson = scan.nextInt()
        when (customerPerson) {
            1 -> {
                println()
                showTheSeats(rows, seat, placeCinema)
                println(
                    """
1. Show the seats
2. Buy a ticket
3. Statistics
0. Exit""".trimIndent()
                )
            }
            2 -> {
                buyTicket(rows, seat, placeCinema)
            }
            3 -> statistics(rows, seat)
            0 -> break
            else -> break
        }
    } while (customerPerson > 0)
}
