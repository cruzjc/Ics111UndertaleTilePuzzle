import java.util.ArrayList;

public class Monster extends Thread{
	//create new debug helper
	DebugHelper d=new DebugHelper();
	//create an array list of images called monster
	ArrayList<EZImage> alMonster=new ArrayList<EZImage>();
	//create ezimage called ezmonsterimage
	EZImage ezMonsterImage;
	//create array of int called monsterPos to hold 2 integers
	int monsterPos[]=new int[2];
	//create static integer called currentimageid
	int currentImageId=0;
	
	Boolean monsterIsInGame;
	
	FlipBook fbMonster;
	long animationDuration=1000*2;
	ArrayList<FlipBook> alFbMonster=new ArrayList<FlipBook>();
	
	public Monster(){
		
	}
	EZGroup ezgMonster;
	public Monster(FlipBook[] aFlipBook, int posx,int posy,double size){
		for(FlipBook fb:aFlipBook){
			//fb.scaleBy(Settings.sizeOnScreen(size)/fbMonster.getWidth());
			fb.go();
			ezgMonster.addElement(fb.ezGroup());
		}
		//ezgMonster.translateTo(posx, posy);
	}
	
	public Monster(String fileNames[],int posx,int posy,Double size,long animationDuration) {
		monsterIsInGame=true;
		//create array called monsterPos 0 and set it to be half the windowsize 0
		monsterPos[0]=posx;
		//create array called monsterPos 1 and set it to be a quarter of windowsize 1
		monsterPos[1]=posy;
		fbMonster=new FlipBook(fileNames, animationDuration, posx, posy);
		fbMonster.scaleBy(Settings.sizeOnScreen(size)/fbMonster.getWidth());
		
		alFbMonster.add(fbMonster);
		this.animationDuration=animationDuration;
	}
	
	//constructor
	public Monster(String fileNames[],int posx,int posy,Double size) {
		monsterIsInGame=true;
		//create array called monsterPos 0 and set it to be half the windowsize 0
		monsterPos[0]=posx;
		//create array called monsterPos 1 and set it to be a quarter of windowsize 1
		monsterPos[1]=posy;
		fbMonster=new FlipBook(fileNames, animationDuration, posx, posy);
		fbMonster.scaleBy(Settings.sizeOnScreen(size)/fbMonster.getWidth());
		
		alFbMonster.add(fbMonster);
		
		
		//older code
		/*
		//for all the filenames
		for(String string:fileNames){
			//create ezmonsterimage at position monsterpos 0 and monsterpos 1
			ezMonsterImage=EZ.addImage(string, monsterPos[0],monsterPos[1]);
			//add ezmonster image to monster
			alMonster.add(ezMonsterImage);
		}
		//for the ith monster image
		for(EZImage i:alMonster){
			//scale the ith monster 
			i.scaleBy(Settings.sizeOnScreen(size)/i.getWidth());
			//hide the ith monster
			i.hide();
		}
		//get the 0th monster and display it
		alMonster.get(0).show();
		imageHeight=alMonster.get(0).getWorldHeight();
		*/
	}
	
	int imageHeight;
	int imageHeight(){
		return imageHeight;
	}
	
	
	
