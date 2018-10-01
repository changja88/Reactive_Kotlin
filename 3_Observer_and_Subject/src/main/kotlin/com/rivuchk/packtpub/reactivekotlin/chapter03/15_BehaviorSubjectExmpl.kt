package com.rivuchk.packtpub.reactivekotlin.chapter03

import io.reactivex.subjects.AsyncSubject
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

/**
 * Created by rivuc on 22-07-2017.
 */

fun main(args: Array<String>) {
    val subject = BehaviorSubject.create<Int>()
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

    subject.onComplete()

// * 정리
// - Understanding BehaviorSubject
//      - PublishSubject는 subscribe를 하면 subscribe이전에 발생했던 이벤트는 전달 받을 수 없지만
//        BehaviorSubject는 가장 최신 next이벤를 받을 수 있다

    println("----------------------------------------")

    val subject2 = AsyncSubject.create<Int>()
    subject2.onNext(1)
    subject2.onNext(2)
    subject2.onNext(3)
    subject2.onNext(4)

    subject2.subscribe({
        //onNext
        println("S1 Received $it")
    },{
        //onError
        it.printStackTrace()
    },{
        //onComplete
        println("S1 Complete")
    })

    subject2.onNext(5)

    subject2.subscribe({
        //onNext
        println("S2 Received $it")
    },{
        //onError
        it.printStackTrace()
    },{
        //onComplete
        println("S2 Complete")
    })

    subject2.onComplete()

// * 정리
// - AsyncSubject는 들어온 순서대로 그대로 각각의 observer에게 보내준다 (결과 봐야됨)

    println("----------------------------------------")

    val subject3 = PublishSubject.create<Int>()
    subject3.onNext(1)
    subject3.onNext(2)
    subject3.onNext(3)
    subject3.onNext(4)

    subject3.subscribe({
        //onNext
        println("S1 Received $it")
    },{
        //onError
        it.printStackTrace()
    },{
        //onComplete
        println("S1 Complete")
    })

    subject3.onNext(5)

    subject3.subscribe({
        //onNext
        println("S2 Received $it")
    },{
        //onError
        it.printStackTrace()
    },{
        //onComplete
        println("S2 Complete")
    })
    subject3.onComplete()
}

