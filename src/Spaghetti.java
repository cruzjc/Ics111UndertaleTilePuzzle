import java.util.Random;

public class Spaghetti extends Items {
	public Spaghetti(int x,int y) {
		super(spaghettiFileName(),x,y);
		// TODO Auto-generated constructor stub
	}

	
	 static String spaghettiFileName(){
		Random r=new Random();
		if(r.nextBoolean()){
			return "spr_spaghetti_0.png";
		}else{
			return "spr_spaghettialone_0.png";
		}
	}
	 
	 
	void updateFrame(){			
			if(playerItemCollisionCheck()){
				healsPlayer(EZ.getCurrentFrameRate()*2);
				removeItem();
			}
	}
}
