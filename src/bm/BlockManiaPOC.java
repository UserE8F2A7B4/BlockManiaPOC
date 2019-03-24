package bm;

import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import bm.block_handling.BlockProcessor;
import bm.block_handling.ControllerField;
import bm.block_handling.ControllerPreview;
import bm.util.GlobalData;

public class BlockManiaPOC extends JFrame
{
	private final static int FRAME_WIDTH  = 600;
	private final static int FRAME_HEIGHT = 700;

	private FlowMain flow;
	private ControllerMain cm;

	private JButton btn01, btn02, btn03, btn04, btn05, btn06, btn07, btn08, btn09;

	private static JTextPane  txtCanvasPreview, txtCanvasField;
	private static JLabel lblCanvasPreview, lblCanvasField;

	private Action actionKeybindings = new CustomAction();

	public enum UserInput
	{
		NONE, START_GAME, NEXT_BLOCK, ROTATE, FLIP, MOVE_LEFT, MOVE_RIGHT, MOVE_UP, MOVE_DOWN
	}

	public BlockManiaPOC()
	{
		super(GlobalData.APPLICATION_NAME);
		// this.addKeyListener(new keyPressedEventHandler());

		Container cont = getContentPane();
		cont.setLayout(null);

		JPanel panel = (JPanel) this.getContentPane();
		InputMap inputMap  = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = panel.getActionMap();
		addKeyBindings(inputMap, actionMap);

		int width, height, X, Y;
		X = 5 ; Y = 5;
		width = 100 ; height = 200;

		txtCanvasPreview = new JTextPane ();
		txtCanvasPreview.setFont(new Font("Consolas", Font.PLAIN, 16));
		txtCanvasPreview.setBounds(X,Y , width,height);
		cont.add(txtCanvasPreview);
//		txtCanvasPreview.setFocusable(false);
//		txtCanvasPreview.setEnabled(false);

		X += 20 + txtCanvasPreview.getWidth();
		width = 100 ; height = 400;

		txtCanvasField = new JTextPane ();
		txtCanvasField.setFont(new Font("Consolas", Font.PLAIN, 16));
		txtCanvasField.setBounds(X,Y , width,height);
		cont.add(txtCanvasField);
//		txtCanvasField.setFocusable(false);
//		txtCanvasField.setEnabled(false);

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
		width  = GlobalData.GAME_FIELD_WIDTH + 4;
		height = GlobalData.GAME_FIELD_HEIGHT + 4;

		lblCanvasField = new JLabel();
		lblCanvasField.setBounds(X,Y , width,height);
		lblCanvasField.setOpaque(true) ; lblCanvasField.setBackground(Color.orange);
		lblCanvasField.setHorizontalAlignment(SwingConstants.CENTER);
		lblCanvasField.setVerticalAlignment(SwingConstants.CENTER);
		cont.add(lblCanvasField);

		X = 5;
		Y += height + 80;

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

		X = 5;
		Y += height + 10;

		btn09 = new JButton(action01);
		btn09.setText("Clear rows");
		width  = (int) btn09.getPreferredSize().getWidth();
		height = (int) btn09.getPreferredSize().getHeight();
		btn09.setBounds(X, Y, width, height);
		cont.add(btn09);

//		btn01.setFocusable(false);
//		btn02.setFocusable(false);
//		btn03.setFocusable(false);
//		btn04.setFocusable(false);
//		btn05.setFocusable(false);
//		btn06.setFocusable(false);
//		btn07.setFocusable(false);
//		btn08.setFocusable(false);
//		btn09.setFocusable(false);

		flow = FlowMain.getInstance();
		cm = ControllerMain.getInstance();
		// controllerMain.setReferenceToGUI(this);

		X = 400;
		Y = 200;
		setBounds(X, Y, FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		requestFocus();
	}

	public static void main(String[] args)
	{
		Runnable runnable = BlockManiaPOC::new;
		EventQueue.invokeLater(runnable);
	}

	public static JLabel getLblCanvasPreview()
	{
		return lblCanvasPreview;
	}

	public static JLabel getLblCanvasField()
	{
		return lblCanvasField;
	}

	public static JTextPane getTxtCanvasPreview()
	{
		return txtCanvasPreview;
	}

	public static JTextPane getTxtCanvasField()
	{
		return txtCanvasField;
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
			else if (obj == btn09) { handleButton09(); }
		}
	}

