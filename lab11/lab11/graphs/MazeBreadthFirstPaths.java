package lab11.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */

    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(s);
        marked[s] = true;
        announce();
        while (!q.isEmpty()) {
            int v = q.remove();
            announce();
            if (v == t) {
                targetFound = true;
                return;
            }
            for (int x : maze.adj(v)) {
                if (!marked[x]) {
                    marked[x] = true;
                    edgeTo[x] = v;
                    distTo[x] = distTo[v] + 1;
                    q.add(x);
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

