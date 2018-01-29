package game;

public class 문지기 extends 에픽 {

	int hp = 3000;
	int 공격력 = 200;
	int 최대mp = 100;
	int 골드 = 3000;
	int 최대hp = 3000;
	int level= 5;
	int 경험치 = 100;
	int dex= 10;
	
	public 문지기() {
		levelup();
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
		System.out.println("				적이 공격을 합니다.");
		시간지연();
		int 딜;
		딜 =(int) 공격력;
		System.out.println("				"+딜+ "의 피해를 입었습니다.");
	}
	public void 밤공격하다() {
		System.out.println("				적이 공격을 합니다.");
		시간지연();
		int 딜;
		딜 =(int) (1.2*공격력);
		System.out.println("				"+딜+ "의 피해를 입었습니다.");
	}
	
	public void 폭발() {
		System.out.println("				적이 죽으며 함께 자폭하려 합니다.");
		시간지연();
		시간지연();
		int 폭발;
		폭발 = (int)4*공격력;
		System.out.println("				"+폭발+"의 피해를 입었습니다.");
	}
	public void levelup() {
		공격력=공격력+(int)(0.1*공격력*level);
		최대hp=최대hp+(int)(0.1*최대hp*level);
		hp=최대hp;
		최대mp=최대mp+(int)(0.1*최대mp*level);
		mp=최대mp; 
	}
}
