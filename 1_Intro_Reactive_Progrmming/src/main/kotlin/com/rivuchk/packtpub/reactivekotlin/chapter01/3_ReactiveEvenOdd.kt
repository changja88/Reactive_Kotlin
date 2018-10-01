package com.rivuchk.packtpub.reactivekotlin.chapter01

import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

/**
 * Created by Rivu Chakraborty on 7/3/2017.
 */

fun main(args: Array<String>) {
    var subject: Subject<Int> = PublishSubject.create()

    subject.map(
            { isEven(it) }
    ).subscribe(
            { println("The number is ${(if (it) "Even" else "Odd")}") }
    )

    subject.map(
            { isEven(it) }
    ).subscribeBy(
            onNext = { println("The number is ${(if (it) "Even" else "Odd")}") }
    )


    subject.onNext(4)
    subject.onNext(9)
}

// * 정리
// - onNext
//      - Subject 에 onNext를 하면 Subject의 subcribe 블럭이 시작된다