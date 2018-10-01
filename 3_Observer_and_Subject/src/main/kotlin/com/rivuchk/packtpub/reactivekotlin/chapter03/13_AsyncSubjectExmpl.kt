package com.rivuchk.packtpub.reactivekotlin.chapter03

import io.reactivex.Observable
import io.reactivex.subjects.AsyncSubject

/**
 * Created by rivuc on 22-07-2017.
 */

fun main(args: Array<String>) {
    val observable = Observable.just(1,2,3,4)//1

    val subject = AsyncSubject.create<Int>()//2

    observable.subscribe(subject)//3

    subject.subscribe({//4
        //onNext
        println("Received $it")
    },{
        //onError
        it.printStackTrace()
    },{
        //onComplete
        println("Complete")
    })
    subject.onNext(5)//5 -> onNext 를 바로 호출할 수도 있다
    subject.onComplete()//6 -> onComplete 를 바로 호출할 수도 있따
}

// * 정리
// - Uderstanding AsyncSubject
//      - Only emitt the last value of the source observable and the last emission only
//      - 즉 마지막으로 얻는 그 값만을 딱하면 emitt한다
