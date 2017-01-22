import java.util.Random;

public class SpaghettiPotBurnt extends Items{
	static String spaghettiPotBurntFileName="spr_spaghettipot_burnt_0.png";
	static Random r=new Random();
	
	public SpaghettiPotBurnt(int x,int y,int speed) {
		super(spaghettiPotBurntFileName,x,y,aToPos()[0],aToPos()[1],speed);
		// TODO Auto-generated constructor stub
	}

	static int[] aToPos(){
		int aToPos[]={r.nextInt(Settings.aWindowSize[0]),r.nextInt(Settings.aWindowSize[1])};
		return aToPos;
	}
}
