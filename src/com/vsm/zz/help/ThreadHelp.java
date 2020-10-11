package com.vsm.zz.help;

import java.util.concurrent.*;

/**
 * @author Zhu
 * @createtime 2020/10/8-10:10
 */
public class ThreadHelp {

    private ThreadHelp() {

    }

    static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
            50,
            100,
            1L,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(3),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.CallerRunsPolicy());

    public static ThreadPoolExecutor getThreadPool() {
        return threadPool;
    }

}
