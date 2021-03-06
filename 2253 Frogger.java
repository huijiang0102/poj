import java.io.*;
import java.util.*;
import java.math.*;

class Node {
	double x;
	double y;
	Node(double x, double y) {
		this.x = x;
		this.y = y;
	}
}
public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int testcase = 1;
		while (true) {
			int nodeNumber = Integer.parseInt(in.nextLine());
			if (nodeNumber == 0) {
				System.exit(0);
			}
			double[][] map = new double[nodeNumber + 1][nodeNumber + 1];
			Node[] nodes = new Node[nodeNumber + 1];
			for (int i = 1; i <= nodeNumber; ++i) {
				String[] strs = in.nextLine().split("\\s+");
				double x = Double.parseDouble(strs[0]);
				double y = Double.parseDouble(strs[1]);
				nodes[i] = new Node(x, y);
			}
			in.nextLine();
			for (int i = 1; i <= nodeNumber; ++i) {
				for (int j = 1; j <= i - 1; ++j) {
					double distance = distance(nodes, i, j);
					map[i][j] = distance;
					map[j][i] = distance;
				}
			}
			System.out.println("Scenario #" + testcase++);
			dijkstra(map, nodes);
		}
	}
	private static double distance(Node[] nodes, int x, int y) {
		return Math.sqrt((nodes[x].x - nodes[y].x) * (nodes[x].x - nodes[y].x) + (nodes[x].y - nodes[y].y) * (nodes[x].y - nodes[y].y));
	}
	private static void dijkstra(double[][] distance, Node[] nodes) {
		boolean[] visited = new boolean[nodes.length];
		double[] dis = new double[nodes.length];
		for (int i = 1; i < nodes.length; ++i) {
			dis[i] = distance[1][i];
		}
		visited[1] = true;
		int rst = 0;
		for (int i = 2; i < nodes.length; ++i) {
			double min = Double.POSITIVE_INFINITY;
			for (int j = 1; j < nodes.length; ++j) {
				if (!visited[j] && min > dis[j]) {
					min = dis[j];
					rst = j;
				}
			}
			visited[rst] = true;
			for (int j = 1; j < nodes.length; ++j) {
				dis[j] = Math.min(dis[j], Math.max(dis[rst], distance[rst][j]));
			}
		}
		System.out.print(String.format("Frog Distance = %.3f\n\n", dis[2]));
	}
}