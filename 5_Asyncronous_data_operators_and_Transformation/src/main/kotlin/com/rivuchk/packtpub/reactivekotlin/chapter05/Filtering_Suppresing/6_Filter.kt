package com.rivuchk.packtpub.reactivekotlin.chapter05.Filtering_Suppresing

import io.reactivex.Observable


fun main(args: Array<String>) {
    Observable.range(1,20)//(1)
            .filter{//(2)
                it%2==0
            }
            .subscribe {
                println("Received $it")
            }


}
// * 정리
// - Filter
//      - 가장 많이 쓰인다
//      - filter 조건을 만족하는 것만 emit한다
//      - defaultIfEmpty 설정이 가능하다

