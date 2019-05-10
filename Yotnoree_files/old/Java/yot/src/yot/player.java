package yot;
import java.util.Random;
import java.util.ArrayList;

public class player {
	int id;
	ArrayList<mal> mal;
	ArrayList<mal> ghost;
	int point;
	int mal_num;//남은 말 수
	player(int _id, int _mal_num)
	{
		id = _id;
		mal = new ArrayList<mal>();
		ghost = new ArrayList<mal>();
		point =0;
		mal_num = _mal_num;
	}
	int getmal_num()
	{
		return mal_num;
	}
	void createmal()
	{
		mal.add(new mal());//판 위에 말 올림
		mal_num--;
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
	void showmal()
	{
		for (int i=0;i<mal.size();i++)
		System.out.println(point+ " "+ mal.get(i).getx()+" "+ mal.get(i).gety());
	}
	void wheremove(int posx, int posy, int active)//판위에 올라갈 수 있는 말들의 갈 수 있는 위치 출력
	{
		ghost.addAll(mal);
		//일단 보류하고 만들자
	}
	void move(int x, int y, int active)//(말 위치, 도개걸윷 정보를 보내주면) 무슨 말을 움직일까요?
	{
		if(mal.size()!=0)
		{
			for(mal m : mal)
			{
				if(m.getx()==x && m.gety()==y)//(말 위치에 있는 말을 찾고 도개걸윷 만큼 이동시킴)
				{
					m.setpos(active);
					showmal();
					ghost.removeAll(ghost);
					ghost.addAll(mal);
				}
			}
			checkupda();
		}
	}
	int check_catch(int positionx, int positiony)
	{
		for(mal m : mal)
		{
			if(m.getx()==positionx && m.gety()==positiony)
			{
				delmal(positionx, positiony);//position과 같은 위치에 말이 있으면 지움
				mal_num++;
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
		for(mal m : mal)
		{
			if(m.getx()==2 && m.gety()>6) {
				point += m.getpoint();
				mal_num--;
				return 1;
			}
		}
		return 0;
	}
	void checkupda()
	{
		for(int p=0;p<mal.size();p++)
		{
			for(int q=p+1;q<mal.size();q++)
			{
				if(mal.get(p).getx()==mal.get(q).getx() && mal.get(p).gety()==mal.get(q).gety())//같은 위치에 말이 있다면
				{
					mal.get(p).setpoint(mal.get(q).getpoint());//q번째 말이 업은 만큼 p번째 말이 업음
					mal.remove(q);
				}
			}
		}
	}
	void delmal(int x, int y)
	{
		for(mal m : mal)
		{
			if(m.getx()==x&&m.gety()==y)
			{
				mal.remove(m);
				break;
			}
		}
	}
	
}
