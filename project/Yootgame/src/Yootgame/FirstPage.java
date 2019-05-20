package Yootgame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class FirstPage extends JFrame{
	private JPanel panel = new JPanel();
	private JRadioButton playerNumbtn[] = new JRadioButton[3];
	private JRadioButton pieceNumbtn[] = new JRadioButton[4];
	private JButton start = new JButton("시작");
	private ButtonGroup playerNumcheck = new ButtonGroup();
	private ButtonGroup pieceNumcheck = new ButtonGroup();
	private ActionListener listen = new ActionListener() {
    	public void actionPerformed(ActionEvent e) {
    		int people=2, mal=2;
    		if(playerNumbtn[2].isSelected()) {
    			people = 4;
    		}
    		else if(playerNumbtn[1].isSelected())
    		{
    			people = 3;
    		}
    		else
    		{
    			people = 2;
    		}
    		if(pieceNumbtn[3].isSelected())
    		{
    			mal =5;
    		}
    		else if(pieceNumbtn[2].isSelected())
    		{
    			mal =4;
    		}
    		else if(pieceNumbtn[1].isSelected())
    		{
    			mal =3;
    		}
    		else
    		{
    			mal =2;
    		}
    		if(e.getSource()==start)
    		{
    			System.out.println(people+" , "+ mal);
    			new PlayGame(people, mal);
    		}
    	}
    };
    public FirstPage(){
        JLabel lb1, lb2;
        lb1 = new JLabel("사용자 수");
        panel.add(lb1);
        for(int i=0;i<3;i++) {
        	playerNumbtn[i] = new JRadioButton(Integer.toString(i+2));
        	playerNumcheck.add(playerNumbtn[i]);
        	panel.add(playerNumbtn[i]);
        	playerNumbtn[i].addActionListener(listen);
        }
        playerNumbtn[0].setSelected(true);
        playerNumbtn[1].setSelected(false);
        playerNumbtn[2].setSelected(false);
        
        lb2 = new JLabel("말 갯수");
        panel.add(lb2);
        for(int i=0;i<4;i++) {
        	pieceNumbtn[i] = new JRadioButton(Integer.toString(i+2));
        	pieceNumcheck.add(pieceNumbtn[i]);
        	panel.add(pieceNumbtn[i]);
        	pieceNumbtn[i].addActionListener(listen);
        }
        pieceNumbtn[0].setSelected(true);
        pieceNumbtn[1].setSelected(false);
        pieceNumbtn[2].setSelected(false);
        pieceNumbtn[3].setSelected(false);

        start.setSelected(false);
        panel.add(start);
        start.addActionListener(listen);
        
        this.add(panel);
        this.setTitle("First page");
        this.setVisible(true);
        this.setSize(600, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
	public static void main(String[] args) {
		new FirstPage();

	}
}