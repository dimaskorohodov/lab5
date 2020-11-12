package ua.kpi;

import javax.swing.*;
import java.awt.*;

public class ChartsDrawer extends JPanel {
    int xg[] =  Up7.x;
    int yg[] =  Up7.y;
    int ng = Up7.n;

    @Override
    protected void paintComponent(Graphics gh) {
        Graphics2D drp = (Graphics2D)gh;
        drp.drawLine(20, 340, 20, 20);
        drp.drawLine(20, 340, 460, 340);
        drp.drawPolyline(xg, yg, ng);
    }
}

  class Up7 extends JFrame{
    public  static int x[] =  {4, 9, 14, 19, 24,29,34,39,44,49,54,59,64,69,74};
    public  static int y[] =  {164, 218, 297, 487, 142,172,225,214,261,308,363,453,491,531,644};
    public static int n = 5;

    public Up7 () {
        super("График по точкам");
        JPanel jcp = new JPanel(new BorderLayout());
        setContentPane(jcp);
        jcp.add(new ChartsDrawer (), BorderLayout.CENTER);
        jcp.setBackground(Color.gray);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args)   {
        new Up7().setVisible(true);
    }
}