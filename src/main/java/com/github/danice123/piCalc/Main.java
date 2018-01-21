package com.github.danice123.piCalc;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		File file = new File("Pi - Dec - Chudnovsky.txt");
		BufferedInputStream piStream = new BufferedInputStream(new FileInputStream(file));
		log("File Loaded");
		
		PiCrawler piCrawler = new PiCrawler(piStream);
		
		RollingAverage crawlData = piCrawler.crawl(0, toBillions(1));
		
		log(crawlData.toString());
	}
	
	private static long toMillions(int n) {
		return n * 1000000;
	}
	
	private static long toBillions(int n) {
		return n * 1000000000;
	}
	
	private static void log(String s) {
		System.out.println(s);
	}
}
