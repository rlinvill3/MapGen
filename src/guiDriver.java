import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

public class guiDriver extends JPanel {
    private JButton genButton;
    private JLabel mapLabel;
    private JTextField jcomp4;
    private JTextField jcomp5;
    private JTextField jcomp6;
    private JTextField jcomp7;
    private JTextField jcomp8;
    private JTextField jcomp9;
    private JTextField jcomp10;
    private JTextField jcomp11;
    private ArrayList<String> strArray;

    public guiDriver() {
        //construct components
        genButton = new JButton ("Generate Map");
        mapLabel = new JLabel ("Random Map Generator");
        jcomp4 = new JTextField (5);
        jcomp5 = new JTextField (5);
        jcomp6 = new JTextField (5);
        jcomp7 = new JTextField (5);
        jcomp8 = new JTextField (5);
        jcomp9 = new JTextField (5);
        jcomp10 = new JTextField (5);
        jcomp11 = new JTextField (5);

        //adjust size and set layout
        setPreferredSize (new Dimension (1600, 900));
        setLayout (null);

        //add components
        add (genButton);
        add (mapLabel);
        add (jcomp4);
        add (jcomp5);
        add (jcomp6);
        add (jcomp7);
        add (jcomp8);
        add (jcomp9);
        add (jcomp10);
        add (jcomp11);

        //set component bounds (only needed by Absolute Positioning)
        genButton.setBounds (25, 635, 270, 50);
        mapLabel.setBounds (775, 0, 135, 40);
        jcomp4.setBounds (25, 45, 270, 45);
        jcomp5.setBounds (25, 110, 270, 45);
        jcomp6.setBounds (25, 175, 270, 45);
        jcomp7.setBounds (25, 240, 270, 45);
        jcomp8.setBounds (25, 305, 270, 45);
        jcomp9.setBounds (25, 370, 270, 45);
        jcomp10.setBounds (25, 440, 270, 45);
        jcomp11.setBounds (25, 510, 270, 45);

        //Button Actions
        genButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                strArray=new ArrayList<>();
                if(!jcomp4.getText().equals("")){strArray.add(jcomp4.getText());}
                if(!jcomp4.getText().equals("")){strArray.add(jcomp5.getText());}
                if(!jcomp4.getText().equals("")){strArray.add(jcomp6.getText());}
                if(!jcomp4.getText().equals("")){strArray.add(jcomp7.getText());}
                if(!jcomp4.getText().equals("")){strArray.add(jcomp8.getText());}
                if(!jcomp4.getText().equals("")){strArray.add(jcomp9.getText());}
                if(!jcomp4.getText().equals("")){strArray.add(jcomp10.getText());}
                if(!jcomp4.getText().equals("")){strArray.add(jcomp11.getText());}

                System.out.println(strArray.toString());
                

                

                String[] inputArray=new String[strArray.size()];
                System.out.println(strArray.size());
                System.out.println(strArray.toString());

                for(int k=0;k<strArray.size();k++){
                    inputArray[k]=strArray.get(k);
                }


                DrawnMap map=new DrawnMap(50, inputArray , 1);
                map.populateTypes();
                map.printMap();
                MapDisplay display=new MapDisplay("Display", map);

                

            }
        });

    }



    public static void main (String[] args) {
        JFrame frame = new JFrame ("gui3");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new guiDriver());
        frame.pack();
        frame.setVisible (true);


    }
}
