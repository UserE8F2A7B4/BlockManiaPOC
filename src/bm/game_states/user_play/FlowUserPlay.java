package bm.game_states.user_play;

import bm.BlockManiaPOC.UserInput;
import bm.algemeen.ControllerMain;
import bm.game_states.user_play.block_handling.ControllerBlockHandling;
import bm.game_states.user_play.line_removal.ControllerLineRemoval;

public class FlowUserPlay
{
	private static FlowUserPlay instance;

	private ControllerMain cm;
	private ControllerUserPlay cup;
	private ControllerBlockHandling cb;

	private final static int MOVEMENT_DELAY = 2;
	private boolean isRepeating;
	private int isMoving;

	public static FlowUserPlay getInstance()
	{
		if (instance == null)
		{
			instance = new FlowUserPlay();
		}
		return instance;
	}

	private FlowUserPlay()
	{
	}

	public void init()
	{
		cm = ControllerMain.getInstance();		
		cup = ControllerUserPlay.getInstance();

		cb = ControllerBlockHandling.getInstance();
		cb.init();
	}

	public void handleGameTick()
	{
		// System.out.println("game tick block handling");

		//		if (!linesCreated)
		//		{
		//			linesCreated = true;
		//			cup.testRowRemoval();
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
					cup.updateScore();
				}
				else
				{
					cb.verwijder_het_block_uit_het_veld();
					if (cb.zijn_er_volledig_gevulde_regels_ontstaan())
					{
						cup.changeGameState(ControllerUserPlay.GameStateUserPlay.LINE_REMOVAL);
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
							cup.updateScore();
						}
						else
						{
							cb.verwijder_het_block_uit_het_veld();
							if (cb.zijn_er_volledig_gevulde_regels_ontstaan())
							{
								cup.changeGameState(ControllerUserPlay.GameStateUserPlay.LINE_REMOVAL);
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

}
