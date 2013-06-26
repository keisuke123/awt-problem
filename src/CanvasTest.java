// applet��Ȥ�����
import java.applet.*;
// AWT��Ȥ����ᡤ
import java.awt.*;
// ���٥�ȶ�ư�ط��Υ��饹���Ѥ��뤿��
import java.awt.event.*;
// Vector���饹���Ѥ��뤿��
import java.util.*;

// ��ʬ�Υ��饹��������롥
class Line{
	// ������������X��ɸ��Y��ɸ�� int ���ݻ����롥
	public int start_x,start_y,end_x,end_y;
	// Line�Υ��󥹥ȥ饯��
	public Line(int x1,int x2,int x3,int x4){
		start_x=x1;
		start_y=x2;
		end_x=x3;
		end_y=x4;
	}
}
// ��¦��Frame��ľ�� paint����ΤǤϤʤ�, �������ΰ���롥
class MyCanvas extends Canvas{
	// Line��������ݻ�����Vector���饹���ѿ� lineArray�����
	Vector< Line> lineArray;
	// �ޥ�����ɥ�å��椫�ɤ����򼨤� boolean��(������)���ѿ�dragging�����
	boolean dragging;
	// ɽ�����뿧���ݻ������ѿ�
	Color lineColor;
	// ���󥹥ȥ饯�������
	public MyCanvas(){
		super();
		lineArray=new Vector<Line>();
		// �ɥ�å���ǤϤʤ�
		dragging=false;
		// ���ο��Ϲ���
		lineColor=Color.black;
		// GUI���ʤȡ�Event Listener���Ϣ�Ť���
		setSize(600,400);
		addKeyListener(new KL());
		addMouseListener(new ML());
		addMouseMotionListener(new MML());
	}

	Image offScreenImage;
	Graphics offScreenGraphics;

	public void update(Graphics g){
		if(offScreenImage==null){
			offScreenImage=createImage(600,400); // ���ե����꡼�󥤥᡼����600x400�Υ������Ǻ���
			offScreenGraphics=offScreenImage.getGraphics(); // ���ե����꡼�󥤥᡼�������褹�뤿��� Graphics ���֥�������
		}
		paint(offScreenGraphics); // ���β��̤Υ��᡼�����롥
		g.drawImage(offScreenImage,0,0,this); // ���᡼������ʪ�Υ����꡼��˽񤭹���
	}
	// offScreenImage�ν�ľ���򤹤�ݤ˸ƤФ��
	public void paint(Graphics g){
		int i;
		// ���(0,0)-(600,400)���ɤ��٤�
		g.setColor(Color.white);
		g.fillRect(0,0,600,400);
		// ��������
		g.setColor(lineColor);
		int size=lineArray.size();
		if(dragging) size--;
		for(i=0;i< size;i++){
			Line l=(Line)lineArray.elementAt(i);
			g.drawLine(l.start_x,l.start_y,l.end_x,l.end_y);
		}
		// �ޥ�����ɥ�å���λ���
		if(dragging){
			// �֤�����
			g.setColor(Color.red);
			// lines[lineCount] �����褹�롥
			Line l=(Line)lineArray.elementAt(i);
			g.drawLine(l.start_x,l.start_y,l.end_x,l.end_y);
		}
	}


	// Delete �ܥ��󤬲����줿���ν���
	public void deleteLine(){
		int size;
		if((size=lineArray.size())>0){
			lineArray.removeElementAt(size-1);
			repaint();
		}
	}
	// Clear �ܥ��󤬲����줿���ν���
	public void clearLine(){
		lineArray.removeAllElements();
		repaint();
	}



