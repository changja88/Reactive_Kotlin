package com.rivuchk.packtpub.reactivekotlin.chapter01


/**
 * Created by Rivu Chakraborty on 7/3/2017.
 */

fun main(args: Array<String>) {
    var list:List<Any> = listOf("One", 2, "Three", "Four", 4.5, "Five", 6.0f) // 1
    var iterator = list.iterator() // 2
    while (iterator.hasNext()) { // 3
        println(iterator.next()) // Prints each element 4
    }
}

// * 정리
// - 그냥 일반 적인 코틀린