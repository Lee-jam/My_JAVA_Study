package algorithm;

import java.util.ArrayList;
import java.util.Arrays;

public class Test {

	public static void main(String[] args) {
		ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(7,9,2,8,4,5,3,10,1,6));
		
		System.out.println(arr);
		QuickSort qs = new QuickSort(arr);
		System.out.println(qs.intArrayCompare(arr));
		
	}

}
