package com.rivuchk.packtpub.reactivekotlin.chapter05.Reducing

import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy

fun main(args: Array<String>) {
    Observable.range(1,10)
            .reduce { previousAccumulation, newEmission ->  previousAccumulation+newEmission  }
            .subscribeBy { println("accumulation $it") }

    Observable.range(1,5)
            .reduce { previousAccumulation, newEmission ->  previousAccumulation*10+newEmission  }
            .subscribeBy { println("accumulation $it") }
}

// * 정리
// - Reducing Operators
//      - 처리후 하나만 리턴