/*William Lilja och Elisabeth Nyström*/
/*Aj0367 och Ai8798*/
/*Medieproduktion och processdesign*/

package View;

import Controll.Controller;

import javax.swing.*;
import java.awt.*;

public class EastPanel extends JPanel {
    private int width;
    private int height;
    private Controller controller;
    private JList txtValue;
    private JList txtAmount;
    private DefaultListModel insideList = new DefaultListModel();
    private DefaultListModel insideListAmount = new DefaultListModel();

    public EastPanel(Controller controller){
        this.controller = controller;
        createComponents();
    }

    public void AddElement(String message){
        if(message=="vinnare"){
            insideList.removeAllElements();
        }else{
            insideList.addElement(message);
        }
        txtValue.setModel(insideList);
        txtValue.revalidate();
    }

    public void createComponents(){
        txtValue = new JList(insideList);
        txtValue.setFont(new Font("Arial", Font.PLAIN, 10));
        Dimension dim = new Dimension(300,600);
        txtValue.setPreferredSize(dim);

        add(txtValue);
    }
}