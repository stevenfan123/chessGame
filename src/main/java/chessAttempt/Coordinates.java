package chessAttempt;

public class Coordinates {

	public int x;
	public int y;

	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void display() {
		System.out.print(x);
		System.out.print(y + "\n");
		
	}
	




	@Override
	public String toString() {
		return "Coordinates [x=" + x + ", y=" + y + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinates other = (Coordinates) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public Coordinates add(int i, int j) {
		return new Coordinates(x+i,y+j);
	}
	

}
