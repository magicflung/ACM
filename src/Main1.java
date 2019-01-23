import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** 1001
 * @author MUSTACHE
 * 方法一：直接从1加到n（用int就可以）——————O(n)
 * 方法二：使用通向公式和（ Sn=n*(n+1)/2 =（n+1)*(n/2)）——————O(1)
 * 
 * 需要注意：题目的数据范围
 * in the range of 32-bit signed integer.
 * 如果使用方法二，我们需要使用long类型，因为n*(n+1) 有可能会超过数据范围。
 * 但是方法二：我们也还是可以使用int类型，把公式换成Sn=（n+1)*(n/2)，
 * 只是要分偶数奇数，偶数还是那个公式，奇数的公式为：Sn=（n+1)*(n/2)+1+n/2;
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
//		推出奇数时：(i+1)*(i/2)+1+i/2
		for(Integer i : list) {
			int t = (int) ((i % 2) == 0 ? (i+1)*(i/2) : (i+1)*(i/2)+1+i/2);
//			System.out.println((long)(i*(i+1)/2));
			System.out.println(t);
			System.out.println();
		}
	}
}
