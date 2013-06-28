import java.awt.*;
import java.awt.event.*;

class C extends Canvas{
 int s=600,x,y;
 Image I;
 Graphics G;
 C(){
  super();
  x=y=s/2;
  setSize(s,s);
  addMouseMotionListener(new MouseMotionListener(){
   public void mouseDragged(MouseEvent e) {
    x=e.getX();y=e.getY();
    repaint();
   }
   public void mouseMoved(MouseEvent e) {
    x=y=300;
    repaint();
   }
  });
 }
 public void update(Graphics g){
  paint(G=(I=createImage(s,s)).getGraphics());
  g.drawImage(I,0,0,this);
 }
 public void paint(Graphics g){
  g.drawLine(s/2,s/2,x,y);
 }
}
class CT extends Frame{
 class W extends WindowAdapter{
  public void windowClosing(WindowEvent e){
   System.exit(0);
  }
 }
 CT(){
  addWindowListener(new W());
  add(new C());
  setSize(600,600);
  setVisible(1>0);
  setResizable(1<0);
 }
 public static void main(String[] a){
  new CT();
 }
}
