/*William Lilja och Elisabeth Nyström*/
/*Aj0367 och Ai8798*/
/*Medieproduktion och processdesign*/

package View;

import Controll.Controller;

import javax.swing.*;
import java.awt.*;

public class MainFrame {
    private JFrame frame;
    private MainPanel panel;
    private Controller controller;
    private EastPanel eastPanel;

    private int width = 500;
    private int height = 600;

    public MainFrame(Controller controller){
        this.controller = controller;
        frame = new JFrame("Battleships");
        frame.setSize(width,height);
        frame.setBounds(10,10,width,height);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(150,150);

        panel = new MainPanel(width, height, controller);
        eastPanel = new EastPanel(controller);

        frame.add(panel);
        frame.getContentPane().setPreferredSize(new Dimension(width, height));
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.add(eastPanel,BorderLayout.EAST);
        frame.getContentPane().setPreferredSize(new Dimension(1000, height));
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

    }

    public String askName(){
        String name = JOptionPane.showInputDialog("Vad heter du?");
        return name;
    }

    public void showHighscore(String[] highscore) {
        JPanel textPanel = new JPanel(new GridLayout(15, 1));
        for (int i = 0; i < highscore.length; i++) {
            textPanel.add(new JLabel(highscore[i]));
        }
        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.add(textPanel);
        JOptionPane.showMessageDialog(null, panel2, "Highscore",JOptionPane.DEFAULT_OPTION);
        panel.reset();
    }

    public void AddElement(String message){
        eastPanel.AddElement(message);
        eastPanel.revalidate();
    }
}
