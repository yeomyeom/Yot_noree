package yot;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class first_page extends JFrame{
    JPanel panel = new JPanel();
    JRadioButton button[] = new JRadioButton[3];
    JRadioButton button2[] = new JRadioButton[4];
    JLabel lb1, lb2;
    JButton start = new JButton("시작");
    
    public first_page(){
        ButtonGroup check = new ButtonGroup();
        ButtonGroup check2 = new ButtonGroup();
        lb1 = new JLabel("사용자 수");
        panel.add(lb1);
        button[0] = new JRadioButton("2");
        check.add(button[0]);
        panel.add(button[0]);
        button[1] = new JRadioButton("3");
        check.add(button[1]);
        panel.add(button[1]);
        button[2] = new JRadioButton("4");
        check.add(button[2]);
        panel.add(button[2]);
        
        button[0].setSelected(true);
        button[1].setSelected(false);
        button[2].setSelected(false);
        
        lb2 = new JLabel("말 갯수");
        panel.add(lb2);
        button2[0] = new JRadioButton("2");
        check2.add(button2[0]);
        panel.add(button2[0]);
        button2[1] = new JRadioButton("3");
        check2.add(button2[1]);
        panel.add(button2[1]);
        button2[2] = new JRadioButton("4");
        check2.add(button2[2]);
        panel.add(button2[2]); 
        button2[3] = new JRadioButton("5");
        check2.add(button2[3]);
        panel.add(button2[3]);

        button2[0].setSelected(true);
        button2[1].setSelected(false);
        button2[2].setSelected(false);
        button2[3].setSelected(false);

        start.setSelected(false);
        panel.add(start);

        
        ActionListener listen = new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int people=2, mal=2;
        		if(button[2].isSelected()) {
        			people = 4;
        		}
        		else if(button[1].isSelected())
        		{
        			people = 3;
        		}
        		else
        		{
        			people = 2;
        		}
        		if(button2[3].isSelected())
        		{
        			mal =5;
        		}
        		else if(button2[2].isSelected())
        		{
        			mal =4;
        		}
        		else if(button2[1].isSelected())
        		{
        			mal =3;
        		}
        		else
        		{
        			mal =2;
        		}
        		if(e.getSource()==start)
        		{
        			//start.setSelected(false);
        			System.out.println(people+" "+mal);
        			new Playgame(people, mal);
        			System.out.println("Playgame create");
        			//new Yot_pan();
        		}
        	}
        };
        button[0].addActionListener(listen);
        button[1].addActionListener(listen);
        button[2].addActionListener(listen);
        button2[0].addActionListener(listen);
        button2[1].addActionListener(listen);
        button2[2].addActionListener(listen);
        button2[3].addActionListener(listen);
        start.addActionListener(listen);
        
        this.add(panel);
        this.setTitle("First page");
        this.setVisible(true);
        this.setSize(600, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
	public static void main(String[] args) {
		new first_page();

	}
}

