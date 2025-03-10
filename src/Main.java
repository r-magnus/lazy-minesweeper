import java.io.InputStreamReader;

public class Main { // NOTE: CTRL + SHIFT + F9 to recompile
  public static void main(String[] args) {
    // temp testing params
    int x = 25;
    int y = 25;
    int mineCount = 75;

    Game g = new Game(x, y, mineCount);
    Minefield m = g.getMinefield();
    System.out.println(m.toString()); // debug

    VisualGrid p = new VisualGrid(g);

//    NOTE: General workflow here.
//    g.exploreAndCheck(0, 0);
//    System.out.println(g.checkGameFinished());
//    System.out.println(m.toString()); // debug
  }
}
