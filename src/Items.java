
public class Items {
	DebugHelper d=new DebugHelper();
	String itemFileName;
	EZImage ezItem;
	int pos[]=new int[2];
	int toPos[]=new int[2];
	int speed;
	boolean isMoving;
	public Items(String itemFileName,int x,int y) {
		// TODO Auto-generated constructor stub
		this.itemFileName="./Sprites/"+itemFileName;
		pos[0]=x;
		pos[1]=y;
		ezItem=EZ.addImage(this.itemFileName, x, y);
		ezItem.scaleTo(Settings.sizeOnScreen(.0025));
	}
	
	public Items(String itemFileName,int x,int y, int toPosX,int toPosY,int speed){
		isMoving=true;
		this.itemFileName="./Sprites/"+itemFileName;
		pos[0]=x;
		pos[1]=y;
		ezItem=EZ.addImage(this.itemFileName, x, y);
		ezItem.scaleTo(Settings.sizeOnScreen(.0025));
		this.toPos[0]=toPosX;
		this.toPos[1]=toPosY;
		this.speed=speed;
	}
	
	void updateFrame(){
		if(isMoving)
		moveToLocation(toPos[0],toPos[1],speed);
		
		playerItemCollisionCheck();
		
	}
	
	boolean inGame=true;
	boolean playerItemCollisionCheck(){
		if(inGame&&EZ.isElementAtPoint(Player.ezPlayerHeart.getXCenter(),Player.ezPlayerHeart.getYCenter(),ezItem)){
			return true;
		}else return false;
	}
	
	
	void healsPlayer(int determination){
		EZSound heal=EZ.addSound("./Sounds/heal.wav");
		heal.play();
		Player.playerHp+=determination;
	}
	
	void removeItem(){
		System.out.println(itemFileName+"removed");
		inGame=false;
		EZ.removeEZElement(ezItem);
	}
	
	
	
	boolean isAtPosition;
	//boolean for collision near position
	boolean isNearPosition;
	void moveToLocation(int x,int y, int speed){
		//set isatposition to be the image x canter to be x and the image y center to be y
		isAtPosition=ezItem.getXCenter()==x&&ezItem.getYCenter()==y;
		//set isnearposition to be absolute value of image x center -x to be equal to speed and absolute value to image y center -y to be equal to y
		isNearPosition=Math.abs(ezItem.getXCenter()-x)<=speed&&Math.abs(ezItem.getYCenter()-y)<=speed;
		//if the image is not at position or near position
		
		if(!isAtPosition&&!isNearPosition){
			//if the image x center if greater than x position
			if(ezItem.getXCenter()>x){
				//move image by -speed
				ezItem.translateBy(-speed,0);}
			//if image x center is less than the x position
			if(ezItem.getXCenter()<x){
				//move image by speed
				ezItem.translateBy(speed, 0);
			}
			//if image y center is greater than y position
			if(ezItem.getYCenter()>y){
				//move image by -speed
				ezItem.translateBy(0,-speed);
			}
			//if image y center is less than y position
			if(ezItem.getYCenter()<y){
				//move image by speed
				ezItem.translateBy(0,speed);
			}
		
		}//otherwise the image is near position
		else if(isNearPosition){
			//move image to x, y
			ezItem.translateTo(x, y);
		}
	}
}
