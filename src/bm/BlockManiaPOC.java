package bm;

import bm.block_handling.BlockProcessor;
import bm.block_handling.ControllerField;
import bm.block_handling.ControllerPreview;
import bm.util.GlobalData;
import bm.util.Util;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

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

		Container cont = getContentPane();
		cont.setLayout(null);

		JPanel panel = (JPanel) cont;
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

		X += 20 + txtCanvasPreview.getWidth();
		width = 100 ; height = 400;

		txtCanvasField = new JTextPane ();
		txtCanvasField.setFont(new Font("Consolas", Font.PLAIN, 16));
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

	private void handleButton01()
	{
		ControllerField. getInstance().clearAllTiles();
		BlockProcessor.getInstance().removeBlock();
		flow.handleGameTick();
	}

	private void handleButton02()
	{
		cm.setUserRequest(UserInput.MOVE_DOWN);
		flow.handleGameTick();
	}

	private void handleButton03()
	{
		cm.setUserRequest(UserInput.MOVE_UP);
		flow.handleGameTick();
	}

	private void handleButton04()
	{
		cm.setUserRequest(UserInput.ROTATE);
		flow.handleGameTick();
	}

	private void handleButton05()
	{
		cm.setUserRequest(UserInput.FLIP);
		flow.handleGameTick();
	}

	private void handleButton06()
	{
		ControllerPreview.getInstance().loadNextBlock();
	}

	private void handleButton07()
	{
		cm.setUserRequest(UserInput.MOVE_LEFT);
		flow.handleGameTick();
	}

	private void handleButton08()
	{
		cm.setUserRequest(UserInput.MOVE_RIGHT);
		flow.handleGameTick();
	}

	private void handleButton09()
	{
		//controllerMain.handleGameTick();
	}


//			currentKey = e.getKeyCode();
//			switch (currentKey)
//			{
//				//				case KeyEvent.VK_1 :
//				//					switchToWelcomeState();
//				//					break;
//				//				case KeyEvent.VK_2 :
//				//					switchToUserplayState();
//				//					break;
//				//				case KeyEvent.VK_3 :
//				//					switchToComputerplayState();
//				//					break;
//


	private void addKeyBindings(InputMap inputMap, ActionMap actionMap)
	{
		Util.createKeyBindingOnKeyPress(  inputMap, actionMap, KeyEvent.VK_UP,   "UP_PRESS",       (actionEvent) -> cm.setUserRequest(UserInput.MOVE_UP));
		Util.createKeyBindingOnKeyRelease(inputMap, actionMap, KeyEvent.VK_UP,   "UP_RELEASE",     (actionEvent) -> cm.setUserRequest(UserInput.NONE));

		Util.createKeyBindingOnKeyPress(  inputMap, actionMap, KeyEvent.VK_DOWN, "DOWN_PRESS",     (actionEvent) -> cm.setUserRequest(UserInput.MOVE_DOWN));
		Util.createKeyBindingOnKeyRelease(inputMap, actionMap, KeyEvent.VK_DOWN, "DOWN_RELEASE",   (actionEvent) -> cm.setUserRequest(UserInput.NONE));

		Util.createKeyBindingOnKeyPress(  inputMap, actionMap, KeyEvent.VK_LEFT, "LEFT_PRESS",     (actionEvent) -> cm.setUserRequest(UserInput.MOVE_LEFT));
		Util.createKeyBindingOnKeyRelease(inputMap, actionMap, KeyEvent.VK_LEFT, "LEFT_RELEASE",   (actionEvent) -> cm.setUserRequest(UserInput.NONE));

		Util.createKeyBindingOnKeyPress(  inputMap, actionMap, KeyEvent.VK_RIGHT,"RIGHT_PRESS",    (actionEvent) -> cm.setUserRequest(UserInput.MOVE_RIGHT));
		Util.createKeyBindingOnKeyRelease(inputMap, actionMap, KeyEvent.VK_RIGHT,"RIGHT_RELEASE",  (actionEvent) -> cm.setUserRequest(UserInput.NONE));

		Util.createKeyBindingOnKeyPress(  inputMap, actionMap, KeyEvent.VK_R,    "ROTATE_PRESS",   (actionEvent) -> cm.setUserRequest(UserInput.ROTATE));
		Util.createKeyBindingOnKeyRelease(inputMap, actionMap, KeyEvent.VK_R,    "ROTATE_RELEASE", (actionEvent) -> cm.setUserRequest(UserInput.NONE));

		Util.createKeyBindingOnKeyPress(  inputMap, actionMap, KeyEvent.VK_F,    "FIP_PRESS",      (actionEvent) -> cm.setUserRequest(UserInput.FLIP));
		Util.createKeyBindingOnKeyRelease(inputMap, actionMap, KeyEvent.VK_F,    "FIP_RELEASE",    (actionEvent) -> cm.setUserRequest(UserInput.NONE));

		Util.createKeyBindingOnKeyPress(  inputMap, actionMap, KeyEvent.VK_S,    "START_PRESS",    (actionEvent) -> cm.setUserRequest(UserInput.START_GAME));
		Util.createKeyBindingOnKeyRelease(inputMap, actionMap, KeyEvent.VK_S,    "START_RELEASE",  (actionEvent) -> cm.setUserRequest(UserInput.NONE));

		Util.createKeyBindingOnKeyPress(  inputMap, actionMap, KeyEvent.VK_N,    "NEXT_PRESS",     (actionEvent) -> cm.setUserRequest(UserInput.NEXT_BLOCK));
		Util.createKeyBindingOnKeyRelease(inputMap, actionMap, KeyEvent.VK_N,    "NEXT_RELEASE",   (actionEvent) -> cm.setUserRequest(UserInput.NONE));
	}

}
