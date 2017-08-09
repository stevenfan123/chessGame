package alternative;

class Coordinates {

	final int x;
	final int y;

	Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	void display() {
		System.out.print(this.x);
		System.out.print(this.y + "\n");

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

	Coordinates addY(int i) {
		return new Coordinates(x, y + i);
	}

	Coordinates add(int i, int j) {
		return new Coordinates(x + i, y + j);
	}

	@Override
	public String toString() {
		return "Coordinates [x=" + x + ", y=" + y + "]";
	}

}
