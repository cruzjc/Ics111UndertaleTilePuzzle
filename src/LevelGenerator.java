import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class LevelGenerator{
	DebugHelper d=new DebugHelper();

	//size of tile puzzle measured in tiles
	static //size of tile puzzle,measured in tiles
	int aTilePuzzleSize[]=new int[2];//length * width
	static EZRectangle aEzTiles[][];//column*row

	//arraylist to store solution directions
	ArrayList<Integer>alSolution=new ArrayList<Integer>();
	ArrayList<Color>solutionTilesSequenceColors=new ArrayList<Color>();
	//number of steps for the solution
	int step;
	
	////might not even be needed
	//positions of solution tiles, in tile coordinates
	ArrayList<int[]> solutionTileCoordinates=new ArrayList<int[]>();


	//square tiles, size in pixels
	static int tileSize;
	double tileSizeRatio;
	
	//randomness
	Random random;

	//constructor
	public LevelGenerator() {
		//initialize variables
		//size of the entire tile puzzle measured in tiles
		aTilePuzzleSize[0]=15;
		aTilePuzzleSize[1]=15;
		//all of the tiles of the puzzle in the form of ezRectangles
		aEzTiles=new EZRectangle[aTilePuzzleSize[0]][aTilePuzzleSize[1]];
		
		//square tile size,size of tile is determined by window size
		tileSizeRatio=.1;
		tileSize=(int)Settings.sizeOnScreen(tileSizeRatio);

		//number of solution tiles generated
		step=0;
		//randomness
		random=new Random();
		
		
		//genereate tiles
		generateTiles();
		//genereateSolution
		generateSolution();
		//printSolutionToConsole();
	}
	
	//initialize variable
	void initialization(){

	}

	//generate all tiles
	void generateTiles(){
		//number of generated tiles
		int tileCount=0;
		
		//tile colors
		Boolean tileColorFilled;
		tileColorFilled=true;
		
		//sets initial pixel coordinates for tiles
		int aTilePos[]=new int[2];
		aTilePos[0]=tileSize+tileSize/2;
		aTilePos[1]=tileSize/2;
		

		//creates ezrectangletiles to display
		//for every row...
		for(int r=0;r<aTilePuzzleSize[1];r++){
			//for every column...
			for(int c=0;c<aTilePuzzleSize[0];c++){
				//create a tile
				aEzTiles[c][r]=EZ.addRectangle(aTilePos[0],aTilePos[1],tileSize,tileSize, cAnyTile(), tileColorFilled);
				//set next tile position to the right
				aTilePos[0]+=tileSize;
				//increase tile counter
				tileCount++;
			}
			//set next tileposition back left
			aTilePos[0]=tileSize+tileSize/2;
			//set next tilepostion downwards
			aTilePos[1]+=tileSize;
		}
		System.out.println("Number of tiles:"+tileCount);
		System.out.println("Finished creating tiles");
	}

	//generates a solution in the form of directions/numbers
	//also sets the solution tile color in the alEzTile
	void generateSolution(){
		//create the coordinates of the first solution tile
		int column=0;
		//starting from top row in column 0, designate the first traversable tile
		int row=random.nextInt(aTilePuzzleSize[1]);
		int aCurrentSolutionTilePos[]={column,row};//initialized with first correct/traversable tile
		
		//boolean to check if the solution tile is in the top or bottom row
		Boolean topRow=(aCurrentSolutionTilePos[1]==0);
		Boolean bottomRow=(aCurrentSolutionTilePos[1]==aTilePuzzleSize[1]);
		
		//used to set the color of the current solution tile
		Color cCurrentColor;

		//while current column is still less then the puzzle length...		
		while(column<aTilePuzzleSize[0]){
			//0=up
			//1=down
			//2=right
			//3=left

			//logs the solution coordinates
			solutionTileCoordinates.add(aCurrentSolutionTilePos);
			
			//adds a color that makes a tile traversable
			cCurrentColor=cTraversableTiles();
			//cCurrentColor=Color.white;//used to display at least one solution
			solutionTilesSequenceColors.add(cCurrentColor);
			
			//sets a color for a tile in the aEzTiles array 
			aEzTiles[aCurrentSolutionTilePos[0]][aCurrentSolutionTilePos[1]].setColor(cCurrentColor);
			
			//checks if the currentSolutionTile is in the top or bottom row
			topRow=(aCurrentSolutionTilePos[1]==0);
			bottomRow=(aCurrentSolutionTilePos[1]==aTilePuzzleSize[1]-1);
			
			//if current solution tile is not in top or bottom rows...
			if(!topRow&&!bottomRow){
				//the next traversable tile is either top,bottom or right .
				alSolution.add(random.nextInt(3));	

				//update solution tile pos variable
				if(alSolution.get(step)==0){
					aCurrentSolutionTilePos[1]--;
					//System.out.print(" Up");
				}
				if(alSolution.get(step)==1){
					aCurrentSolutionTilePos[1]++;
					//System.out.print(" Down");
				}
				if(alSolution.get(step)==2){
					aCurrentSolutionTilePos[0]++;		
					column++;
					//column+=2;
					//aCurrentSolutionTilePos[0]+=2;
					//System.out.print(" Right");
				}
			}

			//if current solution tile is in the top row and in initial column...
			else if(topRow){
				//the next traversable tile is right or down.
				alSolution.add(random.nextInt(2)+1);

				if(alSolution.get(step)==1){
					aCurrentSolutionTilePos[1]++;
					//System.out.print(" Down");
				}

				if(alSolution.get(step)==2){
					aCurrentSolutionTilePos[0]++;
					column++;
					//column+=2;
					//aCurrentSolutionTilePos[0]+=2;
					//System.out.print(" Right");
				}
			}

			//if current solution tile is in the bottom row and in initial column...
			else if(bottomRow){
				//the next traversable tile is either up or right.
				alSolution.add(2*random.nextInt(2));

				if(alSolution.get(step)==0){
					aCurrentSolutionTilePos[1]--;
					//System.out.print(" Up");
				}
				if(alSolution.get(step)==2){
					aCurrentSolutionTilePos[0]++;
					column++;
					//column+=2;
					//aCurrentSolutionTilePos[0]+=2;
					//System.out.print(" Right");
				}
			}
			
			//increase step counter
			step++;
		}//end loop

		System.out.println("Finished generating solution");
	}

	//prints out solution to console in the form of directions represented by numbers
	void printSolutionToConsole(){
		System.out.println("The solution is:");
		//prints out logged solution
		for(int i=0;i<step;i++){
			System.out.print(alSolution.get(i)+" ");
		}
		System.out.println("\nFinished printing solution.");
	}

	//moves all tiles by specified direction
	static void moveAllTiles(int x,int y){
		for(int r=0;r<aTilePuzzleSize[1];r++){
			for(int c=0;c<aTilePuzzleSize[0];c++){
				aEzTiles[c][r].translateBy(x, y);
			}
		}
	}
	
	static void showTiles(Boolean show){
		if(show){
		for(int r=0;r<aTilePuzzleSize[1];r++){
			for(int c=0;c<aTilePuzzleSize[0];c++){
				aEzTiles[c][r].show();
			}
		}
		}
		else{
			for(int r=0;r<aTilePuzzleSize[1];r++){
				for(int c=0;c<aTilePuzzleSize[0];c++){
					aEzTiles[c][r].hide();
				}
			}	
		}

	}
//return functions===========================================
	//all tiles color
	Color cAnyTile(){
		//randomly returns either a traversable tile or a non traversable tile
		int c=random.nextInt(2);
		if(c==0)
			return cTraversableTiles();
		else if(c==1)
			return cNonTraversableTiles();
		else return Color.gray;
	}

	//non traversable tiles color
	Color cNonTraversableTiles(){
		//randomly returns a color that represenets a non traverable tile
		int c=random.nextInt(3);
		if(c==0){
			return Color.red;
		}else if(c==1){
			return Color.yellow;
		}else if(c==2){
			return Color.blue;
		}else{
			return Color.gray;
		}
	}
	
	//traversable tiles color
	Color cPurple=new Color(102,0,153);
	Color cTraversableTiles(){
		//randomly returns a color that represents a traversable tile
		int c=random.nextInt(4);
		switch(c){
			case 0:
				return Color.green;
			case 1:
				return Color.orange;
			case 2:
				return new Color(102, 0, 153);//purple
			case 3:
				return Color.pink;
			default:
				return Color.gray;
		}
	}
	
	//playable area
	static int[] aPlayableArea(){
		int aPlayableArea[]=new int[2];
		//x wise bounds
		aPlayableArea[0]=tileSize*aTilePuzzleSize[0];
		//y wise bounds
		aPlayableArea[1]=tileSize*aTilePuzzleSize[1]-tileSize/3;
		
		//adds space for a beginning and ending column
		aPlayableArea[0]+=tileSize*2;
		
		return aPlayableArea;
	}
}
