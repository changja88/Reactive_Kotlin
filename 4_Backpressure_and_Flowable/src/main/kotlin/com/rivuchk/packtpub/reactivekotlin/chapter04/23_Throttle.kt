package com.rivuchk.packtpub.reactivekotlin.chapter04

import io.reactivex.Flowable
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    val flowable = Flowable.interval(100, TimeUnit.MILLISECONDS)//(1)
    flowable.throttleFirst(200,TimeUnit.MILLISECONDS)//(2)
            .subscribe { println(it) }

    runBlocking { delay(1,TimeUnit.SECONDS) }
}

// * 정리
// - Throttle
//      - buffer와 window는 emission을 모으지만 throttle는 emission을 omit(누락) 한다
