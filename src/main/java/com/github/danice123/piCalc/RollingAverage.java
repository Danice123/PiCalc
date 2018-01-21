package com.github.danice123.piCalc;

public class RollingAverage {
	
	//private List<Integer> data;
	private int dataSize;
	private double rollingAverage;
	private int max = Integer.MIN_VALUE;
	private int min = Integer.MAX_VALUE;
	
	public RollingAverage() {
		//data = Lists.newArrayList();
	}
	
	public void add(Integer i) {
		double f = rollingAverage * dataSize;
		//data.add(i);
		dataSize++;
		f += i;
		rollingAverage = f / (double) dataSize;
		
		if (i > max)
			max = i;
		if (i < min)
			min = i;
	}
	
	public double getAverage() {
		return rollingAverage;
	}
	
//	public double getTrueAverage() {
//		long total = 0;
//		for (Integer i : data) {
//			total += i;
//		}
//		return total / (double) data.size();
//	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Average: ").append(getAverage()).append(" Max: ").append(max).append(" Min: ").append(min);
		return stringBuilder.toString();
	}
	
//	public double getSubsetAverage(int subsetSize) {
//		if (subsetSize > data.size()) {
//			return 0;
//		}
//		long total = 0;
//		for (int i = data.size() - subsetSize; i < data.size(); i++) {
//			total += data.get(i);
//		}
//		return total / (double) subsetSize;
//	}
}