	//create static int called framecounter and initialized to 0
	int frameCounter=0;
	//method to control image switching
	void imageSwitching(){
		fbMonster.go();
		//older code
		/*
		//create integer called image time and initialized at current frame rate/2
		int imageTime=EZ.getCurrentFrameRate()/4;
		//if the framerate counter is equal to imagetime
		if(frameCounter==imageTime){
			//get the currentimageid of the monster and hide it
			alMonster.get(currentImageId).hide();
			//increase the currentimageid
			currentImageId++;
			//if the currentimageid is equal ot the monster side
			if(currentImageId==alMonster.size()){
				//set currentimageid to 0
				currentImageId=0;
			}
			//get the currentimageid for the monster and show it
			alMonster.get(currentImageId).show();
			//set framecounter to 0
			frameCounter=0;
		}
		//increase framecounter
		frameCounter++;
		
		*/
	}
	//method to remove monsters
	void removeMonster(){
		monsterIsInGame=false;
		for(FlipBook fb:alFbMonster){
			fb.remove();
		}
		alFbMonster.clear();
		
		//older code
		/*
		//for monster element
		for(EZElement element:alMonster){
			//remove the element
			EZ.removeEZElement(element);
		}
		
		//clear monster
		alMonster.clear();
		*/
	
	}
	//boolean for collison at position
	boolean isAtPosition;
	//boolean for collision near position
	boolean isNearPosition;
	//method to manage monster movement
	void moveGroupTowardsPosition(int x,int y, int speed){
		if(!isAtPosition&&!isNearPosition){
			//if the image x center if greater than x position
			if(ezgMonster.getXCenter()>x){
				//move image by -speed
				ezgMonster.translateBy(-speed,0);}
			//if image x center is less than the x position
			if(ezgMonster.getXCenter()<x){
				//move image by speed
				ezgMonster.translateBy(speed, 0);
			}
			//if image y center is greater than y position
			if(ezgMonster.getYCenter()>y){
				//move image by -speed
				ezgMonster.translateBy(0,-speed);
			}
			//if image y center is less than y position
			if(ezgMonster.getYCenter()<y){
				//move image by speed
				ezgMonster.translateBy(0,speed);
			}
		
		}//otherwise the image is near position
		else if(isNearPosition){
			//move image to x, y
			ezgMonster.translateTo(x, y);
		}
	}
	
	void moveTowardsPosition(int x,int y, int speed){		
		for(FlipBook image:alFbMonster){
			//set isatposition to be the image x canter to be x and the image y center to be y
			isAtPosition=image.posx()==x&&image.posy()==y;
			//set isnearposition to be absolute value of image x center -x to be equal to speed and absolute value to image y center -y to be equal to y
			isNearPosition=Math.abs(image.posx()-x)<=speed&&Math.abs(image.posy()-y)<=speed;
			//if the image is not at position or near position
			if(!isAtPosition&&!isNearPosition){
				//if the image x center if greater than x position
				if(image.posx()>x){
					//move image by -speed
					image.translateBy(-speed,0);}
				//if image x center is less than the x position
				if(image.posx()<x){
					//move image by speed
					image.translateBy(speed, 0);
				}
				//if image y center is greater than y position
				if(image.posy()>y){
					//move image by -speed
					image.translateBy(0,-speed);
				}
				//if image y center is less than y position
				if(image.posy()<y){
					//move image by speed
					image.translateBy(0,speed);
				}
			
			}//otherwise the image is near position
			else if(isNearPosition){
				//move image to x, y
				image.translateTo(x, y);
			}
		}
		
		
		/*
		//for monster image
		for(EZImage image:alMonster){
			//set isatposition to be the image x canter to be x and the image y center to be y
			isAtPosition=image.getXCenter()==x&&image.getYCenter()==y;
			//set isnearposition to be absolute value of image x center -x to be equal to speed and absolute value to image y center -y to be equal to y
			isNearPosition=Math.abs(image.getXCenter()-x)<=speed&&Math.abs(image.getYCenter()-y)<=speed;
			//if the image is not at position or near position
			if(!isAtPosition&&!isNearPosition){
				//if the image x center if greater than x position
				if(image.getXCenter()>x){
					//move image by -speed
					image.translateBy(-speed,0);}
				//if image x center is less than the x position
				if(image.getXCenter()<x){
					//move image by speed
					image.translateBy(speed, 0);
				}
				//if image y center is greater than y position
				if(image.getYCenter()>y){
					//move image by -speed
					image.translateBy(0,-speed);
				}
				//if image y center is less than y position
				if(image.getYCenter()<y){
					//move image by speed
					image.translateBy(0,speed);
				}
			
			}//otherwise the image is near position
			else if(isNearPosition){
				//move image to x, y
				image.translateTo(x, y);
			}
		}
		*/
	}
	
	void updateFrame(){
		imageSwitching();
	}
	
	void bulletPattern(){
		
	}
	void clearBullets(){
		
	}
}
