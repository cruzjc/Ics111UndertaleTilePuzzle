import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameStart{
	DebugHelper d=new DebugHelper();

	//status of gameInProgress class
	Boolean gameInProgress;

	//sound
	////music
	ArrayList<String>alInGameMusic=new ArrayList<String>();
	ArrayList<String>alInBattleMusic=new ArrayList<String>();
	ArrayList<EZSound>alEzInGameMusic=new ArrayList<EZSound>();
	ArrayList<EZSound>alEzInBattleMusic=new ArrayList<EZSound>();
	int currentMusicId;
	Boolean musicPlaying;
	Boolean musicStateChanged;

	////sound fx
	String sZap;
	EZSound ezZap;

	String sReflect;//traversable tile
	String sEncounter;
	String sFlee;
	String sHeal;
	String sShallowWater;
	String sSoulHit;

	EZSound ezReflect;//traversable tile
	EZSound ezEncounter;
	EZSound ezFlee;
	EZSound ezHeal;
	EZSound ezShallowWater;
	EZSound ezSoulHit;


	//battle related
	Boolean inBattle;
	int battleRestTime=EZ.getCurrentFrameRate()*5;
	int battleRestTimer=battleRestTime;
	int numberOfMonsters;
	int maxNumberOfMonsters;
	//randomness
	Random r;


	//classes
	LevelGenerator lg;
	Battle b;
	Player player;

	//outcome of game
	boolean playerWin;

	//used for tile detection
	EZElement ezContainer;
	ArrayList<EZElement> alEzElementContainer;
	Color purple=new Color(102,0,153);



	//constructor
	public GameStart() {
		gameInProgress=true;		
		lg=new LevelGenerator();
		player=new Player(false,Settings.aWindowSize[0]/2,Settings.aWindowSize[1]/2);
		


		r=new Random();

		//sound/music
		////ingame
		alInGameMusic.add("./Sounds/InGame/toby fox - UNDERTALE Soundtrack - 51 Another Medium.wav");
		alInGameMusic.add("./Sounds/InGame/toby fox - UNDERTALE Soundtrack - 65 CORE.wav");
		for(int i=0;i<alInGameMusic.size();i++){
			//alEzInGameMusic.set(i, new EZSound(alInGameMusic.get(i)));
			alEzInGameMusic.add(new EZSound(alInGameMusic.get(i)));
		}

		////inbattle
		alInBattleMusic.add("./Sounds/InBattle/toby fox - UNDERTALE Soundtrack - 100 MEGALOVANIA.wav");
		alInBattleMusic.add("./Sounds/InBattle/toby fox - UNDERTALE Soundtrack - 16 Nyeh Heh Heh!.wav");
		alInBattleMusic.add("./Sounds/InBattle/toby fox - UNDERTALE Soundtrack - 24 Bonetrousle.wav");
		alInBattleMusic.add("./Sounds/InBattle/toby fox - UNDERTALE Soundtrack - 50 Metal Crusher.wav");
		alInBattleMusic.add("./Sounds/InBattle/toby fox - UNDERTALE Soundtrack - 59 Spider Dance.wav");
		alInBattleMusic.add("./Sounds/InBattle/toby fox - UNDERTALE Soundtrack - 87 Hopes and Dreams.wav");
		alInBattleMusic.add("./Sounds/InBattle/toby fox - UNDERTALE Soundtrack - 89 SAVE the World.wav");
		for(int i=0;i<alInBattleMusic.size();i++){
			alEzInBattleMusic.add(new EZSound(alInBattleMusic.get(i)));
		}

		currentMusicId=0;
		musicPlaying=false;

		//sound fx
		sZap="./Sounds/ZAAAP.wav";
		ezZap=new EZSound(sZap);

		sReflect="./Sounds/Reflect.wav";//traversable tile
		sEncounter="./Sounds/Encounter.wav";
		sFlee="./Sounds/Flee.wav";
		sHeal="./Sounds/Heal.wav";
		sShallowWater="./Sounds/Shallow Water.wav";
		sSoulHit="./Sounds/Soul Hit.wav";

		ezReflect=new EZSound(sReflect);//traversable tile
		ezEncounter=new EZSound(sEncounter);
		ezFlee=new EZSound(sFlee);
		ezHeal=new EZSound(sHeal);
		ezShallowWater=new EZSound(sShallowWater);
		ezSoulHit=new EZSound(sSoulHit);

		timeInbetweenSounds=EZ.getCurrentFrameRate()*2;

		//player
		inBattle=false;
		musicStateChanged=true;

	}

	//game start mechanics
	void updateLoop(){
		player.showPlayerHp();
		player.friskState=0;
		
		
		while(gameInProgress){
			if(battleRestTimer>0){
				battleRestTimer--;
			}
			//start music
			soundPlayer();

			//checks for exit command
			ExitCheck.exitCheck();

			//player control check
			player.processFriskState();
			tileCollisionCheck();

			levelCompletionCheck();

			
			
			//draw elements
			EZ.refreshScreen();
			
			
			if(player.playerHp==0){
				gameInProgress=false;
				playerWin=false;
			}
		}
		musicStateChanged=true;
		endGame=new EndGame(playerWin);
		endGame.initialize();
		endGameUpdateLoop();
		//endGame.updateloop();
	}
EndGame endGame;
	void endGameUpdateLoop(){
		boolean inEndGame=true;
		while(inEndGame){
			
			//player.pc();
			//endGame.pc();
			
			
			if(EZInteraction.wasKeyPressed(Settings.useKey))
				//inEndGame=false;
			
			EZ.refreshScreen();
		}
	}
	
	//battle mechanics loop
	Battle battle;
	static EZText escapeInstructions;
	void battleUpdateLoop(){
		//states that a battle is in progress
		inBattle=true;

		//time to survive for
		int battleTime=EZ.getCurrentFrameRate()*20;
		//timer
		int timer=battleTime;

		//change type of music
		musicStateChanged=true;



		//creates a battle
		battle=new Battle();
		//start battle animation transition
		battle.enteringBattleAnimation(true);
		player.ezCurrent.hide();

		maxNumberOfMonsters=4;
		numberOfMonsters=2+r.nextInt(maxNumberOfMonsters);
		battle.createPlayerAndMonsters(numberOfMonsters);
		
		
		escapeInstructions=EZ.addText("8bitOperatorPlus-Regular.ttf",Settings.aWindowSize[0]/2, Settings.aWindowSize[1]/15, "Avoid/Dodge the monster(s) for "+battleTime/EZ.getCurrentFrameRate()+" seconds!", Color.white,(int)Settings.sizeOnScreen(.025));
		//while in battle...
		while(inBattle){
			//plays music
			soundPlayer();

			//player controls check
			player.processFriskState();

			//updates frames
			battle.updateFrame();

			//decreases timer
			if(timer>0){
			timer--;}


			//checks if player lost
			if(Player.friskState==2){
				//stops timer
				timer=0;
				//changes gameInProgress state
				gameInProgress=false;
				playerWin=false;
				//stops music
				alEzInBattleMusic.get(currentMusicId).stop();
			}

			
			if(timer==1){
				escapeInstructions.setMsg("You can now escape! Move to the edge of the screen to continue");
				escapeInstructions.pullToFront();
			}
			//if timer is 0...
			player.playerTryingToEscapeCheck();
			if(timer<=0&&player.playerTryingToEscape){
				//change inBattle state
				inBattle=false;
				soundPlayer(ezFlee);
			}else if(timer<=0&&playerWin==false&&gameInProgress==false){
				inBattle=false;
			}





			//listens for exit command
			ExitCheck.exitCheck();
			//draws elements
			EZ.refreshScreen();
		}//end battle mechanics loop
		EZ.removeEZElement(escapeInstructions);
		//resets the battle timer
		timer=battleTime;

		//battle class exit sequence
		battle.exitBattle();
		player.ezCurrent.show();

		//updates the state of frisk
		Player.friskState=0;

		//changes type of music
		musicStateChanged=true;
	}

	void initialize(){
		LevelGenerator.moveAllTiles(player.posx,player.posy+player.ezCurrent.getHeight()/10*9);
		//top left position of the entire playable area
		player.posx=0;//LevelGenerator.tileSize/2;
		player.posy=0;//player.ezCurrent.getWorldHeight()/4;//=LevelGenerator.tileSize/2;
	}


	
	//victory check
	void levelCompletionCheck(){
		if(player.posx>=player.aPlayableArea[0]){
			playerWin=true;
			gameInProgress=false;
			musicStateChanged=true;
			player.friskState=0;
			//stops music
			alEzInGameMusic.get(currentMusicId).stop();
		}
	}

	//plays specified sound
	void soundPlayer(EZSound file){
		if(file.getFramePosistion()==0){
			file.play();
			soundPlayedRecently=true;
		}else if(file.getFrameLength()==file.getFramePosistion()){
			file.stop();
			System.out.println(file+"sound played");
		}
	}

	//plays music
	void soundPlayer(){
		//if state changed between battle and idle
		if(musicStateChanged){

			//stop current music
			if(inBattle)
				alEzInGameMusic.get(currentMusicId).stop();
			else
				alEzInBattleMusic.get(currentMusicId).stop();

			musicPlaying=false;
			musicStateChanged=false;
		}

		//not in battle music
		if(!musicPlaying&&!inBattle){
			currentMusicId=r.nextInt(alInGameMusic.size());
			System.out.println(alInGameMusic.get(currentMusicId));
			alEzInGameMusic.get(currentMusicId).play();
			musicPlaying=true;
		}else if(!inBattle&&alEzInGameMusic.get(currentMusicId).getFrameLength()==alEzInGameMusic.get(currentMusicId).getFramePosistion()){
			musicPlaying=false;
		}

		//battle music
		if(!musicPlaying&&inBattle){
			currentMusicId=r.nextInt(alInBattleMusic.size());
			System.out.println(alInBattleMusic.get(currentMusicId));
			alEzInBattleMusic.get(currentMusicId).play();
			musicPlaying=true;
		}else if(inBattle&&alEzInBattleMusic.get(currentMusicId).getFrameLength()==alEzInBattleMusic.get(currentMusicId).getFramePosistion()){
			musicPlaying=false;
		}
	}

	//checks for tile collision

	boolean soundPlayedRecently;
	int soundTimer;
	int timeInbetweenSounds;

	void tileCollisionCheck(){
		if(soundPlayedRecently&&soundTimer>0){
			soundTimer--;}
		else{
			soundTimer=timeInbetweenSounds;
			soundPlayedRecently=false;
		}


		//gets all elements at a point
		alEzElementContainer=EZ.getAllElementsContainingPoint(player.ezCurrent.getXCenter(),player.ezCurrent.getYCenter()+player.ezCurrent.getHeight()*2);
		//if something else other than the player ezimage is in detected...
		if(alEzElementContainer.size()>0){
			try {
				//store it in a container
				ezContainer=alEzElementContainer.get(0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}



		//if something is in the container
		if(ezContainer!=null){
			//if it is color red...
			if(ezContainer.getColor()==Color.red){
				//System.out.println("Red tile contact");
				ezContainer=null;
				//stops player
				player.playerReverseMove(player.speed()*4);
				//player.playerMove(-player.speed()*2);

				//if it is color yellow...
			}else if(ezContainer.getColor()==Color.yellow){
				//System.out.println("Yellow tile contact");
				ezContainer=null;
				//plays zapping sound
				soundPlayer(ezZap);
				//stops player
				player.playerReverseMove(player.speed()*2);
				//player.playerMove(-player.speed);
				Player.playerHp--;

				//if it is color green...
			}else if(ezContainer.getColor()==Color.green){
				//System.out.println("Green tile contact");
				if(!soundPlayedRecently){
				//soundPlayer(ezReflect);
				soundTimer=EZ.getCurrentFrameRate()*1;
				}
				

				//System.out.println("Battle Rest time remaining "+battleRestTimer);

				//if no battle has been done recently...
				if(battleRestTimer<=0){
					ezContainer.setColor(new Color(0,100,0));//changes color so battle wont be repeated
					
					soundPlayer(ezEncounter);
					
					//initiates battle class
					battleUpdateLoop();	
					//sets battle rest timer
					battleRestTimer=EZ.getCurrentFrameRate()*10;
				}

				ezContainer=null;
				
				//if it is color orange...
			}else if(ezContainer.getColor()==Color.orange){
				//System.out.println("Orange tile contact");
				if(!soundPlayedRecently){
				soundPlayer(ezReflect);
				soundTimer=EZ.getCurrentFrameRate()*3;
				}
				ezContainer=null;
				//changes player color
				//changes player flavor
				Player.flavor=1;

				//if it is color blue..
			}else if(ezContainer.getColor()==Color.blue){
				boolean nextToYellowTile;
				int aPos[]=new int[2];
				//top tile
				boolean topTile=false;
				aPos[0]=ezContainer.getXCenter();
				aPos[1]=ezContainer.getYCenter()-LevelGenerator.tileSize;
				if(EZ.getTopElementContainingPoint(aPos[0],aPos[1])!=null){
					topTile=EZ.getTopElementContainingPoint(aPos[0],aPos[1]).getColor()==Color.yellow;}
				//bottom tile
				boolean bottomTile=false;
				aPos[0]=ezContainer.getXCenter();
				aPos[1]=ezContainer.getYCenter()+LevelGenerator.tileSize;
				if(EZ.getTopElementContainingPoint(aPos[0],aPos[1])!=null){
					bottomTile=EZ.getTopElementContainingPoint(aPos[0],aPos[1]).getColor()==Color.yellow;}
				//left tile
				boolean leftTile=false;
				aPos[0]=ezContainer.getXCenter()-LevelGenerator.tileSize;
				aPos[1]=ezContainer.getYCenter();
				if(EZ.getTopElementContainingPoint(aPos[0],aPos[1])!=null){
					leftTile=EZ.getTopElementContainingPoint(aPos[0],aPos[1]).getColor()==Color.yellow;}
				//right tile
				boolean rightTile=false;
				aPos[0]=ezContainer.getXCenter()+LevelGenerator.tileSize;
				aPos[1]=ezContainer.getYCenter();
				if(EZ.getTopElementContainingPoint(aPos[0],aPos[1])!=null){
					rightTile=EZ.getTopElementContainingPoint(aPos[0],aPos[1]).getColor()==Color.yellow;}

				nextToYellowTile=topTile||bottomTile||leftTile||rightTile;
				//System.out.println("Blue tile contact");
				
				if(!soundPlayedRecently){
				soundPlayer(ezShallowWater);
				soundTimer=EZ.getCurrentFrameRate()*1;
				}
				
				ezContainer=null;
				//if next to a yellow, 
				////prevents passage


				//if player stepped on an orange tile, pirahna
				////prevents passage
				if(nextToYellowTile){
					soundPlayer(ezZap);
					Player.playerHp--;
					player.playerReverseMove(player.speed()*2);
					//player.playerMove(-player.speed);
				}
				
				else if(Player.flavor==1){
					//soundPlayer()
					Player.playerHp--;
					player.playerReverseMove(player.speed()*2);
					//player.playerMove(-player.speed);
				}

				//if player is not orange scented,
				////grants passage

				//if it is color purple...
			}else if(ezContainer.getColor().equals(lg.cPurple)){
				//System.out.println("Purple tile contact");
				if(!soundPlayedRecently){
				soundPlayer(ezReflect);
				soundTimer=EZ.getCurrentFrameRate()*1;
				}
				ezContainer=null;
				//change player color
				//change player flavor
				Player.flavor=2;
				
				
				//
				player.playerMove(player.speed()*2);
				/*
				//slides to next tile
				for(int i=0;i<4;i++){
					//player.playerMove(1);
					if(player.direction==0){
						LevelGenerator.moveAllTiles(0,(LevelGenerator.tileSize/4));
						Player.posy+=(LevelGenerator.tileSize/4);
					}
					if(player.direction==1){
						LevelGenerator.moveAllTiles(0,-LevelGenerator.tileSize/4);
						Player.posy-=LevelGenerator.tileSize/4;
					}
					if(player.direction==2){
						LevelGenerator.moveAllTiles(-LevelGenerator.tileSize/4,0);
						Player.posx+=LevelGenerator.tileSize/4;
					}
					if(player.direction==3){
						LevelGenerator.moveAllTiles(LevelGenerator.tileSize/4,0);
						Player.posx-=LevelGenerator.tileSize/4;
					}
					
					EZ.refreshScreen();
				}
				 */
				//if it is color pink...
			}else if(ezContainer.getColor()==Color.pink){
				//System.out.println("Pink tile contact");
				
				
				if(!soundPlayedRecently){
				//soundPlayer(ezReflect);
				soundTimer=EZ.getCurrentFrameRate()*2;
				}
				
				ezContainer=null;
				//grants passage, does nothing

				//if no specific color is detected...
			}else{
				//empty out container
				ezContainer=null;
			}
		}//end "if ezContainer is not null" bracket
	}//end tile collision check
	
	
	
	
	
	
	//=========================================================================
	//=nope========================================
	
	/*
	 * 
	 * tileFlasher();
	 * 
				if(!alTilesToBeFlashed.contains(ezContainer)){
				alTilesToBeFlashed.add((EZRectangle) ezContainer);
				}
	 */
	int timeBetweenTileFlashes=0;
	
	
	
	ArrayList<EZRectangle>alTilesToBeFlashed=new ArrayList<EZRectangle>();
	ArrayList<Color>alColorHistory=new ArrayList<Color>();
	List<Integer>alTileDurationCounter=new ArrayList<Integer>();
	int tileColorChangeDuration=(int)(EZ.getCurrentFrameRate());//1/4 a second for a white tile, 3/4 for original color before flashing again
	//int tileColorChangeCounter=tileColorChangeDuration;
	
	Color colorContainer;
	void tileFlasher(){
			
		if(alTilesToBeFlashed!=null){
			
			if(alTileDurationCounter.size()!=alTilesToBeFlashed.size()){
				alColorHistory.add(alTilesToBeFlashed.get(0).getColor());
				alTilesToBeFlashed.get(0).setColor(Color.white);
				alTileDurationCounter.add(tileColorChangeDuration );
			}
			
			for(int i=0;i<alTileDurationCounter.size();i++){
				int c=alTileDurationCounter.get(i);
				if(c>0){
				c--;
				alTileDurationCounter.set(i, (Integer)c);
				}
				if(c==tileColorChangeDuration/4*3){
					colorContainer=alColorHistory.get(alColorHistory.size()-1);
					alTilesToBeFlashed.get(alTilesToBeFlashed.size()-1).setColor(colorContainer);
				} 
					if(c==0){
					//alTilesToBeFlashed.remove(alTilesToBeFlashed.size()-1);
					alTilesToBeFlashed.remove(alTilesToBeFlashed.lastIndexOf(ezContainer));
					//alTileDurationCounter.remove(alTileDurationCounter.size()-1);
					alTileDurationCounter.remove(alTileDurationCounter.lastIndexOf(tileColorChangeDuration));
					//alColorHistory.remove(alColorHistory.size()-1);
					alColorHistory.remove(alColorHistory.lastIndexOf(colorContainer));
				}
			}
			
			/*
			for(Integer i:alTileDurationCounter){
				if(i>0){
					i-=1;
					d.o(i);
				}
				if(i==0){
					Color colorContainer=alColorHistory.get(alColorHistory.size()-1);
					alTilesToBeFlashed.get(alTilesToBeFlashed.size()-1).setColor(colorContainer);
					alTilesToBeFlashed.remove(alTilesToBeFlashed.size()-1);
					alTileDurationCounter.remove(alTileDurationCounter.size()-1);
					alColorHistory.remove(alColorHistory.size()-1);
				}
			}
			*/
		}

	}
	

	

}
