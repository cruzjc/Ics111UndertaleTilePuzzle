import java.awt.Color;

public class Main {
	static DebugHelper d=new DebugHelper();
	public static void main(String[] args){
		//Settings initialize
		Settings.defaultSettings();
		//EZText.printAvailableFontsToConsole();
		
		//Classes used
		IntroScreen introScreen;
		GameStart gameStart;
		EndGame endGame;
		
		//window initialize
		EZ.initialize(Settings.aWindowSize[0],Settings.aWindowSize[1]);
		EZ.setBackgroundColor(Color.BLACK);
		

		//Main sequence
		////introScreen
		introScreen=new IntroScreen();
		introScreen.updateloop();
		////main game mechanics
		gameStart=new GameStart();
		
		//creates levels and player
		gameStart.initialize();
		gameStart.updateLoop();
		//gameStart.battleUpdateLoop();
		////endgame
		//endGame=new EndGame(gameStart.playerWin);
		//endGame.initialize();
		//endGame.updateloop();
	}
	
	

}
