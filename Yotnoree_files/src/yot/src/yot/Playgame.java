package yot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Playgame implements ActionListener{
	player []play;
	Yot_pan pan;
	int mal_num;
	int player_num;
	int turn=0;
	int winner=-1;
	int result=0;
	player nowplayer;
	int i=0;
	int control=1;
	Playgame(int people, int mal)
	{
		play = new player[people];
		for(int i=0;i<people;i++)
		{
			play[i] = new player(i,mal);
		}
		pan = new Yot_pan(this);//yotpan에서 버튼이 클릭 되었다는 정보를 받기위해 본인 객체를 보냄
		player_num = people;
		mal_num = mal;
	}
	int check_finish()
	{
		for(int i=0;i<play.length;i++)
		{
			if(play[i].getpoint() == mal_num)
			{
				play = null;
				pan.finish(i);
				//pan = null;
				return i;//i번째 플레이어 승리
			}
		}
		return -1;//게임 아직 안끝남
	}
	int check_catch(int index)//지금 플레이어 인덱스값
	{
		player catcher = play[index];//catcher는 지금의 플레이어
		int positionx,positiony;
		for(int q=0;q<catcher.mal.size();q++)//현재 플레이어의 모든 말
		{
			positionx = catcher.mal.get(q).getx();
			positiony = catcher.mal.get(q).gety();//현재 플레이어의 q번째 말 위치
			for(int i=0;i<play.length;i++)
			{
				if(i!=index)
				{
					if(play[i].check_catch(positionx,positiony)==1)//i번째 player의 말들과 비교해서 같으면 없앰
					{
						pan.message("P"+index+"가 P"+i+"의 말을 잡았다");
						return 1;//한칸에 서로 다른 플레이어의 말이 겹쳐있지 않으므로 그냥 만나자 마자 종료
					}
				}
			}
		}
		return 0;
	}
	void go_1()
	{
		if(control==1) {
			result =0;
			nowplayer = play[turn];
			pan.changeplayer(turn);
			pan.buttoncolor(1);
			result=nowplayer.throwyot();//던지기 버튼 클릭
			pan.printyotresult(result);//던진 결과 화면에 출력
			if(nowplayer.getmal_num()>0)//대기중인 말이 있다면 
			{
				pan.buttoncolor(3);//새로운 말 버튼 활성화
			}
			if(nowplayer.getmal().size()>0)//판에 말이 있다면 
			{
				pan.buttoncolor(5);//판 클릭 버튼 활성화
			}
			control =2;
		}
	}
	void go_2(int posx, int posy)
	{
		int index;
		if(control==2)
		{
			if(nowplayer.mal.size()==0 && nowplayer.getmal_num()>0)//판위에 말이 없고 대기중인 말이 있다면 0,0에 새로 만들고
			{
				pan.message("만들어진 말 없음");
				control=3;
				go_3();
			}
			else
			{
				index = nowplayer.check_enable(posx, posy);//해당 버튼에 말이 있는지 확인
				if(index!=-1)
				{
					//말이 있으면 player에서 알아서 찾고 도개걸 결과로 이동함
					pan.printmal(4, posx, posy);//가기전에 흰색으로 원상 복구 후 이동
					if(nowplayer.move(posx, posy, result)==1) //여기서 알아서 업어가는지 판단해줌
					{//들어가거나 겹쳐졌을때 화면에 표시를 안한다 이것때문에 자꾸 오류가 난다.
						pan.message("P "+turn+" 말 하나가 업혔습니다");
					}
					else if(nowplayer.check_malin() ==1)
					{
						pan.message("P "+turn+" 말 하나가 골인했습니다");
					}
					else
					{
						/////////////////////////// 버그생기는 지점
						pan.printmal(turn,nowplayer.mal.get(index).getx(),nowplayer.mal.get(index).gety());//플레이어, 이동 이후 좌표
						/////////////////////////// 버그생기는 지점
					}
					pan.setplayer_info(turn, nowplayer.player_mal());
					if(nowplayer.getmal().size()>0)
					{
						pan.buttoncolor(5);
					}
					else
					{
						pan.buttoncolor(6);
					}
					if(nowplayer.getmal_num()>0)
					{
						pan.buttoncolor(3);
					}
					else
					{
						pan.buttoncolor(4);
					}
					if(nowplayer.getmal_num()<=0 && nowplayer.getmal().size()<=0)//대기중인 말과 판위에 말이 없으면
					{
						control=-1;//경기 종료
						System.out.println("경기 종료");
						go_4();//해당 플레이어 턴 종료
					}
					else//게임이 끝나지 않았다면 (이렇게 해놔야 자바 익셉션 안뜸)
					{
						if(check_catch(turn)==1 || result == 4 || result ==5)//다시 윷 던지기 조건
						{
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							control=1;
							go_1();
						}
						else
						{
							go_4();//해당 플레이어 턴 종료
						}
					}
				}
				else
				{
					pan.message("엉뚱한 버튼 클릭함"+posx +" , "+posy);
					//다시 입력할때 까지 기다림
				}
			}
		}
		else
		{
			//아직 윷을 던지지도 않았는데 판 클릭하면 아무 동작 안함
		}
	}
	void go_3()
	{
		if(control==3)
		{
			//말이 있으면 player에서 알아서 찾고 도개걸 결과로 이동함
			if(nowplayer.createmal()==1) //대기중인 말의 수가 있다면 새로 생성 가능
			{
				nowplayer.move(0, 0, result); //여기서 알아서 업어가는지 판단해줌
				pan.setplayer_info(turn, nowplayer.player_mal());
				pan.printmal(turn,0,result);//플레이어, 이동 이후 좌표
				if(nowplayer.getmal().size()>0)
				{
					pan.buttoncolor(5);
				}
				else
				{
					pan.buttoncolor(6);
				}
				if(nowplayer.getmal_num()>0)
				{
					pan.buttoncolor(3);
				}
				else
				{
					pan.buttoncolor(4);
				}
				if(check_catch(turn)==1 || result == 4 || result ==5)//다시 윷 던지기 조건
				{
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					control=1;
					go_1();
				}
				else
				{
					go_4();//해당 플레이어 턴 종료
				}
			}
			else
			{
				pan.message("더 이상 말을 생성 할 수 없습니다.");
				control=2;//말 생성 한도를 넘어가면 go_2가 작동 할 수 있도록 한다.
			}
		}
	}
	void go_4()
	{
		winner = check_finish();
		if(winner != -1)
		{
			control=5;//아무 동작 안함
			System.out.println(winner+ " 번째 플레이어가 승리하였습니다.");
		}
		else 
		{
			turn++;
			if(turn >= player_num)
			{
				turn =0;
			}
			pan.message("P "+turn+" 차례");
			pan.initcp();
			pan.buttoncolor(2);
			pan.buttoncolor(4);
			pan.buttoncolor(6);
			control =1;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==pan.throw_button)
		{
			if(control==1)
			{
				go_1();
			}
		}
		if(e.getSource()==pan.new_mal)
		{
			if(control == 2)
			{
				control=3;
				go_3();
			}
		}
		if(control==2) {
			for(int i=1;i<21;i++) {
				if(e.getSource()==pan.pan_button[0][i])
				{
					go_2(0,i);
				}
			}
			for(int p=0;p<6;p++) {
				if(e.getSource()==pan.pan_button[1][p])
				{
					go_2(1,p);
				}	
			}
			for(int q=0;q<6;q++) {
				if(e.getSource()==pan.pan_button[2][q])
				{
					go_2(2,q);
				}
			}
		}
		for(int r=0;r<6;r++)
		{
			if(e.getSource()==pan.test_button[r])
			{
				if(control==1)
				{
					go_1();
					r--;
					if(r==0)
					{
						result = 5;
					}
					else
					{
						result = r;
					}
					pan.printyotresult(result);//던진 결과 화면에 출력
				}
			}
		}
	}
}
