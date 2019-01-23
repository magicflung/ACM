import java.util.Scanner;

/** 1008
 * @author MUSTACHE
 *
 */
public class Main8 {
	private static final int UP = 6; 
	private static final int DOWN = 4; 
	private static final int STOP = 5; 
	private static int totaltime;
	public static int getTime(int[] arr) {
		// 一开始是从第0层开始的,所以对于数组中的第一个元素一定是上升的
		totaltime = arr[0] * UP + STOP;
		int len = arr.length;
		if(len == 1) {
			return totaltime;
		}
		for(int i = 1; i < len; i++) {
			// 有三种情况
			// 第一种：arr[i-1] < arr[i]，则为继续上升
			if(arr[i-1] < arr[i]) {
				totaltime += (arr[i]-arr[i-1])*UP + STOP;
			} else if(arr[i-1] > arr[i]) { // 第二种：arr[i-1]>arr[i],则为下降
				totaltime += (arr[i-1]-arr[i])*DOWN + STOP;
			} else { // 第三种：相等的情况，则继续停留
				totaltime += STOP;
			}
		}
		return totaltime;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while(input.hasNext()) {
			int N = input.nextInt();
			if(N == 0) {
				break;
			}
			int[] arr = new int[N];
			for(int i = 0; i < N; i++) {
				arr[i] = input.nextInt();
			}
			System.out.println(getTime(arr));
		}
	}
}
