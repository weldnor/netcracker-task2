package me.weldnor.domain;

public class Matrix implements Cloneable {
    private final long[][] data;

    public Matrix(int height, int width) {
        data = new long[height][width];
    }

    public Matrix(long[][] data) {
        this.data = data;
    }

    public double get(int x, int y) {
        return data[x][y];
    }

    public void set(int x, int y, long value) {
        data[x][y] = value;
    }

    public int getHeight() {
        return data.length;
    }

    public int getWidth() {
        return data[0].length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                sb.append(get(i, j));
                sb.append("\t");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
