package com.rivuchk.packtpub.reactivekotlin.chapter03

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import java.util.concurrent.TimeUnit

/**
* Created by rivuc on 22-07-2017.
*/

fun main(args: Array<String>) {
    val observable = Observable.interval(100, TimeUnit.MILLISECONDS)//1
    val subject = PublishSubject.create<Long>()//2

    observable.subscribe(subject)//3

    subject.subscribe({//4
        println("Subscription 1 Received $it")
    })

    runBlocking { delay(1100) }//5

    subject.subscribe({//6
        println("Subscription 2 Received $it")
    })

    runBlocking { delay(1100) }//7
}
// * 정리
// - Subject의 장점
//      - Subject doesn't replay the actions such as Cold observables, it just relays the emission to
//        all observers, turning a Cold Observable into Hot Observable one
//      - 즉, hot obervable 처럼 connect 되기전에 subscirbe를 안하면 개 무시 되는게 아니라
//        중계자 역할을 한다 언제든 obervable이 subscribe를 할 준비가 되면 emiit 해준다