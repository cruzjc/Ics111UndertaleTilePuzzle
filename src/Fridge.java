
public class Fridge extends Items{
	static String fridgeFileName="spr_fridge_0.png";
	public Fridge(int x,int y) {
		super(fridgeFileName,x,y);
		// TODO Auto-generated constructor stub
	}

	void updateFrame(){
		if(playerItemCollisionCheck()){
			healsPlayer(EZ.getCurrentFrameRate()*5);
			removeItem();
		}
	}
}
