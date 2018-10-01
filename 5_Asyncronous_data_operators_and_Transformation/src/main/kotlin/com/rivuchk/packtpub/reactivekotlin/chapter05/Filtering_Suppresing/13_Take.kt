package com.rivuchk.packtpub.reactivekotlin.chapter05.Filtering_Suppresing

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking
import java.util.concurrent.TimeUnit

fun main(args: Array<String>) {
    val observable1 = Observable.range(1,20)
    observable1
            .take(5)//(1)
            .subscribe(object: Observer<Int> {
                override fun onError(e: Throwable) {
                    println("Error $e")
                }

                override fun onComplete() {
                    println("Complete")
                }

                override fun onNext(t: Int) {
                    println("Received $t")
                }

                override fun onSubscribe(d: Disposable) {
                    println("starting skip(count)")
                }

            })

    val observable2 = Observable.interval(100, TimeUnit.MILLISECONDS)
    observable2
            .take(400, TimeUnit.MILLISECONDS)//(2)
            .subscribe(
                    object: Observer<Long> {
                        override fun onError(e: Throwable) {
                            println("Error $e")
                        }

                        override fun onComplete() {
                            println("Complete")
                        }

                        override fun onNext(t: Long) {
                            println("Received $t")
                        }

                        override fun onSubscribe(d: Disposable) {
                            println("starting skip(time)")
                        }

                    }
            )

    runBlocking {
        delay(1000)
    }

}

// * 정리
// - Take (take 시리지는 skip과 정반대로 작동한다)
//      - count를 파라미터로 받을수 있다 -> count 개수 만큼만 emission을 처리한다
//      - time을 파라미터로 받을 수 있다 -> 그 처리 시간 동안 발생한 emission만을 처리한다
