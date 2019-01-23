import java.util.Scanner;

/** 1002
 * @author MUSTACHE
 * 题目中的：
 * you should not process them by using 32-bit integer. 
 * You may assume the length of each integer will not exceed 1000.
 * 这个其实是数组长度，所以我们可以转化为数组来算
 */
public class Main2 {
	private static int N;
	private static int[] arr1;
	private static int[] arr2;
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		N = input.nextInt();
		if(N < 1 || N > 20) {
			throw new IllegalArgumentException();
		}
		String[] str1 = new String[N];
		String[] str2 = new String[N];
		for(int i = 0; i < N; i++) {
			str1[i] = input.next();
			str2[i] = input.next();
		}
		
		for(int i = 0; i < N; i++) {
			System.out.println("Case " + (i+1) + ":");
			System.out.println(str1[i] + " + " + str2[i] + " = " 
											+ add(str1[i],str2[i]));
			if(i != N-1) {
				System.out.println();
			}
		}
	}

	public static String add(String str1, String str2) {
		arr1 = new int[1001];
		arr2 = new int[1001];
		for(int i = 0; i < str1.length(); i++) {
			arr1[i] = str1.charAt(i)-'0';
		}
		for(int i = 0; i < str2.length(); i++) {
			arr2[i] = str2.charAt(i)-'0';
		}
		int len1 = str1.length()-1;
		int len2 = str2.length()-1;
		int carry = 0;
		StringBuilder sb = new StringBuilder();
		int max = Math.max(len1, len2);
		for(int i = 0; i <= max; i++) {
			int a = (len1 < 0) ? 0 : arr1[len1--];
			int b = (len2 < 0) ? 0 : arr2[len2--];
			int sum = a + b + carry;
			if(sum >= 10) {
				carry = 1;
				sum %= 10;
			} else {
				carry = 0;
			}
			sb.append(sum);
		}
		if(carry == 1) {
			sb.append(carry);
		}
		return sb.reverse().toString();
	}

/*  网上有人这样做，使用BigInteger
   public static void main(String[] args) {
		BigInteger a,b,c;
		int n;
		Scanner cin = new Scanner(System.in);
		n = cin.nextInt();
		for(int i=1;i<=n;i++)
		{
		a = cin.nextBigInteger();
		b = cin.nextBigInteger();
		c = a.add(b);
		System.out.println("Case " + i + ":");
		System.out.println(a + " + " + b + " = " + c);
		if(i!=n)
			System.out.println();
		}
	}

*/
	
}
