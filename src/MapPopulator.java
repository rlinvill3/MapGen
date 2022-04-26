import java.util.ArrayList;

public class MapPopulator {
    

    String type;
    int typeNum;
    int x;
    int y;
    DrawnMap map;
    int probability;




    /**
     * 
     * @param type
     * @param x
     * @param y
     * @param probability
     * @param typeNum
     * @param map
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
        else{
            if(map.randFromProb(probability)){
                spread();
            }
        }

    }

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
     * @return 
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

    

    void generateCurrentTile(){

        map.map[x][y]=new Tile(type, x, y);
        if(checkGuaranteedRemaining()){
            subtractGuaranteedRemaining();
        }

        
    }
    boolean checkGuaranteedRemaining(){
        if(map.typeGuaranteedMap.get(type)>0){
            return true;
        }
        return false;
    }
    void subtractGuaranteedRemaining(){
        map.typeGuaranteedMap.put(type, typeNum-1);
    }


    int getCurTypeNum(){
        return map.typeGuaranteedMap.get(type);
    }

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
