import java.util.ArrayList;
/**
 * Replication agent class, replicates and finds neighbors to replicate to
 * @author Ryan Linville
 */
public class MapPopulator {
    

    /**
     * type of tile this is
     */
    String type;
    /**
     * the number of this tile that should be guaranteed to generate
     */
    int typeNum;
    /**
     * x pos
     */
    int x;
    /**
     * y pos
     */
    int y;
    /**
     * ref to the map itself
     */
    DrawnMap map;
    /**
     * probability of replication
     */
    int probability;




    /**
     * constructs and populates the tile it is created on
     * @param type type of tile this is
     * @param x x pos
     * @param y  y pos
     * @param probability probability of replication as an integer percentage
     * @param typeNum the number of this tile that should be guaranteed to generate
     * @param map ref to the map itself
     */
    MapPopulator(String type,int x,int y,int probability,int typeNum,DrawnMap map){

        this.type=type;
        this.x=x;
        this.y=y;
        this.map=map;
        this.probability=probability;
        this.typeNum=typeNum;


        generateCurrentTile();
        if(checkGuaranteedRemaining()){
            spread();
        }
        //if no guaranteed spawns remain relies on random chance to replicate
        else{
            if(map.randFromProb(probability)){
                spread();
            }
        }

    }

    /**
     * obtains tile in a random 8 way direction
     * @return Tile 
     */
    Tile randomTileDirection(){
        ArrayList<Tile> availableNeighbors=eightSpreadLocList();
        if(availableNeighbors.size()==0){
            return null;
        }
        int newTileIndex=map.rand.nextInt(availableNeighbors.size());
        Tile newTile=availableNeighbors.get(newTileIndex);


        return newTile;
    }

    /**
     * Call god, see if he answers.
     * Very ugly code to check the surrounding eight tiles to see if they are valid
     * @return a collection of the VALID tiles
     */
    ArrayList<Tile> eightSpreadLocList(){
        ArrayList<Tile> returner=new ArrayList<>();
        Tile poller;
        poller=map.getUp(x, y);
        if(poller!=null&&!poller.type.equals(type)){returner.add(poller);}
        poller=map.getUpRight(x, y);
        if(poller!=null&&!poller.type.equals(type)){returner.add(poller);}
        poller=map.getRight(x, y);
        if(poller!=null&&!poller.type.equals(type)){returner.add(poller);}
        poller=map.getDownRight(x, y);
        if(poller!=null&&!poller.type.equals(type)){returner.add(poller);}
        poller=map.getDown(x, y);
        if(poller!=null&&!poller.type.equals(type)){returner.add(poller);}
        poller=map.getDownLeft(x, y);
        if(poller!=null&&!poller.type.equals(type)){returner.add(poller);}
        poller=map.getLeft(x, y);
        if(poller!=null&&!poller.type.equals(type)){returner.add(poller);}
        poller=map.getUpLeft(x, y);
        if(poller!=null&&!poller.type.equals(type)){returner.add(poller);}

        return returner;
    }

    

    /**
     * generates on the current position
     */
    void generateCurrentTile(){

        map.map[x][y]=new Tile(type, x, y);
        if(checkGuaranteedRemaining()){
            subtractGuaranteedRemaining();
        }

        
    }
    /**
     * checks if there are guaranteed replications remaining
     * @return boolean if true
     */
    boolean checkGuaranteedRemaining(){
        if(map.typeGuaranteedMap.get(type)>0){
            return true;
        }
        return false;
    }
    /**
     * subtracts of guaranteed map
     */
    void subtractGuaranteedRemaining(){
        map.typeGuaranteedMap.put(type, typeNum-1);
    }


    /**
     * returns guaranteed info
     * @return
     */
    int getCurTypeNum(){
        return map.typeGuaranteedMap.get(type);
    }

    /**
     * main replication code
     */
    void spread(){
        Tile spreadTile=randomTileDirection();
        if(spreadTile==null){
            return;
        }
        //System.out.println("Spreading to tile x: "+spreadTile.getx()+" y: "+spreadTile.gety());
        MapPopulator replicant=new MapPopulator(type, spreadTile.getx(), spreadTile.gety(), probability, getCurTypeNum(), map);
        subtractGuaranteedRemaining();

    }

    Tile getCurrentLoc(){
        return map.map[this.x][this.y];
    }

}
