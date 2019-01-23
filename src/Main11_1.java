import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** 1011
 * 深度搜索+01背包+树状
 * @author MUSTACHE
 *	使用常规的标记法
 */
public class Main11_1 {
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
	
	private static int[][] adj = new int[SIZE][SIZE]; // 存储房间之间相连的索引 
	
	private static int[][] dp = new int[SIZE][SIZE]; // 表示p个士兵占领以i为节点所获得的大脑的最大值
	
	private static boolean[] mark = new boolean[SIZE]; // 标记
	// 树状的深度搜索——树有根结点
	// 使用pre来记录v的父结点
	public static void dfs(int v) {
		mark[v] = true; // 经过当前房间
		for(int i = costs[v]; i <= M; i++) { // 表示在当前房间至少需要costs[v]个士兵来击杀虫子
			dp[v][i] = brains[v]; // 也就是说至少有i个士兵甚至更多，就可以获得该房间的大脑的最大值
		}
		// 寻找v的子节点,从1开始
		for(int i = 1; i <= N; i++) {
			// 如果相连并且还没走过的
			if(adj[v][i] > 0 && !mark[i]) {
				dfs(i);
				// 01背包
				for(int j = M; j >= costs[v]; j--) { // 从士兵总人数开始
					for(int k = 1; k <= j - costs[v]; k++) {
						// 表示当前以j个人来占领v房所得的脑多还是分配j-k个人到当前房间加上分配k人取下一个房间所得的脑多
						if(dp[v][j] < dp[v][j - k] + dp[i][k]) {
							dp[v][j] = dp[v][j - k] + dp[i][k];
						}
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
			// 因为入门从1开始，所以索引0我们不用，多开一个空间
			// 我们全部都从1开始吧
			// 因为每次执行都需要重新全部初始化，防止污染新数据
			for(int i = 1; i < SIZE; i++) {
				for(int j = 1; j < SIZE; j++) {
					dp[i][j] = 0;
					adj[i][j] = 0;
				}
				costs[i] = 0;
				brains[i] = 0;
				mark[i] = false;
			}
			int bug;
			for(int i = 1; i <= N; i++) {
				bug = input.nextInt();
				brains[i] = input.nextInt();
				costs[i] = (bug + 19) / 20;
			}
			for(int i = 1; i <= N - 1; i++) {
				int v = input.nextInt();
				int w = input.nextInt();
				// 1表示v与w相连
				adj[v][w] = 1;
				adj[w][v] = 1;
			}
			
			if(M == 0) { // 注意：题目的M的取值还可以为0，表示无士兵，则也无法派兵取击杀虫了，所以所有大脑也取不到
				System.out.println("0");
				continue;
			}
			dfs(1);
			System.out.println(dp[1][M]);
		}
	}
}
