import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** 1011
 * �������+01����+��״
 * @author MUSTACHE
 *	ʹ�ó���ı�Ƿ�
 */
public class Main11_1 {
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
	
	private static int[][] adj = new int[SIZE][SIZE]; // �洢����֮������������ 
	
	private static int[][] dp = new int[SIZE][SIZE]; // ��ʾp��ʿ��ռ����iΪ�ڵ�����õĴ��Ե����ֵ
	
	private static boolean[] mark = new boolean[SIZE]; // ���
	// ��״����������������и����
	// ʹ��pre����¼v�ĸ����
	public static void dfs(int v) {
		mark[v] = true; // ������ǰ����
		for(int i = costs[v]; i <= M; i++) { // ��ʾ�ڵ�ǰ����������Ҫcosts[v]��ʿ������ɱ����
			dp[v][i] = brains[v]; // Ҳ����˵������i��ʿ���������࣬�Ϳ��Ի�ø÷���Ĵ��Ե����ֵ
		}
		// Ѱ��v���ӽڵ�,��1��ʼ
		for(int i = 1; i <= N; i++) {
			// ����������һ�û�߹���
			if(adj[v][i] > 0 && !mark[i]) {
				dfs(i);
				// 01����
				for(int j = M; j >= costs[v]; j--) { // ��ʿ����������ʼ
					for(int k = 1; k <= j - costs[v]; k++) {
						// ��ʾ��ǰ��j������ռ��v�����õ��Զ໹�Ƿ���j-k���˵���ǰ������Ϸ���k��ȡ��һ���������õ��Զ�
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
			// ��Ϊ���Ŵ�1��ʼ����������0���ǲ��ã��࿪һ���ռ�
			// ����ȫ������1��ʼ��
			// ��Ϊÿ��ִ�ж���Ҫ����ȫ����ʼ������ֹ��Ⱦ������
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
				// 1��ʾv��w����
				adj[v][w] = 1;
				adj[w][v] = 1;
			}
			
			if(M == 0) { // ע�⣺��Ŀ��M��ȡֵ������Ϊ0����ʾ��ʿ������Ҳ�޷��ɱ�ȡ��ɱ���ˣ��������д���Ҳȡ����
				System.out.println("0");
				continue;
			}
			dfs(1);
			System.out.println(dp[1][M]);
		}
	}
}
