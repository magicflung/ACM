import java.util.Scanner;

/** 1010
 * @author MUSTACHE
 *	���������ͨ�����ѣ��ᳬʱ
 *���������ѵĻ����ϼ���һ�� ��ż��֦��
 *��Ҫ֪���Ķ�����㵽�ŵ�·���ľ�������ſ���ʱ��,������ż����ͬ
 */
public class Main10 {
	private static char[][] map;
	private static int[][] mark;
	private static boolean isSave;
	// next[][] ��ʾ�����ϣ��£�����
	private static int[][] next = {{-1,0},{1,0},{0,-1},{0,1}};
	// �Թ��Ĵ�С�����ŵ�ʱ��
	private static int N; // ����
	private static int M; // ����
	private static int T; // T�����
	// ��¼�ŵ�λ��
	private static int door_i;
	private static int door_j;
	/** ���ȴ���������S
	 * @param x ��ǰ����
	 * @param y ��ǰ����
	 * @param nt ��ǰ�����Թ���ʱ��
	 * @return
	 */
	public static void dfs(int x, int y, int nt) {
		mark[x][y] = 1; // ��ʾ������λ��
		// ��ʾ������ʱ��պõ����ſ���ʱ�䣬���ҵ�ǰλ��Ϊ�ţ����ҵ�����
		if(nt == T && map[x][y] == 'D') {
			isSave = true; // ��ʾ�þ�
			return ;
		}
		// ����ʱ������ſ�ʱ��Ͳ�������������Ҳ���ü�������
		if(nt > T) { 
			return ;
		}
		
		// ��ż��֦ ʣ��ʱ�䣨ʣ�ಽ���� - ��ǰλ�õ��ŵľ���
		int temp = T - nt - Math.abs(x-door_i) - Math.abs(y - door_j);
		// ʣ�ಽ��С�ڵ�ǰλ�õ��ŵľ��룬�򲻿��ܵ�����
		// ʣ�ಽ���뵱ǰλ�õ��ŵľ������ż�Բ�ͬ����ֻ���жϽ����Ϊ�������ɣ���Ϊ��-��=ż��ż-ż=ż��
		if(temp < 0 || (temp & 1) == 1) { 
			return ;
		}
		
		// ��Ϊͼ�е�ÿ����������Գ��ĸ������ߣ��������ǿ��Լ����ȴ������ĳ��������
		for(int i = 0; i < 4; i++) {
			int tx = x + next[i][0];
			int ty = y + next[i][1];
			// �����������Щ���������
			if(tx < 0 || tx >= N || ty < 0 || ty >= M 
					|| map[tx][ty] == 'X' || mark[tx][ty] == 1) {
				continue;
			}
			// ����Ļ���������
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
			// ��¼����λ��
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
