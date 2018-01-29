package game;

import java.util.Random;
import java.util.Scanner;

public class 게임  {	
	int n;
	Scanner scan;
	전사 warrior;
	궁수 archer;
	마법사 magician;
	체력포션 hp포션;
	마나포션 마나포션;
	엘릭서 엘릭서;
	일반 mon;
	문지기 mon1;
	boss mon2;
	int 독뎀;	
	int attackbuff_전사;//낮밤에 전사의 공격력 영향 주기 위한 변수
	int attackbuff_mon;//낮밤에 몬스터 공격력 영향 주기 위한 변수
	int 턴수;// 턴 수를 기억해서 사용하기 위한 변수
	int 보스열쇠;// 보스의 방에 들어가기 위해 문지기를 제거해야 보스의 방에 들어갈 수 있게 만드는 변수
	int 문지기도주;
	
public 게임() {		
		//선언 위치를 잘 설정해야 메뉴 선택이 제대로 돌아간다	
		hp포션 = new 체력포션();
		마나포션 = new 마나포션();
		엘릭서 = new 엘릭서();
		
			while(true) {
				int 게임진행 = init();
				if(게임진행 == 1) {					
					break;
				}else if (게임진행 == 2) {		
				}else {				
					System.out.println("게임을 종료합니다.");
					System.exit(0);
				}
			}
				
		while(true) {
				int 맵이동 = menu1();
				if(맵이동 == 1) {
					menu1();							
				} else {
					System.out.println("게임을 종료합니다.");
					break;		
				} 			
		}		
}
	
	public void 밤낮() {
		Thread th1;			
		Runnable r = new Runnable() {
			@Override
			public void run() {
			int i = 1;
				while(true) {	
					if(n==0) {
							일시정지();	
					}					
						if(i==1) {
							System.out.println(" 										낮이 되었습니다. 영웅의 공격력이 올라갑니다. ");
							i=i--;
							attackbuff_전사=1;
							attackbuff_mon=0;							
						} else {
							System.out.println(" 										밤이 되었습니다. 몬스터의 공격력이 올라갑니다. ");
							i=i+2;
							attackbuff_mon=1;
							attackbuff_전사=0;
						}	
						try {
						Thread.sleep(20000);
						i=i/2;										
							} catch(Exception e) {											
						}																	
				}
			}
		};		
		th1= new Thread(r);
		th1.start();
	}
	
	public void 날짜() {
		Thread th2;
		Runnable r2 = new Runnable() {
			int day=0;
			@Override
			public void run() {				
				while(true) {
					if(n==0) {
						일시정지();	
					}
						try {
							if(day==29) {
							System.out.println(" 										!!! boss 도주 당일입니다. 하루밖에 남지 않았습니다. !!!");						
							} else if(day==30) {
							System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n미션에 실패했습니다.\n boss가 해외로 도주했습니다.\n게임을 종료합니다.");
							System.exit(0);
							}
							day=day+1;
							if(day>=2) { 
								System.out.println(" 										하루가 지났습니다.");						
							}
							System.out.println(" 										오늘은 " +day +" 일입니다."+ "			D-"+(30+(-1)*day));
							Thread.sleep(40002);						
						}catch(Exception e) {
						}					
				}				
			}
		};
		th2= new Thread(r2);
		th2.start();
	}
	
