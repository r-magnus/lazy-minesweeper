import java.util.ArrayList;

public class Tile {
  public Tile(int x, int y, boolean isMine) {
    this.x = x;
    this.y = y;

    this.explored = false;
    this.flagged = false;
    this.isMine = isMine;

    this.mineNeighbors = 0;
    this.neighbors = new ArrayList<>();
  }

  public int x, y;
  public boolean isMine;
  public boolean explored;
  public boolean flagged;
  public ArrayList<Tile> neighbors;
  public int mineNeighbors;

  public void calcNeighbors(Tile[][] minefield, int max_x, int max_y) {
    for (int x_diff: new int[]{1, 0, -1}) {
      for (int y_diff : new int[]{1, 0, -1}) {
        if ((this.x + x_diff >= 0 && this.y + y_diff >= 0)
                && (this.x + x_diff < max_x && this.y + y_diff < max_y)
                && (!(x_diff == 0 && y_diff == 0))) {
          neighbors.add(minefield[this.x + x_diff][this.y + y_diff]);
        }
      }
    }
  }

  public void calcMineNeighbors() {
    for (Tile neighbor: neighbors) {
      if (neighbor.isMine) {
        this.mineNeighbors++;
      }
    }
  }

  public ArrayList<Tile> getNeighbors() {
    return neighbors;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();

    s.append("x: " + this.x + " | y: " + this.y);
    s.append("\nexplored: " + this.explored);
    s.append("\nflagged: " + this.flagged);
    s.append("\nisMine: " + this.isMine);
    s.append("\nmineNeighbors: " + this.mineNeighbors);
    s.append("\n");

    return s.toString();
  }
}
