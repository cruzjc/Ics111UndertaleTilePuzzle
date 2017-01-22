
public class Napstablook extends Monster{

	//create string called file1 initialized as amalgam
	static String file1="./Sprites/spr_napstabattle_0.png";
	//create string called file2 initialized as amalgam
	static String file2="./Sprites/spr_napstabattle_1.png";
	
	
	//create array of strings called filenames and place file1 and fil2 inside
	static String fileNames[]={file1,file2};
	
	
	//constructor
	
	static int animationDuration=1000*1;
	public Napstablook(int posx,int posy,double size) {
		//superclass that needs fileName and size
		super(fileNames,posx,posy,size,animationDuration);
		// TODO Auto-generated constructor stub
		
	}
//method to initialized 

	void updateFrame(){
		//moveTowardsPosition(Player.ezPlayerHeart.getXCenter(),Player.ezPlayerHeart.getYCenter(), speed);
		//imageSwitching();
		fbMonster.go();
	}
	//method to create bulletpattern
	void bulletPattern(){
		
	}



}

