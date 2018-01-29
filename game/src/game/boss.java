package game;

public class boss extends 에픽 {
	
	float 갑옷;
	

	public boss() {
		공격력=200;
		경험치2=100;
		골드=1000000;
		최대mp=1000;
		최대hp=20000;
		hp=20000;
		mp=1000;		
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
	public void 체력회복() {
		System.out.println("			보스가 체력을 회복합니다.");
		int 회복량;
		회복량 = (int) (0.3*hp);
		시간지연();
		시간지연();
		System.out.println("			보스의 체력이 "+회복량 +"만큼 회복하였습니다.");
	}
	
	public void 낮공격하다() {
		System.out.println("				적이 공격을 합니다.");
		int 딜;
		딜 =(int) 공격력;
		시간지연();
		System.out.println("				"	+딜+ "의 피해를 입었습니다.");
	}
	
	public void 밤공격하다() {
		System.out.println("				적이 공격을 합니다.");
		int 딜;
		딜 =(int)(1.2*공격력);
		시간지연();
		System.out.println("				"+딜+ "의 피해를 입었습니다.");
	}
}


