package com.rivuchk.packtpub.reactivekotlin.chapter03

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


/**
 * Created by rivuc on 22-07-2017.
 */

fun main(args: Array<String>) {

    val observer: Observer<String> = object : Observer<String> {
        override fun onComplete() {
            println("All Completed")
        }

        override fun onNext(item: String) {
            println("Next $item")
        }

        override fun onError(e: Throwable) {
            println("Error Occured ${e.message}")
        }

        override fun onSubscribe(d: Disposable) {
            println("New Subscription ")
        }
    }//Create Observer

    val observable:Observable<String> = Observable.create<String> {//1
        it.onNext("Emit 1")
        it.onNext("Emit 2")
        it.onNext("Emit 3")
        it.onNext("Emit 4")
        it.onComplete() // observer의 함수를 호출 할 수 있다 (onSubscribe는 호출 못한다)
        // observer 의 parameter에 원하는 값을 전달 할 수 있다
    }

    observable.subscribe(observer)

    val observable2:Observable<String> = Observable.create<String> {//2
        it.onNext("Emit 1")
        it.onNext("Emit 2")
        it.onNext("Emit 3")
        it.onNext("Emit 4")
        it.onError(Exception("My Custom Exception"))
        // observer 의 parameter에 원하는 값을 전달 할 수 있따
    }

    observable2.subscribe(observer)
}
// * 정리
// - Understanding the Observable.create method
//      - Observable.create() : Observable을 만들어서 사용 할 수 있다
//      - custom data structure 를 사용 할 때 유용 하다
//      - observable이 value를 하나씩 emitt 할때 그 값을 컨트롤 하고 싶을 때 유용하다