package com.rivuchk.packtpub.reactivekotlin.chapter03

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


/**
 * Created by rivuc on 22-07-2017.
 */

fun main(args: Array<String>) {
    val observer: Observer<Any> = object : Observer<Any> {
        override fun onComplete() {
            println("All Completed")
        }

        override fun onNext(item: Any) {
            println("Next $item")
        }

        override fun onError(e: Throwable) {
            println("Error Occured ${e.message}")
        }

        override fun onSubscribe(d: Disposable) {
            println("New Subscription ")
        }
    }//Create Observer

    Observable.just("A String").subscribe(observer)
    Observable.just(54).subscribe(observer)
    Observable.just(listOf("String 1","String 2","String 3","String 4")).subscribe(observer)
    Observable.just(mapOf(Pair("Key 1","Value 1"),Pair("Key 2","Value 2"),Pair("Key 3","Value 3"))).subscribe(observer)
    Observable.just(arrayListOf(1,2,3,4,5,6)).subscribe(observer)
    Observable.just("String 1","String 2","String 3").subscribe(observer)//1
}

// * 정리
// - Understanding the Observable.just method
//      - observable을 만든다 + observer에게 emitt 할 item이 딱 하나 뿐이다 라는 뜻
//      - 리스트 같은 것들도 유일한 item이기 때문에 통으로 observer에게 전달 된다(원소 하나하나를 전달하지 않는다)
//      - Observable.just("String 1","String 2","String 3") 처럼 전달하게 되면 더이상 item이 유일하지 않다
//          -> 따라서 원소 하나하나 observer에게 전달한다