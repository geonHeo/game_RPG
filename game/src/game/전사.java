package game;

public class 전사 extends 캐릭터 {


	public double 광기=1;
	public int 경험치1=0;
	public int 골드=1000;
	public int 공격력= 10;
	public int level=1;
	public int 최대hp=100;
	public int 최대mp=10;
	public int attackbuff_전사;
	public int dex=10;
	int 포만감;
	
	public 전사() {
		if(hp>최대hp) {
		hp=최대hp;
		}
		if(mp>최대mp) {
		mp=최대mp;	
		}
		포만감=100;
	}

	public void 시간지연() {
		Thread 시간지연 = new Thread();
		시간지연.start();
		
				
		while(true) {
			try {	
				시간지연.join();
				Thread.sleep(500);						
				break;
			}catch(Exception e1) {
			}					
		}					
	}
	
	public void 낮강타() {
		int 딜 = (int)((0.2*공격력)+(1.5*공격력*광기));
		mp=mp-1;
		광기up();
		시간지연();
		System.out.println("	"+딜+"의 공격을 가했습니다.");
		if(광기<=1.5) {
			System.out.println("	광기가 0.1 올랐습니다. 	현재 광기:"+this.광기);
		} else {
			System.out.println("				현재광기: MAX");
		}				
	}
	public void 밤강타() {
		int 딜 = (int)(1.5*공격력*광기);
		mp=mp-1;
		광기up();
		시간지연();
		System.out.println("	"+딜+"의 공격을 가했습니다.");
		if(광기<=1.5) {
			System.out.println("	광기가 0.1 올랐습니다. 	현재 광기:"+this.광기);
		} else {
			System.out.println("				현재광기: MAX");
		}				
	}
	public void 광기up() {
				if(광기<1.5) {
					 광기 = 광기 + 0.10;
					}
	}

	public void 광기초기화() {
		광기=1;
	}
	

	
	public void 낮공격하다() {
		시간지연();
		int 딜 = (int)((0.2*공격력)+(광기*공격력));
        System.out.println("	"+딜+"의 공격을 가했습니다.");
		광기up();
		if(광기<=1.5) {
			System.out.println("	광기가 0.1 올랐습니다. 	현재 광기:"+this.광기);
		} else {
			System.out.println("					현재광기: MAX");
		}	
		시간지연();
	}
	public void 밤공격하다() {
		시간지연();
		int 딜 = (int)(광기*공격력);
        System.out.println("	"+딜+"의 공격을 가했습니다.");
		광기up();
		if(광기<=1.5) {
			System.out.println("	광기가 0.1 올랐습니다. 	현재 광기:"+this.광기);
		} else {
			System.out.println("					현재광기: MAX");
		}	
		시간지연();
	}
	public void levelup() {
		if(경험치1>=100) {	
			경험치1=경험치1-100;
			공격력=공격력+(int)(0.2*공격력*level);
			최대hp=최대hp+(int)(0.2*최대hp*level);
			hp=hp+(int)(0.2*최대hp);
			최대mp=최대mp+(int)(0.2*최대mp*level);
			mp=mp+(int)(0.2*최대mp); 
			level=level+1;
			시간지연();
			System.out.println("레벨이 1 올랐습니다. \n레벨이 "+level+" 이 되었습니다.");								
		}
	}
	public void levup() {
		
		공격력=공격력+(int)(0.2*공격력*level);
		최대hp=최대hp+(int)(0.2*최대hp*level);
		hp=hp+(int)(0.2*최대hp);
		최대mp=최대mp+(int)(0.2*최대mp*level);
		mp=mp+(int)(0.2*최대mp);
		level=level+1;
	}
		
	
	
	
	
}
