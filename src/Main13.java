import java.util.Scanner;

public class Main13 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while(input.hasNext()) {
			String str = input.next();
			if(str.charAt(0) == '0') {
				break;
			}
			int len = str.length();
			if(len == 1) {
				System.out.println(str);
				continue;
			}
			long sum = 0;
			// 九余定理
			for(int i = 0; i < len; i++) {
				sum += (str.charAt(i) - '0');
			}
			System.out.println(sum % 9 == 0 ? 9 : sum % 9);
			
			// 常规方法
//			for(int i = 0; i < len; i++) {
//				sum += (str.charAt(i) - '0');
//			}
//			while(sum > 9) {
//				long i = 0;
//				long temp = sum;
//				while(temp != 0) {
//					i += temp % 10;
//					temp /= 10;
//				}
//				sum = i;
//			}
//			System.out.println(sum);
		}
	}
}
