import java.awt.Color;

public class ExitCheck {
	
	//time to hold down exit key to exit
	static private int exitHoldDownFor=1*EZ.getCurrentFrameRate();//how many seconds to hold the exit key for
	static private int exitCountDown=exitHoldDownFor;//counter for holding down exit key, dependent on refresh rate

	//quitting status
	static private Boolean quitting=false;
	
	//part of exiting animation related
	static private int boxHeight=1;
	static private int boxWidth=Settings.aWindowSize[0];
	static private int aTopBoxPos[]={Settings.aWindowSize[0]/2,0};
	static private int aBotBoxPos[]={Settings.aWindowSize[0]/2,Settings.aWindowSize[1]};
	static private int growth=(Settings.aWindowSize[1])/exitHoldDownFor;
	static private Color boxColor=Color.white;
	static private EZRectangle topB=EZ.addRectangle(aTopBoxPos[0],aTopBoxPos[1], boxWidth, boxHeight, boxColor, true);
	static private EZRectangle botB=EZ.addRectangle(aBotBoxPos[0],aBotBoxPos[1], boxWidth, boxHeight, boxColor, true);
	
	
	//the display of exit text when exiting
	static private String exitText="Quitting...";
	static private String exitTextFont="Hachicro.ttf";
	static private Color exitTextColor=Color.BLACK;
	static private int exitTextFontSize=(int)((double)Settings.aWindowSize[1]*.025);
	static private int aExitTextPos[]={Settings.aWindowSize[0]/10,Settings.aWindowSize[1]/40};
	static private EZText ezExitText=EZ.addText(exitTextFont,aExitTextPos[0],aExitTextPos[1], exitText, exitTextColor,exitTextFontSize);
	

	//Constructor
	ExitCheck(){
	}

	//initialize variable
	static void initialize(){
		//time to hold down exit key
		exitHoldDownFor=1*EZ.getCurrentFrameRate();//how many seconds to hold the exit key for
		exitCountDown=exitHoldDownFor;//counter for holding down exit key, dependent on refresh rate

		//exiting state
		quitting=false;
		
		//part of exiting animation
		boxHeight=1;
		boxWidth=Settings.aWindowSize[0];
		aTopBoxPos[0]=Settings.aWindowSize[0];
		aTopBoxPos[1]=0;
		aBotBoxPos[0]=Settings.aWindowSize[0]/2;
		aBotBoxPos[1]=Settings.aWindowSize[1];
		growth=(Settings.aWindowSize[1])/exitHoldDownFor;
		boxColor=Color.white;
		topB=EZ.addRectangle(aTopBoxPos[0],aTopBoxPos[1], boxWidth, boxHeight, boxColor, true);
		EZRectangle botB=EZ.addRectangle(aBotBoxPos[0],aBotBoxPos[1], boxWidth, boxHeight, boxColor, true);
		
		
		//the display of exit text when exiting
		exitText="Quitting...";
		exitTextFont="hachicro";
		exitTextColor=Color.BLACK;
		exitTextFontSize=40;
		aExitTextPos[0]=150;
		aExitTextPos[1]=50;
		ezExitText=EZ.addText(exitTextFont,aExitTextPos[0],aExitTextPos[1], exitText, exitTextColor,exitTextFontSize);
		

	}
	
	//checks if the user is holding down the exit key long enough to exit
	static void exitCheck(){
		
		//if user hold down exit long enough...
		if(EZInteraction.isKeyDown(Settings.exitKey)||Settings.altExitKey){
			//print countdown timer
			System.out.println("Quitting in: "+exitCountDown);
			//decrease timer
			exitCountDown--;
			//exit visualization sequence
			rectHeightModify(true);
			//show exiting text
			ezExitText.show();
			ezExitText.pullToFront();
		//else if key is not held down...
		}else if(exitCountDown<=exitHoldDownFor) {
			//return quitCountDown back to default value
			exitCountDown++;
			//undo exit visualization sequence
			rectHeightModify(false);
			//hide the text
			ezExitText.hide();
		}
		//if timer is at zero...
		if(exitCountDown==0){
			//quit
			quitting=true;
			
			//alt exit 
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//force quit
			System.exit(0);
		}	
	}
	
	//exit visualization sequence
	  static private void rectHeightModify(boolean grow){
		//if true
		if(grow)
			//begin to black out screen
			boxHeight+=growth;
		else
			//undo blacking out screen
			boxHeight-=growth;


		//set height of blacking out boxes
		topB.setHeight(boxHeight);
		botB.setHeight(boxHeight);
		
		//pulls the blacking out box to the front
		topB.pullToFront();
		botB.pullToFront();
	}

}
