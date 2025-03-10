import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class VisualGrid {
  public VisualGrid(Game g) {
    this.g = g;
    JFrame f = new JFrame("Minesweeper");

    JPanel jp = new JPanel();
    this.jp = jp;
    jp.setLayout(new GridLayout(g.getX(), g.getY()));
    for (int i = 0; i < g.getX(); i++) {
      for (int j = 0; j < g.getY(); j++) {
        jp.add(initTileIcon()); // temp
      }
    }
    jp.setVisible(true);

    f.add(jp);
    f.setSize(320,320);
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setResizable(false);
    f.pack(); // temp
    f.setVisible(true);
  }
  private Game g;
  private JPanel jp;

  private JLabel initTileIcon() {
    JLabel j1 = new JLabel();
    j1.setIcon(new ImageIcon("src/sprites/tile_unexplored.jpg"));
    j1.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        JLabel l = (JLabel) e.getSource();
        System.out.println(l.toString()); // debug

        int tile_x = l.getX() / 16;
        int tile_y = l.getY() / 16;

        if (SwingUtilities.isLeftMouseButton(e)) {
          g.exploreAndCheck(tile_x, tile_y);
          for (Component c : jp.getComponents()) { // NOTE: Currently just checking status of all Tiles upon click
            if (c.getClass().equals(JLabel.class)) {
              JLabel l_temp = (JLabel) c;
              checkTileStatus(g, l_temp, e);
            }
          }
        }
        checkTileStatus(g, l, e); // Visual update
        boolean finished = g.checkGameFinished();
        // TODO: win/loss logic
      }
    });
    j1.setVisible(true);

    return j1;
  }

  private void checkTileStatus(Game g, JLabel l, MouseEvent e) {
    int tile_x = l.getX() / 16; // Magic number for pixel sprite size
    int tile_y = l.getY() / 16;
    Minefield m = g.getMinefield();
    Tile t = m.getTile(tile_x, tile_y);

    // Left Click
    if (SwingUtilities.isLeftMouseButton(e)) {
      if (!t.flagged) {
        if (t.explored) {
          l.setIcon(new ImageIcon(String.format("src/sprites/tile_explored%d.jpg", t.mineNeighbors)));
        }
        if (t.isMine && t.explored) {
          l.setIcon(new ImageIcon("src/sprites/mine_explored.jpg"));
        }
      }
    }
    // Right Click
    if (SwingUtilities.isRightMouseButton(e)) {
      if (!t.explored || t.flagged) {
        t.flagged = !t.flagged;
        if (t.flagged) {
          l.setIcon(new ImageIcon("src/sprites/tile_flagged.jpg"));
        } else {
          l.setIcon(new ImageIcon("src/sprites/tile_unexplored.jpg"));
        }
      }
    }
    // Middle Click
    if (SwingUtilities.isMiddleMouseButton(e)) { // TODO: Not working with updating ImageIcon, might need to place in MouseListener
      boolean canBreak = true;
      for (Tile neighbor : t.neighbors) { // Check to see if all neighboring mines are flagged
        if (neighbor.isMine && !neighbor.flagged) {
          canBreak = false;
        }
      } // TESTING STUB
      if (canBreak) { // If so, break neighbors.
        for (Tile neighbor : t.neighbors) {
          neighbor.explored = true;
        }
      }
    }
  }
}
