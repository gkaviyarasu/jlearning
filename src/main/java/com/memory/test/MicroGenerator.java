package com.memory.test;

import javassist.ClassPool;

/**
 * The purpose of this class is to create infinite number of dynamic classes and
 * check the Perm Gen memory growth
 */
public class MicroGenerator {
	public static void main(String[] args) throws Exception {
		int sleepcount = 0;
		for (int i = 0; i < 100_000_000; i++) {
			generate("com.memory.test.DymanicClass" + i);
			if (sleepcount > 1000) {
				Thread.sleep(1000);
				sleepcount = 0;
			} else {
				sleepcount++;
			}

		}
	}

	public static Class<?> generate(String name) throws Exception {
		ClassPool pool = ClassPool.getDefault();
		return pool.makeClass(name).toClass();
	}
}
