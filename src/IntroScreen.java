import java.awt.Color;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class IntroScreen{	
	DebugHelper d=new DebugHelper();

	//intro screen status
	Boolean introScreenInProgress;

	//intro screen background
	Color background;

	//title text related
	String title;
	Color titleColor;
	String titleFont;
	int fontSize;
	int aTitlePos[]=new int[2];
	EZText ezTextTitle;

	//wfi="waiting for input" text related
	int aWfiTextPos[]=new int[2];
	String wfiInputText;
	Color wfiColor;
	String wfiFont;
	int wfiFontSize;
	EZText ezWfiInputText;

	//game instructions text related
	////Papyrus
	EZText ezInstructionsText;
	String instructionsTextFont;
	int instructionsTextFontSize;
	String instructionsText;
	ArrayList<EZText> alEzInstructionsText=new ArrayList<EZText>();
	Color instructionsTextColor;
	int aInstructionsTextPos[]=new int[2];//x,y
	//////Picture of Papyrus
	int aExplainingCharacterPos[]=new int[2];
	EZImage ezExplainingCharacter;
	String explainingCharacterPictureFile;//default is papyrus
	////Sans
	EZText ezInstructionsText2;
	String instructionsText2;
	String instructionsText2Font;
	int aInstructionsText2Pos[]=new int[2];//x,y
	//////Picture of Sans
	int aExplainingCharacter2Pos[]=new int[2];
	EZImage ezExplainingCharacter2;
	String explainingCharacterPictureFile2;

	//sound/music
	String aIntroMusic[]=new String[7];
	EZSound ezIntroMusic[]=new EZSound[7];
	int currentMusicId;
	Boolean musicPlaying;


	//randomness
	Random r;;

	//reader
	Scanner sc;

	//constructor
	public IntroScreen() {
		
		
		
		//set intoscreen status
		introScreenInProgress=true;

		//background
		background=Color.black;
		EZ.setBackgroundColor(background);

		//title text
		title=Settings.title;
		titleColor=Color.white;
		titleFont="Hachicro.ttf";
		fontSize=(int)(((double)Settings.aWindowSize[1])*.08);
		aTitlePos[0]=(int)(((double)Settings.aWindowSize[0])*.50);//Settings.aWindowSize[0]/2;
		aTitlePos[1]=(int)(((double)Settings.aWindowSize[0])*.05);//Settings.aWindowSize[1]/2;
		ezTextTitle=EZ.addText(titleFont,aTitlePos[0],aTitlePos[1], title, titleColor,fontSize);

		//wfi=waiting For Input text
		aWfiTextPos[0]=aTitlePos[0];
		aWfiTextPos[1]=aTitlePos[1]+fontSize;
		wfiInputText="Press enter to begin";
		wfiColor=Color.white;
		wfiFont="8bitOperatorPlus-Regular.ttf";
		wfiFontSize=50;
		ezWfiInputText=EZ.addText(wfiFont, aWfiTextPos[0], aWfiTextPos[1], wfiInputText, wfiColor, wfiFontSize);

		//scanner to read papyrus' instructions from text file
		try {
			sc=new Scanner(new FileReader("Instructions.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//game instructions
		instructionsTextColor=Color.WHITE;
		////Papyrus
		aInstructionsTextPos[0]=(int)(((double)Settings.aWindowSize[0])*.25);
		aInstructionsTextPos[1]=(int)(((double)Settings.aWindowSize[1])*.3);
		instructionsTextFont="Papyrus";
		instructionsTextFontSize=(int)(((double)Settings.aWindowSize[1])*.03);


		//reads text file for papyrus' instructions
		while(sc.hasNextLine()){
			instructionsText=sc.nextLine();
			ezInstructionsText=EZ.addText(instructionsTextFont,aInstructionsTextPos[0],aInstructionsTextPos[1],instructionsText,instructionsTextColor,instructionsTextFontSize);
			alEzInstructionsText.add(ezInstructionsText);
			aInstructionsTextPos[1]+=instructionsTextFontSize;
		}		

		//papyrus picture
		aExplainingCharacterPos[0]=(int)(((double)Settings.aWindowSize[0])*.05);
		aExplainingCharacterPos[1]=(int)(((double)Settings.aWindowSize[1])*.9);
		explainingCharacterPictureFile="./Sprites/spr_face_papyrus_0.png";//default is papyrus
		ezExplainingCharacter=EZ.addImage(explainingCharacterPictureFile, aExplainingCharacterPos[0],aExplainingCharacterPos[1]);
		ezExplainingCharacter.scaleTo((int)(((double)Settings.aWindowSize[1])*.003));

		////Sans
		instructionsText2="Arrow keys to move and escape key to exit";
		instructionsText2Font="Comic Sans MS";
		aInstructionsText2Pos[0]=(int)(((double)Settings.aWindowSize[0])*.75);
		aInstructionsText2Pos[1]=(int)(((double)Settings.aWindowSize[1])*.6);
		ezInstructionsText2=EZ.addText(instructionsText2Font, aInstructionsText2Pos[0],aInstructionsText2Pos[1],instructionsText2, instructionsTextColor,instructionsTextFontSize);
		aExplainingCharacter2Pos[0]=(int)(((double)Settings.aWindowSize[0])*.95);
		aExplainingCharacter2Pos[1]=(int)(((double)Settings.aWindowSize[1])*.9);
		explainingCharacterPictureFile2="./Sprites/spr_face_sans_0.png";//spr_face_sanswink_0.png";
		ezExplainingCharacter2=EZ.addImage(explainingCharacterPictureFile2, aExplainingCharacter2Pos[0],aExplainingCharacter2Pos[1]);
		ezExplainingCharacter2.scaleTo((int)(((double)Settings.aWindowSize[1])*.003));

		//sound/music
		aIntroMusic[0]="./Sounds/Intro/toby fox - UNDERTALE Soundtrack - 02 Start Menu.wav";
		aIntroMusic[1]="./Sounds/Intro/toby fox - UNDERTALE Soundtrack - 12 Home.wav";
		aIntroMusic[2]="./Sounds/Intro/toby fox - UNDERTALE Soundtrack - 13 Home (Music Box).wav";
		aIntroMusic[3]="./Sounds/Intro/toby fox - UNDERTALE Soundtrack - 34 Memory.wav";
		aIntroMusic[4]="./Sounds/Intro/toby fox - UNDERTALE Soundtrack - 71 Undertale.wav";
		aIntroMusic[5]="./Sounds/Intro/toby fox - UNDERTALE Soundtrack - 90 His Theme.wav";
		aIntroMusic[6]="./Sounds/Intro/toby fox - UNDERTALE Soundtrack - 93 Menu (Full).wav";
		currentMusicId=0;
		musicPlaying=false;
		//fills an array with music of type ezsound
		for(int i=0;i<aIntroMusic.length;i++){
			ezIntroMusic[i]=new EZSound(aIntroMusic[i]);
		}

		//random
		r=new Random();



	}

	//intro screen mechanics
	void updateloop(){
		//while still in progress...
		while(introScreenInProgress){
			//check for user input
			checkForInput();
			//checks for exit command
			ExitCheck.exitCheck();
			//plays music
			soundPlayer();
			
			//draws introscreen elements
			EZ.refreshScreen();
		}
		removeIntroScreen();
	}

	//waits for user input before changing screen
	void checkForInput(){
		//when specified key is pressed and released...
		if(EZInteraction.wasKeyReleased(Settings.useKey)){
			//"introScreenInProgress" variable used to stop while loop
			introScreenInProgress=false;
		}
	}

	//remove all introscreen elements
	void removeIntroScreen(){

		EZ.removeEZElement(ezTextTitle);
		EZ.removeEZElement(ezWfiInputText);
		EZ.removeEZElement(ezInstructionsText);

		for(int i=0;i<alEzInstructionsText.size();i++){
			EZ.removeEZElement(alEzInstructionsText.get(i));
		}
		alEzInstructionsText.removeAll(alEzInstructionsText);

		EZ.removeEZElement(ezExplainingCharacter);
		EZ.removeEZElement(ezInstructionsText2);
		EZ.removeEZElement(ezExplainingCharacter2);

		for(int i=0;i<aIntroMusic.length;i++){
			//EZ.removeEZElement(introMusic[i]);
		}

		//EZ.removeAllEZElements();
		ezIntroMusic[currentMusicId].stop();

		//close scanner
		sc.close();
	}

	//plays sounds
	void soundPlayer(){
		//checks if music already is playing
		if(!musicPlaying){
			//selects a random track
			currentMusicId=r.nextInt(aIntroMusic.length);
			System.out.println(aIntroMusic[currentMusicId]);
			
			//plays selected track
			ezIntroMusic[currentMusicId].play();
			
			//music is playing
			musicPlaying=true;
			
			//else if music has stopped...
		}else if(ezIntroMusic[currentMusicId].getFrameLength()==ezIntroMusic[currentMusicId].getFramePosistion()){
			//music is not playing
			musicPlaying=false;
		}
	}
	
	
}
