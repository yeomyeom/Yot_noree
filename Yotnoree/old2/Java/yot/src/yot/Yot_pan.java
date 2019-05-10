package yot;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class Yot_pan extends JFrame {
	JPanel panel_pan;
	JButton [][]pan_button;
	Playgame play;
	int size_x=1000;
	int size_y=1000;
	int btn_x = 50;
	int btn_y = 50;
	JButton throw_button, new_mal;
	JButton []test_button = new JButton[7];
	JButton now_player;
	JButton pan_;
	JButton []player_info_btn;
	JLabel yot_result;
	JLabel fin;
	JLabel []player_info;
	public Yot_pan(Playgame play) {
		panel_pan = new JPanel();
		pan_button = new JButton [3][21];
		int xpos=btn_x*8;
		int ypos=btn_y*7;
   	 	panel_pan.setLayout(null);
   	 	panel_pan.setBackground(new Color(255,255,255));
   	 	int p=0;
		for(int i=0;i<25;i++)//0~20
		{
			if(i<7)
			{
				ypos -= btn_y;
			}else if(i<13)
			{
				xpos -= btn_x;
			}
			else if(i<19)
			{
				ypos += btn_y;
			}else
			{
				xpos += btn_x;
			}
			if(i!=3 && i!=9 &&i!=15 &&i!=21) {
				if(p!=0) {
					pan_button[0][p] = new JButton(new ImageIcon("./img/circle.jpg"));
					//pan_button[0][p] = new JButton(Integer.toString(p));
					pan_button[0][p].setLocation(xpos,ypos);
					pan_button[0][p].setSize(btn_x,btn_y);
					pan_button[0][p].setBorderPainted(false);
					pan_button[0][p].setContentAreaFilled(false);
					panel_pan.add(pan_button[0][p]);
					pan_button[0][p].addActionListener(play);
				}
				p++;
			}
		}
		ypos =0;
		xpos =btn_x*8;
		for(p=0;p<6;p++)
		{
			
			if(p==3)
			{
				xpos -= btn_x;
				ypos += btn_y;
			}else {
				if(p==0)
				{
					xpos -= btn_x;
					ypos += btn_y;
				}
				else 
				{
					pan_button[1][p] = new JButton(new ImageIcon("./img/circle.jpg"));
					//pan_button[1][p] = new JButton(Integer.toString(p));
					pan_button[1][p].setLocation(xpos,ypos);
					xpos -= btn_x;
					ypos += btn_y;
					pan_button[1][p].setSize(btn_x,btn_y);
					pan_button[1][p].setBorderPainted(false);
					pan_button[1][p].setContentAreaFilled(false);
					panel_pan.add(pan_button[1][p]);
					pan_button[1][p].addActionListener(play);
				}
			}
		}
		xpos = btn_x*2;
		ypos = 0;
		for(p=0;p<6;p++)
		{
			if(p==0) {
				xpos += btn_x;
				ypos += btn_y;
			}
			else
			{
				pan_button[2][p] = new JButton(new ImageIcon("./img/circle.jpg"));
				//pan_button[2][p] = new JButton(Integer.toString(p));
				pan_button[2][p].setLocation(xpos,ypos);
				xpos += btn_x;
				ypos += btn_y;
				pan_button[2][p].setSize(btn_x,btn_y);
				pan_button[2][p].setBorderPainted(false);
				pan_button[2][p].setContentAreaFilled(false);
				panel_pan.add(pan_button[2][p]);
				pan_button[2][p].addActionListener(play);
			}
		}
		
		throw_button = new JButton("윷 던지기");
		throw_button.setSize(150,50);
		throw_button.setBorderPainted(true);
		throw_button.setContentAreaFilled(true);
		throw_button.setLocation(450,500);
		throw_button.setBackground(new Color(255,255,0));
		panel_pan.add(throw_button);
		throw_button.addActionListener(play);
		
		pan_ = new JButton("판 클릭");
		pan_.setSize(150,50);
		pan_.setBorderPainted(true);
		pan_.setContentAreaFilled(true);
		pan_.setLocation(450,450);
		pan_.setBackground(new Color(153,204,255));
		panel_pan.add(pan_);
		
		new_mal = new JButton("새로운 말 꺼내기");
		new_mal.setSize(150,50);
		new_mal.setBorderPainted(true);
		new_mal.setContentAreaFilled(true);
		new_mal.setLocation(450,600);
		new_mal.setBackground(new Color(153,204,255));
		panel_pan.add(new_mal);
		new_mal.addActionListener(play);
		
		now_player = new JButton();
		now_player.setBorderPainted(false);
		now_player.setContentAreaFilled(false);
		now_player.setSize(200,70);
		now_player.setLocation(50,500);
		panel_pan.add(now_player);
		
		yot_result = new JLabel();
		yot_result.setSize(300,70);
		yot_result.setLocation(250,500);
		panel_pan.add(yot_result);
		
		fin = new JLabel("Game is not yet finish");
		fin.setSize(300,60);
		fin.setLocation(50,350);
		panel_pan.add(fin);
		
		player_info=new JLabel[4];
		player_info_btn=new JButton[4];
		ImageIcon []img = new ImageIcon[4];
		img[0] = new ImageIcon("./img/red.jpg");
		img[1] = new ImageIcon("./img/blue.jpg");
		img[2] = new ImageIcon("./img/green.jpg");
		img[3] = new ImageIcon("./img/yellow.jpg");
		for(int i=0;i<4;i++)
		{
			player_info_btn[i] = new JButton();
			player_info_btn[i].setIcon(img[i]);
			player_info_btn[i].setSize(50, 50);
			player_info_btn[i].setBorderPainted(false);
			player_info_btn[i].setContentAreaFilled(false);
			player_info_btn[i].setLocation(450,i*50);
			player_info[i] = new JLabel();
			player_info[i].setSize(500,50);
			player_info[i].setLocation(500,i*50);
			panel_pan.add(player_info_btn[i]);
			panel_pan.add(player_info[i]);
		}

		for(int q=0;q<7;q++)
		{
			test_button[q] = new JButton(Integer.toString(q));
			test_button[q].setSize(70,60);
			test_button[q].setBorderPainted(true);
			test_button[q].setContentAreaFilled(true);
			test_button[q].setLocation(200+q*60,700);
			panel_pan.add(test_button[q]);
			test_button[q].addActionListener(play);
		}
		
        this.add(panel_pan);
        this.setTitle("Yot play");
        this.setVisible(true);
        this.setSize(size_x, size_y);
	}
	void initcp()//changeplayer와 printyotresult 화면 갱신때 빈칸으로 만듬
	{
		now_player.setIcon(null);
		yot_result.setText("");
	}
	void changeplayer(int i)
	{
		ImageIcon []img = new ImageIcon[4];
		img[0] = new ImageIcon("./img/red.jpg");
		img[1] = new ImageIcon("./img/blue.jpg");
		img[2] = new ImageIcon("./img/green.jpg");
		img[3] = new ImageIcon("./img/yellow.jpg");
		now_player.setIcon(img[i]);
	}
	void printyotresult(int i)
	{
		String text;
		switch(i){
		case -1:
			text = "빽도";
			break;
		case 1:
			text ="도";
			break;
		case 2:
			text ="개";
			break;
		case 3:
			text ="걸";
			break;
		case 4:
			text ="윷";
			break;
		case 5:
			text ="모";
			break;
		default:
			text = "printyotresult ERROR";
			break;
		}
		yot_result.setText(text);
	}
	void printmal(int player, int posx, int posy)
	{
		if(posx==0 && posy==0)
		{
			posx=0;
			posy=20;
		}
		else if(posx==0 && posy==-1)
		{
			posx=0;
			posy=19;
		}
		else if(posx==1 && posy==3)
		{
			posx=2;
			posy=3;
		}
		else if(posy>20)
		{
			player = 9;//골인 지점으로 들어와서 표시 안해줘도됨
		}
		if(player == 0)
		{
			pan_button[posx][posy].setIcon(new ImageIcon("./img/red.jpg"));
		}
		else if(player == 1)
		{
			pan_button[posx][posy].setIcon(new ImageIcon("./img/blue.jpg"));
		}
		else if(player == 2)
		{
			pan_button[posx][posy].setIcon(new ImageIcon("./img/green.jpg"));
		}
		else if(player == 3)
		{
			pan_button[posx][posy].setIcon(new ImageIcon("./img/yellow.jpg"));
		}
		else if(player == 4)//흰색 판으로 다시 되돌림
		{
			pan_button[posx][posy].setIcon(new ImageIcon("./img/circle.jpg"));
		}
		/*else if(player == 5)//이건 미리가기용도
		{
			pan_button[posx][posy].setIcon(new ImageIcon("./img/yellow.jpg"));
		}*/
		else
		{
			//System.out.println("printmal error");
		}
		
	}
	void message(String s)
	{
		fin.setText(s);
	}
	void finish(int winner)
	{
		fin.setText("Player "+winner+ " is win");
	}
	void setplayer_info(int i, String s)
	{
		player_info[i].setText(s);
	}
	void buttoncolor(int input)
	{
		switch (input)
		{
		case 1:
			throw_button.setBackground(new Color(153,204,255));//던지기 파랑
			break;
		case 2:
			throw_button.setBackground(new Color(255,255,0));//던지기 노랑
			break;
		case 3:
			new_mal.setBackground(new Color(255,255,0));//새로운 말 노랑
			break;
		case 4:
			new_mal.setBackground(new Color(153,204,255));//새로운 말 파랑
			break;
		case 5:
			pan_.setBackground(new Color(255,255,0));//판클릭 노랑
			break;
		case 6:
			pan_.setBackground(new Color(153,204,255));//판클릭 파랑
			break;
		}
	}
}