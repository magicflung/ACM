import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/** 1004
 * @author MUSTACHE
 *
 */
public class Main4 {

    public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Map<String, Integer> map;
		List<String> list = new ArrayList<String>();
		int N = input.nextInt();
		int max;
		while(N != 0) {
			max = Integer.MIN_VALUE;
			String re = null;
			map = new HashMap<String, Integer>();
			Integer j;
			for(int i = 0; i < N; i++) {
				String str = input.next();
				j = map.get(str);
				if(j == null) {
					map.put(str, 0);
					j = 0;
				} else {
					map.put(str, ++j);
				}
				if(max < j) {
					re = str;
					max = j;
				}
			}
			list.add(re);
			N = input.nextInt();
		}
		for(String i : list) {
			System.out.println(i);
		}
	}
    
    // 下面的不知道为什么在oj提交会错误，测试结果都没有错。。。
//	private static List<String> result = new ArrayList<String>();
//	public static void main(String[] args) {
//		Scanner input = new Scanner(System.in);
//		char[][] color;
//		int[] len;
//		boolean[] flag;
//		int N = input.nextInt();
//		while(N != 0) {
//			len = new int[N];
//			flag = new boolean[N];
//			color = new char[1001][16];
//			for(int i = 0; i < N; i++) {
//				String a = input.next();
//				for(int j = 0; j < a.length(); j++) {
//					color[i][j] = a.charAt(j);
//				}
//				flag[i] = false;
//				len[i] = a.length();
//			}
//			int count = 1;
//			int max = Integer.MIN_VALUE;
//			int maxIndex = 0;
//			for(int i = 0; i < N-1; i++) {
//				if(flag[i] == true) {
//					continue;
//				}
//				flag[i] = true;
//				for(int j = i+1; j < N; j++) {
//					if(flag[j] == true) {
//						continue;
//					}
//					if(isEqual(color[i],0,len[i],color[j],0,len[j])) {
//						count++;
//						flag[j] = true;
//					}
//				}
//				if(count > max) {
//					maxIndex = i;
//					max = count;
//				}
//				count = 1;
//			}
//			result.add(String.valueOf(color[maxIndex]));
//			N = input.nextInt();
//		}
//		
//		for(String str : result) {
//			System.out.println(str);
//		}
//	}
//	public static boolean isEqual(char[] ch1, int start1, int end1, char[] ch2, int start, int end2) {
//		if(end1 != end2) {
//			return false;
//		}
//		int i = 0;
//		while(i < end1) {
//			if(ch1[i] != ch2[i]) {
//				return false;
//			}
//			i++;
//		}
//		return true;
//	}
}
