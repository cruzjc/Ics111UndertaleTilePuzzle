
public class Astigmatism extends Monster{

	//create string called file1 initialized as amalgam
	static String file1="./Sprites/spr_astigmatism_anim2_0.png";
	//create string called file2 initialized as amalgam
	static String file2="./Sprites/spr_astigmatism_anim2_1.png";
	//create string called file1 initialized as amalgam
	static String file3="./Sprites/spr_astigmatism_anim2_2.png";
	//create string called file2 initialized as amalgam
	static String file4="./Sprites/spr_astigmatism_anim2_3.png";
	//create string called file1 initialized as amalgam
	static String file5="./Sprites/spr_astigmatism_anim2_4.png";
	//create string called file2 initialized as amalgam
	static String file6="./Sprites/spr_astigmatism_anim2_5.png";
	//create string called file1 initialized as amalgam
	static String file7="./Sprites/spr_astigmatism_anim2_6.png";
	//create string called file2 initialized as amalgam
	static String file8="./Sprites/spr_astigmatism_anim2_7.png";
	//create string called file1 initialized as amalgam
	static String file9="./Sprites/spr_astigmatism_anim2_8.png";
	//create string called file2 initialized as amalgam
	static String file10="./Sprites/spr_astigmatism_anim2_9.png";
	//create string called file1 initialized as amalgam
	static String file11="./Sprites/spr_astigmatism_anim2_10.png";
	//create string called file2 initialized as amalgam
	static String file12="./Sprites/spr_astigmatism_anim2_11.png";
	
	
	//create array of strings called filenames and place file1 and fil2 inside
	static String fileNames[]={file1,file2,file3,file4,file5,file6,file7,file8,
			file8,file9,file10,file11,file11,file11,
			file10,file9,file8,file7,file6,file5,file4,file3,file2,file1,file1};
	
	
	//constructor
	
	static int animationDuration=1000*5;
	public Astigmatism(int posx,int posy,double size) {
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

