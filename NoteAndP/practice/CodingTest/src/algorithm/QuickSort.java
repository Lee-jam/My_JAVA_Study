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
	ArrayList array;
	private Object pivot;
	private Object temp;
	private int size;
	
	
	public QuickSort(ArrayList array){
		pivot = array.get(0);
		this.array = array;
		size = array.size();
	}

	void swap(int left, int right){
		temp = array.get(left);
		array.set(left, array.get(right));
		array.set(right,temp);
		
	}
	
	public ArrayList intArrayCompare(ArrayList array) {
		for(int index=1;index<size/2+1;index++) {
			System.out.println("index : "+index);
//			System.out.println("+size-index :"+(size-index));
			if(index>=size-index) {
				swap(0,size-index);
			}
			if((int)pivot<(int)array.get(index)&&(int)pivot>=(int)array.get(size-index)) {
				swap(index,array.size()-index);
				System.out.println(array);
			}
			else if((int)pivot>=(int)array.get(index)&&(int)pivot>=(int)array.get(size-index)) {
				System.out.println("걸림"+1);
				for(int index2=index;index2<size/2+1;index2++) {
					System.out.println("들어옴");
					if((int)pivot<(int)array.get(index2+1)&&(int)pivot>=(int)array.get(size-index)) {
						swap(index2+1,array.size()-index);
						System.out.println("변경 : "+array);
						continue;
					}
				}
			}
			else{
				System.out.println("안바뀜 : "+array);}
		}
		return array;
	}
}
