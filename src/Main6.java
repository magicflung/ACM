import java.util.Scanner;

/** 1006
 * ������ʱ�룬���룬����ֱ������ǵĽ��ٶ�����ʾ����������������Ŀ���ʱ������Ľ������ɡ�
 * һ������3��ʱ���3��ָ���غϣ���0��00��12��00��24��00
 * ������ֻ��Ҫ������У���0��00-12��00������ָ�붼���ڿ���ʱ��ռ����ʱ��ı���
 * ���룺����ָ��ÿ���ƶ��ĽǶ�
 * H��ʱ����ٶȣ�360 /��12*60*60) = 1/120
 * M��������ٶȣ�360 / ��60*60) = 1/10
 * S��������ٶȣ�360 / 60 = 6 
 * �ж����ǽ��ٶ�����֮��Ĺ�ϵ��DΪ������D�ȵ�happy time,kΪȦ��
 * HM��di=|H-M|, D+360*k <= di <= 360*(k+1)-D, 0<=k<11
 * ����k�ķ�Χ��������ʱ��Ϊ���죬��ʱ��ת1Ȧ������ת12Ȧ������ת720Ȧ��
 * 			   ��ʱ��������Ȧ����ϵ��Ϊ��������12Сʱ����೬ʱ��11Ȧ��12-1��������ͬ��
 * HS��di=|H-S|, D+360*k <= di <= 360*(k+1)-D, 0<=k<719
 * MS��di=|M-S|, D+360*k <= di <= 360*(k+1)-D, 0<=k<708  
 * ��������������������Ŀ���ʱ�䣺
 * HM��di=|H-M|, (D+360*k)/di <= t1 <= (360*(k+1)-D), 0<=k<11
 * HS��di=|H-S|, (D+360*k)/di <= t2 <= (360*(k+1)-D), 0<=k<719
 * MS��di=|M-S|, (D+360*k)/di <= t3 <= (360*(k+1)-D), 0<=k<708 
 * Ȼ�������ǵĽ�������Ϊ���Ƕ����ֵ�ʱ�� 
 * @author MUSTACHE
 *
 */
public class Main6 {
	private static final double h = 1. / 120; 
	private static final double m = 1. / 10 ; 
	private static final double s = 6.		;
	private static double totaltime; // �洢����ָ�붼���ֵ�ʱ��
	// ��������
	private static double start1, start2, start3;
	private static double end1, end2, end3;
	public static double ff(double D) {
		totaltime = 0.0;
		for(int k1 = 0; k1 < 11; k1++) {
			// ���HM������
			double d1 = m - h;
			start1 = (360 * k1 + D) / d1;
			end1 = (360 * (k1+1) - D) / d1;
			for(int k2 = 0; k2 < 708; k2++) {
				// ���MS������(�������ĸ��ȶ��У�ֻ�����ѷ�Χ�Ƚ�С�ķ���ǰ��)
				double d2 = s - m;
				start2 = (360 * k2 + D) / d2;
				end2 = (360 * (k2+1) - D) / d2;
				// ��ǰ��������Ľ���
				double tempstart1 = Math.max(start1, start2);
				double tempend1 = Math.min(end1, end2);
				if(tempstart1 >= tempend1) { // ˵��ǰ����û�н��������ü�����������Ϊ����Ҫ���Ƕ�����
					continue;
				}
				for(int k3 = 0; k3 < 719; k3++) {
					double d3 = s - h;
					start3 = (360 * k3 + D) / d3;
					end3 = (360 * (k3+1) - D) / d3;
					double tempstart2 = Math.max(tempstart1, start3);
					double tempend2 = Math.min(tempend1, end3);
					if(tempstart2 < tempend2) { // ����ָ��Ľ���
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
			System.out.printf("%.3f", time); // ע��û�л���
			System.out.println();
		}
	}
}
