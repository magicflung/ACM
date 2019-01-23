import java.util.Scanner;

/** 1010
 * @author MUSTACHE
 *	如果按照普通的深搜，会超时
 *所以在深搜的基础上加上一个 奇偶剪枝。
 *需要知道的定理：起点到门的路径的距离等于门开的时间,并且奇偶性相同
 */
public class Main10 {
	private static char[][] map;
	private static int[][] mark;
	private static boolean isSave;
	// next[][] 表示方向，上，下，左，右
	private static int[][] next = {{-1,0},{1,0},{0,-1},{0,1}};
	// 迷宫的大小，开门的时间
	private static int N; // 行数
	private static int M; // 列数
	private static int T; // T秒后开门
	// 记录门的位置
	private static int door_i;
	private static int door_j;
	/** 最先传入的是起点S
	 * @param x 当前行数
	 * @param y 当前列数
	 * @param nt 当前所经迷宫的时间
	 * @return
	 */
	public static void dfs(int x, int y, int nt) {
		mark[x][y] = 1; // 表示经过该位置
		// 表示当所经时间刚好等于门开的时间，并且当前位置为门，则找到出口
		if(nt == T && map[x][y] == 'D') {
			isSave = true; // 表示得救
			return ;
		}
		// 所经时间大于门开时间就不满足条件了则也不用继续搜了
		if(nt > T) { 
			return ;
		}
		
		// 奇偶剪枝 剩余时间（剩余步数） - 当前位置到门的距离
		int temp = T - nt - Math.abs(x-door_i) - Math.abs(y - door_j);
		// 剩余步数小于当前位置到门的距离，则不可能到达门
		// 剩余步数与当前位置到门的距离的奇偶性不同，（只需判断结果不为奇数即可，因为奇-奇=偶，偶-偶=偶）
		if(temp < 0 || (temp & 1) == 1) { 
			return ;
		}
		
		// 因为图中的每个格至多可以朝四个方向走，所以我们可以假设先从起点往某个方向走
		for(int i = 0; i < 4; i++) {
			int tx = x + next[i][0];
			int ty = y + next[i][1];
			// 出现下面的这些情况则跳过
			if(tx < 0 || tx >= N || ty < 0 || ty >= M 
					|| map[tx][ty] == 'X' || mark[tx][ty] == 1) {
				continue;
			}
			// 否则的话继续深搜
			dfs(tx, ty, nt+1);
			mark[tx][ty] = 0;
			if(isSave) {
				return ;
			}
		}
	}
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while(input.hasNext()) {
			N = input.nextInt();
			M = input.nextInt();
			T = input.nextInt();
			if(N + M + T == 0) {
				break;
			}
			map = new char[N][M];
			mark = new int[N][M];
			// 记录起点的位置
			int start_i = 0;
			int start_j = 0;
			isSave = false;
			for(int i = 0; i < N; i++) {
				String str = input.next();
				for(int j = 0; j < M; j++) {
					char c = str.charAt(j);
					if(c == 'S') {
						start_i = i;
						start_j = j;
					}
					if(c == 'D') {
						door_i = i;
						door_j = j;
					}
					map[i][j] = c;
				}
			}
			dfs(start_i, start_j, 0);
			System.out.println(isSave ? "YES" : "NO");
		}
	}
}
