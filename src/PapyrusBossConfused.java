import java.util.ArrayList;
import java.util.Random;

public class PapyrusBossConfused extends Monster{

	//create string called file1 initialized as amalgam
	static String file1="./Sprites/spr_papyrusboss_confuse_0.png";
	//create string called file2 initialized as amalgam
	static String file2="./Sprites/spr_papyrusboss_confuse_1.png";
	//create string called file1 initialized as amalgam
	static String file3="./Sprites/spr_papyrusboss_confuse_2.png";
	//create string called file2 initialized as amalgam
	static String file4="./Sprites/spr_papyrusboss_confuse_3.png";
	//create string called file1 initialized as amalgam
	static String file5="./Sprites/spr_papyrusboss_confuse_4.png";
	//create string called file2 initialized as amalgam
	static String file6="./Sprites/spr_papyrusboss_confuse_5.png";
	//create string called file1 initialized as amalgam
	static String file7="./Sprites/spr_papyrusboss_confuse_6.png";
	//create string called file2 initialized as amalgam
	static String file8="./Sprites/spr_papyrusboss_confuse_7.png";
	
	
	//create array of strings called filenames and place file1 and fil2 inside
	static String fileNames[]={file1,file2,file3,file4,file5,file6,file7,file8,
			file8,file7,file6,file5,file4,file3,file2};
	
	
	//constructor
	public PapyrusBossConfused(int posx,int posy,double size) {
		//superclass that needs fileName and size
		super(fileNames,posx,posy,size);
		// TODO Auto-generated constructor stub
		animationDuration=1000*2;
		numberOfPatterns=3;
		aPos[0]=posx;
		aPos[1]=posy;
		patternId=r.nextInt(numberOfPatterns);
	}
//method to initialized 
	void updateFrame(){
		//moveTowardsPosition(Player.ezPlayerHeart.getXCenter(),Player.ezPlayerHeart.getYCenter(), speed);
		imageSwitching();
	}
	
	
	Thread t;
	public void run(){
		while(monsterIsInGame){
			bulletPattern();
			EZ.refreshScreen();
		}
		clearBullets();
	}
	public void start(){
		t=new Thread(this,"PapyrusConfused");
		t.start();
	}
	
	int maxNumberOfBullets=40;
	int currentNumberOfBullets=0;
	String bulletFileName1[]={"./Sprites/spr_s_boneloop_0.png"};
	FlipBook fbBullet1;
	ArrayList<FlipBook> alBullets=new ArrayList<FlipBook>();
	long bulletAnimationDuration=(long) (1000*.05);
	int delay=0;
	int patternId;
	int numberOfPatterns;
	int aPos[]=new int[2];
	Random r=new Random();
	
	//method to create bulletpattern
	void bulletPattern(){
		//pattern 0
		switch(patternId){
		
		case 0:
			if(delay==0){
				if(r.nextBoolean()){
				aPos[0]=(int) (Settings.aWindowSize[0]-Settings.sizeOnScreen(.1));}
				else{
				aPos[0]=(int) (Settings.sizeOnScreen(.1));}	
				aPos[1]=0;
				fbBullet1=new FlipBook(bulletFileName1,bulletAnimationDuration,aPos[0],aPos[1]);
				fbBullet1.scaleTo(Settings.sizeOnScreen(.0015));
				alBullets.add(fbBullet1);
				currentNumberOfBullets++;
				delay=(int)(EZ.getCurrentFrameRate()*.2);
			}
			if(delay>0){delay--;}
			for(FlipBook bulletDown:alBullets){
				bulletDown.translateBy(0,(int)Settings.sizeOnScreen(.01));
				bulletDown.go();
				if(bulletDown.posy()>Settings.aWindowSize[1]){
					bulletDown.remove();
				}
			}
			break;
			
		case 1:
			if(delay==0){
				if(r.nextBoolean())
				aPos[0]=(int) (Settings.aWindowSize[0]-Settings.sizeOnScreen(.1));
				else
				aPos[0]=(int) (Settings.sizeOnScreen(.1));	
				aPos[1]=Settings.aWindowSize[1];
				fbBullet1=new FlipBook(bulletFileName1,bulletAnimationDuration,aPos[0],aPos[1]);
				fbBullet1.scaleTo(Settings.sizeOnScreen(.0015));
				alBullets.add(fbBullet1);
				currentNumberOfBullets++;
				delay=(int)(EZ.getCurrentFrameRate()*.2);

			}
			if(delay>0){delay--;}
			for(FlipBook bulletUp:alBullets){
				bulletUp.translateBy(0,-(int)Settings.sizeOnScreen(.01));
				bulletUp.go();
				if(bulletUp.posy()<0){
					bulletUp.remove();
				}

			}
			break;
			
		case 2:
			if(delay==0){
				maxNumberOfBullets=5;
				aPos[0]=r.nextInt(Settings.aWindowSize[0]);
				aPos[1]=r.nextInt(Settings.aWindowSize[1]);
				fbBullet1=new FlipBook(bulletFileName1,bulletAnimationDuration,aPos[0],aPos[1]);
				fbBullet1.scaleTo(Settings.sizeOnScreen(.0015));
				alBullets.add(fbBullet1);
				currentNumberOfBullets++;
				delay=(int)(EZ.getCurrentFrameRate()*1);

			}
			if(delay>0){delay--;}
			for(FlipBook i:alBullets){
				i.rotateBy(10);
				i.go();
				if(i.getRotation()>360*10){
					i.remove();
					currentNumberOfBullets--;
				}

			}
			break;
		}
	}

	void clearBullets(){
		fbBullet1.remove();
		for(FlipBook i:alBullets){
			i.hide();
			i.remove();
		}
		alBullets.clear();
	}


}

