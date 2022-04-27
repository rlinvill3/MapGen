/**
 * class for actually displaying map
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MapDisplay extends JFrame{



	public MapDisplay(String title,DrawnMap map) {

		super.setTitle(title);
		super.setSize(1920, 1080);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setVisible(true);
		
		// setting grid layout with rows, cols, hgap, and vgap
		super.setLayout(new GridLayout(map.length, map.length, 0, 0));

		init();
	}

    private void init() {

		File file = new File("images");

		// getting files name from folder
		for (String name : file.list()) {

			JLabel label = new JLabel();

			// setting icon
			label.setIcon(new ImageIcon(
					new ImageIcon("images/" + name).getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));

			add(label);

		}

	}
    

    public static void main(String args[]){

        String[] ligma={"m","p"};
        DrawnMap map=new DrawnMap(50, ligma, 1);
        new MapDisplay("Generated Map",map);

    }






}
