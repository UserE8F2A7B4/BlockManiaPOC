package bm;

import bm.algemeen.ControllerMain;
import bm.util.game_loop.GameLoopTimer;
import bm.util.GlobalData;
import bm.util.Util;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.KeyEvent;

public class BlockManiaPOC extends JFrame
{
	private final static int FRAME_WIDTH = 700;
	private final static int FRAME_HEIGHT = 700;

  private static JTextPane txtCanvasPreview, txtCanvasField;
  private static JLabel lblCanvasPreview, lblCanvasField;

  private ControllerMain cm;

  public enum UserInput
	{
		NONE, INTRO, USER_PLAY, DEMO_PLAY, NEXT_BLOCK, ROTATE, FLIP, MOVE_LEFT, MOVE_RIGHT, MOVE_UP, MOVE_DOWN
	}

	public BlockManiaPOC()
	{
		super(GlobalData.APPLICATION_NAME);

		Container cont = getContentPane();
		cont.setLayout(null);

		JPanel panel = (JPanel) cont;
		InputMap inputMap = panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = panel.getActionMap();
		addKeyBindings(inputMap, actionMap);

		int width, height, X, Y;
		X = 5;
		Y = 5;
		width = 100;
		height = 200;

		txtCanvasPreview = new JTextPane();
		txtCanvasPreview.setFont(new Font("Consolas", Font.PLAIN, 16));
		txtCanvasPreview.setBounds(X, Y, width, height);
		cont.add(txtCanvasPreview);

		X += 20 + txtCanvasPreview.getWidth();
		width = 100;
		height = 400;

		txtCanvasField = new JTextPane();
		txtCanvasField.setFont(new Font("Consolas", Font.PLAIN, 16));
		txtCanvasField.setBounds(X, Y, width, height);
		cont.add(txtCanvasField);

		StyledDocument doc = txtCanvasField.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);

		X += 60 + txtCanvasField.getWidth();
		width = GlobalData.PREVIEW_WIDTH + 4;
		height = GlobalData.PREVIEW_HEIGHT + 4;

		lblCanvasPreview = new JLabel();
		lblCanvasPreview.setBounds(X, Y, width, height);
		lblCanvasPreview.setOpaque(true);
		lblCanvasPreview.setBackground(Color.orange);
		lblCanvasPreview.setHorizontalAlignment(SwingConstants.CENTER);
		cont.add(lblCanvasPreview);

		X += 20 + lblCanvasPreview.getWidth();
		width = GlobalData.FIELD_WIDTH + 4;
		height = GlobalData.FIELD_HEIGHT + 4;

		lblCanvasField = new JLabel();
		lblCanvasField.setBounds(X, Y, width, height);
		lblCanvasField.setOpaque(true);
		lblCanvasField.setBackground(Color.orange);
		lblCanvasField.setHorizontalAlignment(SwingConstants.CENTER);
		lblCanvasField.setVerticalAlignment(SwingConstants.CENTER);
		cont.add(lblCanvasField);

		init();

		X = 400;
		Y = 200;
		setBounds(X, Y, FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		requestFocus();
	}

	private void init()
	{
		cm = ControllerMain.getInstance();
		cm.init();

    GameLoopTimer timer = GameLoopTimer.getInstance();
		timer.init();
		timer.start();
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

	private void addKeyBindings(InputMap inputMap, ActionMap actionMap)
	{
		Util.createKeyBindingOnKeyPress(  inputMap, actionMap, KeyEvent.VK_UP,   "UP_press",       (actionEvent) -> cm.setUserRequest(UserInput.MOVE_UP));
		Util.createKeyBindingOnKeyRelease(inputMap, actionMap, KeyEvent.VK_UP,   "UP_release",     (actionEvent) -> cm.setUserRequest(UserInput.NONE));

		Util.createKeyBindingOnKeyPress(  inputMap, actionMap, KeyEvent.VK_DOWN, "DOWN_press",     (actionEvent) -> cm.setUserRequest(UserInput.MOVE_DOWN));
		Util.createKeyBindingOnKeyRelease(inputMap, actionMap, KeyEvent.VK_DOWN, "DOWN_release",   (actionEvent) -> cm.setUserRequest(UserInput.NONE));

		Util.createKeyBindingOnKeyPress(  inputMap, actionMap, KeyEvent.VK_LEFT, "LEFT_press",     (actionEvent) -> cm.setUserRequest(UserInput.MOVE_LEFT));
		Util.createKeyBindingOnKeyRelease(inputMap, actionMap, KeyEvent.VK_LEFT, "LEFT_release",   (actionEvent) -> cm.setUserRequest(UserInput.NONE));

		Util.createKeyBindingOnKeyPress(  inputMap, actionMap, KeyEvent.VK_RIGHT,"RIGHT_press",    (actionEvent) -> cm.setUserRequest(UserInput.MOVE_RIGHT));
		Util.createKeyBindingOnKeyRelease(inputMap, actionMap, KeyEvent.VK_RIGHT,"RIGHT_release",  (actionEvent) -> cm.setUserRequest(UserInput.NONE));

		Util.createKeyBindingOnKeyPress(  inputMap, actionMap, KeyEvent.VK_R,    "ROTATE_press",   (actionEvent) -> cm.setUserRequest(UserInput.ROTATE));
		Util.createKeyBindingOnKeyRelease(inputMap, actionMap, KeyEvent.VK_R,    "ROTATE_release", (actionEvent) -> cm.setUserRequest(UserInput.NONE));

		Util.createKeyBindingOnKeyPress(  inputMap, actionMap, KeyEvent.VK_F,    "FLIP_press",     (actionEvent) -> cm.setUserRequest(UserInput.FLIP));
		Util.createKeyBindingOnKeyRelease(inputMap, actionMap, KeyEvent.VK_F,    "FLIP_release",   (actionEvent) -> cm.setUserRequest(UserInput.NONE));

		Util.createKeyBindingOnKeyPress(  inputMap, actionMap, KeyEvent.VK_N,    "NEXT_press",     (actionEvent) -> cm.setUserRequest(UserInput.NEXT_BLOCK));
		Util.createKeyBindingOnKeyRelease(inputMap, actionMap, KeyEvent.VK_N,    "NEXT_release",   (actionEvent) -> cm.setUserRequest(UserInput.NONE));


		Util.createKeyBindingOnKeyPress(  inputMap, actionMap, KeyEvent.VK_F8,   "INTRO_press",       (actionEvent) -> cm.setUserRequest(UserInput.INTRO));
		Util.createKeyBindingOnKeyRelease(inputMap, actionMap, KeyEvent.VK_F8,   "INTRO_release",     (actionEvent) -> cm.setUserRequest(UserInput.NONE));

		Util.createKeyBindingOnKeyPress(  inputMap, actionMap, KeyEvent.VK_F9,   "USER_PLAY_press",   (actionEvent) -> cm.setUserRequest(UserInput.USER_PLAY));
		Util.createKeyBindingOnKeyRelease(inputMap, actionMap, KeyEvent.VK_F9,   "USER_PLAY_release", (actionEvent) -> cm.setUserRequest(UserInput.NONE));

		Util.createKeyBindingOnKeyPress(  inputMap, actionMap, KeyEvent.VK_F10,  "DEMO_PLAY_press",   (actionEvent) -> cm.setUserRequest(UserInput.DEMO_PLAY));
		Util.createKeyBindingOnKeyRelease(inputMap, actionMap, KeyEvent.VK_F10,  "DEMO_PLAY_release", (actionEvent) -> cm.setUserRequest(UserInput.NONE));

	}

}
