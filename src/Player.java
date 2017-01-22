import java.awt.Color;
import java.util.ArrayList;

public class Player {
	//create new debughelper
	DebugHelper d=new DebugHelper();
	//member variables
	int startingHp;
	static int playerHp;					//variable to control players hp for battle state
	String sDamageSound="./Sounds/Soul Hit.wav";
	EZSound damagesound;			//variable to control sound for battle state
	EZImage frisku;					//variable to control image of frisk up facing
	EZImage friskl;					//variable to control image of frisk left facing
	EZImage friskd;					//variable to control image of frisk down facing
	EZImage friskr;					//variable to control image of frisk right facing
	EZImage friskus;				//variable to control image of frisk forward stepping
	EZImage friskls;				//variable to control image of frisk left stepping
	EZImage friskds;				//variable to control image of frisk down stepping
	EZImage friskrs;				//variable to control iamge of frisk right stepping
	EZImage friskusl;				//variable to control image of frisk up stepping left foot leap
	EZImage friskdsl;				//variable to control image of frisk down stepping left foot leap
	static int posx;						//variable to control x position of frisk
	static int posy;						//variable to control y position of frisk
	int direction;					//variable to control direction

	static final int IDLE = 0;		//variable to manage state of player in idle
	static final int BATTLE = 1;	//variable to manage state of player in battle
	static final int FAINTED = 2;		//variable to manage state of player in death
	static int friskState = IDLE;			//variable to control initial state of frisk
	boolean battle = false;			//variable to manage if player is in battle

	//string called sPlayerHeart that loads image of heart
	String sPlayerHeart="./Sprites/spr_heart_battle_pl_0.png";
	//create static image called ezPlayerHeart
	static EZImage ezPlayerHeart;
	//create array of playerHeartPos that can hold 2 ints
	int playerHeartPos[]=new int[2];
	//create static integer called playable area that can hold 2 ints
	int aPlayableArea[]=new int[2];

	//create int called heart speed set at int
	int heartSpeed;
	//create int called invincibilityFrames initialized at the current frame rate
	int invincibilityFrames;
	//create int called invincibilityframecounter initialized at invincibilityframes
	int invincibilityFrameCounter;
	
