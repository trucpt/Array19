package com.scs71.exercises.array19;

public class Main {

    /**
     * Problem:
     * Input: given an unordered array (called A) with 9 elements.
     * Output: true if A contains all values from 1..9; false otherwise
     **/


    public static void main(String[] args) {
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
