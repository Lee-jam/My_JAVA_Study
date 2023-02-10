package review3;

import java.util.Scanner;

public class PlayBall {
	public static void main(String[] args) {
		Scanner ans = new Scanner(System.in);
		
		System.out.print("맞출 정답의 길이를 입력하세요. : ");
		int ansNum = ans.nextInt();
		
		re:while(true) {
			BaseballGame bg = new BaseballGame(ansNum);
			System.out.println();
			bg.CreateAnswer();
			
			while (true) {
				bg.playBall();
				if(bg.strike ==bg.answer.length) {
					System.out.println("전부 맞췄습니다. 계속 하시겠습니까? 길이 입력 : (3보다 작으면 종료)");
					ansNum = ans.nextInt();
					if(ansNum >= 3) continue re;
					else break;
				}
			}
		if(ansNum <3) {
			System.out.println("게임을 종료합니다.");
			break;	
		}
		}
	}
}
