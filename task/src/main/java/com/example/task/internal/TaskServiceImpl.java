package com.example.task.internal;

import com.example.task.AbstractTaskService;
import com.example.task.TaskCallback;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;



public class TaskServiceImpl extends AbstractTaskService {
    @Override
    public Cancellable executeTask(final Runnable task) {
        Observable observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {
                task.run();
                e.onComplete();
            }
        });
        final Disposable disposal = observable.subscribeOn(Schedulers.io()).subscribe();
        return new Cancellable() {
            @Override
            public void cancel() {
                disposal.dispose();
            }
        };
    }

    @Override
    public <T> Cancellable executeTask(final Callable<T> callable, final TaskCallback<T> listener) {
        Observable<T> observable = Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {
                T result = callable.call();
                e.onNext(result);
                e.onComplete();
            }
        });
        final Disposable disposal =  observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<T>() {
            @Override
            public void accept(T o) {
                if(listener != null){
                    listener.onResult(o);
                }
            }
        });
        return new Cancellable() {
            @Override
            public void cancel() {
                disposal.dispose();
            }
        };
    }

    @Override
    public Cancellable postTask(final Runnable task) {
        Observable observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {
                task.run();
                e.onComplete();
            }
        });
        final Disposable disposal =  observable.subscribeOn(AndroidSchedulers.mainThread()).subscribe();
        return new Cancellable() {
            @Override
            public void cancel() {
                disposal.dispose();
            }
        };
    }
}
