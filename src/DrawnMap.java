/**
 * 2d Map made up of tile classes
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
    int seed;
    Random rand;

    DrawnMap(int length,String types[],int seed){

        this.length=length;
        this.types=types;
        map=new Tile[length][length];
        typeDensityMap=new HashMap<>();
        typeGuaranteedMap=new HashMap<>();
        this.seed=seed;
        this.rand=new Random();


        //Initialize Tiles with only location data
        for(int k=0;k<length;k++){
            for(int l=0;l<length;l++){
                map[k][l]=new Tile("", k, l);
            }
        }

        //setting each type to 5 positions
        for(int k=0;k<types.length;k++){
            typeGuaranteedMap.put(types[k], 5);
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
    boolean checkLeft(int x){
        if(x-1>=0){
            return true;
        }
        return false;

    }
    boolean checkRight(int x){
        
        if(x+1<=length){
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
        if(y+1<=length){
            return true;
        }
        return false;

    }

    public static void main(String[] args) {
        String types[]={"mountain","plains"};
        int seed=1;
        DrawnMap test=new DrawnMap(50, types,seed);
        System.out.println("generating..");
        test.populateTypes();
        System.out.println("population complete");


        System.out.println("map output:");

        for(int k=0;k<50;k++){
            for(int m=0;m<50;m++){
                //System.out.println(test.map[k][m].type);
                if(test.map[k][m].type.equals("mountain")){
                    System.out.print("M");
                }
                else if(test.map[k][m].type.equals("plains")){
                    System.out.print("P");
                }
                else if(test.map[k][m].type==null){
                    System.out.print("_");
                }
            }
            System.out.print('\n');
        }
        

    }

}
