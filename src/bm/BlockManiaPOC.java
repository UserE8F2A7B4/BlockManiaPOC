package bm;

import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class BlockManiaPOC extends JFrame
{
  private final static int FRAME_WIDTH  = 550;
  private final static int FRAME_HEIGHT = 300;  
  JButton btn01, btn02, btn03;
  
  public BlockManiaPOC()
  {       
    super("BlockManiaPOC");
    
    Container cont = getContentPane();
    cont.setLayout(null);
    
    int width, height, X, Y;         
    X = 5 ; Y = 5;    
    width = 500 ; height = 200;
    JTextArea txt01 = new JTextArea();    
    JScrollPane sp2 = new JScrollPane(txt01, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    sp2.setBounds(X,Y , width,height);
    cont.add(sp2);
    
    Action action01 = new CustomAction();
    
    Y += height + 10;    
    btn01 = new JButton(action01);
    btn01.setText("button 01");
    width  = (int) btn01.getPreferredSize().getWidth();
    height = (int) btn01.getPreferredSize().getHeight();
    btn01.setBounds(X, Y, width, height);
    cont.add(btn01);
    
    X += width + 30;    
    btn02 = new JButton(action01);    
    btn02.setText("button 02");
    width  = (int) btn02.getPreferredSize().getWidth();
    height = (int) btn02.getPreferredSize().getHeight();
    btn02.setBounds(X, Y, width, height);
    cont.add(btn02);    
    
    X += width + 30;    
    btn03 = new JButton(action01);    
    btn03.setText("button 03");
    width  = (int) btn03.getPreferredSize().getWidth();
    height = (int) btn03.getPreferredSize().getHeight();
    btn03.setBounds(X, Y, width, height);
    cont.add(btn03);  
    
    X = 400;
    Y = 200;
    setBounds(X, Y, FRAME_WIDTH, FRAME_HEIGHT);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    setVisible(true);
  } 
  
  public static void main(String[] args)
  {
    Runnable runnable = new Runnable()
    {
      @Override
      public void run() { new BlockManiaPOC(); }
    };
    EventQueue.invokeLater(runnable);
  }

  public class CustomAction extends AbstractAction
  {
    @Override
    public void actionPerformed(ActionEvent ae)
    {     
      Object obj = ae.getSource();     
      if      (obj == btn01) { handleButton01(); }
      else if (obj == btn02) { handleButton02(); }
      else if (obj == btn03) { handleButton03(); }
    }
  } 
  
  void handleButton01()
  {
    System.err.println("Button 01 clicked.");
  }
  
  void handleButton02()
  {
    System.err.println("Button 02 clicked.");
  }
  
  void handleButton03()
  {
    System.err.println("Button 03 clicked.");
  }
  
}
