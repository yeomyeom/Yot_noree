package yot;

public class mal {
	int x,y;
	int point;
	mal()
	{
		x=0;
		y=0;
		point =1;
	}
	int getx()
	{
		return x;
	}
	int gety()
	{
		return y;
	}
	int getpoint()
	{
		return point;
	}
	void setpos(int act)
	{
		y += act;
		if(act>0) {
			if(x==0)
			{
				if(y==5)
				{
					x=1;
					y=0;
				}
				else if(y==10)
				{
					x=2;
					y=0;
				}
			}else if(x==1)
			{
				if(y==3)
				{
					x=2;
					y=3;
				}
				else if(y==6)
				{
					x=0;
					y=15;
				}
			}else {
				if(y==6)
				{
					x=0;
					y=20;
				}
			}
		}else//백도가 나올때
		{
			if(x==0)
			{
				if(y==0)
				{
					y=20;
				}
				else if(y==-1)
				{
					y=19;
				}
			}else if(x==1)
			{
				if(y==0)
				{
					x=0;
					y=10;
				}
			}else {
				if(y==0)
				{
					x=0;
					y=5;
				}
			}
		}
	}
	void setpoint(int a)
	{
		point += a;//업었을때 업힌거 갯수
	}
}
