package com.rivuchk.packtpub.reactivekotlin.chapter04

import io.reactivex.rxkotlin.toFlowable
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking

fun main(args: Array<String>) {
    val connectableFlowable = listOf("String 1","String 2","String 3","String 4","String 5").toFlowable()
            .publish() // -> publish 는 cold를 hot으로 바꿔준다(일반 Flowable을 ConnectableFloawble로 바꿔준다)
    connectableFlowable.
            subscribe({
                println("Subscription 1: $it")
                runBlocking { delay(1000) }
                println("Subscription 1 delay")
            })
//    connectableFlowable.connect()

    connectableFlowable
            .subscribe({ println("Subscription 2 $it")})
    connectableFlowable.connect()
}

// * 정리
// - ConnectableFlowable
//      - ConnectableObservable과 마찬가지로 connect된 이후에 emit를 시작하게 되고
//        connect이후에 subscribe 하게 되면 무시 된다
//      -