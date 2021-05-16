/*William Lilja och Elisabeth Nyström*/
/*Aj0367 och Ai8798*/
/*Medieproduktion och processdesign*/
package View;

import Controll.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Skapar en spelplan, beroende på vilken man väljer
public class MainPanel extends JPanel implements ActionListener{
    private int width;
    private int height;
    int arr[][] = new int[10][10];
    int countUp = 0; //Gör att alla knappar får ett nummer som ökar
    Controller controller;

    private int[][] numOfButtons = new int[10][10];
    private JButton[][] buttons;
    private JButton btnSpelplanOne = new JButton("Spelplan 1");
    private JButton btnSpelplanTwo = new JButton("Spelplan 2");
    JButton optionButton1;
    JButton optionButton2;

    private JLabel lblInfo = new JLabel();

    private JPanel pnlNorth = new JPanel();

    private JPanel pnlCenter = new JPanel();
    private EastPanel eastPanel = new EastPanel(controller);

    public MainPanel(int width, int height, Controller controller){
        this.width = width;
        this.height = height;
        this.controller = controller;

        setSize(new Dimension(width,height));
        setLayout(new BorderLayout());

        btnSpelplanOne.setPreferredSize(new Dimension(100,20));
        btnSpelplanTwo.setPreferredSize(new Dimension(100,20));

        pnlNorth.add(btnSpelplanOne);
        pnlNorth.add(btnSpelplanTwo);
        pnlNorth.add(lblInfo);

        add(pnlNorth, BorderLayout.NORTH);
        btnSpelplanOne.addActionListener(this);
        btnSpelplanTwo.addActionListener(this);
    }

    private void creatButtonsInPaneSouth(){
        try{
        }
        catch (Exception e){
            numOfButtons[0][1] = 1;
        }
        buttons = new JButton[10][10];

        for (int i = 0 ; i< numOfButtons.length; i ++){
            for (int u = 0 ; u< numOfButtons[i].length; u ++){
                countUp++;
            buttons[i][u] = new JButton(String.format("%d", countUp));
            buttons[i][u].setPreferredSize(new Dimension(50,50));
            buttons[i][u].addActionListener(this);
            pnlCenter.add(buttons[i][u]);
            }
        }
        countUp =0;

        add(pnlCenter,BorderLayout.CENTER);
        GridLayout grid = new GridLayout(10,10);
        pnlCenter.setLayout(grid);
        revalidate();
        repaint();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSpelplanOne){
            controller.createGameField(1,true);
            creatButtonsInPaneSouth();
            pnlNorth.removeAll();

        }else if(e.getSource() == btnSpelplanTwo){
            controller.createGameField(2,true);
            creatButtonsInPaneSouth();
            pnlNorth.removeAll();

        }else if(e.getSource() == optionButton1){
            creatButtonsInPaneSouth();
            controller.createGameField(1,false);
        }else if(e.getSource() == optionButton2){
            creatButtonsInPaneSouth();
            controller.createGameField(2,false);
        }
        else{
            handleDynamicButtons(e);
        }
    }

    private void handleDynamicButtons(ActionEvent e){
        JButton button = (JButton) e.getSource();
        for (int i = 0; i < numOfButtons.length;i++){
            for (int u = 0; u < numOfButtons[i].length;u++) {
                if (buttons[i][u].equals(button)) {
                    buttons[i][u].setEnabled(false);
                    controller.checkPosition(i,u);
                }
            }
        }
    }

    public void reset() {
        //resetButtons();
        pnlCenter.removeAll();
        pnlCenter.revalidate();
        //Popup fönstret i slutet
        JPanel textPanel = new JPanel(new GridLayout(1, 3));
        optionButton1 = new JButton("Spelplan 1");
        optionButton2 = new JButton("Spelplan 2");
        textPanel.add(optionButton1);
        textPanel.add(optionButton2);
        optionButton1.addActionListener(this);
        optionButton2.addActionListener(this);
        JPanel panel2 = new JPanel(new BorderLayout());
        panel2.add(textPanel);
        JOptionPane.showMessageDialog(null, panel2, "Välj spelfält",JOptionPane.DEFAULT_OPTION);
    }

    private void resetButtons(){
        for (int i = 0 ; i< numOfButtons.length; i ++){
            for (int u = 0 ; u< numOfButtons[i].length; u ++){
                buttons[i][u].setEnabled(true);
            }
        }
    }

}
