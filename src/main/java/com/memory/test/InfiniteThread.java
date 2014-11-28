package com.memory.test;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The purpose of this class is to create infinite threads and verfiy the memory
 * model.
 */
public class InfiniteThread implements Runnable {

	private static final Logger logger = LoggerFactory
			.getLogger(InfiniteThread.class);

	@Override
	public void run() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}

		Thread t = new Thread(new InfiniteThread());
		t.start();
		Thread t1 = new Thread(new InfiniteThread());
		t1.start();

		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
	}

	public static void main(String args[]) throws IOException {
		logger.info("Process Name {}", java.lang.management.ManagementFactory
				.getRuntimeMXBean().getName());
		System.in.read();
		Thread local = new Thread(new InfiniteThread());
		local.start();
	}

}
