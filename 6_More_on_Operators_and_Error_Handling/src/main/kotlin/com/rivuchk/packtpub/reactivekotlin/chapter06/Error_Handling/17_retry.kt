package com.rivuchk.packtpub.reactivekotlin.chapter06.Error_Handling

import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy

fun main(args: Array<String>) {
    Observable.just(1, 2, 3, 4, 5)
            .map { it / (3 - it) }
            .retry(3)//(1)
            .subscribeBy(
                    onNext = { println("Received $it") },
                    onError = { println("Error") }
            )


    println("\n With Predicate \n")


    var retryCount = 0
    Observable.just(1, 2, 3, 4, 5)
            .map { it / (3 - it) }
            .retry {//(2)
                _, _ ->
                (++retryCount) < 3
            }
            .subscribeBy(
                    onNext = { println("Received $it") },
                    onError = { println("Error") }
            )
}

// * 정리
// - retry
//      - 이것도 errorhandling 방법중 하나
//      - 파라미터로 리트라이를 몇번 할 건지 받을 수 있다
//      - 파라미터로 predicate를 받을 수 있다 -> predicate가 true일 동안계속 재시도를 한다
//