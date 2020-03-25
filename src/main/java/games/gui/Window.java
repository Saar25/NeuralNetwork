package games.gui;

import javax.swing.*;

public class Window {

    private final JFrame jFrame;

    public Window(String title) {
        this.jFrame = new JFrame(title);
    }

    public void initialized(int width, int height, boolean resizeable) {
        this.jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jFrame.setSize(width, height);
        this.jFrame.setLocation(200, 50);
        this.jFrame.setResizable(resizeable);
        this.jFrame.setVisible(true);
    }

    public void add(JComponent component) {
        this.jFrame.add(component);
    }

    public void repaint() {
        this.jFrame.repaint();
    }

}
