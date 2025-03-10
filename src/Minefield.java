import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Minefield {
  private final int x;
  private final int y;
  private final int mineCount;
  private Tile[][] minefield;
  Random r = new Random();

  public Minefield(int x, int y, int mineCount) {
    this.x = x;
    this.y = y;
    this.mineCount = mineCount;
    this.minefield = new Tile[x][y];

    initMinefield(); // Auto-Init
  }

  public void initMinefield() {
    for (int ind_x = 0; ind_x < x; ind_x++) {
      for (int ind_y = 0; ind_y < y; ind_y++) {
        minefield[ind_x][ind_y] = new Tile(ind_x, ind_y, false);
      }
    }
    setMines();
    for (int ind_x = 0; ind_x < x; ind_x++) {
      for (int ind_y = 0; ind_y < y; ind_y++) {
        minefield[ind_x][ind_y].calcNeighbors(minefield, x, y);
        minefield[ind_x][ind_y].calcMineNeighbors();
      }
    }
  }

  public void setMines() { // Tile t
    for (int mineTally = 0; mineTally < mineCount; mineTally++) {
      int rand_x = r.nextInt(0, x);
      int rand_y = r.nextInt(0, y);
      if (!minefield[rand_x][rand_y].isMine) {
        minefield[rand_x][rand_y].isMine = true;
      } else {
        mineTally--;
      }
    }
  }

  public Tile getTile(int x, int y) {
    if ((x < 0 || x > this.x) || ( y < 0 || y > this.y)) {
      throw new IndexOutOfBoundsException();
    } else {
      return minefield[x][y];
    }
  }

  public int getX() {return x;}

  public int getY() {return y;}

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("  ");
    for (int i = 0; i < x; i++) {
      s.append(i + " ");
    }
    s.append("\n");

    for (int ind_y = 0; ind_y < x; ind_y++) {
      s.append(ind_y + " ");

      for (int ind_x = 0; ind_x < y; ind_x++) {
        Tile currTile = minefield[ind_x][ind_y];

        if (currTile.isMine) {
          s.append("X "); // Mine :(
        } else {
          s.append("O "); // Not a Mine :)
        }
      }
      s.append('\n');
    }

    return s.toString();
  }
}
