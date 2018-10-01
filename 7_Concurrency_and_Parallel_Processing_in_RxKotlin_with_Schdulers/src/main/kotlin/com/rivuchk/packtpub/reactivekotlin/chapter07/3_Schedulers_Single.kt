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
                .subscribeOn(Schedulers.single())//(1)
                .subscribe {
                    runBlocking { delay(200) }
                    println("Observable1 Item Received $it")
                }

        Observable.range(21, 10)
                .subscribeOn(Schedulers.single())//(2)
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
// - Single Thread
//      - 결과를 돌려보면 obsevable1이 다 끝날 때까지 observable2가 시작을 하지 않는다 -> single 이니깐 !
//      - Despite the fact that both the subscriptions run sequentially,
//        they run in parallel to the calling thread
//      - single 쓰레드로 걸어 놓은 것만 순차적으로 실행 하겠다는 뜻, 그래서 반복문 부분이 같이 실행된다