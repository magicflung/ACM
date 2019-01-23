import java.util.Scanner;

/** 1008
 * @author MUSTACHE
 *
 */
public class Main8 {
	private static final int UP = 6; 
	private static final int DOWN = 4; 
	private static final int STOP = 5; 
	private static int totaltime;
	public static int getTime(int[] arr) {
		// һ��ʼ�Ǵӵ�0�㿪ʼ��,���Զ��������еĵ�һ��Ԫ��һ����������
		totaltime = arr[0] * UP + STOP;
		int len = arr.length;
		if(len == 1) {
			return totaltime;
		}
		for(int i = 1; i < len; i++) {
			// ���������
			// ��һ�֣�arr[i-1] < arr[i]����Ϊ��������
			if(arr[i-1] < arr[i]) {
				totaltime += (arr[i]-arr[i-1])*UP + STOP;
			} else if(arr[i-1] > arr[i]) { // �ڶ��֣�arr[i-1]>arr[i],��Ϊ�½�
				totaltime += (arr[i-1]-arr[i])*DOWN + STOP;
			} else { // �����֣���ȵ�����������ͣ��
				totaltime += STOP;
			}
		}
		return totaltime;
	}
	
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		while(input.hasNext()) {
			int N = input.nextInt();
			if(N == 0) {
				break;
			}
			int[] arr = new int[N];
			for(int i = 0; i < N; i++) {
				arr[i] = input.nextInt();
			}
			System.out.println(getTime(arr));
		}
	}
}
