package bm;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import bm.block_handling.BlockProcessor;
import bm.block_handling.ControllerField;
import bm.block_handling.ControllerPreview;
import bm.util.GlobalData;
import bm.view.CanvasFieldText;
import bm.view.CanvasPreviewText;

public class BlockManiaPOC extends JFrame
{
	private final static int FRAME_WIDTH  = 500;
	private final static int FRAME_HEIGHT = 600;

	JButton btn01, btn02, btn03, btn04, btn05, btn06, btn07, btn08;
	JTextPane  txtCanvasPreview, txtCanvasField;

	private ControllerMain controllerMain;
	// private CanvasField canvasField;

	public static JLabel lblCanvasPreview;
	public static JLabel lblCanvasField;

	private UserInput request = UserInput.NONE;

	public static enum UserInput
	{
		NONE, START_GAME, NEXT_BLOCK, ROTATE, FLIP, MOVE_LEFT, MOVE_RIGHT, MOVE_UP, MOVE_DOWN
	}

	public BlockManiaPOC()
	{
		super(GlobalData.APPLICATION_NAME);
		this.addKeyListener(new keyPressedEventHandler());

		Container cont = getContentPane();
		cont.setLayout(null);

		int width, height, X, Y;
		X = 5 ; Y = 5;
		width = 100 ; height = 200;

		// canvasField = CanvasField.getInstance();

		txtCanvasPreview = new JTextPane ();
		txtCanvasPreview.setFont(new Font("Consolas", Font.PLAIN, 16));
		CanvasPreviewText.getInstance().setCanvasPreview(txtCanvasPreview);
		txtCanvasPreview.setBounds(X,Y , width,height);
		cont.add(txtCanvasPreview);

		X += 20 + txtCanvasPreview.getWidth();
		width = 100 ; height = 400;

		txtCanvasField = new JTextPane ();
		txtCanvasField.setFont(new Font("Consolas", Font.PLAIN, 16));
		CanvasFieldText.getInstance().setCanvasField(txtCanvasField);
		txtCanvasField.setBounds(X,Y , width,height);
		cont.add(txtCanvasField);

		StyledDocument doc = txtCanvasField.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);

		X += 20 + txtCanvasField.getWidth();
		width = 50 ; height = 50;

		lblCanvasPreview = new JLabel();
		lblCanvasPreview.setBounds(X,Y , width,height);
		lblCanvasPreview.setOpaque(true) ; lblCanvasPreview.setBackground(Color.orange);
		lblCanvasPreview.setHorizontalAlignment(SwingConstants.CENTER);
		cont.add(lblCanvasPreview);

		X += 20 + lblCanvasPreview.getWidth();
		width = 100 ; height = 400;

		lblCanvasField = new JLabel();
		lblCanvasField.setBounds(X,Y , width,height);
		lblCanvasField.setOpaque(true) ; lblCanvasField.setBackground(Color.orange);
		lblCanvasField.setHorizontalAlignment(SwingConstants.CENTER);
		cont.add(lblCanvasField);

		X = 5;
		Y += height + 10;

		Action action01 = new CustomAction();

		btn01 = new JButton(action01);
		btn01.setText("Reset");
		width  = (int) btn01.getPreferredSize().getWidth();
		height = (int) btn01.getPreferredSize().getHeight();
		btn01.setBounds(X, Y, width, height);
		cont.add(btn01);

		X += width + 15;
		btn06 = new JButton(action01);
		btn06.setText("Next block");
		width  = (int) btn06.getPreferredSize().getWidth();
		height = (int) btn06.getPreferredSize().getHeight();
		btn06.setBounds(X, Y, width, height);
		cont.add(btn06);

		X = 5;
		Y += height + 10;

		btn02 = new JButton(action01);
		btn02.setText("Move Down");
		width  = (int) btn02.getPreferredSize().getWidth();
		height = (int) btn02.getPreferredSize().getHeight();
		btn02.setBounds(X, Y, width, height);
		cont.add(btn02);

		X += width + 15;
		btn03 = new JButton(action01);
		btn03.setText("Move Up");
		width  = (int) btn03.getPreferredSize().getWidth();
		height = (int) btn03.getPreferredSize().getHeight();
		btn03.setBounds(X, Y, width, height);
		cont.add(btn03);

		X = 5;
		Y += height + 10;

