package com.rivuchk.packtpub.reactivekotlin.chapter7

import io.reactivex.Observable
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking

fun main(args: Array<String>) {
    Observable.range(1,10)
            .subscribe {
                runBlocking { delay(200) }
                println("Observable1 Item Received $it")
            }

    Observable.range(21,10)
            .subscribe {
                runBlocking { delay(100) }
                println("Observable2 Item Received $it")
            }
}

// * 정리
// - scheduler를 사용 하지 않은 경우
//      - 메인쓰레드가 멈춰서 구독을 하고 있게 된다