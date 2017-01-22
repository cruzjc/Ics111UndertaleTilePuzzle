import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

//this 
public class EndGame{
	//member variables 
	//boolean toggle between victory and defeat screen
	boolean victory = false;	//set initial victory to be false
	boolean defeat = false;		//set initial defeat to be false
	//position variable
	int posx;			//integer variable to control x position of text
	int posy;			//integer variable to control y position of text
	//sound variable
	EZSound D;			//sound variable to control soundtrack 11-Determination
	EZSound F;			//sound variable to control soundtrack 80-Finale
	EZSound DGU;		//sound variable to control soundtrack 86-Dont Give Up
	EZSound LG;			//sound variable to control soundtrack 96-Last Goodbye
	//text variable
	EZText victorT;		//text variable to control text displayed in victory
	EZText defeatT;		//text variable to control text displayed in defeat

	//constructor
	EndGame (Boolean playerWin){
		//if playerwins
		if(playerWin){
			//set victory to true
			victory=true;
			//print player wins
			System.out.println("Player wins");
		}//else the player lost the game
		else{
			//set defeat to true
			defeat=true;
			//print out player defeated
			System.out.println("Player Defeated");
		}

	}
	
	void initialize(){
		//initializesounds
		initializeSound();
		//endsoundplayer
		endSoundPlayer();
		//end text display
		endTextDisplay();
		//end character display
		endCharactersDisplay();
		//end character movement
		//endPlayerMovement();
	}
	
	
	//method to call movement during endgame vicotry
	void endPlayerMovement(){
		if(victory == true){
		//Player move = new Player(victory, posx, posx);
		//move.pc();
		}
	}
	//method to call the end game characters
	void endCharactersDisplay(){
		if (victory ==true){
			ArrayList<EndGameGroup> endCharacters = new ArrayList<EndGameGroup>();
			for(int i = 0; i<1;i++){
				EndGameGroup characters = new EndGameGroup(0, 0);
				endCharacters.add(characters);
			}
		}
		if (defeat == true){
			EZImage annoyingDog = EZ.addImage("./Sprites/spr_tobydogeat_1.png", EZ.getWindowWidth()/2, (2*EZ.getWindowHeight())/3);
			annoyingDog.scaleBy(2);
		}
	}
	//method for end text display
	void endTextDisplay() {
		// TODO Auto-generated method stub
		//if victory is true
		if (victory == true){
			//add text to screen that says you did it
			victorT = EZ.addText(3*EZ.getWindowWidth()/4, 50, "You Did it!", Color.WHITE, 50);
		}//if defeat is true
		else if (defeat ==true){
			//add text to screen that says defeated by annoying dog
			defeatT = EZ.addText(EZ.getWindowWidth()/2, EZ.getWindowHeight()/2, "Defeated by Annoying Dog", Color.WHITE, 75);
		}
	}
	//method for end sound player
	void endSoundPlayer() {
		// TODO Auto-generated method stub
		//if victory is true
		if(victory == true){
			//have determination song play
			LG.play();
		}//if defeat is true
		else if(defeat == true){
			//have last goodbye song play
			D.play();
		}
	}
	//function to update sound in the end game
	void updateloop() {
		// TODO Auto-generated method stub
		EZ.refreshScreen();
	}
	//function to initialize the sound in the end game
	void initializeSound() {
		// TODO Auto-generated method stub
		//add end game sounds
		D = EZ.addSound("./Sounds/EndGame/toby fox - UNDERTALE Soundtrack - 11 Determination.wav");
		F = EZ.addSound("./Sounds/EndGame/toby fox - UNDERTALE Soundtrack - 80 Finale.wav");
		DGU = EZ.addSound("./Sounds/EndGame/toby fox - UNDERTALE Soundtrack - 86 Don't Give Up.wav");
		LG = EZ.addSound("./Sounds/EndGame/toby fox - UNDERTALE Soundtrack - 96 Last Goodbye.wav");
	}
	void pc(){
		int speed=(int) Settings.sizeOnScreen(.003125);
		if(speed%2!=0)
			speed++;
		
		
		
		//if key down is up key use move up function to switch between the left and right step
		if(EZInteraction.isKeyDown(Settings.upKey)){
			//move up by speed
			EndGameGroup.moveAllCharacters(0,speed);
		}
		//if key down is down key use move down function to switch between te left and right step
		else if (EZInteraction.isKeyDown(Settings.downKey)){
			//move down by speed
			EndGameGroup.moveAllCharacters(0, -speed);
		}
		
		
		//if key down is left key
		else if (EZInteraction.isKeyDown(Settings.leftKey)){
			//move left by speed
			EndGameGroup.moveAllCharacters(speed,0);
		}//if key down is right key 
		else if(EZInteraction.isKeyDown(Settings.rightKey)){
			//move right by speed
			EndGameGroup.moveAllCharacters(-speed,0);
		}
	}
}
