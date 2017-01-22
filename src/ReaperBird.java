import java.util.ArrayList;

public class ReaperBird extends Monster{
	
	//head
	//create string called file1 initialized as amalgam
	static String head1="./Sprites/spr_reaperbird_bite_0.png";
	//create string called file2 initialized as amalgam
	static String head2="./Sprites/spr_reaperbird_bite_1.png";
	//create string called file1 initialized as amalgam
	static String head3="./Sprites/spr_reaperbird_bite_2.png";
	//create string called file2 initialized as amalgam
	static String head4="./Sprites/spr_reaperbird_bite_3.png";
	//create string called file1 initialized as amalgam
	static String head5="./Sprites/spr_reaperbird_bite_4.png";
	//create string called file2 initialized as amalgam
	static String head6="./Sprites/spr_reaperbird_bite_5.png";
	//create string called file1 initialized as amalgam
	static String head7="./Sprites/spr_reaperbird_bite_6.png";
	//create string called file2 initialized as amalgam
	static String head8="./Sprites/spr_reaperbird_bite_7.png";
	//create string called file1 initialized as amalgam
	static String head9="./Sprites/spr_reaperbird_head_0.png";
	//create string called file2 initialized as amalgam
	static String head10="./Sprites/spr_reaperbird_head_1.png";
	//create string called file1 initialized as amalgam
	static String head11="./Sprites/spr_reaperbird_head_2.png";
	//create string called file2 initialized as amalgam
	static String head12="./Sprites/spr_reaperbird_head_3.png";
	
	static String head13="./Sprites/spr_reaperbird_head_4.png";
	//create string called file1 initialized as amalgam
	static String head14="./Sprites/spr_reaperbird_head_5.png";
	//create string called file2 initialized as amalgam
	static String head15="./Sprites/spr_reaperbird_head_6.png";
	
	//create string called file2 initialized as amalgam
	static String body1="./Sprites/spr_reaperbird_torso_0.png";
	//create string called file2 initialized as amalgam
	static String body2="./Sprites/spr_reaperbird_torso_1.png";
	//create string called file2 initialized as amalgam
	static String body3="./Sprites/spr_reaperbird_torso_2.png";
	//create string called file2 initialized as amalgam
	static String body4="./Sprites/spr_reaperbird_torso_3.png";
	//create string called file2 initialized as amalgam
	static String body5="./Sprites/spr_reaperbird_torso_4.png";
	//create string called file2 initialized as amalgam
	static String body6="./Sprites/spr_reaperbird_torso_5.png";
	//create string called file2 initialized as amalgam
	static String body7="./Sprites/spr_reaperbird_torso_6.png";
	//create string called file2 initialized as amalgam
	static String body8="./Sprites/spr_reaperbird_torso_7.png";
	
	//create array of strings called filenames and place file1 and fil2 inside
	static String headFileNames[]={
			head1,head2,head3,head4,head5,
			head6,head7,head8,head8,head9,
			head10,head11,head12,head13,head14,
			head15,head14,head13,head12,head11,
			head10,head9};
	static String bodyFileNames[]={
			body1,body2,body3,body4,body5,
			body6,body7,body8};
	
	

	static long headAnimationDuration=1000*5;
	static long bodyAnimationDuration=1000*5;
	static FlipBook fbReaperBirdBody;
	static FlipBook fbReaperBirdHead;
	
	
	public ReaperBird(int x,int y,double size) {
		fbReaperBirdBody=new FlipBook(bodyFileNames,bodyAnimationDuration,0,0);
		fbReaperBirdBody.scaleBy(Settings.sizeOnScreen(size)/fbReaperBirdBody.getWidth(1));
		fbReaperBirdHead=new FlipBook(headFileNames, headAnimationDuration, 0,-fbReaperBirdBody.getHeight(1)/4*3);
		fbReaperBirdHead.scaleBy(Settings.sizeOnScreen(size/2)/fbReaperBirdHead.getWidth(1));
		
		fbReaperBirdBody.translateBy(x, y);
		fbReaperBirdHead.translateBy(x, y);
		// TODO Auto-generated constructor stub
	}
	
	void updateFrame(){
		fbReaperBirdBody.go();
		fbReaperBirdHead.go();
		fbReaperBirdHead.pullToFront();
	}

	void removeMonster(){
		fbReaperBirdBody.remove();
		fbReaperBirdHead.remove();
	}
}
