package com.rivuchk.packtpub.reactivekotlin.chapter03

import io.reactivex.subjects.ReplaySubject

/**
 * Created by rivuc on 22-07-2017.
 */

fun main(args: Array<String>) {
    val subject = ReplaySubject.create<Int>()
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
}