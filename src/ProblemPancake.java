import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ProblemPancake extends Problem {
    
	boolean goal_test(Object state) {
		State cur = (State)state;
		for(int i=0; i<cur.N; i++)
		{
			if(cur.pos[i] != i)
				return false;
		}
		return true;
	}

    Set<Object> getSuccessors(Object state) {
		Set<Object> successors = new HashSet<Object>();
		State cur = (State)state;
		int[] arr = cur.pos;
		for(int i=1; i<cur.N; i++)
		{
			int[] temp = Arrays.copyOf(arr, cur.N);
			swap(temp, 0, i);
			successors.add(new State(temp, cur.N));
		}
		return successors;
    }

    private void swap(int[] arr, int x, int y)
	{
		while(x < y)
		{
			int temp = arr[x];
			arr[x] = arr[y];
			arr[y] = temp;
			x++; y--;
		}
	}
	
	double step_cost(Object fromState, Object toState) { return 1; }

	public double h(Object state) {
		State cur = (State)state;
		int gap = 0;
		for(int i=1; i<cur.N; i++)
			if(Math.abs(cur.pos[i] - cur.pos[i-1]) != 1)
				gap++;
		return gap;
	}


	public static void main(String[] args) throws Exception {
		ProblemPancake problem = new ProblemPancake();
		problem.initialState = new State(new int[]{1, 0, 3, 5, 2, 4}, 6);
		
		Search search  = new Search(problem);
		
		System.out.println("TreeSearch------------------------");
		System.out.println("BreadthFirstTreeSearch:\t\t" + search.BreadthFirstTreeSearch());
		System.out.println("UniformCostTreeSearch:\t\t" + search.UniformCostTreeSearch());
		System.out.println("DepthFirstTreeSearch:\t\t" + search.DepthFirstTreeSearch());
		System.out.println("GreedyBestFirstTreeSearch:\t" + search.GreedyBestFirstTreeSearch());
		System.out.println("AstarTreeSearch:\t\t" + search.AstarTreeSearch());
		
		System.out.println("\n\nGraphSearch----------------------");
		System.out.println("BreadthFirstGraphSearch:\t" + search.BreadthFirstGraphSearch());
		System.out.println("UniformCostGraphSearch:\t\t" + search.UniformCostGraphSearch());
		System.out.println("DepthFirstGraphSearch:\t\t" + search.DepthFirstGraphSearch());
		System.out.println("GreedyBestGraphSearch:\t\t" + search.GreedyBestFirstGraphSearch());
		System.out.println("AstarGraphSearch:\t\t" + search.AstarGraphSearch());
		
		System.out.println("\n\nIterativeDeepening----------------------");
		System.out.println("IterativeDeepeningTreeSearch:\t" + search.IterativeDeepeningTreeSearch());
		System.out.println("IterativeDeepeningGraphSearch:\t" + search.IterativeDeepeningGraphSearch());
	}
	
}
