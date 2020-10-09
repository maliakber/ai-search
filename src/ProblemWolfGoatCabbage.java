import java.util.HashSet;
import java.util.Set;

public class ProblemWolfGoatCabbage extends Problem {
    
	boolean goal_test(Object state) {
		State cur = (State)state;
		for(int i=0; i< cur.N; i++)
		{
			if(cur.pos[i] == 1)
				return false;
		}
		return true;
	}

    Set<Object> getSuccessors(Object state) {
		Set<Object> successors = new HashSet<Object>();
		State cur = (State)state;
		int[] arr = cur.pos;
		// change man
		arr[0] = (arr[0] + 1) % 2;
		if(isValid(arr))
			successors.add(new State(arr, cur.N));
		arr[0] = (arr[0] + 1) % 2; // revert last operation
		// change man and wolf
		if(arr[0] == arr[1])
		{
			arr[0] = (arr[0] + 1) % 2;
			arr[1] = (arr[1] + 1) % 2;
			if(isValid(arr))
				successors.add(new State(arr, cur.N));
			// revert last operations
			arr[0] = (arr[0] + 1) % 2;
			arr[1] = (arr[1] + 1) % 2;
		}
		// change man and goat
		if(arr[0] == arr[2])
		{
			arr[0] = (arr[0] + 1) % 2;
			arr[2] = (arr[2] + 1) % 2;
			if(isValid(arr))
				successors.add(new State(arr, cur.N));
			// revert last operations
			arr[0] = (arr[0] + 1) % 2;
			arr[2] = (arr[2] + 1) % 2;
		}
		// change man and cabbage
		if(arr[0] == arr[3])
		{
			arr[0] = (arr[0] + 1) % 2;
			arr[3] = (arr[3] + 1) % 2;
			if(isValid(arr))
				successors.add(new State(arr, cur.N));
			// revert last operations
			arr[0] = (arr[0] + 1) % 2;
			arr[3] = (arr[3] + 1) % 2;
		}
		return successors;
    }

	private boolean isValid(int[] pos){
		if(pos[0] == pos[1]) // man and wolf same side
			return true;
		else if(pos[1] == pos[2] && pos[1] == pos[3]) // wolf, goat and cabbage same side
			return true;
		else if(pos[0] == pos[2] && pos[0] == pos[3]) // man, goat and cabbage same side
			return true;
		return false;
	}
	
	double step_cost(Object fromState, Object toState) { return 1; }

	public double h(Object state) {
		State cur = (State)state;
		return cur.pos[0] + cur.pos[1] + cur.pos[2] + cur.pos[3];
	}


	public static void main(String[] args) throws Exception {
		ProblemWolfGoatCabbage problem = new ProblemWolfGoatCabbage();
		problem.initialState = new State(new int[]{1, 1, 1, 1}, 4);
		
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
