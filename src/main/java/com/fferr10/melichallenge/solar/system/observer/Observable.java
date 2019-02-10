package com.fferr10.melichallenge.solar.system.observer;

import com.google.common.collect.Lists;

import java.util.List;

public abstract class Observable<T> {

    protected List<Observer<T>> observers;

    public Observable() {
        observers = Lists.newArrayList();
    }

    public void subscribe(Observer<T> observer){
        observers.add(observer);
    }

    protected void notifyObservers(T t){
        for (Observer<T> observer : observers) {
            observer.update(t);
        }
    }
}
