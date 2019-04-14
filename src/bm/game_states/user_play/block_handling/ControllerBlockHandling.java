package bm.game_states.user_play.block_handling;

import bm.BlockManiaPOC.UserInput;
import bm.algemeen.ControllerMain;
import bm.game_states.user_play.canvas.field.ControllerFieldUserPlay;
import bm.game_states.user_play.canvas.preview.ControllerPreviewUserPlay;

import javax.swing.*;

public class ControllerBlockHandling
{
	private static ControllerBlockHandling instance;

	private ControllerFieldUserPlay cf;
	private ControllerPreviewUserPlay cp;
	private BlockProcessor bp;

	public static ControllerBlockHandling getInstance()
	{
		if (instance == null)
		{
			instance = new ControllerBlockHandling();
		}
		return instance;
	}

	private ControllerBlockHandling()
	{
	}

	public void init()
	{
		cf = ControllerFieldUserPlay.getInstance();

		cp = ControllerPreviewUserPlay.getInstance();
		cp.init();

		bp = BlockProcessor.getInstance();
	}

	public boolean staat_er_een_block_op_de_preview()
	{
		return cp.hasBlock();
	}

	public void plaats_een_nieuw_willekeurig_block_op_de_preview()
	{
		cp.loadRandomBlock();
	}

	public boolean probeer_het_previewblock_te_verplaatsen_naar_het_veld()
	{
		return bp.tryToPlaceNewBlockOnField();
	}

	public boolean staat_er_een_block_op_het_veld()
	{
		return bp.hasBlock();
	}

	public boolean is_het_tijd_voor_een_move_down()
	{
		return false;
	}

	public boolean probeer_actie_uit_te_voeren_op_het_block(UserInput input)
	{
		if (input == UserInput.MOVE_DOWN)
		{
			return bp.tryToMoveBlockDown();
		}
		else if (input == UserInput.MOVE_UP)
		{
			// return bp.tryToMoveBlockUp();
			return bp.tryToRotateBlock();
		}
		else if (input == UserInput.MOVE_LEFT)
		{
			return bp.tryToMoveBlockLeft();
		}
		else if (input == UserInput.MOVE_RIGHT)
		{
			return bp.tryToMoveBlockRight();
		}
		else if (input == UserInput.FLIP)
		{
			return bp.tryToFlipBlock();
		}
		else if (input == UserInput.ROTATE)
		{
			return bp.tryToRotateBlock();
		}
		else
		{
			return false;
		}
	}

	public void verwijder_het_block_uit_het_veld()
	{
		bp.removeBlock();
	}

	public boolean zijn_er_volledig_gevulde_regels_ontstaan()
	{
		return cf.hasCompletedRows();
	}

	public void handleGameOver()
	{
		JOptionPane.showMessageDialog(null, "Game Over !..", "BlockMania", JOptionPane.INFORMATION_MESSAGE);

		bp.removeBlock();
		cp.clearBlock();
		cf.clearAllTiles();

		ControllerMain.getInstance().changeGameState(ControllerMain.GameStateMain.INTRO);
	}

}
