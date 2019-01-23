import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/** 1007
 * @author MUSTACHE
 *���ͣ� ����������㣬���η���
 *һ��ģ�壬���Լ�
 *
 *˼·�����������Ϊ Ҫ��һ��Ȧֻ����һ��toy����������Ҫ����Щtoy������������������ǵľ���ó��뾶��Ҳ��������Ҫ��Ƶ�Ȧ��
 *��ô�ͱ���� �������ľ��� �����⡣
 *�����������ٷ���������Щ�������������ǵľ��룬�ж���С���룬�����Ļ�̫���ˣ������ҪO(n^2)
 *���������÷��η����������ǰ�����x����Ȼ��ָ���������ߣ��ó��м���mid������3�ֿ��ܣ�
 *	1.�󼯺����������Եľ��롣d1
 *	2.�Ҽ������������Եľ��롣d2
 *	3.һ��������󼯺ϣ�һ��������Ҽ��ϵ������Եľ��롣d3
 *Ȼ����������������ĸ��������:d=min(d1,d2,d3)��
 *Ȼ����ڷָ�����Ĳ��֣������ֻ�����ָ�ݹ���⡣
 *
 *�������ֻ��3�������ڵĻ������ǿ����ȴ���
 *	����ֻ��1���㣺����ֱ�ӷ���0.00������Ҳ��֤�����ǲ������������������������д�ϡ�
 *	������2���㣺����ֱ��������ǵľ��룬��Ϊ��̾��롣
 *	������3���㣺������������룬�����̼��ɡ�
 *
 *Ȼ������ĸ������ϵģ��ָ�ݹ����Ѿ���ǰ������d1��d2����̾���������ˣ���ô�ص����������֣�
 *����d=min(d1,d2)���ȵó�ǰ���������ĸ��Ƚ�С����ô�Ϳ��Ը���d����С���㷶Χ��
 *	��������������mid���ָ�������֣���ô������ߵļ�������㵽mid�ĺ��������d��Ҳ���ÿ����ˣ�һ��������̡�
 *	�����Ҽ���Ҳ�ǡ�
 *	���Եó��������ֵĴ𰸵ĵ��һ����mid-d��mid+d�ķ�Χ�ڡ�
 *
 *�ڼ���ü��ϵĵ��ʱ������԰�y�����������һ����Եľ���С��d����ô����һ����һ��d*(d+d)�ľ����ڡ�
 *������������d*d�������������ڣ���඼ֻ�ܺ���4���㡣�������4���㣬��������������������ٴ���һ����Եľ���С��d��
 *�������ǰ�������Ѿ�����õ���dì�ܵģ���Ϊ��������ͬһ�����ڻ��б�d��С���ˡ�
 *�������Ҽ����ܹ�������8���㡣���ԣ���������һ��������ĵ㣬ֻҪ�������밴y���������ҽ����ŵ�7����֮��ľ���Ϳ��ԡ�
 *
 *Ҳ���԰ѵ������ֿ��ɶ���ǰ���������d�Ľ�һ���жϣ��ж����Ҽ��ϸ�һ��ľ����Ƿ񻹱�dС������������d��û�оͻ���d
 */
public class Main7 {
	private static class Point {
		double x, y;
		Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
	private static Point[] points; // �洢���������
	private static Integer[] arr; // ���洢 �������Ҽ��ϴ�����̾��������� �������
	
	// ��X������ıȽ���
	private static class CompareX implements Comparator<Point> {

		public int compare(Point a0, Point a1) {
			if(a0.x > a1.x) {
				return 1;
			} else if(a0.x < a1.x) {
				return -1;
			} else {
				if(a0.y >= a1.y) {
					return 1;
				} else {
					return -1;
				}
			}
		}
		
	}
	
	// ��Y������ıȽ���
	private static class CompareY implements Comparator<Integer> {

		public int compare(Integer a0, Integer a1) {
			if(points[a0].y > points[a1].y) {
				return 1;
			} else if(points[a0].y < points[a1].y) {
				return -1;
			} else {
				if(points[a0].x >= points[a1].x) {
					return 1;
				} else {
					return -1;
				}
			}
		}
		
	}
	
	// ����ľ���
	public static double getDistance(Point a0, Point a1) {
		double x = Math.abs(a0.x - a1.x);
		double y = Math.abs(a0.y - a1.y);
		return Math.sqrt(x*x + y*y);
	}
	
	// ���η�
	public static double solve(int l, int r) {
		double ans = 0;
		if(r - l + 1 <= 3) { // �������С�ڵ���3��
			if(r - l + 1 == 1) return ans;  // ���Ϊ1��
			ans = getDistance(points[l], points[l+1]);
			if(r - l + 1 == 2) return ans; // ���Ϊ2��
			// ����Ϊ3��
			double t1 = getDistance(points[l], points[l+2]);
			double t2 = getDistance(points[l+1], points[l+2]);
			if(t1 > t2) ans = Math.min(ans, t2);
			else ans = Math.min(ans, t1);
			return ans;
		}
		
		// �������Ϊ4������
		int mid = l + (r - l)/2;
		double d1 = solve(l, mid); // ��ݹ����ұ߼��ϵĵ�֮����̾���
		double d2 = solve(mid + 1, r); // �ҵݹ����ұ߼��ϵĵ�֮����̾���
		ans = Math.min(d1, d2); /*��ʼ������ˡ����´���һ���������ҷ���ֵΪans�����´���*/
		// ���ж����Ҽ����Ƿ������̾��룬һ��������һ��������
		int k = 0; // ����¼�󼯺Ϻ��Ҽ����ܹ��ж��ٸ�������mid-ans��mid+ans
		// ע�⣬�����Ѿ���x���������������points[mid].x - points[i].x��points[i].x - points[mid].x��Ϊ��
		// ���
		for(int i = mid - 1; i >= l && points[mid].x - points[i].x <= ans; i--) {
			arr[k++] = i; // �������������Ǳ��
		}
		// �ұߣ�i<=r��Ϊ���ǵ�solve�ķ�Χ�ǰ���r�ģ�����r����Խ�磬�տ�ʼpoints[i].x - points[mid].x <= ans�������д���ˣ�д�ɸ�����һ����
		for(int i = mid + 1; i <= r && points[i].x - points[mid].x <= ans; i++) {
			arr[k++] = i;
		}
		// �ó���������y������
		Comparator comparey = new CompareY();
		Arrays.sort(arr, 0, k, comparey);
		//  �ٰ�arr��������points��ȡ��Ƚ�
		for(int i = 0; i < k; i++) {
			// (j - i) <= 7 ��ʾ������ĵ㲻����8��
			for(int j = i + 1; j < k-1 && (j - i) <= 7; j++) {
				ans = Math.min(ans, getDistance(points[arr[i]], points[arr[j]]));
			}
		}
		return ans;
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while(input.hasNext()) {
			int N = input.nextInt();
			if(N == 0) {
				break;
			}
			points = new Point[N];
			arr = new Integer[N];
			
			for(int i = 0; i < N; i++) {
				double x = input.nextDouble();
				double y = input.nextDouble();
				points[i] = new Point(x, y);
			}
			Comparator comparex = new CompareX();
			Arrays.sort(points, comparex);
			System.out.println(String.format("%.2f", solve(0, N-1)/2));
			
		}
	}
	
}
