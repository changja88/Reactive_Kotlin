package com.rivuchk.packtpub.reactivekotlin.chapter03

import io.reactivex.subjects.AsyncSubject

/**
 * Created by rivuc on 22-07-2017.
 */

fun main(args: Array<String>) {
    val subject = AsyncSubject.create<Int>()
    subject.onNext(1)
    subject.onNext(2)
    subject.onNext(3)
    subject.onNext(4)

    subject.subscribe({
        //onNext
        println("S1 Received $it")
    },{
        //onError
        it.printStackTrace()
    },{
        //onComplete
        println("S1 Complete")
    })

    subject.onNext(5)

    subject.subscribe({
        //onNext
        println("S2 Received $it")
    },{
        //onError
        it.printStackTrace()
    },{
        //onComplete
        println("S2 Complete")
    })

    subject.onComplete()//6
}

// * 정리
// - Uderstanding AsyncSubject
//      - 말 그대로 비동기 방식이다 즉, 위 예제처럼 onNext(5)를 호출 하기전에 subscribe를 시작해도
//        onNext(5)가 호출 될 때 자동으로 호출 된다(비동기)