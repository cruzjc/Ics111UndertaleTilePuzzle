
public class Sound {
	String introMusic[]=new String[7];
	String inGameMusic[]=new String[2];
	String inGameBattleMusic[]=new String[7];
	String defeatMusic[]=new String[2];
	String victoryMusic[]=new String[2];
	
	EZSound ezIntroMusic[]=new EZSound[7];
	EZSound ezIngameMusic[]=new EZSound[2];
	EZSound ezIngameBattleMusic[]=new EZSound[7];
	EZSound ezDefeatMusic[]=new EZSound[2];
	EZSound ezVictoryMusic[]=new EZSound[2];
	
	public Sound() {
		// TODO Auto-generated constructor stub
		initializeMusic();
	}
	
	void initializeMusic(){
		introMusic[0]="/Project3/Music/toby fox - UNDERTALE Soundtrack - 02 Start Menu.wav";
		introMusic[1]="/Project3/Music/toby fox - UNDERTALE Soundtrack - 12 Home.wav";
		introMusic[2]="/Project3/Music/toby fox - UNDERTALE Soundtrack - 13 Home (Music Box).wav";
		introMusic[3]="/Project3/Music/toby fox - UNDERTALE Soundtrack - 34 Memory.wav";
		introMusic[4]="/Project3/Music/toby fox - UNDERTALE Soundtrack - 71 Undertale.wav";
		introMusic[5]="/Project3/Music/toby fox - UNDERTALE Soundtrack - 90 His Theme.wav";
		introMusic[6]="/Project3/Music/toby fox - UNDERTALE Soundtrack - 93 Menu (Full).wav";
		
		inGameMusic[0]="/Project3/Music/toby fox - UNDERTALE Soundtrack - 51 Another Medium.wav";
		inGameMusic[1]="/Project3/Music/toby fox - UNDERTALE Soundtrack - 65 CORE.wav";
		
		inGameBattleMusic[0]="/Project3/Music/toby fox - UNDERTALE Soundtrack - 100 MEGALOVANIA.wav";
		inGameBattleMusic[1]="/Project3/Music/toby fox - UNDERTALE Soundtrack - 16 Nyeh Heh Heh!.wav";
		inGameBattleMusic[2]="/Project3/Music/toby fox - UNDERTALE Soundtrack - 24 Bonetrousle.wav";
		inGameBattleMusic[3]="/Project3/Music/toby fox - UNDERTALE Soundtrack - 50 Metal Crusher.wav";
		inGameBattleMusic[4]="/Project3/Music/toby fox - UNDERTALE Soundtrack - 59 Spider Dance.wav";
		inGameBattleMusic[5]="/Project3/Music/toby fox - UNDERTALE Soundtrack - 87 Hopes and Dreams.wav";
		inGameBattleMusic[6]="/Project3/Music/toby fox - UNDERTALE Soundtrack - 89 SAVE the World.wav";
		
		defeatMusic[0]="/Project3/Music/toby fox - UNDERTALE Soundtrack - 11 Determination.wav";
		defeatMusic[1]="/Project3/Music/toby fox - UNDERTALE Soundtrack - 86 Don't Give Up.wav";
		
		victoryMusic[0]="/Project3/Music/toby fox - UNDERTALE Soundtrack - 80 Finale.wav";
		victoryMusic[1]="/Project3/Music/toby fox - UNDERTALE Soundtrack - 96 Last Goodbye.wav";
	}
	

}
