package com.example.myfirstapp.observer

import java.util.*

abstract class MovieObservable {

    protected val observers = LinkedList<MovieObserver>()

    abstract fun notifyObservers()

    fun addObserver(observer: MovieObserver) = observers.add(observer)

    fun removeObserver(observer: MovieObserver) = observers.remove(observer)
}