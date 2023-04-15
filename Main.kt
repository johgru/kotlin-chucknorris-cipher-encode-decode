package chucknorris

fun main() {
    while (true) {
        println("Please input operation (encode/decode/exit):")
        val input = readln()
        when {
            input.equals("encode", true) -> encode()
            input.equals("decode", true) -> decode()
            input.equals("exit", true) -> break
            input.equals("encode", true) -> encode()
            else -> println("There is no '$input' operation")
        }
        println()
    }
    println("Bye!")
}

fun decode() {
    println("Input encoded string:")
    val input = readln(); if (input.contains(Regex("[a-zA-z1-9]"))) { printError(); return }

    val list = Regex("0+|1+").findAll(input).map { it.value }.toList()
    if (list.size % 2 != 0)  { printError(); return }

    val mut = mutableListOf<String>()
    for (i in list.indices step 2) {
        if (list[i] == "000") { printError(); return }
        if (list[i] == "0") mut.add("1".repeat(list[i + 1].length))
        else mut.add("0".repeat(list[i + 1].length))
    }

    if (mut.joinToString("").length % 7 != 0)  { printError(); return }

    println("Decoded string:")
    mut.joinToString("").chunked(7).forEach { print(Integer.parseInt(it, 2).toChar()) }
    println()
}

fun printError() {
    println("Encoded string is not valid.")
}

fun encode() {
    println("Input string:")
    val input = readln()

    println("Encoded string:")
    val mut = mutableListOf<String>()
    input.forEach {
        val binary = it.code.toString(2).padStart(7, '0')
        mut.add(binary)
    }
    val list = Regex("0+|1+").findAll(mut.joinToString("")).map { it.value }.toList()

    for (i in list.indices) {
        if (list[i].first() == '1') {
            print("0 " + "0".repeat(list[i].length))
        } else {
            print("00 " + "0".repeat(list[i].length))
        }
        print(" ")
    }
    println()
}
