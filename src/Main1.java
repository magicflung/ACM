import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** 1001
 * @author MUSTACHE
 * ����һ��ֱ�Ӵ�1�ӵ�n����int�Ϳ��ԣ�������������O(n)
 * ��������ʹ��ͨ��ʽ�ͣ� Sn=n*(n+1)/2 =��n+1)*(n/2)��������������O(1)
 * 
 * ��Ҫע�⣺��Ŀ�����ݷ�Χ
 * in the range of 32-bit signed integer.
 * ���ʹ�÷�������������Ҫʹ��long���ͣ���Ϊn*(n+1) �п��ܻᳬ�����ݷ�Χ��
 * ���Ƿ�����������Ҳ���ǿ���ʹ��int���ͣ��ѹ�ʽ����Sn=��n+1)*(n/2)��
 * ֻ��Ҫ��ż��������ż�������Ǹ���ʽ�������Ĺ�ʽΪ��Sn=��n+1)*(n/2)+1+n/2;
 */
public class Main1 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		List<Integer> list = new ArrayList<Integer>();
		while(input.hasNext()) {
			int d = input.nextInt();
			list.add(d);
		}
//		5  15 6*2+1=13 2
//		7  28 8*3+1=25 3
//		�Ƴ�����ʱ��(i+1)*(i/2)+1+i/2
		for(Integer i : list) {
			int t = (int) ((i % 2) == 0 ? (i+1)*(i/2) : (i+1)*(i/2)+1+i/2);
//			System.out.println((long)(i*(i+1)/2));
			System.out.println(t);
			System.out.println();
		}
	}
}
