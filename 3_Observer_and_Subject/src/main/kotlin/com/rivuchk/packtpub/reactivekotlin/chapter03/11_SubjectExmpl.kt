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
    val subject = PublishSubject.create<Long>()//2 Susbject 중류 중 하나
    observable.subscribe(subject)//3
    subject.subscribe({//4
        println("Received $it")
    })
    runBlocking { delay(1100) }//5
}

// * 정리
// - Subject
//      - 다른 종류의 hot observable
//      - Combination of observable and observer
//      - 내부적으로 observer list를 가지고 있음
//      - 기능
//          - observable의 모든 기능을 가지고 있음
//          - observer처럼 emitt되는 item을 listen 할 수 있음
//          - completed/errored/unsubscribed 된 이후에도 재사용할 수 있음
//          - 스스로 item을 emiit 할 수 있다

// - Understanding PublishSubject
//      - 값을 받는 데로 즉시 emitt 한다 (반복)