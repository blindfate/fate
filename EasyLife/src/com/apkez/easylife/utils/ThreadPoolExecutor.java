package com.apkez.easylife.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class ThreadPoolExecutor {
	
	private ExecutorService service;
	
	/*默认线程池线程数*/
    public static final int DEFAULT_THREAD_POOL_SIZE = getDefaultThreadPoolSize();
    
    
    /**
     * get recommend default thread pool size
     * 
     * @return if 2 * availableProcessors + 1 less than 8, return it, else return 8;
     * @see {@link #getDefaultThreadPoolSize(int)} max is 8
     */
    public static int getDefaultThreadPoolSize() {
        return getDefaultThreadPoolSize(8);
    }

    /**
     * get recommend default thread pool size
     * 
     * @param max
     * @return if 2 * availableProcessors + 1 less than max, return it, else return max;
     */
    public static int getDefaultThreadPoolSize(int max) {
        int availableProcessors = 2 * Runtime.getRuntime().availableProcessors() + 1;
        return availableProcessors > max ? max : availableProcessors;
    }
    


	/*单列模式*/
	private ThreadPoolExecutor() {
		service = Executors.newFixedThreadPool(DEFAULT_THREAD_POOL_SIZE);
	}
	
	private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor();
	public static ThreadPoolExecutor getInstance() {
		return EXECUTOR;
	}

	/*启动线程*/
	public void execute(Runnable runnable) {
		service.execute(runnable);
	}
}
