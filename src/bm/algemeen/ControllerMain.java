package bm.algemeen;

import bm.BlockManiaPOC.UserInput;
import bm.game_states.intro.canvas.field.ControllerFieldIntro;
import bm.game_states.user_play.ControllerUserPlay;
import bm.util.game_loop.GameLoopPause;

public class ControllerMain
{
	private static ControllerMain instance;

	private UserInput request = UserInput.NONE;

	private ControllerFieldIntro cts;
	private ControllerUserPlay cup;

	private GameStateMain gameStateMain;

	public enum GameStateMain
	{
		INTRO, USER_PLAY, DEMO_PLAY
	}

	private GameLoopPause pausing = new GameLoopPause(60);

	public static ControllerMain getInstance()
	{
		if (instance == null)
		{
			instance = new ControllerMain();
		}
		return instance;
	}

	private ControllerMain()
	{
	}

	public void init()
	{
		gameStateMain = GameStateMain.INTRO;

		cts = ControllerFieldIntro.getInstance();
		cts.init();

		cup = ControllerUserPlay.getInstance();
		cup.init();
	}

	public synchronized void setUserRequest(UserInput req)
	{
		// System.out.println(String.format("UserRequest : %s ", req));
		request = req;
	}

	public synchronized UserInput getUserRequest()
	{
		return request;
	}

	public void changeGameState(GameStateMain newState)
	{
		gameStateMain = newState;
	}

	public void handleGameTick()
	{
		final UserInput input = getUserRequest();
		if (input == UserInput.USER_PLAY)
		{
			cup.changeGameState(ControllerUserPlay.GameStateUserPlay.BLOCK_HANDLING);
			changeGameState(GameStateMain.USER_PLAY);
		}
		
    switch (gameStateMain)
    {
      case INTRO     : cts.handleGameTick() ; break;
      case USER_PLAY : cup.handleGameTick() ; break;
      case DEMO_PLAY : break;
      default        : throw new RuntimeException("Invalid game-State-Main.");
    }
	}


}
