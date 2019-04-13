package bm.game_states.title_screen;

import bm.BlockManiaPOC;
import bm.util.GlobalData;
import bm.view.CanvasBase;

import javax.swing.*;
import java.awt.*;

public class CanvasTitleScreen extends CanvasBase
{
	private static CanvasTitleScreen instance;

	public static CanvasTitleScreen getInstance()
	{
		if (instance == null)
		{
			instance = new CanvasTitleScreen();
		}
		return instance;
	}

	private CanvasTitleScreen()
	{
		super(GlobalData.FIELD_WIDTH, GlobalData.FIELD_HEIGHT, Color.CYAN);
	}

	public void drawString(String text, int X, int Y)
	{
		canvas.setColor(Color.BLACK);
		canvas.drawString(text, X, Y);
		renderTitleScreen();
	}

	private void renderTitleScreen()
	{
		BlockManiaPOC.getLblCanvasField().setIcon(new ImageIcon(image));
	}

}
