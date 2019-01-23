import java.util.Scanner;

/** 1006
 * 这道题的时针，分针，秒针分别用他们的角速度来表示，并求出他们两两的快乐时间区间的交集即可。
 * 一天中有3个时间段3个指针重合，即0：00，12：00，24：00
 * 但我们只需要求半天中，即0：00-12：00中三个指针都处于快乐时间占半天时间的比例
 * 引入：各个指针每秒移动的角度
 * H：时针角速度：360 /（12*60*60) = 1/120
 * M：分针角速度：360 / （60*60) = 1/10
 * S：秒针角速度：360 / 60 = 6 
 * 判断它们角速度两两之间的关系：D为至少离D度的happy time,k为圈数
 * HM：di=|H-M|, D+360*k <= di <= 360*(k+1)-D, 0<=k<11
 * 解释k的范围：根据总时间为半天，则时针转1圈，分针转12圈，秒针转720圈。
 * 			   而时针与分针的圈数关系则为：分针在12小时内最多超时针11圈（12-1）。下面同理
 * HS：di=|H-S|, D+360*k <= di <= 360*(k+1)-D, 0<=k<719
 * MS：di=|M-S|, D+360*k <= di <= 360*(k+1)-D, 0<=k<708  
 * 即：可以求出它们两两的快乐时间：
 * HM：di=|H-M|, (D+360*k)/di <= t1 <= (360*(k+1)-D), 0<=k<11
 * HS：di=|H-S|, (D+360*k)/di <= t2 <= (360*(k+1)-D), 0<=k<719
 * MS：di=|M-S|, (D+360*k)/di <= t3 <= (360*(k+1)-D), 0<=k<708 
 * 然后求它们的交集，即为它们都快乐的时间 
 * @author MUSTACHE
 *
 */
public class Main6 {
	private static final double h = 1. / 120; 
	private static final double m = 1. / 10 ; 
	private static final double s = 6.		;
	private static double totaltime; // 存储三个指针都快乐的时间
	// 三个区间
	private static double start1, start2, start3;
	private static double end1, end2, end3;
	public static double ff(double D) {
		totaltime = 0.0;
		for(int k1 = 0; k1 < 11; k1++) {
			// 求出HM的区间
			double d1 = m - h;
			start1 = (360 * k1 + D) / d1;
			end1 = (360 * (k1+1) - D) / d1;
			for(int k2 = 0; k2 < 708; k2++) {
				// 求出MS的区间(求区间哪个先都行，只不过把范围比较小的放在前面)
				double d2 = s - m;
				start2 = (360 * k2 + D) / d2;
				end2 = (360 * (k2+1) - D) / d2;
				// 求前两个区间的交集
				double tempstart1 = Math.max(start1, start2);
				double tempend1 = Math.min(end1, end2);
				if(tempstart1 >= tempend1) { // 说明前两个没有交集，则不用继续往下求，因为我们要的是都快乐
					continue;
				}
				for(int k3 = 0; k3 < 719; k3++) {
					double d3 = s - h;
					start3 = (360 * k3 + D) / d3;
					end3 = (360 * (k3+1) - D) / d3;
					double tempstart2 = Math.max(tempstart1, start3);
					double tempend2 = Math.min(tempend1, end3);
					if(tempstart2 < tempend2) { // 三个指针的交集
						totaltime += (tempend2 - tempstart2);
					}
				}
			}
		}
		return totaltime;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while(input.hasNext()) {
			int D = input.nextInt();
			if(D == -1) {
				break;
			}
			if(D == 0) {
				System.out.println("100.000");
				continue;
			}
			double time = ff(D) / (12*6*6);
			System.out.printf("%.3f", time); // 注意没有换行
			System.out.println();
		}
	}
}
