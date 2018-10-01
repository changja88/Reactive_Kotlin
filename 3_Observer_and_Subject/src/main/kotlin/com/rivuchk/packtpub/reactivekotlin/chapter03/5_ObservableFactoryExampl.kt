package com.rivuchk.packtpub.reactivekotlin.chapter03

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import java.util.concurrent.TimeUnit


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

    Observable.range(1,10).subscribe(observer)//(1)
    Observable.empty<String>().subscribe(observer)//(2)

    runBlocking {

        Observable.interval(300, TimeUnit.MILLISECONDS).subscribe(observer)//(3)
        delay(900)
        val subscription = Observable.timer(400,TimeUnit.MILLISECONDS).subscribe(observer)//(4)
        delay(450)
    }
}

// * 정리
// - Observable factory methods
//      - Observable.range() : Observable 생성 가능
//      - Observable.empty() : Observable 생성 가능 (비어 있기 때문에 바로 oncComplete가 호출됨)
//      - Observable.interval() : Observable 생성 가능 (0초에 한번, 정해진 시간마다 원소를 하나씩 emitt함)
//      - Obervable.timer() : Observable 생성 가능 (정해진 시간이 되면 딱 한번만 emiit함)