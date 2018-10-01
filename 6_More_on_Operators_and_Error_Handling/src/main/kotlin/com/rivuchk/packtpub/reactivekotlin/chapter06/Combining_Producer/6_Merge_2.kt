package com.rivuchk.packtpub.reactivekotlin.chapter06.Combining_Producer

import io.reactivex.Observable
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    val observable1 = Observable.interval(500, TimeUnit.MILLISECONDS).map { "Observable 1 $it" }//(1)
    val observable2 = Observable.interval(100, TimeUnit.MILLISECONDS).map { "Observable 2 $it" }//(2)

    Observable
            .merge(observable1,observable2)
            .subscribe {
                println("Received $it")
            }

    runBlocking { delay(1500) }
}

// * 정리
// - Merge
//      - emit 시간이 다른 애들 둘이 merge가 되면 그냥 시간 둘중에 아무나 emit 하는 item을 처리해준다