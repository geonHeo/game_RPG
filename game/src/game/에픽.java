package game;

public class 에픽 extends 몬스터{
	
	
	float 갑옷;
	

	
	public void 낮공격하다() {
		System.out.println("적이 공격을 합니다.");
		int 딜;
		딜 =(int) 공격력;
		System.out.println(딜+ "의 피해를 입었습니다.");
	}
	
	public void 밤공격하다() {
		System.out.println("적이 공격을 합니다.");
		int 딜;
		딜 =(int)(1.2*공격력);		
		System.out.println(딜+ "의 피해를 입었습니다.");
	}
}
