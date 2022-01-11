/*
 * Nama      : Fauzyah Hadirahma
 * NPM       : 20191310070
 * Materi    : Curve Bezier 
 * Tanggal   : 6 Agustus 2021
 */
package beziier2;

/**
 *
 * @author Fauzyah
 */
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.GeneralPath;


public class Main extends Frame
implements MouseListener, MouseMotionListener {
     
    private int[] xs = { 150, 150, 300, 400 };
    private int[] ys = { 150, 150, 300, 400 };
    private int dragIndex = NOT_DRAGGING;
    private final static int NEIGHBORHOOD = 30;
    private final static int NOT_DRAGGING = -10;
    
    public static void main(String[] args) {
        new Main().setVisible(true);
    }
    
    public Main() {
        setSize(600, 500);
        addMouseListener(this);
        addMouseMotionListener(this);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        }); 
    }
    
    public void paint(Graphics g) {
        for (int i=0; i<4; i++) {
            if (i==0 || i==3)
                g.setColor(Color.blue);
            else
                g.setColor(Color.cyan);
            g.fillOval(xs[i] - 6, ys[i] - 6, 12, 12);
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.red);
        GeneralPath path = new GeneralPath();
        path.moveTo(xs[0], ys[0]);
        path.curveTo(xs[1], ys[1], 
                xs[2], ys[2], xs[3], ys[3]);
        g2d.draw(path);
    }
    
    public void mousePressed(MouseEvent e) {
        dragIndex = NOT_DRAGGING;
        int minDistance = Integer.MAX_VALUE;
        int indexOfClosestPoint = -1;
        for (int i=0; i<4; i++) {
            int deltaX = xs[i] - e.getX();
            int deltaY = ys[i] - e.getY();
            int distance = (int)
                    (Math.sqrt(deltaX *
                    deltaX + deltaY * deltaY));
            if (distance < minDistance) {
                minDistance = distance;
                indexOfClosestPoint = i;
            }
        }
        if (minDistance > NEIGHBORHOOD)
            return;
        
        dragIndex = indexOfClosestPoint;
    }
    
    public void mouseReleased(MouseEvent e) {
        if (dragIndex == NOT_DRAGGING)
            return;
        xs[dragIndex] = e.getX();
        ys[dragIndex] = e.getY();
        dragIndex = NOT_DRAGGING;
        repaint();
    }
    
    public void mouseDragged(MouseEvent e) {
        if (dragIndex == NOT_DRAGGING)
            return;
        xs[dragIndex] = e.getX();
        ys[dragIndex] = e.getY();
        repaint();
    }
    
    public void mouseClicked(MouseEvent e) {
    }
    
    public void mouseEntered(MouseEvent e) {
    }
    
    public void mouseExited(MouseEvent e) {
    }
    
    public void mouseMoved(MouseEvent e) {
    }
}
