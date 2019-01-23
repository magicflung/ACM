import java.util.Scanner;

/** 1005
 * 因为f(n-1) % 7 只有0——6共7种值,f(n-2)也总共7种值
 * 而f(n) 是以f(n-1)和f(n-2)组合的，则总共7*7=49种，即循环周期为49+1
 * 
 * 本来是用动态规划做的，即循环周期为n+1，但提交时错误
 * @author MUSTACHE
 * 可以每输入一组就处理一组，
 * 也可以输入多组再来处理多组，都可以提交
 */
public class Main5 {	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while(input.hasNext()) {
			int A = input.nextInt();
			int B = input.nextInt();
			int n = input.nextInt();
			if(A + B + n == 0) {
				break;
			}
			if(n == 1 || n == 2) {
				System.out.println("1");
			} else {
				int[] f = new int[50];
				f[1] = 1;
				f[2] = 1;
				for(int i = 3; i < 50; i++) {
					f[i] = (A * f[i-1] + B * f[i-2]) % 7;
				}
				System.out.println(f[n % 49]);
			}
		}
	}
}
