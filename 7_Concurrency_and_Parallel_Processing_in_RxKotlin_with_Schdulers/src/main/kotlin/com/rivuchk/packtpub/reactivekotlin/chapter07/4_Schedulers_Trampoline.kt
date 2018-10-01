package com.rivuchk.packtpub.reactivekotlin.chapter7

import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking

fun main(args: Array<String>) {

    async(CommonPool) {
        Observable.range(1, 10)
                .subscribeOn(Schedulers.trampoline())//(1)
                .subscribe {
                    runBlocking { delay(200) }
                    println("Observable1 Item Received $it")
                }

        Observable.range(21, 10)
                .subscribeOn(Schedulers.trampoline())//(2)
                .subscribe {
                    runBlocking { delay(100) }
                    println("Observable2 Item Received $it")
                }

        for (i in 1..10) {
            delay(100)
            println("Blocking Thread $i")
        }
    }

    runBlocking { delay(6000) }
}

// * 정리
// - Trampoline
//      - 쓰레드들으 큐에 담아 놨다가 순차적으로 실행한다
//      - 그래서 비동기로(async)로 감아 놨지만 순차적으로 실행된다