	EZText playerHpText; 
			
	
	//constructor
	Player(boolean inBattle,int x,int y){
		heartSpeed=(int) Settings.sizeOnScreen(.01);
		invincibilityFrames=EZ.getCurrentFrameRate()*2;
		invincibilityFrameCounter=invincibilityFrames;
		
		
		//double size initialized as 0.06
		double size=.06;
		//double heartsize initialized as 0.02
		double heartSize=.02;
		//if you are not in battle
		if(!inBattle){
			//posx is x
			posx = x;
			//posy is y
			posy = y;
			//upward facing image
			frisku= EZ.addImage("./Sprites/spr_f_maincharau_0.png", x, y);
			//left facing image
			friskl=EZ.addImage("./Sprites/spr_f_maincharal_0.png", x, y);
			//right facing image
			friskr=EZ.addImage("./Sprites/spr_f_maincharar_0.png", x, y);
			//downward facing image
			friskd= EZ.addImage("./Sprites/spr_f_maincharad_2.png", x, y);
			//upward facing step image right foot leap
			friskus = EZ.addImage("./Sprites/spr_f_maincharau_1.png", x, y);
			//upward facing step image left foot leap
			friskusl = EZ.addImage("./Sprites/spr_f_maincharau_3.png", x, y);
			//left facing step image
			friskls= EZ.addImage("./Sprites/spr_f_maincharal_1.png", x, y);
			//right facing step image
			friskrs= EZ.addImage("./Sprites/spr_f_maincharar_1.png", x, y);
			//downward facing step image right foot leap
			friskds= EZ.addImage("./Sprites/spr_f_maincharad_3.png", x, y);
			//downward facing step image left foot leap
			friskdsl= EZ.addImage("./Sprites/spr_f_maincharad_1.png", x, y);
			//hide all frisk images
			hideFrisk();
			//show the downfacing frisk
			friskd.show();
			//set ezcurrent to frisk down
			ezCurrent=friskd;

			//scaling for all frisk images
			frisku.scaleBy(Settings.sizeOnScreen(size)/frisku.getWidth());
			friskl.scaleBy(Settings.sizeOnScreen(size)/friskl.getWidth());
			friskr.scaleBy(Settings.sizeOnScreen(size)/friskr.getWidth());
			friskd.scaleBy(Settings.sizeOnScreen(size)/friskd.getWidth());
			friskus.scaleBy(Settings.sizeOnScreen(size)/friskus.getWidth());
			friskusl.scaleBy(Settings.sizeOnScreen(size)/friskusl.getWidth());
			friskls.scaleBy(Settings.sizeOnScreen(size)/friskls.getWidth());
			friskrs.scaleBy(Settings.sizeOnScreen(size)/friskrs.getWidth());
			friskds.scaleBy(Settings.sizeOnScreen(size)/friskds.getWidth());
			friskdsl.scaleBy(Settings.sizeOnScreen(size)/friskdsl.getWidth());
		}//else player is in a battle and requires HP
		else{
			//add ezPlayerHeart at position x y
			ezPlayerHeart=EZ.addImage(sPlayerHeart,x,y);
			//scale playerHeart
			ezPlayerHeart.scaleBy(Settings.sizeOnScreen(heartSize)/ezPlayerHeart.getWidth());
			//bring playerHeart to front
			ezPlayerHeart.pullToFront();
		}
		
		
		damagesound=EZ.addSound(sDamageSound);
		aPlayableArea=LevelGenerator.aPlayableArea();
	}
	
	
	EZRectangle hpBox;
	int startingHpBoxWidth=(int) Settings.sizeOnScreen(.25);
	int hpBoxWidth=startingHpBoxWidth;
	void showPlayerHp(){
		startingHp=EZ.getCurrentFrameRate()*3;//5 seconds worth of collision
		playerHp=startingHp;
		int hpTextPos[]={Settings.aWindowSize[0]/8,Settings.aWindowSize[1]/10*9};
		String hpText="Determination: " + playerHp;
		Color hpTextColor=Color.white;
		int hpTextFontSize=(int) Settings.sizeOnScreen(0.02);
		String hpTextFont="8bitOperatorPlus-Regular.ttf";
		
		hpBox=EZ.addRectangle(hpTextPos[0], hpTextPos[1],hpBoxWidth,(int)Settings.sizeOnScreen(.025), Color.green, true);
		playerHpText= EZ.addText(hpTextFont,hpTextPos[0], hpTextPos[1],hpText , Color.WHITE, hpTextFontSize);
		
		
	}

	//method for playerHeartControl
	void playerHeartControls(){
		//if key presed is the up key
		
		Boolean notAtTop=ezPlayerHeart.getYCenter()>0;
		if(EZInteraction.isKeyDown(Settings.upKey)&&notAtTop){
			//move playerHeart up
			ezPlayerHeart.translateBy(0, -heartSpeed);
			//for playerHeartPos 0, get the x center
			playerHeartPos[0]=ezPlayerHeart.getXCenter();
			//for playerHeartPos 1, get the y center
			playerHeartPos[1]=ezPlayerHeart.getYCenter();
		}//if the key pressed is the down key 
		
		
		Boolean notAtBottom=ezPlayerHeart.getYCenter()<Settings.aWindowSize[1];
		if (EZInteraction.isKeyDown(Settings.downKey)&&notAtBottom){
			//move playerHeart down
			ezPlayerHeart.translateBy(0, heartSpeed);
			//for playerHeartPos 0, get the x center
			playerHeartPos[0]=ezPlayerHeart.getXCenter();
			//for playerHeartPos 0, get the y center
			playerHeartPos[1]=ezPlayerHeart.getYCenter();
		}//if the key pressed is left key
		
		Boolean notAtLeft=ezPlayerHeart.getXCenter()>0;
		if (EZInteraction.isKeyDown(Settings.leftKey)&&notAtLeft){
			//move playerHeart left
			ezPlayerHeart.translateBy(-heartSpeed,0);
			//for playerHeartPos 0, get the x center
			playerHeartPos[0]=ezPlayerHeart.getXCenter();
			//for playerHeartPos 0, get the y center
			playerHeartPos[1]=ezPlayerHeart.getYCenter();
		}//if the key pressed is right key 
		
		Boolean notAtRight=ezPlayerHeart.getXCenter()<Settings.aWindowSize[0];
		if (EZInteraction.isKeyDown(Settings.rightKey)&&notAtRight){
			//move playerHeart right
			ezPlayerHeart.translateBy(heartSpeed,0);
			//for playerHeartPos 0, get the x center
			playerHeartPos[0]=ezPlayerHeart.getXCenter();
			//for playerHeartPos 0, get the y center
			playerHeartPos[1]=ezPlayerHeart.getYCenter();
		}
	}
	//method to control where player is
	/*void friskPosition(int x, int y){
		x = posx;
		y = posy;
	}*/
	//method to manage collision