	void handleButton01()
	{
		ControllerField. getInstance().clearAllTiles();
		BlockProcessor.getInstance().removeBlock();
		flow.handleGameTick();
	}

	void handleButton02()
	{
		cm.setUserRequest(UserInput.MOVE_DOWN);
		flow.handleGameTick();
	}

	void handleButton03()
	{
		cm.setUserRequest(UserInput.MOVE_UP);
		flow.handleGameTick();
	}

	void handleButton04()
	{
		cm.setUserRequest(UserInput.ROTATE);
		flow.handleGameTick();
	}

	void handleButton05()
	{
		cm.setUserRequest(UserInput.FLIP);
		flow.handleGameTick();
	}

	void handleButton06()
	{
		ControllerPreview.getInstance().loadNextBlock();
	}

	void handleButton07()
	{
		cm.setUserRequest(UserInput.MOVE_LEFT);
		flow.handleGameTick();
	}

	void handleButton08()
	{
		cm.setUserRequest(UserInput.MOVE_RIGHT);
		flow.handleGameTick();
	}

	void handleButton09()
	{
		//controllerMain.handleGameTick();
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
					cm.setUserRequest(UserInput.START_GAME);
					break;
				case KeyEvent.VK_N :
					cm.setUserRequest(UserInput.NEXT_BLOCK);
					break;

				case KeyEvent.VK_R :
					cm.setUserRequest(UserInput.ROTATE);
					break;
				case KeyEvent.VK_F :
					cm.setUserRequest(UserInput.FLIP);
					break;

				case KeyEvent.VK_UP :
					cm.setUserRequest(UserInput.MOVE_UP);
					break;
				case KeyEvent.VK_DOWN :
					cm.setUserRequest(UserInput.MOVE_DOWN);
					break;
				case KeyEvent.VK_LEFT :
					cm.setUserRequest(UserInput.MOVE_LEFT);
					break;
				case KeyEvent.VK_RIGHT :
					cm.setUserRequest(UserInput.MOVE_RIGHT);
					break;
				default:
			}
		}

		@Override
		public void keyReleased(KeyEvent e)
		{
			int kc = e.getKeyCode();
			if (kc == currentKey)
			{
				cm.setUserRequest(UserInput.NONE);
			}
		}
	}

	//---

	private void addKeyBindings(InputMap inputMap, ActionMap actionMap)
	{
		KeyStroke keyLeft = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0);
		inputMap.put(keyLeft, "keyLeft");
		actionMap.put("keyLeft", new CustomKeyLeftAction());

		KeyStroke keyRight = KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0);
		inputMap.put(keyRight, "keyRight");
		actionMap.put("keyRight", new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("RIGHT");
			}
		});

//		KeyStroke keyDown = KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0);
//		inputMap.put(keyDown, "keyDown");
//		actionMap.put("keyDown", new FunctionalAction(ae -> { down(); }));

		addKeyBinding(inputMap, actionMap, KeyEvent.VK_DOWN, "down", (evt) -> down());

		KeyStroke keyUp   = KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0);
		inputMap.put(keyUp, "keyUp");
		actionMap.put("keyUp", new FunctionalAction(ae -> { up(); }));
	}

	public void addKeyBinding(InputMap im, ActionMap am, int keyCode, String id, ActionListener actionListener)
	{
		im.put(KeyStroke.getKeyStroke(keyCode, 0), id);
		am.put(id, new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				actionListener.actionPerformed(e);
			}
		});
	}

	public class CustomKeyLeftAction extends AbstractAction
	{
		@Override
		public void actionPerformed(ActionEvent ae)
		{
			System.out.println("LEFT");
		}
	}

	void up()
	{
		System.out.println("UP");
	}

	void down()
	{
		System.out.println("DOWN");
	}


	public class FunctionalAction extends AbstractAction
	{
		ActionListener myAction;

		public FunctionalAction(ActionListener customAction)
		{
			this.myAction = customAction;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			myAction.actionPerformed(e);
		}

		//		public ActionListener getMyAction()
		//		{
		//			return myAction;
		//		}
		//
		//		public void setMyAction(ActionListener myAction)
		//		{
		//			this.myAction = myAction;
		//		}
	}



}
