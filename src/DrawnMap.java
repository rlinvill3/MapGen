/**
 * 2d Map made up of tile classes
 */
public class DrawnMap {
    

    Tile[][] map;
    int length;
    Tile types[];

    DrawnMap(int length,Tile types[]){

        this.length=length;
        this.types=types;
        map=new Tile[length][length];

    }

    void populateMap(){

    }


    Tile getLeft(){
        return null;

    }
    Tile getRight(){
        return null;

    }
    Tile getUp(){
        return null;

    }
    Tile getDown(){
        return null;

    }
    boolean checkLeft(int x){
        return false;

    }
    boolean checkRight(int x){
        return false;

    }
    boolean checkUp(int y){
        return false;

    }
    boolean checkDown(int y){
        return false;

    }

    public static void main(String[] args) {
        

    }

}
