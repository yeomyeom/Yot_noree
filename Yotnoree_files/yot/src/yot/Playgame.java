package yot;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Playgame implements ActionListener{
	Player []play;
	Yotboard board;
	int pieceNum;
	int playerNum;
	int turn=0;
	int winner=-1;
	int result=0;
	Player nowPlayer;
	int i=0;
	int control=1;
	Playgame(int people, int mal)
	{
		play = new Player[people];
		for(int i=0;i<people;i++)
		{
			play[i] = new Player(i,mal);
		}
		board = new Yotboard(this);//yotboard에서 버튼이 클릭 되었다는 정보를 받기위해 본인 객체를 보냄
		playerNum = people;
		pieceNum = mal;
	}
	int checkFinish()
	{
		for(int i=0;i<play.length;i++)
		{
			if(play[i].getPoint() == pieceNum)
			{
				play = null;
				board.finishMessage(i);
				//board = null;
				return i;//i번째 플레이어 승리
			}
		}
		return -1;//게임 아직 안끝남
	}
	int checkCatch(int index)//지금 플레이어 인덱스값
	{
		Player catcher = play[index];//catcher는 지금의 플레이어
		int positionx,positiony;
		for(int q=0;q<catcher.getPiece().size();q++)//현재 플레이어의 모든 말
		{
			positionx = catcher.getPiece().get(q).getX();
			positiony = catcher.getPiece().get(q).getY();//현재 플레이어의 q번째 말 위치
			for(int i=0;i<play.length;i++)
			{
				if(i!=index)
				{
					if(play[i].checkCatch(positionx,positiony)==1)//i번째 Player의 말들과 비교해서 같으면 없앰
					{
						board.message("P"+index+"가 P"+i+"의 말을 잡았다");
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
			nowPlayer = play[turn];
			board.changePlayer(turn);
			board.buttonColor(1);
			result=nowPlayer.throwYot();//던지기 버튼 클릭
			board.printResult(result);//던진 결과 화면에 출력
			if(nowPlayer.getPieceNum()>0)//대기중인 말이 있다면 
			{
				board.buttonColor(3);//새로운 말 버튼 활성화
			}
			if(nowPlayer.getPiece().size()>0)//판에 말이 있다면 
			{
				board.buttonColor(5);//판 클릭 버튼 활성화
			}
			control =2;
		}
	}
	void go_2(int posx, int posy)
	{
		int index;
		if(control==2)
		{
			if(nowPlayer.getPiece().size()==0 && nowPlayer.getPieceNum()>0)//판위에 말이 없고 대기중인 말이 있다면 0,0에 새로 만들고
			{
				board.message("만들어진 말 없음");
				control=3;
				go_3();
			}
			else
			{
				index = nowPlayer.checkEnable(posx, posy);//해당 버튼에 말이 있는지 확인
				if(index!=-1)
				{
					//말이 있으면 Player에서 알아서 찾고 도개걸 결과로 이동함
					board.printPiece(4, posx, posy);//가기전에 흰색으로 원상 복구 후 이동
					if(nowPlayer.move(posx, posy, result)==1) //여기서 알아서 업어가는지 판단해줌
					{//들어가거나 겹쳐졌을때 화면에 표시를 안한다 이것때문에 자꾸 오류가 난다.
						board.message("P "+turn+" 말 하나가 업혔습니다");
					}
					else if(nowPlayer.checkPiecein() ==1)
					{
						board.message("P "+turn+" 말 하나가 골인했습니다");
					}
					else
					{
						/////////////////////////// 버그생기는 지점
						board.printPiece(turn,nowPlayer.getPiece().get(index).getX(),nowPlayer.getPiece().get(index).getY());//플레이어, 이동 이후 좌표
						/////////////////////////// 버그생기는 지점
					}
					board.setplayerInfo(turn, nowPlayer.playerPiece());
					if(nowPlayer.getPiece().size()>0)
					{
						board.buttonColor(5);
					}
					else
					{
						board.buttonColor(6);
					}
					if(nowPlayer.getPieceNum()>0)
					{
						board.buttonColor(3);
					}
					else
					{
						board.buttonColor(4);
					}
					if(nowPlayer.getPieceNum()<=0 && nowPlayer.getPiece().size()<=0)//대기중인 말과 판위에 말이 없으면
					{
						control=-1;//경기 종료
						System.out.println("경기 종료");
						go_4();//해당 플레이어 턴 종료
					}
					else//게임이 끝나지 않았다면 (이렇게 해놔야 자바 익셉션 안뜸)
					{
						if(checkCatch(turn)==1 || result == 4 || result ==5)//다시 윷 던지기 조건
						{
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
					board.message("엉뚱한 버튼 클릭함"+posx +" , "+posy);
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
			//말이 있으면 Player에서 알아서 찾고 도개걸 결과로 이동함
			if(nowPlayer.createPiece()==1) //대기중인 말의 수가 있다면 새로 생성 가능
			{
				nowPlayer.move(0, 0, result); //여기서 알아서 업어가는지 판단해줌
				board.setplayerInfo(turn, nowPlayer.playerPiece());
				board.printPiece(turn,0,result);//플레이어, 이동 이후 좌표
				if(nowPlayer.getPiece().size()>0)
				{
					board.buttonColor(5);
				}
				else
				{
					board.buttonColor(6);
				}
				if(nowPlayer.getPieceNum()>0)
				{
					board.buttonColor(3);
				}
				else
				{
					board.buttonColor(4);
				}
				if(checkCatch(turn)==1 || result == 4 || result ==5)//다시 윷 던지기 조건
				{
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
				board.message("더 이상 말을 생성 할 수 없습니다.");
				control=2;//말 생성 한도를 넘어가면 go_2가 작동 할 수 있도록 한다.
			}
		}
	}
	void go_4()
	{
		winner = checkFinish();
		if(winner != -1)
		{
			control=5;//아무 동작 안함
			System.out.println(winner+ " 번째 플레이어가 승리하였습니다.");
		}
		else 
		{
			turn++;
			if(turn >= playerNum)
			{
				turn =0;
			}
			board.message("P "+turn+" 차례");
			board.initcp();
			board.buttonColor(2);
			board.buttonColor(4);
			board.buttonColor(6);
			control =1;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==board.throwButton)
		{
			if(control==1)
			{
				go_1();
			}
		}
		if(e.getSource()==board.newPiece)
		{
			if(control == 2)
			{
				control=3;
				go_3();
			}
		}
		if(control==2) {
			for(int i=1;i<21;i++) {
				if(e.getSource()==board.panButton[0][i])
				{
					go_2(0,i);
				}
			}
			for(int p=1;p<6;p++) {
				if(e.getSource()==board.panButton[1][p])
				{
					go_2(1,p);
				}	
			}
			for(int q=1;q<6;q++) {
				if(e.getSource()==board.panButton[2][q])
				{
					go_2(2,q);
				}
			}
		}
		for(int r=0;r<6;r++)
		{
			if(e.getSource()==board.testButton[r])
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
					board.printResult(result);//던진 결과 화면에 출력
				}
			}
		}
	}
}
