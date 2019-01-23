import java.util.Scanner;

/** 1005
 * ��Ϊf(n-1) % 7 ֻ��0����6��7��ֵ,f(n-2)Ҳ�ܹ�7��ֵ
 * ��f(n) ����f(n-1)��f(n-2)��ϵģ����ܹ�7*7=49�֣���ѭ������Ϊ49+1
 * 
 * �������ö�̬�滮���ģ���ѭ������Ϊn+1�����ύʱ����
 * @author MUSTACHE
 * ����ÿ����һ��ʹ���һ�飬
 * Ҳ���������������������飬�������ύ
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
