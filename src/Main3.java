import java.util.Scanner;

/** 1003
 * @author MUSTACHE
 *	1<=T<=20
 *	(1<=N<=100000)，然后是N个整数(所有整数都在-1000和1000之间)。
 *	并且，如果有多个结果，则输出第一个结果。
 */
public class Main3 {
	private static int[] start;
	private static int[] end;
	private static int[] maxsum;
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int T = input.nextInt();
		start = new int[T];
		end = new int[T];
		maxsum = new int[T];
		for(int i = 0; i < T; i++) {
			int N = input.nextInt();
			int[] arr = new int[N];
			for(int j = 0; j < N; j++) {
				arr[j] = input.nextInt();
			}
			find(arr, N, i);
		}
		for(int i = 0; i < T; i++) {
			System.out.println("Case " + (i+1) + ":");
			System.out.println(maxsum[i] + " " + start[i] + " " + end[i]);
			if(i != T-1) {
				System.out.println();
			}
		}
	}
	private static void find(int[] arr, int len, int i) {
		start[i] = 0;
		end[i] = 0;
		int sum = 0;
		maxsum[i] = -1001;
		int temp = 1;
		for(int j = 0; j < len; j++) {
			sum += arr[j];
			if(sum > maxsum[i]) {
				maxsum[i] = sum;
				start[i] = temp;
				end[i] = j+1;
			}
			if(sum < 0) {
				sum = 0; 
				temp = j+2; // j+1表示跳到下一个j，而j+1+1 表示子序列的位置
			}
		}
//		int temp = 1;
//		for(int j = 0; j < len; j++) {
//			sum += arr[j];
//			if(arr[j] > sum) {
//				sum = arr[j];
//				temp = j+1;
//			} 
//			if(sum > maxsum[i]) {
//				maxsum[i] = sum;
//				end[i] = j+1;
//				start[i] = temp;
//			}
//		}
	}
}
