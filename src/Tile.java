/**
 * Singular tile for the 2d map
 * Just contains simple data types
 * @author Ryan Linville
 */
public class Tile{


    String filepath;
    String type;
    int locx;
    int locy;

    int getx(){
        return this.locx;
    }
    int gety(){
        return this.locy;
    }
    String getType(){
        return this.type;
    }
    void setx(int x){
        this.locx=x;
    }
    void sety(int y){
        this.locy=y;
    }
    void setType(String type){
        this.type=type;
    }

    Tile(int x, int y){
        this.locx=x;
        this.locy=y;
        this.type="";
    }

    Tile(String type, int x, int y){
        this.type=type;
        this.locx=x;
        this.locy=y; 
    }


}