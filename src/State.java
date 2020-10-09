import java.util.Arrays;

public class State
{
	int N;
	int[] pos;

	public State(int[] arr, int n) {
		N = n;
		pos = Arrays.copyOf(arr, n);
	}

	public boolean equals(Object o) {
		return Arrays.equals( pos, ((State) o).pos );
	}

	public int hashCode() {
		return pos.hashCode();
	}

	public String toString() {
		return Arrays.toString(pos);
	}
}