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
			if(play[i].point > mal_num)
			{
				play = null;
				pan.finish(i);
				pan = null;
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
						System.out.println("잡았다");
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
			System.out.println("윷 던지기 클릭함");
			nowplayer = play[turn];
			pan.changeplayer(turn);
			result=nowplayer.throwyot();//던지기 버튼 클릭
			pan.printyotresult(result);//던진 결과 화면에 출력
			control =2;
		}
	}
	void go_2(int posx, int posy)
	{
		int index;
		if(control==2)
		{
			if(nowplayer.mal.size()==0)//만약 움직일 말이 없으면 0에 새로 생성하고 던진 결과 만큼 이동 
			{
				System.out.println("만들어진 말 없음");
				control=3;
				go_3();
			}
			else
			{
				index = nowplayer.check_enable(posx, posy);//해당 위치의 버튼에 말이 있는지 확인
				if(index!=-1)
				{
					System.out.println("버튼 입력이 유효함");
					//말이 있으면 player에서 알아서 찾고 도개걸 결과로 이동함
					pan.printmal(4, posx, posy);//가기전에 흰색으로 원상 복구 후 이동
					nowplayer.move(posx, posy, result); //여기서 알아서 업어가는지 판단해줌
					if(nowplayer.check_malin()==0)
					{
						pan.printmal(turn,nowplayer.mal.get(index).getx(),nowplayer.mal.get(index).gety());//플레이어, 이동 이후 좌표
					}
					if(check_catch(turn)==1 || result == 4 || result ==5)//다시 윷 던지기 조건
					{
						control=1;
						go_1();
					}
					else
					{
						System.out.println("next player");
						go_4();//해당 플레이어 턴 종료
					}
				}
				else
				{
					System.out.println("엉뚱한 버튼 클릭함"+posx +" , "+posy);
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
		if(control==3) {
			if(nowplayer.getmal_num()>0) {
				System.out.println("새로운 말을 꺼냄");
				nowplayer.createmal();//새로 만든것은 0 버튼에 위치해 있음
				nowplayer.move(0, 0, result); //여기서 알아서 업어가는지 판단해줌
				if(result<0)
				{
					result += 20;
				}
				pan.printmal(turn, 0, result);
				if(check_catch(turn)==1 || result == 4 || result ==5)//다시 윷 던지기 조건
				{
					control=1;
					go_1();
				}
				else
				{
					System.out.println(turn + " 번째 player 차례");
					go_4();//해당 플레이어 턴 종료
				}
			}//사용할 수 있는 말이 양수 있으면 0 0에 추가
			else
			{
				System.out.println("더이상 추가할 말이 없습니다.");
				control=2;
			}
		}
	}
	void go_4()
	{
		play[turn].check_malin();
		turn++;
		if(turn >= player_num)
		{
			turn -= player_num;
		}
		winner = check_finish();
		if(winner != -1)
		{
			System.out.println(winner+ " 번째 플레이어가 승리하였습니다.");
		}
		control =1;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==pan.throw_button)
		{
			go_1();
		}
		if(e.getSource()==pan.new_mal)
		{
			if(control == 2)
			{
				control=3;
				go_3();
			}
		}
		for(int i=1;i<21;i++) {
			if(e.getSource()==pan.pan_button[0][i])
			{
				go_2(0,i);
			}
		}
		for(int p=0;p<7;p++) {
			if(e.getSource()==pan.pan_button[1][p])
			{
				go_2(1,p);
			}
		}
		for(int q=0;q<7;q++) {
			if(e.getSource()==pan.pan_button[2][q])
			{
				go_2(2,q);
			}
		}
		for(int r=0;r<7;r++)
		{
			if(e.getSource()==pan.test_button[r])
			{
				if(control==1)
				{
					if(r==0)
					{
						result = -1;
					}
					else
					{
						result = r;
					}
					pan.printyotresult(result);//던진 결과 화면에 출력
					control =2;
				}
			}
		}

	}
}
