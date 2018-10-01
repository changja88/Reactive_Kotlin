 package com.rivuchk.packtpub.reactivekotlin.chapter03

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.toObservable

/**
 * Created by rivuc on 22-07-2017.
 */

fun main(args: Array<String>) {

    val observer: Observer<Any> = object : Observer<Any> {
        //1
        override fun onComplete() {//2


            println("All Completed")
        }

        override fun onNext(item: Any) {//3
            println("Next $item")
        }

        override fun onError(e: Throwable) {//4
            println("Error Occured $e")
        }

        override fun onSubscribe(d: Disposable) {//5
            println("Subscribed to $d")
        }
    }

    val observable: Observable<Any> = listOf("One", 2, "Three", "Four", 4.5, "Five", 6.0f).toObservable() //6

    observable.subscribe(observer)//7

    val observableOnList: Observable<List<Any>> = Observable.just(listOf("One", 2, "Three", "Four", 4.5, "Five", 6.0f),
            listOf("List with Single Item"),
            listOf(1, 2, 3, 4, 5, 6))//8

    observableOnList.subscribe(observer)//9
}

// * 정리
// - push base : observable 이 push를 observer 한테하면 observer가 반응을 하는 형식

// - onNext : observable passes all items one by one to this method
// - onComplte : When all items have gone through the onEnxt method,
//               Observable calls the onComplete method
// - onError : observable이 error를 만나게 되면 호출 되는 함수
//             onError, onComple 둘다 terminal event이기 때문에 둘중 하나만 호출 된다
// - onSubscribe : Observer가 subscribe를 하는 순간 호출 된다

// - Observable
//      -> Observable<T>로 정의 되어 있기 때문에 아무 타입이나 될수 있다 (list, arrary 도 가능)