package bm.line_removal;

import java.util.List;

import bm.block_handling.ControllerField;

public class ControllerLineRemoval
{
	private ControllerField controllerField;

	private static ControllerLineRemoval instance;
	public static ControllerLineRemoval getInstance()
	{
		if (instance == null)
		{
			instance = new ControllerLineRemoval();
		}
		return instance;
	}

	private ControllerLineRemoval()
	{
		controllerField = ControllerField.getInstance();
	}

	public void handleGameTick()
	{
		System.err.println("ControllerLineRemoval.handleGameTick");
	}

	private void removeCompletedRows()
	{
		List<Integer> completedRows = controllerField.getCompletedRows();
	}

}
