package com.github.danice123.piCalc;

import java.util.Iterator;
import java.util.Map;
import java.util.Queue;

import com.google.common.collect.Maps;
import com.google.common.collect.Queues;

public class PiChain {
	
	private final Map<Integer, Integer> chainContents;
	private final Queue<Integer> chain;
	
	public PiChain() {
		chainContents = Maps.newHashMap();
		chainContents.put(0, 0);
		chainContents.put(1, 0);
		chainContents.put(2, 0);
		chainContents.put(3, 0);
		chainContents.put(4, 0);
		chainContents.put(5, 0);
		chainContents.put(6, 0);
		chainContents.put(7, 0);
		chainContents.put(8, 0);
		chainContents.put(9, 0);
		chain = Queues.newArrayDeque();
	}
	
	public void initializeCrawler(Iterator<Integer> piIterator) {
		fillChain(piIterator);
	}
	
	public void crawlNextDigit(Iterator<Integer> piIterator) {
		Integer firstDigit = chain.poll();
		chainContents.put(firstDigit, chainContents.get(firstDigit) - 1);
		fillChain(piIterator);
	}
	
	private void fillChain(Iterator<Integer> piIterator) {
		while(!hasAllDigits() && piIterator.hasNext()) {
			Integer next = piIterator.next();
			chainContents.put(next, chainContents.get(next) + 1);
			chain.offer(next);
		}
	}
	
	private boolean hasAllDigits() {
		for (Integer key : chainContents.keySet()) {
			if (chainContents.get(key) <= 1) {
				return false;
			}
		}
		return true;
	}
	
	public int getLengthOfChain() {
		return chain.size();
	}

}
