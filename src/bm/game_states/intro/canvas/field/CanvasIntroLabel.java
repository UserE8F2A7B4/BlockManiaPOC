package bm.game_states.intro.canvas.field;

import bm.BlockManiaPOC;
import bm.util.GlobalData;
import bm.view.CanvasBase;

import javax.swing.*;
import java.awt.*;

public class CanvasIntroLabel extends CanvasBase
{
	private static CanvasIntroLabel instance;

	public static CanvasIntroLabel getInstance()
	{
		if (instance == null)
		{
			instance = new CanvasIntroLabel();
		}
		return instance;
	}

	private CanvasIntroLabel()
	{
		super(GlobalData.FIELD_WIDTH, GlobalData.FIELD_HEIGHT, Color.CYAN);
	}

	public void drawString(String text, int X, int Y)
	{
		canvas.setColor(Color.BLACK);
		canvas.drawString(text, X, Y);
	}

	@Override
	public void render()
	{
		BlockManiaPOC.getLblCanvasField().setIcon(new ImageIcon(image));
	}

}
