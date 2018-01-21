package com.github.danice123.piCalc;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Iterator;

public class PiCrawler {
	
	private final BufferedInputStream piStream;
	
	public PiCrawler(BufferedInputStream piStream) {
		this.piStream = piStream;
	}
	
	public RollingAverage crawl(int startDigit, long digits) {
		Iterator<Integer> piIterator = new PiIterator(startDigit, piStream);
		RollingAverage total = new RollingAverage();
		
		PiChain piChain = new PiChain();
		piChain.initializeCrawler(piIterator);
		total.add(piChain.getLengthOfChain());
		
		for (long i = 1; i < digits; i++) {
			if (i % 1000000 == 0) {
				//System.out.println(total.getSubsetAverage(1000000));
				System.out.println(total.toString());
			}
			
			piChain.crawlNextDigit(piIterator);
			if (piIterator.hasNext()) {
				total.add(piChain.getLengthOfChain());
			} else {
				System.out.println("Hitting end of pi calculation");
				break;
			}
		}
		
		return total;
	}
	
	private class PiIterator implements Iterator<Integer> {
		
		private BufferedInputStream piStream;
		private byte[] buffer;
		private int bufferSize;
		private int index;
		
		public PiIterator(int startDigit, BufferedInputStream piStream) {
			this.piStream = piStream;
			this.index = startDigit;
			buffer = new byte[8000];
		}

		public boolean hasNext() {
			if (index < bufferSize) {
				return true;
			}
			try {
				return piStream.available() > 0;
			} catch (IOException e) {
				return false;
			}
		}

		public Integer next() {
			if (bufferSize == 0 || index == bufferSize) {
				readToBuffer();
				index = 0;
			}
			char c = (char) buffer[index++];
			if (c == '.')
				return next();
			return Character.getNumericValue(c);
		}
		
		private void readToBuffer() {
			try {
				bufferSize = piStream.read(buffer);
			} catch (IOException e) {
				throw new RuntimeException();
			}
		}
		
	}
}
