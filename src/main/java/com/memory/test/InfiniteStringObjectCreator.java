package com.memory.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The purpose of this class is to create infinite String objects with
 * increasing size to verify the GC operations
 */
public class InfiniteStringObjectCreator {

	private static final Logger logger = LoggerFactory
			.getLogger(InfiniteStringObjectCreator.class);

	public static void main(String[] args) throws InterruptedException,
			IOException {

		List<String> strList = new ArrayList<String>();
		StringBuilder builder = new StringBuilder("");
		logger.info("Process Name {}", java.lang.management.ManagementFactory
				.getRuntimeMXBean().getName());
		System.in.read();

		for (int i = 1; i < 10000001; i++) {
			builder.append("Abcdefghijklmnopqrstuvwxyz " + i);
			strList.add(builder.toString());

			if (i % 100 == 0) {
				Thread.sleep(1000);
				//logger.info("Iteration count {}", i);

				// Ignoring one object for every 100 objects to check the
				// incremental ratio of memory
				emptyRange(strList, 4);
			} else if (i % 5 == 0) {
				emptyRange(strList, 5);
			}
		}
	}

	public static void emptyRange(List<String> list, int range)
			throws InterruptedException {
		for (int j = 0; j < range; j++) {
			list.remove(0);
		}
	}
}