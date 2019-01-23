import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** 1011
 * @author MUSTACHE
 *	�ڶ���������ʹ���ӽڵ�������Ƚ��������
 */
public class Main11_2 {
	private static final int SIZE = 102;
	private static int N; // ��Ѩ�еķ�����
	private static int M; // ʿ����
	
//	private static int[] bugs; // ÿ������ĳ�������
	// ��Ϊһ��ʿ�����Ի�ɱ20ֻ�棨bug),���������½��������洢 ��ɱ��i��������Ҫ���ٸ�ʿ��
	// ���������������65ֻ����ΪҪȫ����ɱ�ſ�ȡ���ԣ���������������Ҫ4��ʿ��
	// ��ʿ���ڷ����ڻ�ɱ��������ڵ��ڳ�����
	// �������ǿ���ʹ�� (bug + 19) / 20 ����������ʾ��ɱbug��Ҫ���ٸ�ʿ����
	// +19��ʾ ����20�Ĳ��ְ�������20
	// �ٸ����ӣ�bug=61����ô20*3-bug=1<20,��ΪҪȫ��ɱ������ֻ�����ɳ�һ����ȡ��ɱ��һֻ����
	private static int[] costs = new int[SIZE];
	private static int[] brains = new int[SIZE]; // ÿ�����京�д��ԵĿ�����
	
	private static int[][] dp = new int[SIZE][SIZE];
	private static List<Integer>[] adj;
	// ��״����������������и����
	// ʹ��pre����¼v�ĸ����
	public static void dfs(int v, int pre) {
		for(int i = costs[v]; i <= M; i++) { // ��ʾ�ڷ���v��Ҫ����i��ʿ������ɱ����
			dp[v][i] = brains[v]; // Ҳ����˵������i��ʿ���������࣬�Ϳ��Ի�ø÷���Ĵ��Ե����ֵ
		}
		int num = adj[v].size();
		// Ѱ��v���ӽڵ�	
		for(int i = 0; i < num; i++) {
				int w = adj[v].get(i);
				if(w == pre) continue;
				dfs(w, v);
				for(int j = M; j >= costs[v]; j--) { // ��ʾʿ������ΪM����ôĿǰ������Ҫcosts[j]��ʿ��
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
			// ��Ϊÿ��ִ�ж���Ҫ����ȫ����ʼ������ֹ��Ⱦ������
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
			// N==1ʱ�����������adj���ָ���������ǵ����������ж�
			if(N == 1) {
				// N=1��ʾֻ��һ�����䣬�����������ֱ����������
				if(costs[0] > M) { // ��ʾ�÷�����Ҫ��ʿ�������������е�ʿ��
					System.out.println("0"); // ��ɱ���������ò��˴���
				} else { // ���� С�ڵ����������е�ʿ��
					System.out.println(brains[0]); // ��÷���Ĵ���ȫ����
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
			
			if(M == 0) { // ע�⣺��Ŀ��M��ȡֵ������Ϊ0����ʾ��ʿ������Ҳ�޷��ɱ�ȡ��ɱ���ˣ��������д���Ҳȡ����
				System.out.println("0");
				continue;
			}
			dfs(1, -1);
			System.out.println(dp[1][M]);
		}
	}
}
