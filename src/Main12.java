
/**
 * @author MUSTACHE
 * Ҫע�⾫�����⣬һ��ʼe[3]��e[4]ֱ��ʹ�������е�������ݲ���С��λ����ȡ��9�����º���e[5]֮��
 * һЩС��λ�����ݴ���
 * String.format() %.f����������Ĺ���
 */
public class Main12 {
	public static void main(String[] args) {
		double[] e = new double[10];
		e[0] = 1;
		e[1] = 2;
		e[2] = 2.5;
		for(int i = 3; i < 10; i++) {
			int sum = 1;
			int k = i;
			while(k != 0) {
				sum *= k--;
			}
			e[i] = (1.0 / sum) + e[i-1];
		}
		System.out.println("n e");
		System.out.println("- -----------");
		System.out.println("0 " + String.format("%.0f", e[0]));
		System.out.println("1 " + String.format("%.0f", e[1]));
		System.out.println("2 " + e[2]);
		for(int i = 3; i < 10; i++) {
			System.out.println(i + " " + String.format("%.9f", e[i]));
		}
	}
}
