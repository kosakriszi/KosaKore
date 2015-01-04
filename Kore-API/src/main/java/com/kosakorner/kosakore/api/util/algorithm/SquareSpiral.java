package com.kosakorner.kosakore.api.util.algorithm;

public class SquareSpiral {

    public static int[] get(int index) {
        double intRoot = (int) Math.floor(Math.sqrt(index));
        double x = (Math.round(intRoot / 2) * Math.pow(-1, intRoot + 1)) + (Math.pow(-1, intRoot + 1) * (((intRoot * (intRoot + 1)) - index) - Math.abs((intRoot * (intRoot + 1)) - index)) / 2);
        double y = (Math.round(intRoot / 2) * Math.pow(-1, intRoot)) + (Math.pow(-1, intRoot + 1) * (((intRoot * (intRoot + 1)) - index) + Math.abs((intRoot * (intRoot + 1)) - index)) / 2);
        return new int[]{(int) x, (int) y};
    }

}
