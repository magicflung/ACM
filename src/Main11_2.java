import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** 1011
 * @author MUSTACHE
 *	第二种做法：使用子节点与根结点比较来做标记
 */
public class Main11_2 {
	private static final int SIZE = 102;
	private static int N; // 洞穴中的房间数
	private static int M; // 士兵数
	
//	private static int[] bugs; // 每个房间的虫子数量
	// 因为一个士兵可以击杀20只虫（bug),所以我们新建数组来存储 击杀第i个房间需要多少个士兵
	// 特例：假设虫子有65只，因为要全部击杀才可取大脑，所以我们至少需要4个士兵
	// 即士兵在房间内击杀量必须大于等于虫子数
	// 所以我们可以使用 (bug + 19) / 20 ————表示击杀bug需要多少个士兵，
	// +19表示 不足20的部分把它看成20
	// 举个例子：bug=61，那么20*3-bug=1<20,因为要全击杀，所以只能再派出一个人取击杀这一只虫子
	private static int[] costs = new int[SIZE];
	private static int[] brains = new int[SIZE]; // 每个房间含有大脑的可能性
	
	private static int[][] dp = new int[SIZE][SIZE];
	private static List<Integer>[] adj;
	// 树状的深度搜索——树有根结点
	// 使用pre来记录v的父结点
	public static void dfs(int v, int pre) {
		for(int i = costs[v]; i <= M; i++) { // 表示在房间v需要至少i个士兵来击杀虫子
			dp[v][i] = brains[v]; // 也就是说至少有i个士兵甚至更多，就可以获得该房间的大脑的最大值
		}
		int num = adj[v].size();
		// 寻找v的子节点	
		for(int i = 0; i < num; i++) {
				int w = adj[v].get(i);
				if(w == pre) continue;
				dfs(w, v);
				for(int j = M; j >= costs[v]; j--) { // 表示士兵假如为M，那么目前至少需要costs[j]个士兵
					for(int k = 1; k <= j - costs[v]; k++) {
						if(dp[v][j] < dp[v][j - k] + dp[w][k]) {
							dp[v][j] = dp[v][j - k] + dp[w][k];
						}
					}
				}
		}
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while(input.hasNext()) {
			N = input.nextInt();
			M = input.nextInt();
			if(N == -1 && M == -1) {
				break;
			}
			// 因为每次执行都需要重新全部初始化，防止污染新数据
			for(int i = 0; i < SIZE; i++) {
				for(int j = 0; j < SIZE; j++) {
					dp[i][j] = 0;
				}
				costs[i] = 0;
				brains[i] = 0;
			}
			adj = new ArrayList[SIZE];
			int bug;
			for(int i = 1; i <= N; i++) {
				bug = input.nextInt();
				brains[i] = input.nextInt();
				costs[i] = (bug + 19) / 20;
			}
			// N==1时，我们下面的adj会空指向，所以我们得在这做出判断
			if(N == 1) {
				// N=1表示只有一个房间，所以下面可以直接用索引。
				if(costs[0] > M) { // 表示该房间需要得士兵大于我们已有的士兵
					System.out.println("0"); // 则杀不尽虫子拿不了大脑
				} else { // 否则 小于等于我们已有的士兵
					System.out.println(brains[0]); // 则该房间的大脑全拿下
				}
				continue;
			}
			for(int i = 1; i <= N - 1; i++) {
				int v = input.nextInt();
				int w = input.nextInt();
				if(adj[v] == null) {
					adj[v] = new ArrayList<Integer>();
				}
				if(adj[w] == null) {
					adj[w] = new ArrayList<Integer>();
				}
				adj[v].add(w);
				adj[w].add(v);
			}
			
			if(M == 0) { // 注意：题目的M的取值还可以为0，表示无士兵，则也无法派兵取击杀虫了，所以所有大脑也取不到
				System.out.println("0");
				continue;
			}
			dfs(1, -1);
			System.out.println(dp[1][M]);
		}
	}
}
