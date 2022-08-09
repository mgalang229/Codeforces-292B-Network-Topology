import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/*

bus topology matrix:
  1 2 3 4
1 0 1 0 0 
2 1 0 1 0 
3 0 1 0 1 
4 0 0 1 0 

ring topology matrix:
  1 2 3 4
1 0 1 0 1 
2 1 0 1 0 
3 0 1 0 1 
4 1 0 1 0 

star topology matrix:
  1 2 3 4
1 0 1 1 1 
2 1 0 0 0 
3 1 0 0 0 
4 1 0 0 0 

 */

public class AuthorSol {
	
	public static void main(String[] args) {	
		FastScanner fs = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int T = 1;
		//T = fs.nextInt();
		for (int tc = 0; tc < T; tc++) {
			int n = fs.nextInt();
			int m = fs.nextInt();
			int[] counter = new int[n];
			Arrays.fill(counter, 0);
			for (int i = 0; i < m; i++) {
				int u = fs.nextInt();
				int v = fs.nextInt();
				counter[u-1]++;
				counter[v-1]++;
			}
			String type = "unknown";
			if (isBusTopo(counter)) {
				type = "bus";
			} else if (isRingTopo(counter)) {
				type = "ring";
			} else if (isStarTopo(counter)) {
				type = "star";
			}
			out.println(type + " topology");
		}
		out.close();
	}
	
	static boolean isBusTopo(int[] a) {
		int x = 0;
		int y = 0;
		int n = a.length;
		for (int i = 0; i < n; i++) {
			if (a[i] == 2) {
				x++;
			} else if (a[i] == 1) {
				y++;
			}
		}
		return (x + y == n && y == 2 && x == n - y);
	}
	
	static boolean isRingTopo(int[] a) {
		int x = 0;
		int n = a.length;
		for (int i = 0; i < n; i++) {
			if (a[i] == 2) {
				x++;
			}
		}
		return (x == n);
	}
	
	static boolean isStarTopo(int[] a) {
		int x = 0;
		int y = 0;
		int n = a.length;
		for (int i = 0; i < n; i++) {
			if (a[i] == 1) {
				x++;
			} else {
				y++;
			}
		}
		return (x + y == n && y == 1 && x == n - y);
	}
	
	static void sort(int[] a) {
		ArrayList<Integer> arr = new ArrayList<>();
		for (int x : a) {
			arr.add(x);
		}
		Collections.sort(arr);
		for (int i = 0; i < a.length; i++) {
			a[i] = arr.get(i);
		}
	}
	
	static void swap(int[] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;
	}
	
	static class FastScanner {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		String next() {
			while (!st.hasMoreTokens()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}
		
		int nextInt() {
			return Integer.parseInt(next());
		}
		
		int[] readArray(int n) {
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextInt();
			}
			return a;
		}
		
		long[] readLongArray(int n) {
			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = nextLong();
			}
			return a;
		}
		
		long nextLong() {
			return Long.parseLong(next());
		}
		
		double nextDouble() {
			return Double.parseDouble(next());
		}
		
		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			} catch(IOException e) {
				e.printStackTrace();
			}
			return str;
		}
	}
}
