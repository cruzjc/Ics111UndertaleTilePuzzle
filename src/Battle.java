import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Battle {
	DebugHelper d=new DebugHelper();

	//inbattle state
	boolean inBattle;

	//time left to survive
	int timer;

	//randomness
	Random r=new Random();
	ArrayList<Monster> alMonster=new ArrayList<Monster>();
	
	//constructor
	public Battle() {
		//update current state
		inBattle=true;
	}



	void updateFrame(){
		for(Monster monster:alMonster)
			monster.updateFrame();
		
		itemGenerator();
		for(Items item:alItems){
			item.updateFrame();
		}
	}

	void removeAllMonsters(){
		for(Monster monster:alMonster){
				monster.removeMonster();
		}
	}
	//transition sequence
	void enteringBattleAnimation(boolean entering){
		//in battle background related
		int aBackDropSize[]=new int[2];//x,y
		Color cBackDrop=Color.BLACK;;
		boolean backDropFill=true;
		EZRectangle ezBackDrop=EZ.addRectangle(Settings.aWindowSize[0]/2,Settings.aWindowSize[1]/2,aBackDropSize[0],aBackDropSize[1],cBackDrop, backDropFill);;

		//rate at which backdrop grows
		int growthRate;
		if(Settings.aWindowSize[0]<Settings.aWindowSize[1]){
			growthRate=(int)((double)Settings.aWindowSize[1]*.05);
		}else{
			growthRate=(int)((double)Settings.aWindowSize[0]*.05);
		}

		//entering battle state transition
		if(entering){
			aBackDropSize[0]=0;
			aBackDropSize[1]=0;
			//while background is smaller than window...
			while(aBackDropSize[0]<Settings.aWindowSize[0]||aBackDropSize[1]<Settings.aWindowSize[1]){

				//grow the background
				aBackDropSize[0]+=growthRate;
				aBackDropSize[1]+=growthRate;
				ezBackDrop.setWidth(aBackDropSize[0]);
				ezBackDrop.setHeight(aBackDropSize[1]);
				//draw elements
				EZ.refreshScreen();
			}
			
			LevelGenerator.showTiles(false);
			//exiting battle state transition
		}else{
			aBackDropSize[0]=Settings.aWindowSize[0];
			aBackDropSize[1]=Settings.aWindowSize[1];
			LevelGenerator.showTiles(true);
			//while background is smaller than window...
			while(aBackDropSize[0]>0||aBackDropSize[1]>0){
				//shring the background
				aBackDropSize[0]-=growthRate;
				aBackDropSize[1]-=growthRate;
				ezBackDrop.setWidth(aBackDropSize[0]);
				ezBackDrop.setHeight(aBackDropSize[1]);
				//draw elements

				EZ.refreshScreen();
			}
		}
	EZ.removeEZElement(ezBackDrop);
	}//end enteringbattleanimation function



	//exit sequence
	void exitBattle(){
		//removes player battle element
		EZ.removeEZElement(Player.ezPlayerHeart);
		removeAllMonsters();
		removeAllItems();
		
		//battle exit animation sequence
		enteringBattleAnimation(false);

	}

	void createPlayerAndMonsters(int numberOfMonsters){
		//update player state
		Player.friskState=1;
		//creates a player instance for battle
		Player player=new Player(true, Settings.aWindowSize[0]/2,Settings.aWindowSize[1]/4*3);
		newRandomMonster();
	}

	ArrayList<Items>alItems=new ArrayList<Items>();
	void itemGenerator(){
		int numberOfDifferentItems=4;
		if(itemTimeCounter<=0){
			if(r.nextInt(10)==0){//10 percent chance
				int id=r.nextInt(numberOfDifferentItems);
				int aPos[]={r.nextInt(Settings.aWindowSize[0]),r.nextInt(Settings.aWindowSize[1])};
				switch(id){
				case 0:
					Spaghetti spaghetti;//=new Spaghetti(aPos[0],aPos[1]);
					alItems.add(spaghetti=new Spaghetti(aPos[0],aPos[1]));
					break;
				case 1:
					Fridge fridge;//=new Fridge(aPos[0],aPos[1]);
					alItems.add(fridge=new Fridge(aPos[0],aPos[1]));
					break;
				case 2:
					PieSlice pieSlice;//=new PieSlice(aPos[0],aPos[1]);
					alItems.add(pieSlice=new PieSlice(aPos[0],aPos[1]));
					break;
				case 3:
					int speed=r.nextInt((int) Settings.sizeOnScreen(.1));
					SpaghettiPotBurnt spaghettiPotBurnt;//=new SpaghettiPotBurnt(aPos[0],aPos[1],speed);
					alItems.add(spaghettiPotBurnt=new SpaghettiPotBurnt(aPos[0],aPos[1],speed));
					break;
				}
			}
		itemTimeCounter=itemGenerationFrequency;
		}else itemTimeCounter--;
	}
	
	int itemGenerationFrequency=EZ.getCurrentFrameRate();//every second
	int itemTimeCounter=itemGenerationFrequency;
	
	
	void removeAllItems(){
		for(Items items:alItems){
			items.removeItem();
		}
		alItems.clear();
	}
	
	int difficulty=10;
	
	//int numberOfCurrentMonsters=0;
	//int numberOfMonsters=0;
	//||numberOfCurrentMonsters<numberOfMonsters;numberOfCurrentMonsters++
	
	//create a random monster
	void newRandomMonster(){
		
		
		//number of different monsters available
		int numberOfDifferentMonsters=14;
		int aPos[]=new int[2];
		//select a random monster
		int id;

		
		double size;
		int speed;
		for(int i=0;i<difficulty;){
			//static pos
			int l1MonsterPos[]={r.nextInt(Settings.aWindowSize[0]),Settings.aWindowSize[1]/2};
			int l2MonsterPos[]={r.nextInt(Settings.aWindowSize[0]),(int)((double)Settings.aWindowSize[1]/2.5)};
			int l3MonsterPos[]={r.nextInt(Settings.aWindowSize[0]),Settings.aWindowSize[1]/3};
			int l4MonsterPos[]={r.nextInt(Settings.aWindowSize[0]),(int)((double)Settings.aWindowSize[1]/3.5)};
			int l5MonsterPos[]={r.nextInt(Settings.aWindowSize[0]),Settings.aWindowSize[1]/4};

			int l6MonsterPos[]={(Settings.aWindowSize[0])/2,Settings.aWindowSize[1]/4};
			int l7MonsterPos[]={(Settings.aWindowSize[0])/2,Settings.aWindowSize[1]/4};

			//select a random monster
		id=r.nextInt(numberOfDifferentMonsters);
		//id=13;//used to select a specific monster for debugging purposes
		switch(id){
		case 0:			
			size=.1;
			speed=2+r.nextInt(5);
			aPos[0]=l2MonsterPos[0];
			aPos[1]=l2MonsterPos[1];
			AmalgamateDog ad=new AmalgamateDog(aPos[0],aPos[1],size,speed);
			alMonster.add(ad);
			i+=2;
			break;
		case 1:
			size=.15;
			speed=2+r.nextInt(2);
			aPos[0]=l2MonsterPos[0];
			aPos[1]=l2MonsterPos[1];
			AmalgamateFridge af=new AmalgamateFridge(aPos[0],aPos[1],size,speed);
			alMonster.add(af);	
			i+=2;
			break;
		case 2:
			size=.2;
			speed=1+r.nextInt(1);
			aPos=l1MonsterPos;
			AmalgamateSink as=new AmalgamateSink(aPos[0],aPos[1],size,speed);
			alMonster.add(as);
			i+=1;
			break;
		case 3:
			size=.1;
			speed=2+r.nextInt(5);
			aPos=l2MonsterPos;
			AmalgamateSave asa=new AmalgamateSave(aPos[0],aPos[1],size,speed);
			alMonster.add(asa);
			i+=2;
			break;
		case 4:
			size=.15;
			aPos=l6MonsterPos;
			aPos[0]=Settings.aWindowSize[0]/10*4;
			TorielBoss toriel=new TorielBoss(aPos[0],aPos[1],size);
			toriel.start();
			alMonster.add(toriel);
			i+=6;
			break;
		case 5:
			size=.10;
			aPos=l4MonsterPos;
			Astigmatism astigmatism=new Astigmatism(aPos[0],aPos[1],size);
			alMonster.add(astigmatism);	
			i+=4;
			break;
		case 6:
			size=.15;
			aPos=l6MonsterPos;
			aPos[0]=Settings.aWindowSize[0]/10*3;
			PapyrusBossConfused papyrusBossConfused=new PapyrusBossConfused(aPos[0],aPos[1],size);
			papyrusBossConfused.start();
			alMonster.add(papyrusBossConfused);	
			i+=5;
			break;
		case 7:
			size=.3;
			aPos[0]=Settings.aWindowSize[0]/2;//unique
			aPos[1]=Settings.aWindowSize[1]/10*9;
			HideousCupCake hideousCupCake=new HideousCupCake(aPos[0],aPos[1],size);
			alMonster.add(hideousCupCake);	
			i+=1;
			break;
		case 8:
			size=.075;
			aPos[0]=l1MonsterPos[0];
			aPos[1]=l1MonsterPos[1];
			Froggit froggit=new Froggit(aPos[0],aPos[1],size);
			alMonster.add(froggit);	
			i+=1;
			break;
		case 9:
			size=.15;
			aPos[0]=l3MonsterPos[0];
			aPos[1]=l3MonsterPos[1];
			GreatDog greatdog=new GreatDog(aPos[0],aPos[1],size);
			alMonster.add(greatdog);	
			i+=3;
			break;
		case 10:
			size=.1;
			aPos[0]=l1MonsterPos[0];
			aPos[1]=l1MonsterPos[1];
			IceCap icecap=new IceCap(aPos[0],aPos[1],size);
			alMonster.add(icecap);	
			i+=1;
			break;
		case 11:
			size=.1;
			aPos[0]=Settings.aWindowSize[0]/10*2;//unique
			aPos[1]=l3MonsterPos[1];
			SnowDrakesMom snowdrakesmom=new SnowDrakesMom(aPos[0],aPos[1],size);
			alMonster.add(snowdrakesmom);	
			i+=3;
			break;
		case 12:
			size=.1;
			aPos[0]=Settings.aWindowSize[0]/10*9;//unique character
			aPos[1]=l3MonsterPos[1];
			Napstablook napstablook=new Napstablook(aPos[0],aPos[1],size);
			alMonster.add(napstablook);	
			i+=3;
			break;
		case 13:
			size=.1;
			aPos[0]=l4MonsterPos[0];
			aPos[1]=l4MonsterPos[1];
			ReaperBird reaperBird=new ReaperBird(aPos[0],aPos[1],size);
			alMonster.add(reaperBird);	
			i+=4;
			break;
		}
	}
	}
}
