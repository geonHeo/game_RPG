package game;

public class 일반 extends 몬스터 {
	
	
	
	
	
	
	public 일반() {
		공격력=5;
		경험치2=51;
		골드=300;
		최대mp=10;
		
	}
	public void 시간지연() {
		Thread 시간지연 = new Thread();
		시간지연.start();
		
				
		while(true) {
			try {	
				시간지연.join();
				Thread.sleep(300);						
				break;
			}catch(Exception e1) {
			}					
		}					
	}
	public void 낮공격하다() {
		System.out.println("					적이 공격을 합니다.");
		시간지연();
		int 딜;
		딜 =(int) 공격력;
		System.out.println("					"+딜+ "의 피해를 입었습니다.");
	}
	
	public void 밤공격하다() {
		System.out.println("					적이 공격을 합니다.");
		시간지연();
		int 딜;
		딜 =(int)(1.2*공격력);		
		System.out.println("					"+딜+ "의 피해를 입었습니다.");
	}			
}
