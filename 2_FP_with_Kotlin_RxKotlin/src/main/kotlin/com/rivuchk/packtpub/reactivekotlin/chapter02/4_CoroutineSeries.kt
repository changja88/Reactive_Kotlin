package com.rivuchk.packtpub.reactivekotlin.chapter02

import kotlin.coroutines.experimental.buildSequence

/**
 * Created by rivuc on 14-07-2017.
 */

fun main(args: Array<String>) {
    val fibonacciSeries = buildSequence {
        var a = 0
        var b = 1
        yield(a)
        yield(b)

        while (true) {
            val c = a + b
            yield(c)
            a = b
            b = c
        }
    }

//    println(fibonacciSeries.take(10) join "," )
    println(fibonacciSeries.take(10))
}

// * 정리
// - buildSequence -> buildSeqence 블럭으로 fibonacciSeries 변수를 구성 하겠다는 뜻
// - yield -> 어떤 연산을 하게 되면 결과가 나오는데 그 결과를 선택한 변수의 값으로 설정 하겠다는 뜻
//      - yield(a) -> a=0으로 a값에 대한 연산이 끝났고, 그값을(0)을 a의 값으로 설정 하겠다는 뜻
//      - yield(c) -> a+b으로 c값에 대한 연산이 끝났고, 그값을 c츼 값으로 설정하겠다는 뜻
// - take -> take(10)은 열번 돌리겠다는 뜻