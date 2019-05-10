package yot;
import java.util.Random;
import java.util.ArrayList;

public class player {
	int id;
	ArrayList<mal> mal;
	ArrayList<mal> ghost;
	int point;
	int mal_num;//아직 윷판에 안나오고 대기중인 말 수
	player(int _id, int _mal_num)
	{
		id = _id;
		mal = new ArrayList<mal>();
		ghost = new ArrayList<mal>();
		point =0;
		mal_num = _mal_num;
	}
	ArrayList<mal> getmal()
	{
		return mal;
	}
	int getpoint()
	{
		return point;
	}
	int getmal_num()
	{
		return mal_num;
	}
	int createmal()
	{
		if(mal_num>0)
		{
			mal.add(new mal());//판 위에 말 올림
			mal_num--;
			System.out.println("말 생성 완료");
			return 1;
		}
		return 0;
	}
	int throwyot()
	{
		Random r = new Random();
		int act=0;
		act = r.nextInt(6)-1;
		if(act == 0)
			act = 5;
		return act;
	}
	String player_mal()
	{
		String s="";
		for(mal m : mal)
		{
			s += "<남말:"+mal_num+"포:"+point+">("+m.getx()+","+m.gety()+","+m.getpoint()+")";
		}
		return s;
	}
	void wheremove(int posx, int posy, int active)//판위에 올라갈 수 있는 말들의 갈 수 있는 위치 출력
	{
		//ghost.addAll(mal);
		//일단 보류하고 만들자
	}
	int move(int x, int y, int active)//(말 위치, 도개걸윷 정보를 보내주면) 무슨 말을 움직일까요?
	{
		if(mal.size()!=0)
		{
			for(mal m : mal)
			{
				if(m.getx()==x && m.gety()==y)//(말 위치에 있는 말을 찾고 도개걸윷 만큼 이동시킴)
				{
					m.setpos(active);
					//ghost.removeAll(ghost);
					//ghost.addAll(mal);
				}
			}
			return checkupda();
		}
		return 0;
	}
	int check_catch(int positionx, int positiony)
	{
		for(mal m : mal)
		{
			if(m.getx()==positionx && m.gety()==positiony)
			{
				System.out.println(m.getpoint()+"개 잡혔다");
				mal_num += m.getpoint();
				delmal(positionx, positiony);//position과 같은 위치에 말이 있으면 지움
				return 1;
			}
		}
		return 0;
	}
	int check_enable(int posx, int posy)//해당 위치에 말이 있나 없나
	{
		int i=0;
		for(mal m : mal)
		{
			if(m.getx()==posx && m.gety()==posy)
			{
				return i;
			}
			i++;
		}
		return -1;
	}
	int check_malin()
	{
		System.out.println("P "+id+" 말 들어갔는지 확인");
		for(mal m : mal)
		{
			if(m.gety()>20) {
				point += m.getpoint();
				//mal_num -= m.getpoint();<<버그 찾았다
				System.out.println("말이 "+m.getpoint()+"개 들어옴 현재 포인트"+point);
				mal.remove(m);
				return 1;
			}
		}
		return 0;
	}
	int checkupda()
	{
		for(int p=0;p<mal.size();p++)
		{
			for(int q=p+1;q<mal.size();q++)
			{
				if(mal.get(p).getx()==mal.get(q).getx() && mal.get(p).gety()==mal.get(q).gety())//같은 위치에 말이 있다면
				{
					System.out.println("업힘 발생함");
					mal.get(p).setpoint(mal.get(q).getpoint());//q번째 말이 업은 만큼 p번째 말이 업음
					mal.remove(q);
					return 1;
				}
			}
		}
		return 0;
	}
	void delmal(int x, int y)
	{
		for(mal m : mal)
		{
			if(m.getx()==x&&m.gety()==y)
			{
				System.out.println("말을 제거했습니다");
				mal.remove(m);
				break;
			}
		}
	}
	void showall()
	{
		for(mal m : mal)
		{
			System.out.println(" "+m.getx()+m.gety()+m.getpoint()+" ");
		}
	}
}
