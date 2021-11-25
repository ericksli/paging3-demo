package net.swiftzer.paging3demo

import javax.inject.Qualifier

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class IoDispatcher

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class DefaultDispatcher

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class MainDispatcher
