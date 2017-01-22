
public class HideousCupCake extends Monster{

	//create string called file1 initialized as amalgam
	static String file1="./Sprites/spr_hideouscupcake_0.png";
	//create string called file2 initialized as amalgam
	static String file2="./Sprites/spr_hideouscupcake_1.png";
	//create string called file1 initialized as amalgam
	static String file3="./Sprites/spr_hideouscupcake_2.png";
	//create string called file2 initialized as amalgam
	static String file4="./Sprites/spr_hideouscupcake_3.png";
	
	
	//create array of strings called filenames and place file1 and fil2 inside
	static String fileNames[]={file1,file2,file3,file2,file1};
	
	//constructor
	public HideousCupCake(int posx,int posy,double size) {
		//superclass that needs fileName and size
		super(fileNames,posx,posy,size);
		// TODO Auto-generated constructor stub
	}
//method to initialized 
	void updateFrame(){
		//moveTowardsPosition(Player.ezPlayerHeart.getXCenter(),Player.ezPlayerHeart.getYCenter(), speed);
		imageSwitching();
	}
	//method to create bulletpattern
	void bulletPattern(){
		
	}



}
