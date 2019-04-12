package bm.util;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Util
{
	public static void createKeyBindingOnKeyPress(InputMap im, ActionMap am, int keyCode, String id, ActionListener actionListener)
	{
		createKeyBinding(im, am, keyCode, id, actionListener, false);
	}

	public static  void createKeyBindingOnKeyRelease(InputMap im, ActionMap am, int keyCode, String id, ActionListener actionListener)
	{
		createKeyBinding(im, am, keyCode, id, actionListener, true);
	}

	private static void createKeyBinding(InputMap im, ActionMap am, int keyCode, String id, ActionListener actionListener, boolean onKeyRelease)
	{
		final int NO_MODIFIERS = 0;
		im.put(KeyStroke.getKeyStroke(keyCode, NO_MODIFIERS, onKeyRelease), id);
		am.put(id, new AbstractAction()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				actionListener.actionPerformed(e);
			}
		});
	}

}
