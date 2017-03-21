package com.scs.exercises.array19;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ExerciseArray19 {

	/**
	 * Problem: Input: given an unordered array (called A) with 9 elements.
	 * Output: true if A contains all values from 1..9; false otherwise
	 **/

	private static Map<String, Solution> solutions = new HashMap<>();

	public static void main(String[] args) {
		solutions.put("Anh1", new AnhAnswer1());
		solutions.put("Anh2", new AnhAnswer2());
		solutions.put("Lam", new LamAnswer());
		
		solutions.put("Loi", new LoiAnswer());
		solutions.put("Huong", new HuongAnswer());
		
		solutions.put("Will", new WillAnswer());
		
		solutions.put("Young", new YoungAnswer());
		solutions.put("Nam", new NamAnswer());
		

		execute(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, true, "arr = [1,2,3,4,5,6,7,8,9] #true");
		execute(new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1 }, false, "arr = [1,1,1,1,1,1,1,1,1] #false");
		execute(new int[] { 2, 3, 11, 3, 4, 5, 6, 7, 2 }, false, "arr = [2,3,11,3,4,5,6,7,2] #false");
		execute(new int[] { 9, 8, 7, 4, 5, 6, 1, 2, 3 }, true, "arr = [9,8,7,4,5,6,1,2,3] #true");
		execute(new int[] { 11, 12, 7, 4, 13, 90, 1, 2, 3 }, false, "arr = [11, 12, 7, 4, 13, 90, 1, 2, 3] #false");
	}

	private static final void execute(int[] array, boolean expected, String message) {
		System.out.println("-------------------");
		System.out.println("Excute: " + message);
		Profiler profiler = new Profiler();
		long delta;
		boolean actual;

		Set<String> keys = solutions.keySet();
		for (String k : keys) {
			profiler.start();
			actual = solutions.get(k).solve(array);
			delta = profiler.end();
			System.out.println("Answer from " + k + " - expected: " + expected + " - actual: " + actual + " cost: "
					+ delta + " ns.");
		}
	}

	public static class WillAnswer implements Solution {
		@Override
		public boolean solve(int[] arrayA) {
			boolean[] arr = new boolean[256];

			for (int a : arrayA) {
				if (a < 1 || a > 9)
					return false;
				else {
					if (arr[(byte) a]) {
						return false;
					} else {
						arr[a] = true;
					}
				}
			}
			return true;
		}
	}

	public static class HuongAnswer implements Solution {
		@Override
		public boolean solve(int[] arrayA) {
			int[] A = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
			int t = 0, l = 0;
			for (int i = 0; i <= 8; i++) {
				t = t + (int) (Math.pow(10, (9 - A[i])) * A[i]);
				l = l + (int) (Math.pow(10, (9 - arrayA[i])) * arrayA[i]);
			}

			return (l - t) == 0;
		}
	}

	public static class AnhAnswer2 implements Solution {
		@Override
		public boolean solve(int[] arrayA) {
			int result = 0b000000000;
			for (int i : arrayA) {
				result = result & (int) Math.pow(2, i);
			}
			return result == 0b111111111;
		}
	}

	public static class LamAnswer implements Solution {
		@Override
		public boolean solve(int[] arrayA) {
			int result = 0;
			for (int i : arrayA) {
				result |= 1 << (i - 1);
			}
			return (result == 511);
		}
	}

	public static class LoiAnswer implements Solution {
		@Override
		public boolean solve(int[] arrayA) {
			int[] arrayB = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
			for (int e : arrayA) {
				if (e < 1 || e > 9)
					return false;
				else if (arrayB[e - 1] == 0)
					arrayB[e - 1] = 1;
				else
					return false;
			}
			return true;
		}
	}

	public static class NamAnswer implements Solution {
		@Override
		public boolean solve(int[] arrayA) {
			int start = 0;
			int end = arrayA.length;
			while (start < end) {
				int current = (start + end) / 2;
				if (arrayA[current] >= 9) {
					end = current;
				} else if (arrayA[current] < 1) {
					start = current + 1;
				} else {
					return true;
				}
			}
			return false;
		}
	}

	public static class AnhAnswer1 implements Solution {
		@Override
		public boolean solve(int[] arrayA) {
			int result = 362880;
			for (int i : arrayA) {
				if (i > 9 || i < 1)
					return false;
				result = result / i;
			}
			return result == 1;
		}
	}

	public static class YoungAnswer implements Solution {

		@Override
		public boolean solve(int[] arrayA) {
			Arrays.sort(arrayA);
			return Arrays.equals(arrayA, new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });
		}

	}

	public interface Solution {
		boolean solve(int[] arrayA);
	}

	public static class Profiler {
		private long mStartTime;

		public Profiler() {

		}

		public void start() {
			mStartTime = System.nanoTime();
		}

		public long end() {
			return System.nanoTime() - mStartTime;
		}
	}
}
