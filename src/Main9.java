import java.util.Arrays;
import java.util.Scanner;

/** 1009
 * @author MUSTACHE
 *
 */
class Trade implements Comparable<Trade>{
	int j;
	int f;
	double ratio; // j/f ��ֵ
	Trade(){}
	@Override
	public int compareTo(Trade o) {
		// ���ǴӴ�С����
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
			int M = input.nextInt(); // ��è��
			int N = input.nextInt();
			if(M == -1 && N == -1) {
				break;
			}
			Trade[] trade = new Trade[N];
			for(int i = 0; i < N; i++) {
				trade[i] = new Trade();
				trade[i].j = input.nextInt(); // è��
				trade[i].f = input.nextInt(); // ��
				trade[i].ratio = (double)trade[i].j / trade[i].f;
			}
			Arrays.sort(trade); // ����ֵ�Ӵ�С����
			double sum = 0;
			int i = 0;
			// ʹ��̰���㷨
			// ��������õ���è������ʣ�����Ҷ��ӻ�û����
			while(M != 0 && i < N) { 
				if(M >= trade[i].f) { // ������õ�è�����ڿɻ���è��
					sum += trade[i].j; // ��ȫ����
					M -= trade[i].f;
				} else { // ��С�ڣ��򰴱�����è�������ұ����ǱȽϴ��
					sum += M * trade[i].ratio; // ע�����ľ���
					M = 0; // è��������
				}
				i++; // ��һ������
			}
			System.out.printf("%.3f",sum); // ��ȷ��С����3λ
			System.out.println();
		}
	}
}