	class ML extends MouseAdapter{
		// MouseListener��������뤿��Υ᥽�å�
		public void mousePressed(MouseEvent e){
			// �����줿���Υޥ�����������ΰ��֤�����
			int mx=e.getX(),my=e.getY();
			// �ǥХå��Ѥ�ɽ��
			System.out.println("mousePressed("+e+","+mx+","+my+")");
			// ����lines��lineCount���ܤ���ʬ����Ͽ
			lineArray.addElement(new Line(mx,my,mx,my));
			// �ɥ�å���Ǥ��뤳�Ȥ򼨤�
			dragging=true;
			// ��ɽ���򤪤��ʤ�
			repaint();
		}
		// �ޥ����Υܥ���Υ���줿���Υ��٥��
		public void mouseReleased(MouseEvent e){
			// �ޥ�����������ΰ��֤�����
			int mx=e.getX(),my=e.getY();
			// �ǥХå��Ѥ�ɽ��
			System.out.println("mouseUp("+e+","+mx+","+my+")");
			// ����lines��lineCount���ܤλ������ѹ�
			Line l=(Line)lineArray.elementAt(lineArray.size()-1);
			l.end_x=mx;
			l.end_y=my;
			dragging=false;
			// ��ɽ���򤪤��ʤ�
			repaint();
		}
	}

	class MML extends MouseMotionAdapter{
		// MouseMotionListener��������뤿��Υ᥽�å�
		public void mouseDragged(MouseEvent e){
			// �ޥ�����������ΰ��֤�����
			int mx=e.getX(),my=e.getY();
			// �ǥХå��Ѥ�ɽ��
			System.out.println("mouseDrag("+e+","+mx+","+my+")");
			// ����lines��lineCount���ܤλ������ѹ�
			Line l=(Line)lineArray.elementAt(lineArray.size()-1);
			l.end_x=mx;
			l.end_y=my;
			// ��ɽ���򤪤��ʤ�
			repaint();
		}
	}
}

class KL extends KeyAdapter{
	// KeyListener��������뤿��Υ᥽�å�
	public void keyPressed(KeyEvent e){
		// ���٥�Ȥ��饭���Υ����ɤ���Ф�
		int key=e.getKeyChar();
		// �ǥХå��Ѥ�ɽ��
		System.out.println("keyPressed("+e+","+key+")");
		// ���Ϥ� 'q'�λ��Ͻ�λ����
		if(key=='q') System.exit(0);
	}
}


class CanvasTest extends Frame implements ActionListener{
	MenuBar menuBar;
	Menu fileMenu,editMenu;
	MyCanvas myCanvas;
	Button deleteButton,clearButton;

	class MyWindow extends WindowAdapter{
		@Override
		public void windowClosing(WindowEvent e){
			System.exit(0);
		}
	}

	CanvasTest(){
		super("CanvasTest");
		// �ѥͥ�(�ܥ���ʤɤ����֤��뤿�������ʪ)��ʬ��������롥
		Panel panel=new Panel();
		panel.setLayout(new FlowLayout());
		panel.add(deleteButton=new Button("Delete"));
		deleteButton.addActionListener(this);
		panel.add(clearButton=new Button("Clear"));
		clearButton.addActionListener(this);
		this.addWindowListener(new MyWindow());
		// BorderLayout���Ѥ��롥
		setLayout(new BorderLayout());
		// ������ Panel
		add(panel,"North");
		// ������ MyCanbas�����֤��롥
		add(myCanvas=new MyCanvas(),"South");
		// �������Ϥ�button�˲���ꤵ��ʤ��褦�ˤ��뤿��
		deleteButton.addKeyListener(new KL());
		clearButton.addKeyListener(new KL());
		// ���ʤ򤪤����Τ�Ŭ���Ȼפ��륵�����ˤ��롥
		setSize(getPreferredSize());
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e){
		System.out.println(e);
		Object source=e.getSource();
		// Delete�ܥ��󤬲����줿��
		if(source.equals(deleteButton)){
			myCanvas.deleteLine();
		}
		// Clear�ܥ��󤬲����줿��
		else if(source.equals(clearButton)){
			myCanvas.clearLine();
		}
	}
	public static void main(String args[]) {
		// CanvasTest�Υ��󥹥��󥹤���� frame������
		CanvasTest frame=new CanvasTest();
	}
}
