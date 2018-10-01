package com.rivuchk.packtpub.reactivekotlin.chapter06.Combining_Producer

import io.reactivex.Observable

fun main(args: Array<String>) {
    val observable = Observable.range(1, 30)

    observable
            .groupBy { it % 5 }
            .blockingSubscribe {
                println("Key ${it.key} ")
                it.subscribe {
                    // 위에 subscribe결과로 GroupedObservable이 나오기때문에 그걸 다시 subscribe해준다
                    println("Received $it")
                }
            }

    observable
            .groupBy { it % 5 }
            .subscribe {
                println("Key ${it.key} ")
                it.subscribe {
                    // 위에 subscribe결과로 GroupedObservable이 나오기때문에 그걸 다시 subscribe해준다
                    println("Received $it")
                }
            }
}

// * 정리
// - Grouping
//      - property에 따라서 emissions group을 만들 수 있다


// - subcribe 와 blockingSubscribe와의 차이 (바꿔서 돌려보면 알 수 있다)
//      - subscirbe dosen't wait for the given task on emission to complete before taking
//        the next emission
//      - blockingSubcribe wait unitl it complete processing an emission before proceeding to a new one

