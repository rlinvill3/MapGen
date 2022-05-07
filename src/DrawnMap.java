/**
 * 2d Map made up of tile classes
 * @author Ryan Linville
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
public class DrawnMap {
    

    Tile[][] map;
    /**
     * squared length of map
     */
    int length;
    /**
     * The types of Tiles that will be present in an array, no duplicates
     */
    String types[];
    /**
     * A mapping of counts of how many tiles will be "guaranteed" to generate for each string
     */
    Map<String,Integer> typeGuaranteedMap;
    /**
     * A mapping to each tile type's base probability of replicating
     */
    Map<String,Integer> typeDensityMap;
    /**
     * seed of the map
     * 
     */
    int seed;
    /**
     * prob agent
     */
    Random rand;

    DrawnMap(int length,String types[],int seed){

        this.length=length;
        this.types=types;
        map=new Tile[length][length];
        typeDensityMap=new HashMap<>();
        typeGuaranteedMap=new HashMap<>();
        this.seed=seed;
        this.rand=new Random();
        rand.setSeed(seed);


        //Initialize Tiles with only location data
        for(int k=0;k<length;k++){
            for(int l=0;l<length;l++){
                map[k][l]=new Tile("", k, l);
            }
        }

        //setting each type to X positions
        for(int k=0;k<types.length;k++){
            typeGuaranteedMap.put(types[k], 150);
        }
        //setting default replication probability to 35%
        for(int k=0;k<types.length;k++){
            typeDensityMap.put(types[k], 35);
        }

    }

    /**
     * for each type in the array checks for a random tile with no type data and populates 
     */
    void populateTypes(){

        for(int k=0;k<types.length;k++){

            System.out.println("populating type "+types[k]);

            Tile randTile=getRandomTile();
            while(!randTile.type.equals("")){
                System.out.println("aquiring random tile to start");
                randTile=getRandomTile();
            }

            System.out.println("Random seeding tile aquired X: "+randTile.getx()+" Y:"+randTile.gety());

            int baseProbability=typeDensityMap.get(types[k]);
            int guaranteedNum=typeGuaranteedMap.get(types[k]);


            MapPopulator poper=new MapPopulator(types[k], randTile.getx(), randTile.gety(), baseProbability, guaranteedNum,  this);
            
        }


    }


/**
     * "rolls a d100" for probability
     * if <= set probability variable then "passes"
     * @return if rolled probability successfully
     */
    boolean randFromProb(int probability){
        int roll=1+rand.nextInt(99);
        if(roll<=probability){
            return true;
        }
        return false;
    }



    /*
     * GETTERS AND SETTERS BELOW
     * 
     */

    Tile getRandomTile(){
        return map[rand.nextInt(length)][rand.nextInt(length)];
    }
    Tile getLeft(int x,int y){
        if(checkLeft(x)){
            return map[x-1][y];
        }
        return null;

    }
    Tile getRight(int x,int y){
        if(checkRight(x)){
            return map[x+1][y];
        }
        return null;

    }
    Tile getUp(int x,int y){
        if(checkUp(y)){
            return map[x][y-1];
        }
        return null;

    }
    Tile getDown(int x,int y){
        if(checkDown(y)){
            return map[x][y+1];
        }
        return null;

    }

    Tile getUpLeft(int x,int y){
        if(checkUpLeft(x,y)){
            return map[x-1][y-1];
        }
        return null;

    }
    Tile getUpRight(int x,int y){
        if(checkUpRight(x,y)){
            return map[x+1][y-1];
        }
        return null;

    }
    Tile getDownLeft(int x,int y){
        if(checkDownLeft(x,y)){
            return map[x-1][y+1];
        }
        return null;

    }
    Tile getDownRight(int x,int y){
        if(checkDownRight(x,y)){
            return map[x+1][y+1];
        }
        return null;

    }


    boolean checkLeft(int x){
        if(x-1>=0){
            return true;
        }
        return false;

    }
    boolean checkRight(int x){
        
        if(x+1<length){
            return true;
        }
        return false;

    }
    boolean checkUp(int y){
        if(y-1>=0){
            return true;
        }
        return false;

    }
    boolean checkDown(int y){
        if(y+1<length){
            return true;
        }
        return false;

    }

    /*
    *Anti cardinal getters and setters
     */
    boolean checkUpLeft(int x,int y){
        if(x-1>=0&&y-1>=0){
            return true;
        }
        return false;

    }
    boolean checkUpRight(int x,int y){
        
        if(x+1<length&&y-1>=0){
            return true;
        }
        return false;

    }
    boolean checkDownLeft(int x,int y){
        if(x-1>=0&&y+1<length){
            return true;
        }
        return false;

    }
    boolean checkDownRight(int x,int y){
        if(y+1<length&&x+1<length){
            return true;
        }
        return false;

    }
    void printMap(){
        for(int k=0;k<50;k++){
            for(int m=0;m<50;m++){
                if(this.map[m][k].type.equals("")){
                    System.out.print(" ");
                }
                System.out.print(this.map[k][m].type);

            }
            System.out.print('\n');
        }
    }

    /**
     * KEEP LENGTH AT 50 FOR SOME REASON???????
     * @param args
     */
    public static void main(String[] args) {
        String types[]={"m","p","x","o"};
        int seed=9;
        DrawnMap test=new DrawnMap(50, types,seed);

        System.out.println("generating..");
        test.populateTypes();
        System.out.println("population complete, map output: ");

        test.printMap();
        
        

    }

}
