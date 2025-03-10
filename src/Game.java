public class Game {
  public Game(int x, int y, int mineCount) {
    this.m = new Minefield(x, y, mineCount);
    this.lost = false;
  }

  private Minefield m;
  private boolean lost;
  public boolean firstClick = true; // TODO: Generate game on first click

  public void exploreAndCheck(int x, int y) { // boolean: Safe?
    Tile t = m.getTile(x, y);
    t.explored = true;

    if (t.isMine) {
      lost = true;
    }

    for (Tile neighbor: t.getNeighbors()) {
      if (!neighbor.explored && neighbor.mineNeighbors == 0 && !neighbor.isMine) {
        exploreAndCheck(neighbor.x, neighbor.y); // recursive 'break'
      } else {
        if (t.mineNeighbors == 0) {
          neighbor.explored = true;
        }
      }
    }
  }

  public boolean checkGameFinished() { // Iterative check to see if the game is over, AKA won.
    if (lost) {
      return true;
    }

    for (int y = 0; y < m.getY(); y++) {
      for (int x = 0; x < m.getX(); x++) {
        if (!m.getTile(x, y).explored) {
          return false;
        }
      }
    }
    return true;
  }

  public Minefield getMinefield() {
    return m;
  }

  public boolean isLost() {
    return lost;
  }

  public int getX() {
    return m.getX();
  }

  public int getY() {
    return m.getY();
  }
}
