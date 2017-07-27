package chessAttempt;

public class Coordinates {

	public int x;
	public int y;

	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void display() {
		System.out.print(this.x);
		System.out.print(this.y + "\n");
		
	}
	
	@Override
	public boolean equals(Object other) {
	    if (!(other instanceof Coordinates)) {
	        return false;
	    }

	    Coordinates that = (Coordinates) other;

	    // Custom equality check here.
	    return this.x==that.x && this.y == that.y;
	}
	

}