		btn07 = new JButton(action01);
		btn07.setText("Move Left");
		width  = (int) btn07.getPreferredSize().getWidth();
		height = (int) btn07.getPreferredSize().getHeight();
		btn07.setBounds(X, Y, width, height);
		cont.add(btn07);

		X += width + 15;
		btn08 = new JButton(action01);
		btn08.setText("Move Right");
		width  = (int) btn08.getPreferredSize().getWidth();
		height = (int) btn08.getPreferredSize().getHeight();
		btn08.setBounds(X, Y, width, height);
		cont.add(btn08);

		X = 5;
		Y += height + 10;

		btn04 = new JButton(action01);
		btn04.setText("Rotate");
		width  = (int) btn04.getPreferredSize().getWidth();
		height = (int) btn04.getPreferredSize().getHeight();
		btn04.setBounds(X, Y, width, height);
		cont.add(btn04);

		X += width + 15;
		btn05 = new JButton(action01);
		btn05.setText("Flip");
		width  = (int) btn05.getPreferredSize().getWidth();
		height = (int) btn05.getPreferredSize().getHeight();
		btn05.setBounds(X, Y, width, height);
		cont.add(btn05);

		controllerMain = ControllerMain.getInstance();
		controllerMain.setReferenceToGUI(this);

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
			@SuppressWarnings("unused")
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
			else if (obj == btn04) { handleButton04(); }
			else if (obj == btn05) { handleButton05(); }
			else if (obj == btn06) { handleButton06(); }
			else if (obj == btn07) { handleButton07(); }
			else if (obj == btn08) { handleButton08(); }
		}
	}

	void handleButton01()
	{
		ControllerField. getInstance().clearAllTiles();
		BlockProcessor.getInstance().removeBlock();

		controllerMain.handleGameTick();
	}

	void handleButton02()
	{
		setUserRequest(UserInput.MOVE_DOWN);
		controllerMain.handleGameTick();
	}

	void handleButton03()
	{
		setUserRequest(UserInput.MOVE_UP);
		controllerMain.handleGameTick();
	}

	void handleButton04()
	{
		setUserRequest(UserInput.ROTATE);
		controllerMain.handleGameTick();
	}

	void handleButton05()
	{
		setUserRequest(UserInput.FLIP);
		controllerMain.handleGameTick();
	}

	void handleButton06()
	{
		ControllerPreview.getInstance().loadNextBlock();
	}

	void handleButton07()
	{
		setUserRequest(UserInput.MOVE_LEFT);
		controllerMain.handleGameTick();
	}

	void handleButton08()
	{
		setUserRequest(UserInput.MOVE_RIGHT);
		controllerMain.handleGameTick();
	}

	class keyPressedEventHandler extends KeyAdapter
	{
		private int currentKey;

		@Override
		public void keyPressed(KeyEvent e)
		{
			// System.out.println(String.format("keyPressed : %s ", e.getKeyCode()));

			currentKey = e.getKeyCode();
			switch (currentKey)
			{
				//				case KeyEvent.VK_1 :
				//					switchToWelcomeState();
				//					break;
				//				case KeyEvent.VK_2 :
				//					switchToUserplayState();
				//					break;
				//				case KeyEvent.VK_3 :
				//					switchToComputerplayState();
				//					break;

				case KeyEvent.VK_S :
					setUserRequest(UserInput.START_GAME);
					break;
				case KeyEvent.VK_N :
					setUserRequest(UserInput.NEXT_BLOCK);
					break;

				case KeyEvent.VK_R :
					setUserRequest(UserInput.ROTATE);
					break;
				case KeyEvent.VK_F :
					setUserRequest(UserInput.FLIP);
					break;

				case KeyEvent.VK_UP :
					setUserRequest(UserInput.MOVE_UP);
					break;
				case KeyEvent.VK_DOWN :
					setUserRequest(UserInput.MOVE_DOWN);
					break;
				case KeyEvent.VK_LEFT :
					setUserRequest(UserInput.MOVE_LEFT);
					break;
				case KeyEvent.VK_RIGHT :
					setUserRequest(UserInput.MOVE_RIGHT);
					break;
				default:
			}
		}

		@Override
		public void keyReleased(KeyEvent e)
		{
			int kc = e.getKeyCode();
			if (kc == currentKey)
				setUserRequest(UserInput.NONE);
		}
	}

	public synchronized void setUserRequest(UserInput req)
	{
		request = req;
	}

	public synchronized UserInput getUserRequest()
	{
		return request;
	}

}
