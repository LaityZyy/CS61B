package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int N;
    private final WeightedQuickUnionUF sites;
    // sites without bottomSite
    private final WeightedQuickUnionUF sites2;
    private final int topSite;
    private final int bottomSite;
    private final boolean[][] flagOpen;
    private int numOpen;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        this.topSite = N * N;
        this.bottomSite = N * N + 1;

        sites = new WeightedQuickUnionUF(N * N + 2);
        for (int i = 0; i < N; i++) {
            sites.union(bottomSite, xyTo1D(0, i));
        }
        for (int i = 0; i < N; i++) {
            sites.union(topSite, xyTo1D(N - 1, i));
        }

        sites2 = new WeightedQuickUnionUF(N * N + 1);
        for (int i = 0; i < N; i++) {
            sites2.union(topSite, xyTo1D(0, i));
        }

        this.flagOpen = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                flagOpen[i][j] = false;
            }
        }

        this.numOpen = 0;
    }

    private int xyTo1D(int row, int col) {
        return row * N + col;
    }

    private void validateRange(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
    }

    public void open(int row, int col) {
        validateRange(row, col);
        if (flagOpen[row][col]) {
            return;
        }
        numOpen++;
        flagOpen[row][col] = true;
        unionOpenNeighbor(row, col, row + 1, col);
        unionOpenNeighbor(row, col, row - 1, col);
        unionOpenNeighbor(row, col, row, col + 1);
        unionOpenNeighbor(row, col, row, col - 1);
    }

    private void unionOpenNeighbor(int row, int col, int newRow, int newCol) {
        if (newRow < 0 || newRow >= N || newCol < 0 || newCol >= N) {
            return;
        }
        if (flagOpen[newRow][newCol]) {
            sites.union(xyTo1D(row, col), xyTo1D(newRow, newCol));
            sites2.union(xyTo1D(row, col), xyTo1D(newRow, newCol));
        }
    }

    public boolean isOpen(int row, int col) {
        validateRange(row, col);
        return flagOpen[row][col];
    }

    public boolean isFull(int row, int col) {
        validateRange(row, col);
        if (!flagOpen[row][col]) {
            return false;
        }
        return sites2.connected(xyTo1D(row, col), topSite);
    }

    public int numberOfOpenSites() {
        return numOpen;
    }

    public boolean percolates() {
        if (numOpen == 0) {
            return false;
        }
        return sites.connected(topSite, bottomSite);
    }

    public static void main(String[] argv) {

    }

}
