import java.util.ArrayList;
import java.util.Random;

public class TorielBoss extends Monster{
	DebugHelper d=new DebugHelper();
	//create string called file1 initialized a
	static String file1="./Sprites/spr_torielboss_0.png";

	//create array of strings called filenames and place file1 and fil2 inside
	static String fileNames[]={file1};

	int aPos[]=new int[2];
	Random r=new Random();
	int patternId;
	int numberOfPatterns;
	//constructor
	public TorielBoss(int posx,int posy,double size) {
		//superclass that needs fileName and size
		super(fileNames,posx,posy,size);
		// TODO Auto-generated constructor stub
		numberOfPatterns=3;
		aPos[0]=posx;
		aPos[1]=posy;
		patternId=r.nextInt(numberOfPatterns);


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
		t=new Thread(this,"Toriel");
		t.start();
	}






	ArrayList<FlipBook>alBullets=new ArrayList<FlipBook>();
	FlipBook fbBullet;
	String bulletFileName[]={
			"./Sprites/spr_torielflame_0.png",
			"./Sprites/spr_torielflame_1.png",
			"./Sprites/spr_torielflame_2.png",
			"./Sprites/spr_torielflame_3.png"
	};
	long bulletAnimationDuration=(long) (1000*.05);
	int delayCounter=0;
	int delayTime;
	//method to create bulletpattern
	void bulletPattern(){
		//pattern 0
		switch(patternId){

		case 0:
			delayTime=(int) (EZ.getCurrentFrameRate()*.1);

			if(delayCounter==0){
				aPos[0]=r.nextInt(Settings.aWindowSize[0]);
				aPos[1]=0;
				fbBullet=new FlipBook(bulletFileName,bulletAnimationDuration,aPos[0],aPos[1]);
				fbBullet.scaleTo(Settings.sizeOnScreen(.0015));
				fbBullet.go();
				alBullets.add(fbBullet);
				delayCounter=delayTime;

			}
			if(delayCounter>0){
				delayCounter--;}
			
				for(FlipBook i:alBullets){
					int y=((int)Settings.sizeOnScreen(.01)+r.nextInt((int)Settings.sizeOnScreen(.01)*2));
				i.translateBy(0,y);
				i.go();
				if(i.posy()>Settings.aWindowSize[1]){
					i.remove();
				}

			}
			
			break;
			
		case 1:
			delayTime=(int) (EZ.getCurrentFrameRate()*.1);
			if(delayCounter==0){
				aPos[0]=0;
				aPos[1]=r.nextInt(Settings.aWindowSize[1]);
				fbBullet=new FlipBook(bulletFileName,bulletAnimationDuration,aPos[0],aPos[1]);
				fbBullet.scaleTo(Settings.sizeOnScreen(.0015));
				fbBullet.rotateTo(270);
				fbBullet.go();
				alBullets.add(fbBullet);
				delayCounter=(int) (EZ.getCurrentFrameRate()*.1);

			}
			if(delayCounter>0){
				delayCounter--;}
			for(FlipBook i:alBullets){
				int x=((int)Settings.sizeOnScreen(.01)+r.nextInt((int)Settings.sizeOnScreen(.01)*2));
				
				i.translateBy(x,0);
				i.go();
				if(i.posx()>Settings.aWindowSize[0]){
					i.remove();
				}

			}
			break;
			
		case 2:
			delayTime=(int) (EZ.getCurrentFrameRate()*.1);
			if(delayCounter==0){
				aPos[0]=Settings.aWindowSize[0];
				aPos[1]=r.nextInt(Settings.aWindowSize[1]);
				fbBullet=new FlipBook(bulletFileName,bulletAnimationDuration,aPos[0],aPos[1]);
				fbBullet.scaleTo(Settings.sizeOnScreen(.0015));
				fbBullet.rotateTo(90);
				fbBullet.go();
				alBullets.add(fbBullet);
				delayCounter=delayTime;

			}
			if(delayCounter>0){
				delayCounter--;}
			for(FlipBook i:alBullets){
				int x=-((int)Settings.sizeOnScreen(.01)+r.nextInt((int)Settings.sizeOnScreen(.01)*2));
				//int x=-(int)Settings.sizeOnScreen(.01);
				i.translateBy(x,0);
				i.go();
				if(i.posx()<0){
					i.remove();
				}
			}
			break;
		}
	}

	void clearBullets(){
		fbBullet.remove();
		for(FlipBook i:alBullets){
			i.hide();
			i.remove();
		}
		alBullets.clear();
	}


}


