package com.example.mkitt.test.rxjava;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.example.mkitt.R;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * @author tjoven
 * https://www.jianshu.com/p/7b839b7c5884
 *
 */
@SuppressLint("CheckResult")
public class TestRxJavaActivity extends Activity {

    Subscription subscription;

    private static final String TAG = "TestRxJavaActivity";
    Button mBt;
    Button mBtMerge;
    Button mBtZip;
    Button mBtJust;
    Button mBtMap;
    Button mBtFlatMap;
    Button mBtFlow;
    Button mBtBackPress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_rxjava);
        mBt =  (Button)findViewById(R.id.button);
        mBtMerge =  (Button)findViewById(R.id.bt_merge);
        mBtZip =  (Button)findViewById(R.id.bt_zip);
        mBtJust =  (Button)findViewById(R.id.bt_just);
        mBtMap =  (Button)findViewById(R.id.bt_map);
        mBtFlatMap =  (Button)findViewById(R.id.bt_flatMap);
        mBtFlow =  (Button)findViewById(R.id.bt_flow);
        mBtBackPress =  (Button)findViewById(R.id.bt_back_press);

        mBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                test();
            }
        });

        mBtMerge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testRxjavaMerge();
            }
        });

        mBtZip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testRxjavaZip();
            }
        });

        mBtJust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testRxjavaJust();
            }
        });

        mBtMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testRxjavaMap();
            }
        });

        mBtFlatMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testRxjavaFlatMap();
            }
        });

        mBtFlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testRxjavaFlow();
            }
        });

        mBtBackPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(subscription != null){
                   subscription.request(10);
               }
            }
        });

    }

    private void testRxjavaFlow() {
        Flowable.create(new FlowableOnSubscribe<String>() {

            @Override
            public void subscribe(@NonNull FlowableEmitter<String> emitter) throws Exception {
                Log.d(TAG,"subscribe ThreadName: "+Thread.currentThread().getName());
                Log.d(TAG,"subscribe emitter.size: "+emitter.requested());
                emitter.requested();
                emitter.onNext("a");
                Log.d(TAG,"subscribe emitter.size: "+emitter.requested());
                emitter.onNext("b");
                Log.d(TAG,"subscribe emitter.size: "+emitter.requested());
                emitter.onNext("c");
                Log.d(TAG,"subscribe emitter.size: "+emitter.requested());
                emitter.onNext("d");
                Log.d(TAG,"subscribe emitter.size: "+emitter.requested());
                emitter.onComplete();

            }
        }, BackpressureStrategy.ERROR)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<String>() {
                @Override
                public void onSubscribe(Subscription s) {
                    Log.d(TAG,"onSubscribe ThreadName: "+Thread.currentThread().getName());
                    subscription = s;
                }

                @Override
                public void onNext(String s) {
                    Log.d(TAG,"next "+ s);
                }

                @Override
                public void onError(Throwable t) {
                    Log.d(TAG,"onError "+ t);
                }

                @Override
                public void onComplete() {
                    Log.d(TAG,"onComplete ");
                }
            });

    }


    private void test() {
        Observable.create(new ObservableOnSubscribe<Object>() {

            @Override
            public void subscribe(@NonNull ObservableEmitter<Object> emitter) throws Exception {
                Log.d(TAG,"111");
                emitter.onNext(111);
                Log.d(TAG,"222");
                emitter.onNext(222);
                emitter.onComplete();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

            .subscribe(new Observer<Object>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Object o) {
                Log.d(TAG,"onNext: "+o.toString());
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.d(TAG,"onComplete");
            }
        });
    }

    private void testRxjavaFlatMap() {
        Observable<Integer> observable1 = Observable.just(1,2,3,4,5);
        observable1.flatMap(new Function<Integer, ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> apply(@NonNull Integer integer) throws Exception {
                return Observable.create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                        Log.d(TAG,"subscribe "+integer);
                        emitter.onNext(integer + 200);
                        emitter.onNext(integer + 300);
                        emitter.onComplete();
                    }
                });
            }
        }).subscribe(new Consumer<Integer>() {

            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG,"accept "+integer);
            }
        });
    }


    /**
     * 转换 操作符
     *  数组 + 100
     */
    private void testRxjavaMap() {
        Observable<Integer> observable1 = Observable.just(1,2,3,4,5);
        observable1.map(new Function<Integer, Integer>() {
            @Override
            public Integer apply(@NonNull Integer integer) throws Exception {
                return integer + 100;
            }
        }).subscribe(new Consumer<Integer>() {

            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG,"accept "+integer);
            }
        });
    }

    @SuppressLint("CheckResult")
    private void testRxjavaJust() {
        Observable<Integer> observable1 = Observable.just(1,2,3,4,5);
        Observable<Integer> observable2 = Observable.fromArray(6,7,8,9);
        observable2.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                Log.d(TAG,"accept "+integer);
            }
        });
    }

    private void testRxjavaZip(){
        String[] strs = {"a","b","c","d","e"};
        Integer[] ints = {1,2,3,4,5,6};
//        Observable observable1 = Observable.interval(1, TimeUnit.SECONDS);
        Observable<Integer> observable1 = Observable.fromArray(ints);
        Observable<String> observable2 = Observable.fromArray(strs);
        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {

            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG,"onSubscribe: ");
                    }

                    @Override
                    public void onNext(String o) {
                        Log.d(TAG,"onNext: "+o);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"onError: "+e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG,"onComplete: ");
                    }
                });
    }

    private void testRxjavaMerge(){
        String[] strs = {"a","b","c","d","e"};
        Integer[] ints = {1,2,3,4,5};
//        Observable observable1 = Observable.interval(1, TimeUnit.SECONDS);
        Observable<Integer> observable1 = Observable.fromArray(ints);
        Observable<String> observable2 = Observable.fromArray(strs);
        Observable.merge(observable2,observable1)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.d(TAG,"doOnSubscribe: Thread "+ Thread.currentThread().getName());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG,"onSubscribe: Thread "+ Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(Object o) {
                        if(o instanceof String){
                            Log.d(TAG,"onNext String: "+o);
                        }else if(o instanceof Integer){
                            Log.d(TAG,"onNext Integer: "+o);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG,"onError: "+e);
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG,"onComplete: ");
                    }
                });
    }
}
