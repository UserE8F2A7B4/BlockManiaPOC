package bm.game_states.intro.canvas.field;

import bm.BlockManiaPOC;
import bm.util.GlobalData;
import bm.view.ControllerCanvasBase;

import javax.swing.*;
import java.awt.*;

public class ControllerCanvasIntroLabel extends ControllerCanvasBase
{
	private static ControllerCanvasIntroLabel instance;

	public static ControllerCanvasIntroLabel getInstance()
	{
		if (instance == null)
		{
			instance = new ControllerCanvasIntroLabel();
		}
		return instance;
	}

	private ControllerCanvasIntroLabel()
	{
		super(GlobalData.FIELD_WIDTH, GlobalData.FIELD_HEIGHT, Color.CYAN);
	}

	public void drawString(String text, int X, int Y)
	{
		canvas.setColor(Color.BLACK);
		canvas.drawString(text, X, Y);
	}

	public void renderIntro()
	{
		BlockManiaPOC.getLblCanvasField().setIcon(new ImageIcon(image));
	}

}
