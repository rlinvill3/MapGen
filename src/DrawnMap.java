/**
 * 2d Map made up of tile classes
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
public class DrawnMap {
    

    Tile[][] map;
    int length;
    String types[];
    Map<String,Integer> typeVals;

    DrawnMap(int length,String types[]){

        this.length=length;
        this.types=types;
        map=new Tile[length][length];
        typeVals=new HashMap<>();


        //Initialize Tiles to locations only
        for(int k=0;k<length;k++){
            for(int l=0;l<length;l++){
                map[k][l]=new Tile(k, l);
            }
        }

        //setting each type to 5 positions
        for(int k=0;k<types.length;k++){
            typeVals.put(types[k], 5);
        }




        

    }

    Tile getRandomTile(){
        Random rand=new Random();
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
        
        if(x+1<=length-1){
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
        if(y+1<=length-1){
            return true;
        }
        return false;

    }

    public static void main(String[] args) {
        String test[]={"ligma","sugma"};
        DrawnMap map=new DrawnMap(5, test);
        System.out.println(map.map[0][0].type);

    }

}