	//method to manage bullet collision to frisk
	void bulletCollisionCheck(){
		
		//create array list of EZElements called elementHolder to hold the playerHeart X and Y centers
		try {
			ArrayList<EZElement> elementHolder=EZ.getAllElementsContainingPoint(ezPlayerHeart.getXCenter(),ezPlayerHeart.getYCenter());
			while(
					elementHolder.contains(ezPlayerHeart)||elementHolder.contains(hpBox)||
					elementHolder.contains(playerHpText)||elementHolder.contains(GameStart.escapeInstructions)
					//||elementHolder.contains(Items.ezItem)
			){
			elementHolder.remove(ezPlayerHeart);
			elementHolder.remove(hpBox);
			elementHolder.remove(playerHpText);
			elementHolder.remove(GameStart.escapeInstructions);
			//elementHolder.remove(Items.ezItem);
			}
			//if elementholder size is greater than the invincibility frame counter
			
			boolean bulletcollide=elementHolder.size()>0;//&&invincibilityFrameCounter==0;
			if(bulletcollide){
				//print player hit
				System.out.println("Player Hit"+elementHolder.get(0).toString());
				
				//decrease playerHP
				playerHp--;
				soundPlayer(damagesound);
				//print out new playerHp
				//System.out.println(playerHp);
				//set invincibilityframecounter to be invincibilityframes
				invincibilityFrameCounter=invincibilityFrames;	
				
			}
			
			for(EZElement e:elementHolder){
				EZ.removeEZElement(e);
			}
			
			elementHolder.clear();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//if invincibilityframecounter is greater than 0
		if(invincibilityFrameCounter>0){
			//decrease invincibilityframecounter
			invincibilityFrameCounter--;
			//System.out.println("Invincibility Frames Left:"+invincibilityFrameCounter);
		}
	}
	//method to check playerHp
	void playerHpCheck(){
		//if the player HP is over 0
		if(playerHp>0){
			//frisk is in BATTLE state
			//friskState=1;
		}//if the player HP is lessthan or equal to 0
		else if(playerHp<=0){
			//frisk is in DEATH state
			friskState=2;
		}
	}
	//method to control imageSwitching
	void imageSwitching(EZImage idle,EZImage step){
		//increase the frameCounter
		frameCounter++;
		//hide frisk image
		hideFrisk();
		//if the number of frames in half a second have passed...
		if(frameCounter>=EZ.getCurrentFrameRate()/4){
			//show the steps
			step.show();
			//set ezcurrent to be step
			ezCurrent=step;
		}//otherwise
		else{
			//show idle image
			idle.show();
			//set ezcurrent to be idle
			ezCurrent=idle;
		}
		//if the frame counter is greater than or equal to the current framerate/2
		if(frameCounter>=EZ.getCurrentFrameRate()/2){
			//set framecounter to be 0
			frameCounter=0;
		}

	}

	//method to control when frisk is displayed
	//need other sprites to create images
	void hideFrisk(){		
		frisku.hide();
	friskl.hide();
	friskr.hide();
	friskd.hide();
	friskus.hide();
	friskusl.hide();
	friskls.hide();
	friskrs.hide();
	friskds.hide();
	friskdsl.hide();
	}

	
	//method for stepping up 
	void stepUp(int step){
		//if position y is greather than tor equal to 0
		if(posy >0){
			//subtract posy from step
			posy-= step;
			//hide frisk
			hideFrisk();
			//show frisku
			frisku.show();
			//set ezcurrent to frisku
			ezCurrent = frisku;
		}
	}
	//method for stepping down
	void stepDown(int step){
		//if position y is less than tor equal to 0
		if(posy >0){
			//add posy to step
			posy+= step;
			//hide frisk
			hideFrisk();
			//show friskd
			friskd.show();
			//set ezcurrent to friskd
			ezCurrent = friskd;
		}
	}
	
	
	//method for moving up
	void moveUp(int step){
		//if position y is greater than or equal to 0
		if(posy>0){
			//switch image between frisk left and right step
			imageSwitching(friskus,friskusl);
			//move all tiles in level generator by step
			LevelGenerator.moveAllTiles(0,step);
			//set position y to subtract from step
			posy-=step;
			//set direction to be 0
			direction=0;
		}else if(posy<0){
			LevelGenerator.moveAllTiles(0,-posy);
			posy=0;
		}
	}
	//method for moving down
	void moveDown(int step){
		//if position y is less than or equal to playable area
		if(posy<aPlayableArea[1]){
			//switch image between frisk left and right step
			imageSwitching(friskds,friskdsl);
			//move all tiles in level generator by -step
			LevelGenerator.moveAllTiles(0,-step);
			//set position y to add from step
			posy+=step;
			//set direction tp be 1
			direction=1;
		}else if(posy>aPlayableArea[1]){
			LevelGenerator.moveAllTiles(0,posy-aPlayableArea[1]);
			posy=aPlayableArea[1];
		}
	}
	//method for moving left
	void moveLeft(int step){
		//if positionx is greater than 0
		if(posx>0){
			//switch image between frisk left and frisk left step
			imageSwitching(friskl,friskls);
			//move all tiles in level generator by step
			LevelGenerator.moveAllTiles(step,0);
			//set position x to subtract from step
			posx-=step;
			//set direction to be 3
			direction=3;
		}
	}
	//method for moving right
	void moveRight(int step){
		//if position x is less than or equal to aplayerable area
		if(posx<aPlayableArea[0]){
			//switch image between frisk right and frisk right step
			imageSwitching(friskr,friskrs);
			//move all tiles in level generator by -step
			LevelGenerator.moveAllTiles(-step,0);
			//set position x to add from step
			posx+=step;
			//set direction to be 2
			direction=2;
		}
	}


	//method to control frisk image during IDLE and BATTLE
	//pc = player control
	
	int speed(){
		int speed=(int) Settings.sizeOnScreen(.003125);
		if(speed%2!=0)
			speed++;
		return speed;
	}
	
	//used to count frames inbetween sprites
	int frameCounter=0;
	void pc(){

		//if key down is up key use move up function to switch between the left and right step
		if(EZInteraction.isKeyDown(Settings.upKey)){
			//move up by speed
			moveUp(speed());
		}
		
		//if key down was up key show the standing up 
		else if(EZInteraction.wasKeyReleased(Settings.upKey)){
			//stepUp(speed);
			ezCurrent.hide();
			//show friskd
			frisku.show();
			//set ezcurrent to friskd
			ezCurrent = frisku;
		}
		
		//if key down was down key show the standing down
		else if (EZInteraction.wasKeyReleased(Settings.downKey)){
			//stepDown(speed);
			ezCurrent.hide();
			friskd.show();
			ezCurrent=friskd;
		}
		//if key down is down key use move down function to switch between te left and right step
		else if (EZInteraction.isKeyDown(Settings.downKey)){
			//move down by speed
			moveDown(speed());
		}
		
		
		//if key down is left key
		else if (EZInteraction.isKeyDown(Settings.leftKey)){
			//move left by speed
			moveLeft(speed());
		}//if key down is right key 
		else if(EZInteraction.isKeyDown(Settings.rightKey)){
			//move right by speed
			moveRight(speed());
		}
		else if(EZInteraction.wasKeyReleased(Settings.leftKey)){
			ezCurrent.hide();
			friskl.show();
			ezCurrent=friskl;
		}
		else if(EZInteraction.wasKeyReleased(Settings.rightKey)){
			ezCurrent.hide();
			friskr.show();
			ezCurrent=friskr;
		}
	}

	boolean playerAtLeftRightEdges;
	boolean playerAtTopBottomEdges;
	boolean playerTryingToEscape;
	void playerTryingToEscapeCheck(){		
		playerAtLeftRightEdges=ezPlayerHeart.getXCenter()<=5||ezPlayerHeart.getXCenter()>=Settings.aWindowSize[0]-5;
		playerAtTopBottomEdges=ezPlayerHeart.getYCenter()<=5||ezPlayerHeart.getYCenter()>=Settings.aWindowSize[1]-5;

		if(playerAtLeftRightEdges||playerAtTopBottomEdges){
			playerTryingToEscape=true;
		}else{
			playerTryingToEscape=false;
		}
	}
	
	//method to control state of player(frisk)s
	void processFriskState(){
		double playerHpDisplay=((double)playerHp/(double)startingHp*100);
		double green=(255.0/100.0)*playerHpDisplay;
		double red=((double)255/(double)100)*(100.0-playerHpDisplay);
		green=Math.min(255, green);
		green=Math.max(0, green);
		red=Math.min(255, red);
		red=Math.max(0, red);
		Color hpBoxColor=new Color((int)red,(int)green,0);
		hpBox.setColor(hpBoxColor);
		hpBox.setWidth(
				(int)((double)startingHpBoxWidth/100.0*playerHpDisplay));
		
		playerHp=Math.min(startingHp,playerHp);
		playerHp=Math.max(0, playerHp);
		

		switch (friskState){
		//first case is idle
		//In Idle state, frisk is walking in game over puzzle. Frisk will not be fighting in this state.
		case IDLE:
			//friskstate is IDLE
			friskState = IDLE;
			
			//playerhpupdater
			playerHpText.setMsg("Determination: "+(int)playerHpDisplay);
			
			//allow for playercontrol
			pc();
			
			playerHpCheck();
			
			break;
			//second case is battle
			//in Battle state, frisk engages monsters in the form of a heart. Frisk's heart will be dodging in this state.
			//Battle state is unlocked through tile interaction
		case BATTLE:
			//frisk state is BATTLE
			friskState = BATTLE;
			//allow for playerHeartControl
			playerHeartControls();
			//allow for bulletCollisionCheckFrisk
			bulletCollisionCheck();
			
			//playerhpupdater
			playerHpText.setMsg("Determination: "+(int)playerHpDisplay);
			//allow for playerHpCheck
			playerHpCheck();
			

			
			
			break;
			//third case is death
			//in death state, frisk has died due to losing in BATTLE and the game has ended.
		case FAINTED:
			//call game ending here

			break;
		}
	}

	//EZImage called ezCurrent
	EZImage ezCurrent;
	//EZImage ezFrisk
	EZImage ezFrisk(){
		//return ezCurrent to be ezFrisk
		return ezCurrent;
	}
	//variable to control the flavor of player
	static int flavor=0;


	//moves player in their reverse direction
		void playerReverseMove(int distance){
			//if player is facing down...
			if(direction==1){
				//move up
				moveUp(distance);
			
			//if player is facing up...
			}else
			if(direction==0){
				//move down
				moveDown(distance);
			
			//if player is facing left...
			}else
			if(direction==3){
				//move right
				moveRight(distance);
				
			//if player is facing right...
			}else

			if(direction==2){
				//move left
				moveLeft(distance);
			}
		}

		//move player in their facing directions
		void playerMove(int distance){
			//player is facing up...
			if(direction==0){
				//move up
				moveUp(distance);
			//if player is facing down
			}else if(direction==1){
				//move down
				moveDown(distance);
			//if player is facing right
			}else if(direction==2){
				//move right
				moveRight(distance);
			//if player is facing left
			}else if(direction==3){
				//move left
				moveLeft(distance);
			}
		}
	
		//plays specified sound
		void soundPlayer(EZSound file){
			if(file.getFramePosistion()==0){
				file.play();
			}else if(file.getFrameLength()==file.getFramePosistion()){
				file.stop();
				System.out.println(file+"sound played");
			}
		}

}
