package yot;

import java.util.Random;
import java.util.ArrayList;

public class Player {
	private int id;
	private ArrayList<Piece> pieces;
	//private ArrayList<Piece> ghost;
	private int point;
	private int pieceNum;//아직 윷판에 안나오고 대기중인 말 수
	
	public Player(int id, int pieceNum){
		this.id = id;
		this.pieces = new ArrayList<Piece>();
		//this.ghost = new ArrayList<Piece>();
		this.point =0;
		this.pieceNum = pieceNum;
	}
	public ArrayList<Piece> getPiece(){
		return this.pieces;
	}
	public int getPoint()
	{
		return this.point;
	}
	public int getPieceNum(){
		return this.pieceNum;
	}
	public int createPiece(){
		if(pieceNum>0)
		{
			pieces.add(new Piece());//판 위에 말 올림
			pieceNum--;
			System.out.println("말 생성 완료");
			return 1;
		}
		return 0;
	}
	public int throwYot(){
		Random r = new Random();
		int act=0;
		act = r.nextInt(6)-1;
		if(act == 0)
			act = 5;
		return act;
	}
	public String playerPiece(){
		String s="";
		for(Piece piece : pieces)
		{
			s += "<남말:"+pieceNum+"포:"+point+">("+piece.getX()+","+piece.getY()+","+piece.getPoint()+")";
		}
		return s;
	}
	/*public void wheremove(int posX, int posY, int active) {//판위에 올라갈 수 있는 말들의 갈 수 있는 위치 출력
	
		//ghost.addAll(mal);
		//일단 보류하고 만들자
	}*/
	
	public int move(int x, int y, int active) {  //(말 위치, 도개걸윷 정보를 보내주면) 무슨 말을 움직일까요?		
		if(pieces.size()!=0)
		{
			for(Piece piece : pieces)
			{
				if(piece.getX()==x && piece.getY()==y)//(말 위치에 있는 말을 찾고 도개걸윷 만큼 이동시킴)
				{
					piece.setPos(active);
					//ghost.removeAll(ghost);
					//ghost.addAll(mal);
				}
			}
			return checkUpda();
		}
		return 0;
	}
	public int checkCatch(int positionx, int positiony){
		for(Piece piece : pieces)
		{
			if(piece.getX()==positionx && piece.getY()==positiony)
			{
				System.out.println(piece.getPoint()+"개 잡혔다");
				pieceNum += piece.getPoint();
				pieces.remove(piece);
				return 1;
			}
		}
		return 0;
	}
	public int checkEnable(int posX, int posY) { //해당 위치에 말이 있나 없나
		int i=0;
		for(Piece piece : pieces){
			if(piece.getX()==posX && piece.getY()==posY){
				return i;
			}
			i++;
		}
		return -1;
	}
	public int checkPiecein(){
		System.out.println("P "+id+" 말 들어갔는지 확인");
		for(Piece piece : pieces){
			if(piece.getY()>20) {
				point += piece.getPoint();
				System.out.println("말이 "+piece.getPoint()+"개 들어옴 현재 포인트"+point);
				pieces.remove(piece);
				return 1;
			}
		}
		return 0;
	}
	public int checkUpda(){
		for(int p=0; p<pieces.size(); p++)
		{
			for(int q=p+1; q<pieces.size(); q++)
			{
				if(pieces.get(p).getX()==pieces.get(q).getX() && pieces.get(p).getY()==pieces.get(q).getY())//같은 위치에 말이 있다면
				{
					System.out.println("업힘 발생함");
					pieces.get(p).setPoint(pieces.get(q).getPoint());//q번째 말이 업은 만큼 p번째 말이 업음
					pieces.remove(q);
					return 1;
				}
			}
		}
		return 0;
	}
}