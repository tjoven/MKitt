package com.example.task;

import com.example.task.internal.TaskServiceImpl;

import java.util.concurrent.Callable;

/**
 * 任务服务类
 * 该类用来执行异步任务，目前支持两种异步任务： 无返回的任务和有返回的任务
 * 无返回的任务用Runnable类型表示
 * 有返回的任务用Callable类型表示，其返回信息通过TaskCallback接口回调
 */

public abstract class AbstractTaskService {
    public interface Cancellable {
        /**
         * Cancel the action or free a resource.
         */
        void cancel();
    }

    /**
     * 在非主线程执行指定的任务，该任务没有返回值
     * @param task 指定的任务
     * @return 可以用来取消该任务的对象
     */
    public abstract Cancellable executeTask(Runnable task);

    /**
     * 在非主线程执行指定的任务，任务执行完成后在主线程回调该任务的返回值
     * @param callable 带有返回值的任务
     * @param listener 回调接口
     * @param <T> 任务返回值的类型
     * @return 可以用来取消该任务的对象
     */
    public abstract<T> Cancellable executeTask(Callable<T> callable, TaskCallback<T> listener);

    /**
     * 在主线程执行特定的任务，该任务没有返回值
     * @param task 指定任务
     * @return 可以用来取消该任务的对象
     */
    public abstract Cancellable postTask(Runnable task);

    /**
     * 创建任务服务类的实例
     * @return 任务服务类的实例
     */
    public static AbstractTaskService get(){
        return new TaskServiceImpl();
    }
}
