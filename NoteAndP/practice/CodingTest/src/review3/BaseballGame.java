package review3;

import java.util.Scanner;

public class BaseballGame {
	int[] answer;
	int[] inputNumber;
	int i=0;
	int strike;
	int ball;
	int out;
	Scanner input;
	
	public BaseballGame(int level) {
		answer = new int[level];
		inputNumber = new int[level];
		input = new Scanner(System.in);
	}
	//정답 생성
	int[] CreateAnswer() {
		while(true) {
			int a = (int)(Math.random()*10);
			//10 또는 0 삭제
			if(a>=10 || a==0) {
				a=9;
			}
			//중복 확인
			for(int index=0;index<answer.length;index++) {
				if(answer[index] == a) {
					a = (int)(Math.random()*10);
					index =-1;
				}
			}
			//길이 만큼만 추가
			if(i==answer.length)break;
			
			//배열에 입력
			answer[i]=a;
			i++;
			//정답 확인
//			System.out.print(a);
		}
		System.out.println(" 정답 생성 ");
		return answer;
	}
	//답을 하나씩 입력해서 비교
	int[] inputNum() {
		System.out.println(answer.length+"자리의 숫자를 입력하세요 :");
		for(int i=0;i<inputNumber.length;i++) {
			int number = input.nextInt();
			inputNumber[i] = number;		
		}
		return inputNumber;
	}
	//답을 한번에 입력해서 비교
	int[] inputNum2() {
		System.out.println(answer.length+"자리의 숫자를 입력하세요 :");
		//입력 값을 숫자 배열에 넣기 위해 스플릿을 사용하여 스트링 배열 자료형 변수에 넣고 parseInt를 이용하여 인트값으로 변경 후 배열에 삽입
		String[] num = input.next().split("");
		int index = 0;
		for(String str : num) {
			inputNumber[index] =Integer.parseInt(str);
			index++;
		}
		
		return inputNumber;
	}
	//정답과 입력값 비교
	int playBall() {
		inputNum2();
		strike = 0;
		ball = 0;
		for(int i=0;i<answer.length;i++) {
			//같은 인덱스(자리)값 비교해서 맞으면 스트라이크
			if(answer[i] == inputNumber[i]) {
				strike++;
			}
			//다른 인덱스(자리)값 비교해서 맞으면 볼
			for(int j=0;j<inputNumber.length;j++) {
				if(inputNumber[j] == answer[i]&&i!=j) {
					ball++;
				}
			}
		}
		System.out.println(strike+"스트라이크 "+ball+"볼");
		return strike;
	}
	
}
