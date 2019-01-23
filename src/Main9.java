import java.util.Arrays;
import java.util.Scanner;

/** 1009
 * @author MUSTACHE
 *
 */
class Trade implements Comparable<Trade>{
	int j;
	int f;
	double ratio; // j/f 比值
	Trade(){}
	@Override
	public int compareTo(Trade o) {
		// 我们从大到小排序
		if(this.ratio > o.ratio) {
			return -1;
		} else if(this.ratio < o.ratio) {
			return 1;
		} else {
			return 0;
		}
	}
	
}
public class Main9 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while(input.hasNext()) {
			int M = input.nextInt(); // 总猫粮
			int N = input.nextInt();
			if(M == -1 && N == -1) {
				break;
			}
			Trade[] trade = new Trade[N];
			for(int i = 0; i < N; i++) {
				trade[i] = new Trade();
				trade[i].j = input.nextInt(); // 猫粮
				trade[i].f = input.nextInt(); // 豆
				trade[i].ratio = (double)trade[i].j / trade[i].f;
			}
			Arrays.sort(trade); // 按比值从大到小排序
			double sum = 0;
			int i = 0;
			// 使用贪心算法
			// 如果肥鼠拿的总猫粮还有剩，并且豆子还没换完
			while(M != 0 && i < N) { 
				if(M >= trade[i].f) { // 如果鼠拿的猫粮大于可换的猫粮
					sum += trade[i].j; // 则全部换
					M -= trade[i].f;
				} else { // 若小于，则按比例换猫粮，而且比例是比较大的
					sum += M * trade[i].ratio; // 注意除后的精度
					M = 0; // 猫粮用完了
				}
				i++; // 下一个房间
			}
			System.out.printf("%.3f",sum); // 精确到小数点3位
			System.out.println();
		}
	}
}
