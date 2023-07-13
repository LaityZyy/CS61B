package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */


/**
 * 有点烦人啊这个功能， 就是算坐标然后填充就得了， 考验两个东西
 * 一是能正确地计算对坐标，二是熟练地使用那个东西进行填充及绘画，先不写了
 * 我看一堆人都没写lab5
 */




public class HexWorld {
    static final int WIDTH = 80;
    static final int HEIGHT = 50;
    public static final long SEED = 2873123;
    public static Random RANDOM = new Random(SEED);

    private static class Position {
        public int x;
        public int y;
        public Position(int X, int Y) {
            x = X;
            y = Y;
        }
    }

    /**
     * 1.第一步要做的就是在给定位置画给定大小的六边形，至于随机颜色之类的东西，在实现基本功能的基础上再研究
     * 2.第二步也很关键，就是研究出来到底应该在哪些地方画，然后用1实现的方法画出想要的图形
     */
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(3);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.NOTHING;
            default: return Tileset.NOTHING;
        }
    }
    private static void helper(TETile[][] world, Position p, int s) {
        int x = p.x;
        int y = p.y;//左下角的坐标
        TETile random = randomTile();
        for (int i = 0; i < 2 * s; i++) {
            //第几行, 第几列, 每行有几个
            int r = x + i;
            int c, n;
            if (i < s) {
                c = y - i;
                n = s + 2 * i;
            } else {
                c = y - s + 1 + i - s;
                n = s + 2 * (s + s - i - 1);
            }
            for (int j = 0; j < n; j++) {
                world[i][j] = random;
            }
        }
    }
    /**
     * public static void addHexagon(TETile[][] world, Position p, int s, TETile t),
     * where Position is a very simple class with two variables p.x and p.y and no methods.
     * p specifies the lower left corner of the hexagon.
     */
    public static void addHexagon(TETile[][] world, Position p, int s) {
        int x = p.x;
        int y = p.y;
    }

    public static void main(String[] args) {
        int s = 3;
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] randomTiles = new TETile[WIDTH][HEIGHT];

        Position p = new Position(20, 10);
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                randomTiles[x][y] = Tileset.NOTHING;
            }
        }
        addHexagon(randomTiles, p, s);
        ter.renderFrame(randomTiles);
    }

}
