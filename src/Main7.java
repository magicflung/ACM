import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/** 1007
 * @author MUSTACHE
 *类型： 坐标中最近点，分治法。
 *一类模板，可以记
 *
 *思路：该题的题意为 要求一个圈只能套一个toy，所以我们要求这些toy中哪两个最近，求它们的距离得出半径，也就是我们要设计的圈。
 *那么就变成了 求最近点的距离 的问题。
 *如果我们用穷举法，就是这些点中两两求它们的距离，判断最小距离，这样的话太慢了，大概需要O(n^2)
 *所以我们用分治法。首先我们按坐标x排序，然后分割成左右两边，得出中间轴mid。答案有3种可能：
 *	1.左集合中求最近点对的距离。d1
 *	2.右集合中求最近点对的距离。d2
 *	3.一个点存在左集合，一个点存在右集合的最近点对的距离。d3
 *然后求出这三部分中哪个距离最短:d=min(d1,d2,d3)。
 *然后对于分割出来的部分，它们又会继续分割递归求解。
 *
 *如果数据只有3个点以内的话，我们可以先处理：
 *	对于只有1个点：我们直接返回0.00，题中也保证了我们不会遇到这种情况，不过可以写上。
 *	对于有2个点：我们直接求出它们的距离，即为最短距离。
 *	对于有3个点：我们两两求距离，求出最短即可。
 *
 *然后对于四个点以上的，分割递归中已经把前两部分d1，d2的最短距离求出来了，那么重点解决第三部分：
 *假设d=min(d1,d2)，先得出前两部分中哪个比较小，那么就可以根据d来缩小计算范围：
 *	就是由于我们以mid来分割成两部分，那么对于左边的集合如果点到mid的横坐标大于d则也不用考虑了，一定不是最短。
 *	对于右集合也是。
 *	所以得出第三部分的答案的点对一定在mid-d到mid+d的范围内。
 *
 *在计算该集合的点对时，点可以按y坐标排序。如果一个点对的距离小于d，那么它们一定在一个d*(d+d)的矩阵内。
 *而在左右两个d*d的正方形区域内，最多都只能含有4个点。如果超过4个点，则这个正方形区域内至少存在一个点对的距离小于d，
 *这跟我们前两部分已经求出得到的d矛盾的，因为不可能在同一区域内还有比d更小的了。
 *所以左右集合总共不超过8个点。所以，对于任意一个该区域的点，只要计算它与按y坐标排序且紧接着的7个点之间的距离就可以。
 *
 *也可以把第三部分看成对于前两种求出的d的进一步判断，判断左右集合各一点的距离是否还比d小，如果有则更新d，没有就还是d
 */
public class Main7 {
	private static class Point {
		double x, y;
		Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
	}
	private static Point[] points; // 存储输入的坐标
	private static Integer[] arr; // 来存储 假设左右集合存在最短距离的情况的 点的索引
	
	// 按X轴排序的比较器
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
	
	// 按Y轴排序的比较器
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
	
	// 两点的距离
	public static double getDistance(Point a0, Point a1) {
		double x = Math.abs(a0.x - a1.x);
		double y = Math.abs(a0.y - a1.y);
		return Math.sqrt(x*x + y*y);
	}
	
	// 分治法
	public static double solve(int l, int r) {
		double ans = 0;
		if(r - l + 1 <= 3) { // 如果点数小于等于3个
			if(r - l + 1 == 1) return ans;  // 如果为1个
			ans = getDistance(points[l], points[l+1]);
			if(r - l + 1 == 2) return ans; // 如果为2个
			// 否则为3个
			double t1 = getDistance(points[l], points[l+2]);
			double t2 = getDistance(points[l+1], points[l+2]);
			if(t1 > t2) ans = Math.min(ans, t2);
			else ans = Math.min(ans, t1);
			return ans;
		}
		
		// 如果点数为4个以上
		int mid = l + (r - l)/2;
		double d1 = solve(l, mid); // 左递归求右边集合的点之间最短距离
		double d2 = solve(mid + 1, r); // 右递归求右边集合的点之间最短距离
		ans = Math.min(d1, d2); /*开始这里错了。。新创建一个变量并且返回值为ans，导致错误*/
		// 来判断左右集合是否存在最短距离，一个点在左，一个点在右
		int k = 0; // 来记录左集合和右集合总共有多少个点满足mid-ans和mid+ans
		// 注意，我们已经按x轴排序，所以下面的points[mid].x - points[i].x和points[i].x - points[mid].x不为负
		// 左边
		for(int i = mid - 1; i >= l && points[mid].x - points[i].x <= ans; i--) {
			arr[k++] = i; // 保存索引，就是标记
		}
		// 右边（i<=r因为我们的solve的范围是包括r的，所以r不会越界，刚开始points[i].x - points[mid].x <= ans这条语句写错了，写成跟上面一样）
		for(int i = mid + 1; i <= r && points[i].x - points[mid].x <= ans; i++) {
			arr[k++] = i;
		}
		// 得出的索引按y轴排序
		Comparator comparey = new CompareY();
		Arrays.sort(arr, 0, k, comparey);
		//  再把arr的索引在points中取点比较
		for(int i = 0; i < k; i++) {
			// (j - i) <= 7 表示该区域的点不超过8个
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
