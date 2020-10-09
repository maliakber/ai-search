import java.util.HashSet;
import java.util.Set;

public class ProblemMissionariesCannibals extends Problem {
    
	boolean goal_test(Object state) {
		State cur = (State) state;
		for(int i=0; i<cur.N; i++)
		{
			if(cur.pos[i] != 0)
				return false;
		}
		return true;
	}

    Set<Object> getSuccessors(Object state) {
		Set<Object> successors = new HashSet<Object>();
		State cur = (State)state;
		int[] arr = cur.pos;
		if(arr[0] == 1) // boat on right side
		{
			arr[0] = 0;
			// transfer 1 missionary
			if(arr[1] > 0)
			{
				arr[1] -= 1;
				if(isValid(arr))
					successors.add(new State(arr, cur.N));
				arr[1] += 1;
			}
			// transfer 1 cannibal
			if(arr[2] > 0)
			{
				arr[2] -= 1;
				if(isValid(arr))
					successors.add(new State(arr, cur.N));
				arr[2] += 1;
			}
			// transfer 1 missionary and 1 cannibal
			if(arr[1] > 0 && arr[2] > 0)
			{
				arr[1] -= 1;
				arr[2] -= 1;
				if(isValid(arr))
					successors.add(new State(arr, cur.N));
				arr[1] += 1;
				arr[2] += 1;
			}
			// transfer 2 missionaries
			if(arr[1] > 1)
			{
				arr[1] -= 2;
				if(isValid(arr))
					successors.add(new State(arr, cur.N));
				arr[1] += 2;
			}
			// transfer 2 cannibals
			if(arr[2] > 1)
			{
				arr[2] -= 2;
				if(isValid(arr))
					successors.add(new State(arr, cur.N));
				arr[2] += 2;
			}
			arr[0] = 1;
		}
		else
		{
			arr[0] = 1;
			// transfer 1 missionary
			if(3 - arr[1] > 0)
			{
				arr[1] += 1;
				if(isValid(arr))
					successors.add(new State(arr, cur.N));
				arr[1] -= 1;
			}
			// transfer 1 cannibal
			if(3 - arr[2] > 0)
			{
				arr[2] += 1;
				if(isValid(arr))
					successors.add(new State(arr, cur.N));
				arr[2] -= 1;
			}
			// transfer 1 missionary and 1 cannibal
			if(3 - arr[1] > 0 && 3 - arr[2] > 0)
			{
				arr[1] += 1;
				arr[2] += 1;
				if(isValid(arr))
					successors.add(new State(arr, cur.N));
				arr[1] -= 1;
				arr[2] -= 1;
			}
			// transfer 2 missionaries
			if(3 - arr[1] > 1)
			{
				arr[1] += 2;
				if(isValid(arr))
					successors.add(new State(arr, cur.N));
				arr[1] -= 2;
			}
			// transfer 2 cannibals
			if(3 - arr[2] > 1)
			{
				arr[2] += 2;
				if(isValid(arr))
					successors.add(new State(arr, cur.N));
				arr[2] -= 2;
			}
			arr[0] = 0;
		}

		return successors;
    }

	private boolean isValid(int[] pos){
		int mis_right= pos[1];
		int can_right = pos[2];
		int mis_left = 3 - mis_right;
		int can_left = 3 - can_right;
		if(mis_left != 0 && mis_left < can_left) // if boat is on right side, we check left
			return false;
		else if(mis_right != 0 &&  mis_right < can_right)
			return false;
		return true;
	}
	
	double step_cost(Object fromState, Object toState) { return 1; }

	public double h(Object state) {
		State cur = (State)state;
		return cur.pos[1] + cur.pos[2];
	}

	public static void main(String[] args) throws Exception {
		ProblemMissionariesCannibals problem = new ProblemMissionariesCannibals();
		problem.initialState = new State(new int[]{1, 3, 3}, 3);
		
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
