
public class Settings {
	
	//title of game
	static String title;

	//window size
	static int aWindowSize[]=new int[2];

	//control bindings
	static int upKey;
	static int downKey;
	static int leftKey;
	static int rightKey;
	static int useKey;
	static int keyA;//previous menu/cancel
	static int keyB;//options menu
	static int exitKey;
	static Boolean altExitKey;
	
	//framerate related
	static int ezFramerate;
	static Boolean ezFramerateAsap;

	//constructor
	Settings() {
	}
 
	//sets values to default settings
	static void defaultSettings(){

		//title of game
		title="Undertale's Tile Puzzle";

		//window size
		double r=80*1;//1.5;//used to scale resolution more easily
		aWindowSize[0]=(int)r*16;//x
		aWindowSize[1]=(int)r*10;//y
		
		//aWindowSize[0]=1280;//x
		//aWindowSize[1]=800;//y


		/*
				40 down arrow
				39 right arrow
				38 up arrow
				37 left arrow
				27 escape
				17 control
				16 shift
				10 enter
		 */
		upKey=38;
		downKey=40;
		leftKey=37;
		rightKey=39;
		useKey=10;
		keyA=16;
		keyB=17;
		exitKey=27;
		altExitKey=EZInteraction.isMouseRightButtonDown()&&EZInteraction.isMouseLeftButtonDown();

		ezFramerateAsap=true;
		ezFramerate=120;
		
		
	}

	
	//used to return a size on screen with a given percentage
	static double sizeOnScreen(double sizeRatio){
		double size;
		if(aWindowSize[0]>=aWindowSize[1]){
			size=(int)(aWindowSize[0]*sizeRatio);	
		}else{
			size=(int)(aWindowSize[1]*sizeRatio);	
		}
		return size;
	}

}
