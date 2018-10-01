package com.rivuchk.packtpub.reactivekotlin.chapter09

// * 9 Resource Management and Extending RxKotlin
// - Resource Management : Aboutcreating, accessing, and cleaning up resources

// - 다루는 내용
//      - Resource management with the 'using' method
//      - Creating custom operators with the 'lift' operator
//      - Creating custom transformer(Transforming operators) with the 'compose'operator



// - subscribeOn, observeOn 차이
// - subscribeOn
//      - 이건 처리를 어떤 쓰레드에서 할건지 정하는 것
//      - 한번반에 적용을 못한 여러번 적용해도 마지막걸로 적용됨

// - observeOn
//      - 이건 처리를 마치고 나온 결과값을 어떤 쓰레드에서 할건지 정하는 것