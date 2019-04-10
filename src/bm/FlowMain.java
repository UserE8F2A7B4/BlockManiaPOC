package bm;

import bm.BlockManiaPOC.UserInput;
import bm.ControllerMain.GameState;
import bm.block_handling.ControllerBlockHandling;
import bm.line_removal.ControllerLineRemoval;

public class FlowMain
{
	private static FlowMain instance;

	private ControllerMain cm;
	private ControllerBlockHandling cb;
	private ControllerLineRemoval cl;

	private boolean isRepeating;
	private int isMoving;
	private final static int MOVEMENT_DELAY = 2;

	public static long gameTickCounter;

	public static FlowMain getInstance()
	{
		if (instance == null)
		{
			instance = new FlowMain();
		}
		return instance;
	}

	private FlowMain()
	{
	}

	public void init()
	{
		cm = ControllerMain.getInstance();

		cb = ControllerBlockHandling.getInstance();
		cb.init();

		cl = ControllerLineRemoval.getInstance();
		cl.init();
	}

	public void handleGameTick()
	{
		gameTickCounter++;

    switch (cm.getGameState())
    {
      case BLOCK_HANDLING : flowBlockHandling() ; break;
      case LINE_REMOVAL   : flowLineRemoval() ; break;
      default             : assert false : "Invalid game-state"; break;
    }
	}

	private void flowBlockHandling()
	{
		// System.out.println("game tick block handling");

		//		if (!linesCreated)
		//		{
		//			linesCreated = true;
		//			cm.testRowRemoval();
		//			return;
		//		}

		if (!cb.staat_er_een_block_op_de_preview())
		{
			cb.plaats_een_nieuw_willekeurig_block_op_de_preview();
		}

		if (cb.staat_er_een_block_op_het_veld())
		{
			if (cb.is_het_tijd_voor_een_move_down())
			{
				if (cb.probeer_actie_uit_te_voeren_op_het_block(UserInput.MOVE_DOWN))
				{
					cm.updateScore();
				}
			else
				{
					cb.verwijder_het_block_uit_het_veld();
					if (cb.zijn_er_volledig_gevulde_regels_ontstaan())
					{
						cm.changeGameState(GameState.LINE_REMOVAL);
					}
				}
			}
			else
			{
				final UserInput input = cm.getUserRequest();
				if (input == UserInput.NONE)
				{
					isRepeating = false;
					isMoving = 0;
				}
				else if (input == UserInput.START_GAME)
				{
					System.out.println("START_GAME");
				}
				else if (input == UserInput.NEXT_BLOCK)
				{
					if (!isRepeating)
					{
						isRepeating = true;
						cb.plaats_een_nieuw_willekeurig_block_op_de_preview();
					}
				}
				else if (input == UserInput.ROTATE || input == UserInput.FLIP)
				{
					if (!isRepeating)
					{
						isRepeating = true;
						cb.probeer_actie_uit_te_voeren_op_het_block(input);
					}
				}
				else if (input == UserInput.MOVE_LEFT || input == UserInput.MOVE_RIGHT || input == UserInput.MOVE_UP)
				{
					isMoving++;
					if (isMoving == 1 || isMoving > MOVEMENT_DELAY)
					{
						cb.probeer_actie_uit_te_voeren_op_het_block(input);
					}
				}
				else if (input == UserInput.MOVE_DOWN)
				{
					isMoving++;
					if (isMoving == 1 || isMoving > MOVEMENT_DELAY)
					{
						if (cb.probeer_actie_uit_te_voeren_op_het_block(UserInput.MOVE_DOWN))
						{
							cm.updateScore();
						}
						else
						{
							cb.verwijder_het_block_uit_het_veld();
							if (cb.zijn_er_volledig_gevulde_regels_ontstaan())
							{
								cm.changeGameState(GameState.LINE_REMOVAL);
							}
						}
					}
				}
			}
		}
		else // Er is [geen] block aanwezig op het veld.
		{
			if (cb.probeer_het_previewblock_te_verplaatsen_naar_het_veld())
			{
				// Het previewblock is nu verplaatst naar het veld.
			}
			else
			{
				// Er is [niet] genoeg ruimte op het veld om het previewblock te plaatsen ; GAME OVER !..
				cb.handleGameOver();
			}
		}
	}

	private void flowLineRemoval()
	{
		cl.handleGameTick();
	}

}
