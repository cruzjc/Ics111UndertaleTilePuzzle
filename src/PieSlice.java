import java.util.Random;

public class PieSlice extends Items {
	static String pieSliceFileName="spr_pieslice_0.png";
	Random r=new Random();
	static int aPos[]={Settings.aWindowSize[0],Settings.aWindowSize[1]};
	static int speed=(int) Settings.sizeOnScreen(.001);
	public PieSlice(int x,int y) {
		super(pieSliceFileName,x,y);
		// TODO Auto-generated constructor stub
	}

	void updateFrame(){
		if(playerItemCollisionCheck()){
			healsPlayer(EZ.getCurrentFrameRate()*3);
			removeItem();
		}
	}
	
}
