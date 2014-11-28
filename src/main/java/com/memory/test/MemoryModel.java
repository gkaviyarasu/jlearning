package com.memory.test;

import java.lang.management.MemoryPoolMXBean;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * The purpose of this class is to create a java JVM process that will not get
 * terminated. This helps to understand the properties that are available for a
 * default JVM. The purpose is to monitor or explore the JMX properties.
 */
public class MemoryModel {

	private static final Logger logger = LoggerFactory
			.getLogger(MemoryModel.class);

	public static void main(String args[]) throws InterruptedException {
		logger.info("Process Name {}", java.lang.management.ManagementFactory
				.getRuntimeMXBean().getName());
		logger.info("HeapMemory Usage {}",
				java.lang.management.ManagementFactory.getMemoryMXBean()
						.getHeapMemoryUsage());
		logger.info("NonHeapMemory Usage {}",
				java.lang.management.ManagementFactory.getMemoryMXBean()
						.getNonHeapMemoryUsage());

		Iterator<MemoryPoolMXBean> i = java.lang.management.ManagementFactory
				.getMemoryPoolMXBeans().iterator();
		while (i.hasNext()) {
			MemoryPoolMXBean m = i.next();
			logger.info("Name: {}, Usage: {}, PeakUsage: {}, Type: {}",
					m.getName(), m.getUsage(), m.getPeakUsage(), m.getType());
		}

		// The below loop prevents the JVM from getting terminated
		while (true) {
			Thread.sleep(5000);
		}
	}
}