/**
 * class for actually displaying map
 * @author Ryan Linville
 */
import java.io.File;
import java.awt.GridLayout;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MapDisplay extends JFrame{
    DrawnMap map;



	public MapDisplay(String title,DrawnMap map) {

        this.map=map;
		super.setTitle(title);
		super.setSize(1920, 1080);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setVisible(true);
		
		// setting grid layout with rows, cols, hgap, and vgap
		super.setLayout(new GridLayout(map.length, map.length, 0, 0));
        
		init();
        pack();
	}


	
	private void init() {

		File file = new File("images");

		// getting files name from folder

		


        for(int k=0;k<50;k++){
            for(int m=0;m<50;m++){

                JLabel label = new JLabel();
                if(!map.map[m][k].type.equals("")){
                    
                    String tileType=map.map[m][k].type;
                    label.setIcon(new ImageIcon(
            		new ImageIcon("images/" + tileType+".png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
                    add(label);
                }
                else{
                    label.setIcon(new ImageIcon(
            		new ImageIcon("images/blank.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
                    add(label);
                    
                    
                }

        
            }
        }
        
        
    }


    public static void main(String args[]){

        String[] test={"m","t"};
        DrawnMap map=new DrawnMap(50, test, 3);
        map.populateTypes();
        map.printMap();
        MapDisplay display=new MapDisplay("Generated Map",map);
        

    }






}
