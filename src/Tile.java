/**
 * Singular tile for the 2d map
 */
import java.awt.image.BufferedImage;
public class Tile{


    String filepath;
    String type;

    Tile(String filepath){
        this.filepath=filepath;
        this.type=null;

        
    }
    Tile(String filepath, String type){
        this.filepath=filepath;
        this.type=type;

        
    }

}