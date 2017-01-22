
public class AmalgamateSave extends Monster{

	//create string called file1 initialized as amalgam
	static String file1="./Sprites/spr_amalgam_save_npc_0.png";
	//create string called file2 initialized as amalgam
	static String file2="./Sprites/spr_amalgam_save_npc_1.png";
	//create string called file1 initialized as amalgam
	
	//create array of strings called filenames and place file1 and fil2 inside
	static String fileNames[]={file1,file2};
	
	int speed;
	
	//constructor
	public AmalgamateSave(int posx,int posy,double size,int speed) {
		//superclass that needs fileName and size
		super(fileNames,posx,posy,size);
		// TODO Auto-generated constructor stub
		this.speed=speed;
	}
//method to initialized 
	void updateFrame(){
		moveTowardsPosition(Player.ezPlayerHeart.getXCenter(),Player.ezPlayerHeart.getYCenter(), speed);
		imageSwitching();
	}
	//method to create bulletpattern
	void bulletPattern(){
		
	}



}