	public void 시간지연() { // warrior의 시간 지연 메소드를 사용할 수 있었음 굳이 두개 만들 필요없었음.
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
	
	public void 일시정지() {
		Thread 일시정지 = new Thread();
		일시정지.start();						
		while(true) {
			try {	
				일시정지.join();
				n = Integer.parseInt(scan.nextLine());
				if(n==1) {
					System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
				break;
				} else {}
			}catch(Exception e1) {
			}					
		}					
	}
	
	public void 독데미지() { 	//20%의 확률로 독 공격 		몬스터 딜의 40%가 4초마다 발생 총 10번의 피해량 발생
		int 확률;
		Random random = new Random();
		확률 = random.nextInt(10);			
		Thread th4;
		Runnable r4 = new Runnable() {				
		@Override
		public void run() {	
			독뎀=1;
			while(true) {
				try {
					if(n==0) {
						일시정지();	
					}
						if(독뎀<11) {
							warrior.hp=warrior.hp-(int)(0.4*mon.공격력);
							System.out.println(" 										독에 의해"+(int)(0.4*mon.공격력)+"의 피해를 입었습니다.");
							Thread.sleep(4000);							
						} else {
							System.out.println(" 										상태 이상이 회복되었습니다.");
							독뎀=0;
							break;
						}															
				}catch(Exception e1) {
				}
				독뎀=독뎀+1;
			}				
		}
	};
		th4= new Thread(r4);
			if(확률<3) { //30%확률로 중독 상태 일반 몬스터 공격력의 40%가 딜
				if(독뎀==0) {
				System.out.println(" 		중독상태가 되었습니다. 독 공격을 받습니다.");
				th4.start();				
				}			
			} else {	
			}	
	}
	
	public void 전사상태확인() { //전사가 중독 또는 턴을 넘기지 않은 상태에서 죽었을 경우를 추적해주는 쓰레드
		Thread th5;
		Runnable r5 = new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(2000);
						if(warrior.hp<=0) {
							System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n============================================\n캐릭터가 죽었습니다.\n게임을 종료합니다.");
							System.exit(0);
						}
					}catch(Exception e) {						
					}
				}
			}
		};
		th5 = new Thread(r5);
		th5.start();				
	}
	
	public void 보스리얼타임() { 	//보스는 턴을 받았을 때의 공격과 스킬을 이용해 랜덤한 시간(3~5초)간격으로 캐릭터를 공격		
		Random random = new Random();		
		Thread th6;			
		Runnable r6 = new Runnable() {				
		@Override
		public void run() {	
			
			while(true) {
				
				try {
					if(n==0) {
						일시정지();	
					}	
					int 공격력확률;
					int 공격시간확률;
					공격력확률 = random.nextInt(5)+2;	
					공격시간확률 = random.nextInt(4)+2;	
					if(mon2.hp<=0) {
						break;
					}
					if(mon2.mp>=20) { 
						warrior.hp=warrior.hp-(int)(mon2.공격력+(공격력확률*mon2.공격력));
						System.out.println("						boss가 빈틈을 찾아 공격했습니다.(mp: -20) ");
						System.out.println("						"+(int)(mon2.공격력+(공격력확률*mon2.공격력))+"의 피해를 입었습니다.");
						mon2.mp=mon2.mp-20;
						Thread.sleep(공격시간확률*1000);
					}else {
						System.out.println("						boss가 빈틈을 찾아 공격할 여력이 남지 않았습니다.");
						break;
					}
				} catch(Exception e1) {
				}
			}				
		}
	};
		th6 = new Thread(r6);
		th6.start();	
	}
	
	public void 문지기리얼타임() { //문지기는 자신의 dex에 비례해서 공격속도가 빨라지고 자동으로 캐릭터를 공격할 수 있음	
		Thread th7;
		Runnable r7 = new Runnable() {			
			@Override
			public void run() {
				while(true) {
					try {												
 						Thread.sleep((12-mon1.dex)*1000);												
						if (문지기도주 != 0) {
							break;
						}
						if(warrior.hp<=0) {
							break;
						}
						if(mon1.hp<=0) {
							break;
						} else {
						warrior.hp=warrior.hp-(int) ((0.5*attackbuff_mon*mon1.공격력)+ mon1.공격력);
						System.out.println("						문지기가 공격합니다.");
						System.out.println("						"+(int) ((0.5*attackbuff_mon*mon1.공격력)+ mon1.공격력)+"의 피해를 입었습니다.");						
						}
						}catch(Exception e){			
					}
				}		
			}
		};
		th7 = new Thread(r7);
		th7.start();
	}
	
	public void 휴식() {		
		독뎀=11;		
		시간지연();
		System.out.println("\n\n 마을에서 휴식을 취합니다. 모든 상태 이상이 치유됩니다.");
		시간지연();
		시간지연();
		시간지연();
		System.out.println(" hp와 mp를 회복합니다." +  
							"				보유 골드:" + warrior.골드);	
		시간지연();
		시간지연();
		시간지연();
		시간지연();
		독뎀=0;
		warrior.hp=warrior.최대hp;
		warrior.mp=warrior.최대mp;
	}
	
	public void 포션체력회복() {
		warrior.hp=warrior.hp+100;
		if(warrior.hp>warrior.최대hp) {
			warrior.hp=warrior.최대hp;
		}
		if(warrior.mp>warrior.최대mp) {
			warrior.mp=warrior.최대mp;	
		}		
	}
	public void 마나회복() {
		warrior.mp=warrior.mp+30;
		if(warrior.hp>warrior.최대hp) {
			warrior.hp=warrior.최대hp;
		}
		if(warrior.mp>warrior.최대mp) {
			warrior.mp=warrior.최대mp;	
		}		
	}
	public void 엘릭서회복() {
		warrior.mp=(int)(warrior.최대mp*0.5+warrior.mp);
		warrior.hp=(int)(warrior.최대hp*0.5+warrior.hp);
		if(warrior.hp>warrior.최대hp) {
			warrior.hp=warrior.최대hp;
		}
		if(warrior.mp>warrior.최대mp) {
			warrior.mp=warrior.최대mp;	
		}	
	}
	
	
	public int init() { //게임 도입부 
		scan = new Scanner(System.in);
		
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n		Boss를 잡아라 \n");
		System.out.println("	게임을 시작합니다");//내일 할 일 
		System.out.println(" 1.게임시작		2.게임 설명		3.종료");
		System.out.print("입력: ");
		n = Integer.parseInt(scan.nextLine());
			
		if(n==1) {	//캐릭터 선택 ->캐릭터 생성하여 캐릭터 위치 이동 메뉴 시작
			System.out.println("\n	캐릭터를 선택하세요");
			System.out.println(" 1.전사		");		
			System.out.print("입력: ");
			n = Integer.parseInt(scan.nextLine());
			if(n==1) {
				System.out.println("	전사를 선택하였습니다.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
				시간지연();
				시간지연();
				System.out.println("\n 										당신은 boss를 물리치기 위한 여행을 시작합니다."
						+ "\n 										! mission ! 30일 내에 boss를 제거해야합니다.");
				warrior = new 전사();
				밤낮();
				날짜();
				전사상태확인();
				return 1;	 // menu1 메소드로 이동
			
			} else if(n==2) {
			  System.out.println("	궁수를 선택하였습니다.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			  System.out.println("\n 										당신은 boss를 물리치기 위한 여행을 시작합니다."
						+ "\n 										30일 내에 boss를 제거해야합니다.");
			  archer = new 궁수();
			    밤낮();			  
			    날짜();
			  	return 1;
			
			} else {
				System.out.println("	마법사를 선택하였습니다.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
				System.out.println("\n 										당신은 boss를 물리치기 위한 여행을 시작합니다."
						+ "\n 										30일 내에 boss를 제거해야합니다.");
				magician = new 마법사();
				밤낮();
				날짜();
				return 1;				
			}
		} else if(n==2) {
				System.out.println("\n30일 내에 해외로 도주하려는 boss를 제거해야 합니다."
						+ "\n캐릭터를 선택하고 몬스터를 잡아 레벨을 올리세요."
						+ "\n충분히 성장하여 boss를 제거하면 게임을 clear하게 됩니다."
						+ "\n tip. 전사는 '광기'특성을 가지고 있어 공격을 할수록 데미지가 강해집니다."
						+ "\n tip. '낮'에는 영웅의 공격력이, '밤'에는 몬스터의 공격력이 강해집니다."
						+ "\n\n !!!!! 게임시간 30일이내에 boss를 잡지 못하면 미션에 실패하여 종료됩니다. !!!!!\n");
				return 2; //게임 메뉴 다시 선택		
		} else {
		return 9; // 종료
		}
	}
	
	public int menu1() { //캐릭터 위치 이동 메뉴
						 //현재 전사만 구현된 상태로 if문과 instanceof 궁수or마법사로 다른 직업의 캐릭터도 구현 가능
		scan= new Scanner(System.in);
		System.out.println("\n\n가고 싶은 곳을 선택해주세요");
		System.out.println(" ______________________________________________________________");
		System.out.println("|  1.마을  |  2.상점  |  3.일반몬스터던전  |  4.boss의 문 앞  |  5.boss의 방  | 0.일시정지");
		System.out.println(" ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		System.out.print("입력: ");
		n = Integer.parseInt(scan.nextLine());
		
		
		if(n==1) {			
			휴식();
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		} else if(n==2) {
			System.out.println("상점에서 원하는 상품군을 선택합니다.			내 소지금:"+warrior.골드+"g\n"
								+ "1.체력포션 	1000g			(체력을 100회복) "
								+ "\n2.마나포션 	500g			(mp를 30회복)"
								+ "\n3.엘릭서 		5000g			(모든 hp와 mp를 절반 회복)"
								+ "\n4.밖으로나가기");
			System.out.print("입력: ");	
			n = Integer.parseInt(scan.nextLine());
				if(n==1) {					
						System.out.println("체력포션을 얼마나 구입하시겠습니까? ( 0  밖으로 나가기 )");
						System.out.print("입력: ");
						n = Integer.parseInt(scan.nextLine());
						if(n==0) {
							return 1;
						} else {
							if(warrior.골드>=hp포션.체력포션가격*n) {
								warrior.골드= warrior.골드- (hp포션.체력포션가격 * n);
								hp포션.체력포션 =hp포션.체력포션 + n;
								시간지연();
								System.out.println("\n	체력포션을 " + n +" 개 구매했습니다.");
								System.out.println("	"+hp포션.체력포션 +"개의 체력포션을 소지하고 있습니다.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
							} else {
								시간지연();
								System.out.println("\n	골드가 모자라 구매할 수 없습니다.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
								return 1;
							}
						}											
				} else if(n==2){
					System.out.println("마나포션을 얼마나 구입하시겠습니까? ( 0  밖으로 나가기 )");
					System.out.print("입력: ");
					n = Integer.parseInt(scan.nextLine());
					if(n==0) {
						return 1;
					} else {
						if(warrior.골드>=마나포션.마나포션가격*n) {
							warrior.골드= warrior.골드- (마나포션.마나포션가격 * n);
							마나포션.마나포션 =마나포션.마나포션 + n;
							시간지연();
							System.out.println("\n	마나포션을 " + n +" 개 구매했습니다.");
							System.out.println("\n	"+마나포션.마나포션 +"개의 마나포션을 소지하고 있습니다.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
						} else {
							시간지연();
							System.out.println("\n	골드가 모자라 구매할 수 없습니다.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
							return 1;
						}
					}			
				} else if(n==3) {
					System.out.println("엘릭서를 얼마나 구입하시겠습니까? ( 0  밖으로 나가기 )");
						System.out.print("입력: ");
						n = Integer.parseInt(scan.nextLine());
						if(n==0) {
						return 1;
						} else {
						if(warrior.골드>=엘릭서.엘릭서가격*n) {
							warrior.골드= warrior.골드- (엘릭서.엘릭서 * n);
							엘릭서.엘릭서 =엘릭서.엘릭서 + n;
							시간지연();
							System.out.println("\n	엘릭서를 " + n +" 개 구매했습니다.");
							System.out.println("\n	"+엘릭서.엘릭서 +"개의 엘릭서를 소지하게 되었습니다.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
						} else {
							시간지연();
							System.out.println("\n	골드가 모자라 구매할 수 없습니다.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
							return 1;
						}
						}			
				}
			return 1;
		} else if(n==3) {		//일반 몬스터와 싸움					
				mon= new 일반();
				warrior.광기초기화();				
				
				System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n일반 몬스터 던전에 입장했습니다.\n"
									+ " 'lv.1 몬스터'를 만났습니다.\n 싸움을 시작합니다.");				
			while(true) {
				if(mon.hp<=0) {
					시간지연();
					System.out.println("==================전투에서 승리했습니다.==================");
					warrior.경험치1=warrior.경험치1+mon.경험치2;
					warrior.골드=warrior.골드+mon.골드;
					System.out.println("소지금이 "+warrior.골드+"이 되었습니다.\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");		
					warrior.levelup();
					시간지연();
					시간지연();					
					break;					
				}
				else if(warrior.hp<=0) {
					System.out.println("전투에서 패배했습니다.");
					시간지연();
					시간지연();
					break;
				}
					시간지연();
					System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n LV."+warrior.level+" 전사 					LV."+mon.level+" 몬스터");
					System.out.println("hp:"+warrior.hp+"/"+warrior.최대hp+"					적hp:"+ mon.hp+"/"+mon.최대hp); 
					System.out.println("mp:"+warrior.mp+"/"+warrior.최대mp+" 					적mp:"+ mon.mp+"/"+mon.최대mp);
					
					System.out.println("\n 1.일반공격	2.강타		3.아이템 사용	4.도망간다");
					System.out.print("입력: ");
					n = Integer.parseInt(scan.nextLine());
					
					if(n==1) {
						if(attackbuff_전사==1) {							
						System.out.println("\n\n\n  일반 공격을 합니다.");												
						mon.hp = (int)mon.hp - (int)((0.2*attackbuff_전사*warrior.공격력)+(warrior.공격력* warrior.광기));
						warrior.낮공격하다();						
						mon.낮공격하다();
						독데미지();
						warrior.hp =(int) warrior.hp - (int) ((0.2*attackbuff_mon*mon.공격력)+ mon.공격력);
						} else {
						System.out.println("\n\n\n  일반 공격을 합니다.");																	
						mon.hp = (int)mon.hp - (int)((0.2*attackbuff_전사*warrior.공격력)+(warrior.공격력* warrior.광기));
						warrior.밤공격하다();					
						mon.밤공격하다();
						독데미지();
						warrior.hp =(int) warrior.hp - (int) ((0.2*attackbuff_mon*mon.공격력)+ mon.공격력);
						} 
					}else if(n==2) {				
						if(attackbuff_전사==1) {
							if(warrior.mp>0) {
							System.out.println("\n\n\n강타를 사용합니다.");						
							mon.hp= (int)mon.hp - (int)((0.2*attackbuff_전사*warrior.공격력)+(1.5*warrior.공격력* warrior.광기));
							warrior.낮강타();
							mon.낮공격하다();
							독데미지();
							warrior.hp =(int) warrior.hp - (int) ((0.2*attackbuff_mon*mon.공격력)+ mon.공격력);
							}else {
								System.out.println("mp가 모자라 강타를 사용할 수 없습니다.\n");
							}
						} else {
							if(warrior.mp>0) {
								System.out.println("\n\n\n강타를 사용합니다.");								
								mon.hp= (int)mon.hp - (int)((0.2*attackbuff_전사*warrior.공격력)+(1.5*warrior.공격력* warrior.광기));
								warrior.밤강타();
								mon.밤공격하다();
								독데미지();
								warrior.hp =(int) warrior.hp - (int) ((0.2*attackbuff_mon*mon.공격력)+ mon.공격력);
								}else {
								System.out.println("mp가 모자라 강타를 사용할 수 없습니다.\n");
								}						
						}
					}else if(n==3) {
						System.out.println("1.체력포션		2.마나포션		3.엘릭서 		4.되돌아가기");
						System.out.print("입력: ");
						n = Integer.parseInt(scan.nextLine());
						if(n==1) {
							if(hp포션.체력포션<=0) {
							System.out.println("사용할 아이템이 존재하지 않습니다.");
							} else {
							hp포션.체력포션=hp포션.체력포션-1;
							포션체력회복();
							hp포션.hp포션사용();
							}
						} else if(n==2) {
							if(마나포션.마나포션<=0) {
							System.out.println("사용할 아이템이 존재하지 않습니다.");
							} else {
							마나포션.마나포션=마나포션.마나포션-1;
							마나회복();
							마나포션.마나포션사용();
							}							
						} else if(n==3) {
							if(엘릭서.엘릭서<=0) {
							System.out.println("사용할 아이템이 존재하지 않습니다.");
							}else {
							엘릭서.엘릭서=엘릭서.엘릭서-1;
							엘릭서회복();
							엘릭서.엘릭서사용();
							}
						}
					
					} else if(n==4) {
						System.out.println("도망갑니다.");
						warrior.도망가다();
						break;
					}
	
				} // while End.
			} else if(n==4) {	//문지기와 싸움
				문지기도주=0;//문지기를 도주한 뒤에 다시 문지기와 싸울 수 있게 만드는 값
				mon1= new 문지기();
				warrior.광기초기화();				
				
				System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n boss의 문에 입장했습니다.\n"
									+ " 'lv.5 문지기'를 만났습니다.\n 싸움을 시작합니다.");
				시간지연();
				문지기리얼타임();
			while(true) {
				if(mon1.hp<=0) {
					mon1.폭발();
					시간지연();
					warrior.hp=warrior.hp-(4*mon1.공격력);	
					if(warrior.hp<=0) {
						break;
					} else {	
					시간지연();
					System.out.println("==================전투에서 승리했습니다.==================");
					warrior.경험치1=warrior.경험치1+mon1.경험치2;
					warrior.골드=warrior.골드+mon1.골드;
					System.out.println(" 소지금이 "+warrior.골드+"이 되었습니다.");
					warrior.levelup();			
					시간지연();
					System.out.println("\n boss의 방으로 들어갈 수 있는 열쇠를 획득했습니다.\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
					보스열쇠=1;
					시간지연();				
					break;	
					}				
				}
				else if(warrior.hp<=0) {
					System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n============================================\n전투에서 패배했습니다.");
					break;
				}
					시간지연();
					System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n LV."+warrior.level+" 전사 						LV."+mon1.level+" 문지기");
					System.out.println("hp:"+warrior.hp+"/"+warrior.최대hp+"					적hp:"+ mon1.hp+"/"+mon1.최대hp); 
					System.out.println("mp:"+warrior.mp+"/"+warrior.최대mp+" 					적mp:"+ mon1.mp+"/"+mon1.최대mp);
					System.out.println("\n 1.일반공격	2.강타		3.아이템 사용	4.도망간다");
					System.out.print("입력: ");
					n = Integer.parseInt(scan.nextLine());
					
					if(n==1) {
						if(attackbuff_전사==1) {
							System.out.println("\n\n\n일반 공격을 합니다.");							
							mon1.hp = (int)mon1.hp - (int)((0.2*attackbuff_전사*warrior.공격력)+(warrior.공격력* warrior.광기));
							warrior.낮공격하다();
							} else {
							System.out.println("\n\n\n일반 공격을 합니다.");						
							mon1.hp = (int)mon1.hp - (int)((0.2*attackbuff_전사*warrior.공격력)+(warrior.공격력* warrior.광기));
							warrior.밤공격하다();
							} 
					} else if(n==2) {
						if(attackbuff_전사==1) {
							if(warrior.mp>0) {
							System.out.println("\n\n\n강타를 사용합니다.");							
							mon1.hp= (int)mon1.hp - (int)((0.2*attackbuff_전사*warrior.공격력)+(1.5*warrior.공격력* warrior.광기));
							warrior.낮강타();
							}else {
							System.out.println("mp가 모자라 강타를 사용할 수 없습니다.\n");
							}
						} else {
							if(warrior.mp>0) {
								System.out.println("\n\n\n강타를 사용합니다.");
								mon1.hp= (int)mon1.hp - (int)((0.2*attackbuff_전사*warrior.공격력)+(1.5*warrior.공격력* warrior.광기));
								warrior.밤강타();							
								}else {
								System.out.println("mp가 모자라 강타를 사용할 수 없습니다.\n");
								}	
							}	
					} else if(n==3) {
						System.out.println("1.체력포션		2.마나포션		3.엘릭서 		4.되돌아가기");
						System.out.print("입력: ");
						n = Integer.parseInt(scan.nextLine());
						if(n==1) {if(hp포션.체력포션<=0) {
							System.out.println("사용할 아이템이 존재하지 않습니다.");
						} else {
							hp포션.체력포션=hp포션.체력포션-1;
							포션체력회복();
							hp포션.hp포션사용();
							}
						}else if(n==2) {
							if(마나포션.마나포션<=0) {
							System.out.println("사용할 아이템이 존재하지 않습니다.");
							} else {
							마나포션.마나포션=마나포션.마나포션-1;
							마나회복();
							마나포션.마나포션사용();
							}							
						} else if(n==3) {
							if(엘릭서.엘릭서<=0) {
							System.out.println("사용할 아이템이 존재하지 않습니다.");
							}else {
							엘릭서.엘릭서=엘릭서.엘릭서-1;
							엘릭서회복();
							엘릭서.엘릭서사용();
							}
						}
					
					} else if(n==4) {
						System.out.println("도망갑니다.");
						warrior.도망가다();
						문지기도주=1;
						break;
					}
			
				} // while End.
			}// else if End.
			else if(n==5) {	//boss와 싸움
				if(보스열쇠==1) {
					시간지연();
				mon2= new boss();
				warrior.광기초기화();				
				보스리얼타임();
				System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n boss의 방에 입장했습니다.\n"
									+ " 'lv.??? boss'를 만났습니다.\n 싸움을 시작합니다.\n\n\n\n\n\n\n\n\n\n\n");
				시간지연();
				시간지연();
			while(true) {
				if(mon2.hp<=0) {
					if(warrior.hp<=0) {
						break;
					} else {				
						System.out.println("==================전투에서 승리했습니다.==================");
						warrior.경험치1=warrior.경험치1+mon2.경험치2;
						warrior.골드=warrior.골드+mon2.골드;
						System.out.println("소지금이 "+warrior.골드+"이 되었습니다.");
						warrior.levelup();
						시간지연();
						시간지연();
						시간지연();
						System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n"
							+ "\n==================================================================================================="
							+ "\n==================================================================================================="
							+ "\n=====================================승리하셨습니다. 게임을 클리어했습니다.=================================="
							+ "\n==================================================================================================="
							+ "\n===================================================================================================\n\n\n\n\n\n\n\n\n\n");
						System.exit(0);
					break;	
					}				
				}
				else if(warrior.hp<=0) {
					System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n============================================\n전투에서 패배했습니다.");
					시간지연();
					break;
				}
				
					턴수=턴수+1;  //보스 몬스터의 체력 회복을 위한 변수 증가
					if(턴수==5) {
						mon2.체력회복();
						mon2.hp=mon2.hp+(int)(0.1*mon2.hp);
						턴수=턴수-5;					
					}
					시간지연();
					System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n LV."+warrior.level+" 전사 						LV.??? boss");
					System.out.println("hp:"+warrior.hp+"/"+warrior.최대hp+"					적hp:"+ mon2.hp+"/"+mon2.최대hp); 
					System.out.println("mp:"+warrior.mp+"/"+warrior.최대mp+" 					적mp:"+ mon2.mp+"/"+mon2.최대mp);
					System.out.println("\n 1.일반공격	2.강타		3.아이템 사용	4.도망간다");
					System.out.print("입력: ");
					n = Integer.parseInt(scan.nextLine());
					
					if(n==1) {
						if(attackbuff_전사==1) {
							System.out.println("\n\n\n일반 공격을 합니다.");							
							mon2.hp = (int)mon2.hp - (int)((0.2*attackbuff_전사*warrior.공격력)+(warrior.공격력* warrior.광기));
							warrior.낮공격하다();
							mon2.낮공격하다();
							warrior.hp =(int) warrior.hp - (int) ((0.2*attackbuff_mon*mon2.공격력)+ mon2.공격력);
							} else {
							System.out.println("\n\n\n일반 공격을 합니다.");						
							warrior.밤공격하다();
							mon2.hp = (int)mon2.hp - (int)((0.2*attackbuff_전사*warrior.공격력)+(warrior.공격력* warrior.광기));
							mon2.밤공격하다();
							warrior.hp =(int) warrior.hp - (int) ((0.2*attackbuff_mon*mon2.공격력)+ mon2.공격력);
							} 
					} else if(n==2) {
						if(attackbuff_전사==1) {
							if(warrior.mp>0) {
							System.out.println("\n\n\n강타를 사용합니다.");							
							mon2.hp= (int)mon2.hp - (int)((0.2*attackbuff_전사*warrior.공격력)+(1.5*warrior.공격력* warrior.광기));
							warrior.낮강타();
							mon2.낮공격하다();
							warrior.hp =(int) warrior.hp - (int) ((0.2*attackbuff_mon*mon2.공격력)+ mon2.공격력);
							}else {
							System.out.println("mp가 모자라 강타를 사용할 수 없습니다.\n");
							}
						} else {
							if(warrior.mp>0) {
								System.out.println("\n\n\n강타를 사용합니다.");
								mon2.hp= (int)mon2.hp - (int)((0.2*attackbuff_전사*warrior.공격력)+(1.5*warrior.공격력* warrior.광기));
								warrior.밤강타();
								mon2.밤공격하다();
								warrior.hp =(int) warrior.hp - (int) ((0.2*attackbuff_mon*mon2.공격력)+ mon2.공격력);
								}else {
								System.out.println("mp가 모자라 강타를 사용할 수 없습니다.\n");
								}	
							}	
					} else if(n==3) {
						System.out.println("1.체력포션		2.마나포션		3.엘릭서 		4.되돌아가기");
						System.out.print("입력: ");
						n = Integer.parseInt(scan.nextLine());
						if(n==1) {
							if(hp포션.체력포션<=0) {
							System.out.println("사용할 아이템이 존재하지 않습니다.");
							} else {
							hp포션.체력포션=hp포션.체력포션-1;
							포션체력회복();
							hp포션.hp포션사용();
							}
						}else if(n==2) {
							if(마나포션.마나포션<=0) {
							System.out.println("사용할 아이템이 존재하지 않습니다.");
							} else {
							마나포션.마나포션=마나포션.마나포션-1;
							마나회복();
							마나포션.마나포션사용();
							}							
						} else if(n==3) {
							if(엘릭서.엘릭서<=0) {
							System.out.println("사용할 아이템이 존재하지 않습니다.");
							}else {
							엘릭서.엘릭서=엘릭서.엘릭서-1;
							엘릭서회복();
							엘릭서.엘릭서사용();
							}	
						}
					
					} else if(n==4) {
						System.out.println("boss와의 전투에서는 도망갈 수 없습니다.");
					}
			
				} // while End.
					
				} else {
					System.out.println("\nboss의 방 열쇠가 없어 들어갈 수 없습니다.");			
					시간지연();
					System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
				}
				
				
			} //보스 싸움 종료
				else if(n==0) {
					System.out.println("게임이 일시정지 되었습니다.");
					System.out.println("1을 입력하면 게임으로 돌아갑니다.");	
					System.out.print("입력: ");	
					일시정지();
				}		
				else if(n==10) {								
				while(true) {
					System.out.println("개발자 모드에 입장하셨습니다.\n 1.캐릭터의 레벨 up		2.골드 설정		3.되돌아가기");
					System.out.println("입력 :");
					n = Integer.parseInt(scan.nextLine());
					if(n==1) {				
					warrior.levup();
					System.out.println("캐릭터의 레벨을 올렸습니다. 케릭터의 레벨이 "+warrior.level+"이 되었습니다.");
					warrior.hp=warrior.최대hp;
					warrior.mp=warrior.최대mp;
					} else if (n==2){
						System.out.println("원하는 골드량을 입력하세요");
						System.out.println("입력 :");
						n = Integer.parseInt(scan.nextLine());
						warrior.골드=warrior.골드+n;	
						break;
					} else {
						break;
					}
				}				
			}//else if End.
				
					if(warrior.hp<=0) {
						System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n============================================\n캐릭터가 죽었습니다.\n게임을 종료합니다.");
						System.exit(0);
					}
				
				return 1;
			
	}//menu1 End. 
}

	
	

