package algorithm;
import java.util.ArrayList;

public class QuickSort {
	/* 퀵정렬은 분할 정복 알고리즘 중 하나로 매우빠른 수행속도를 자랑하며
	 * 불안정한 정렬에 속함. 합병 정렬과 달리 리스트를 비균등하게 분할함.
	 * */
	/* 하나의 값(피벗)을 지정해서 배열을 반으로 나눈다.
	 * 나누기 전에 피벗을 반의 기준이 되도록 피벗을 제외한 나머지 배열 값을 비교
	 * 피벗은 0 인덱스 위치에 있는 값으로 지정
	 *  */
	Object pivot;
	Object temp;
	
	QuickSort(ArrayList array, int index){
		
	}
	ArrayList intArrayCompare(ArrayList array) {
		pivot = array.get(0);
		for(int i = 1; i<array.size();i++) {
			if((int)array.get(i)>(int)array.get(array.size())) {
				temp = array.get(i);
				array.add(i, array.get(array.size()));
				array.add((int)array.get(array.size()), temp);
			}
			else if((int)array.get(i)==(int)array.get(array.size())) {
				if((int)pivot>(int)array.get(i)) {
					
				}
			}
		}
		return array;
	}
}
