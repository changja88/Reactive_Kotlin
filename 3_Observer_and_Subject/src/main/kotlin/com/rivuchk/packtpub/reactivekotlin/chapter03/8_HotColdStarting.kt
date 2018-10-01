package com.rivuchk.packtpub.reactivekotlin.chapter03

import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable

/**
 * Created by rivuc on 22-07-2017.
 */

fun main(args: Array<String>) {
    val observable: Observable<String> = listOf("String 1","String 2","String 3","String 4").toObservable()//1

    observable.subscribe({//2
        println("Received $it")
    },{
        println("Error ${it.message}")
    },{
        println("Done")
    })

    observable.subscribe({//3
        println("Received $it")
    },{
        println("Error ${it.message}")
    },{
        println("Done")
    })

}
// * 정리
// - Hot and Cold Observables
//      - Cold Observables (data와 비슷하다고 생각하면됨)
//          - Start running upon subscriptions and Cold Observable starts pushing items after subscribe
//            get called, and pushes the same sequence of items on each subscription
//          - 이제까지 사용했던 Observable은 전부 Cold observable이었음
//          - 즉, Cold Observable은 subscribe를 하고 난 이후에 item을 하나씩 emiitt 하는 Observable을 말한다

//      - Hot Observables (Event와 비슷하다고 생각하면됨)
//          - It doesn't need subscriptions to start emission
//          - time sensitive -> Oberver가 제떄 subscribe를 하면 previously emitted item을 놓칠수도 있다
//          - UI작업이나 서버 통신 작업할때 유용하다