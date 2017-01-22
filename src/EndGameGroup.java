import java.awt.Color;

public class EndGameGroup {
//add images for toriel, sans, papyrus, muffet, hotel receptionist, ice cream guy, big dog, and asirel end game images
EZImage torielEnd;
EZImage sansEnd;
EZImage papyrusEnd;
EZImage muffetEnd;
EZImage hotelEnd;
EZImage iceCreamGuyEnd;
EZImage bigDogEnd;
EZImage asrielEnd;
//add text for toriel, sans, papyrus, muffet, and asriel end game text
EZText torielText;
EZText sansText;
EZText papyrusText;
EZText muffetText;
EZText hotelText;
EZText iceCreamGuyText;
EZText bigDogText;
EZText asrielText;
//add groups for toriel, sans, papyrus, muffet, and asirel end game groups
EZGroup torielGrp = EZ.addGroup();
EZGroup sansGrp = EZ.addGroup();
EZGroup papyrusGrp = EZ.addGroup();
EZGroup muffetGrp = EZ.addGroup();
EZGroup hotelGrp = EZ.addGroup();
EZGroup iceCreamGuyGrp = EZ.addGroup();
EZGroup bigDogGrp = EZ.addGroup();
EZGroup asrielGrp = EZ.addGroup();

//constructor
EndGameGroup(int x, int y){
	//make groups for toriel, sans, papyrus, muffet, hotel reciptionist, ice cream guy, big dog, and asriel
	torielGrp = EZ.addGroup();
	sansGrp = EZ.addGroup();
	papyrusGrp = EZ.addGroup();
	muffetGrp = EZ.addGroup();
	hotelGrp = EZ.addGroup();
	iceCreamGuyGrp = EZ.addGroup();
	bigDogGrp = EZ.addGroup();
	asrielGrp = EZ.addGroup();
	//actually add the images for toriel, sans, papyrus, muffet, hotel receptionist, ice cream guy, big dog, and asriel
	torielEnd = EZ.addImage("./Sprites/spr_toriel_d_0.png", x, y+50);
	sansEnd = EZ.addImage("./Sprites/spr_sans_d_0.png", x, y+75);
	papyrusEnd = EZ.addImage("./Sprites/spr_papyrus_d_0.png", x, y+100);
	muffetEnd = EZ.addImage("./Sprites/spr_muffet_buysell_0.png", x, y+125);
	hotelEnd = EZ.addImage("./Sprites/spr_hotel_receptionist2_0.png", x, y+150);
	iceCreamGuyEnd = EZ.addImage("./Sprites/spr_icecreamguy_happy_1.png", x, y+175);
	bigDogEnd = EZ.addImage("./Sprites/spr_npc_bigdog_0.png", x, y+200);
	asrielEnd = EZ.addImage ("./Sprites/spr_asriel_d_0.png", x, y+225);
	//actually add the text for toriel, sands, papyrus, muffet, hotel receptionist, ice cream guy, big dog, and asriel
	torielText = EZ.addText((x +275), y+50, "I'm glad the training didn't hurt you.", Color.WHITE, 20);
	sansText = EZ.addText(x+275, y+75, "Glad you made it through kid", Color.WHITE, 20);
	papyrusText = EZ.addText(x+275, y+100, "Your fleeing skills has improved!", Color.WHITE, 20);
	muffetText = EZ.addText(x+275, y+125, "It was a nice time playing with you!", Color.WHITE, 20);
	hotelText = EZ.addText(x+275, y+150, "Thanks for visiting!", Color.WHITE, 20);
	iceCreamGuyText = EZ.addText(x+275, y+175, "Come again anytime!", Color.WHITE, 20);
	bigDogText = EZ.addText(x+275, y+200, "Woof Woof!", Color.WHITE, 20);
	asrielText = EZ.addText(x+275, y+225, "Lets play again sometime!", Color.WHITE, 20);
	//add images to groups
	torielGrp.addElement(torielEnd);
	sansGrp.addElement(sansEnd);
	papyrusGrp.addElement(papyrusEnd);
	muffetGrp.addElement(muffetEnd);
	hotelGrp.addElement(hotelEnd);
	iceCreamGuyGrp.addElement(iceCreamGuyEnd);
	bigDogGrp.addElement(bigDogEnd);
	asrielGrp.addElement(asrielEnd);
	//add text to groups
	torielGrp.addElement(torielText);
	sansGrp.addElement(sansText);
	papyrusGrp.addElement(papyrusText);
	muffetGrp.addElement(muffetText);
	hotelGrp.addElement(hotelText);
	iceCreamGuyGrp.addElement(iceCreamGuyText);
	bigDogGrp.addElement(bigDogText);
	asrielGrp.addElement(asrielText);
	//move groups to correct place on screen
	torielGrp.translateTo(EZ.getWindowWidth()/2, ((2*EZ.getWindowHeight())/20));
	sansGrp.translateTo(EZ.getWindowWidth()/2, (3*EZ.getWindowHeight())/20);
	papyrusGrp.translateTo(EZ.getWindowWidth()/2, (4*EZ.getWindowHeight())/20);
	muffetGrp.translateTo(EZ.getWindowWidth()/2, (5*EZ.getWindowHeight())/20);
	hotelGrp.translateTo(EZ.getWindowWidth()/2, (6*EZ.getWindowHeight())/20);
	iceCreamGuyGrp.translateTo(EZ.getWindowWidth()/2, (7*EZ.getWindowHeight())/20);
	bigDogGrp.translateTo(EZ.getWindowWidth()/2, (8*EZ.getWindowHeight())/20);
	asrielGrp.translateTo(EZ.getWindowWidth()/2, (9*EZ.getWindowHeight())/20);
	
	allCharacters=EZ.addGroup();
	allCharacters.addElement(torielGrp);
	allCharacters.addElement(sansGrp);
	allCharacters.addElement(papyrusGrp);
	allCharacters.addElement(muffetGrp);
	allCharacters.addElement(hotelGrp);
	allCharacters.addElement(iceCreamGuyGrp);
	allCharacters.addElement(bigDogGrp);
	allCharacters.addElement(asrielGrp);
	
}

	static EZGroup allCharacters;
	static void moveAllCharacters(int x,int y){
		allCharacters.translateBy(x, y);
	}
}
