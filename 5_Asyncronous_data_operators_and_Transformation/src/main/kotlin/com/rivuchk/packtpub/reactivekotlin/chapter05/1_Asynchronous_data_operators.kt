package com.rivuchk.packtpub.reactivekotlin.chapter05

// * 정리
// - Asynchronous data operators and trasnfromation
//      - Operator : 특수한 문자이고, 특별한 기능이 있다
//      - User operator instead of writing blocking code
//      - Do not mix imperative programming with reactive programming
//      - Reactive를 통해서 얻을 수 있는 것 (imperative로 섞어쓰면 얻을 수 없다)
//          - Lower memory usage
//          - flexible concurrency
//          - Disposability
//      - RxKotlin 의 five operators
//          - Filtering / suppressing operators
//              - debounce
//              - distinct and distinctuntilchanged
//              - elementAt
//              - Filter
//              - first and last
//              - ignoreElements
//              - skip, skipLast, skipUntil, skipWhile
//              - take, takeLast, takeUntil, takeWhile
//          - Transforming operators
//              - map
//              - flatMap, concatMap, flatMapIterable
//              - switchMap
//              - switchIfEmpty
//              - scan (emit을 싹다 모아서 처리 후 다시 하나 하나 emit)
//              - groupBy
//              - startWith
//              - defaultIfEmpty
//              - sorted
//              - buffer
//              - window
//              - cast
//              - delay
//              - repeat
//          - Reducing operators (싹다 모아서 처리 후 하나만 emit)
//              - count
//              - reduce
//              - all
//              - any
//              - contains
//          - Error handling operators (chapter 6에서 자세하게 나옴)
//              - onErrorResumeNext
//              - onErrorReturn
//              - onExceptionResumeNext
//              - retry
//              - retryWhen
//          - Utility operators (chapter 6에서 자세하게 나옴)
//              - doOnNext, doOnComplete, doOnError
//              - doOnSubcribe, doOnDispose, doOnSuccess
//              - serialize
//              - cache
//          - Collection operators

