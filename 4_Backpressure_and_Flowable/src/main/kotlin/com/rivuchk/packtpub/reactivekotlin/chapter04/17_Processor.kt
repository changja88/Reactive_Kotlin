package com.rivuchk.packtpub.reactivekotlin.chapter04

import io.reactivex.Observable
import io.reactivex.processors.PublishProcessor
import io.reactivex.rxkotlin.toFlowable
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking

fun main(args: Array<String>) {
    val flowable = listOf("String 1","String 2","String 3","String 4","String 5").toFlowable()//(1)\

    val processor = PublishProcessor.create<String>()//(2)

    processor.//(3)
            subscribe({
                println("Subscription 1: $it")
                runBlocking { delay(1000) }
                println("Subscription 1 delay")
            })
    processor//(4)
            .subscribe({ println("Subscription 2 $it")})

    flowable.subscribe(processor)//(5)

}


// * 정리
// - Processor
//      - Observer 대신 해서 쓸수 있음
//      - 3,4번 바꾸면 결과 물이 달라짐 (순서가 바뀜 돌려보면 암)
//        -> Processor는 내부적으로 subcribe한 순서를 기억 하고 있기 때문에 그런거 같음
//        -> Processor 가 처음 subscribe한게 돌고 다음으로 subscribe 한게 돌고 하는 방식
//        -> Processor is waiting for all its subscribers to compete before pushing the next emission