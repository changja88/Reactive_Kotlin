package com.rivuchk.packtpub.reactivekotlin.chapter06.Combining_Producer

import io.reactivex.Observable
import java.util.*
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    println("Without delay")
    Observable.range(1, 10)
            .switchMap {
                val randDelay = Random().nextInt(10)
                return@switchMap Observable.just(it)//(1)
            }
            .blockingSubscribe {
                println("Received $it")
            }
    println("With delay")
    Observable.range(1, 10)
            .switchMap {
                val randDelay = Random().nextInt(10)
                return@switchMap Observable.just(it)
                        .delay(randDelay.toLong(), TimeUnit.MILLISECONDS)//(2)
            }
            .blockingSubscribe {
                println("Received $it")
            }

    Observable.range(1, 10)
            // delay가 걸린 item들은 출력이 안되고 3으로 나누어 떨어지는(딜레이가 없는)애들은 출력이됨
            // + 마지막 10은 나옴
            .switchMap {
                val randDelay = Random().nextInt(10)
                if (it % 3 == 0) Observable.just(it)
                else {
                    Observable.just(it)
                            .delay(randDelay.toLong(), TimeUnit.MILLISECONDS)
                }
            }
            .blockingSubscribe {
                println("Received $it")
            }
}

// * 정리
// - Understanding switchMap operator
//      - It listens to all the emissions of the source producer(Observable/Flowable)asynchronously,
//        but emits only the latest one 'within the timeframe'
//      - delay가 걸리면 맨마지막 꺼만 emit하고, delay가 없는건 전부다 emit 한다