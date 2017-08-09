package alternative;

import java.util.LinkedHashSet;
import java.util.Set;

abstract class GamePiece {

	final Team team;
	protected Coordinates coordinates;

	protected GamePiece(Team team) {
		super();
		this.team = team;
	}

	Coordinates getCoordinates() {
		return coordinates;
	}

	void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	abstract char display();

	abstract Set<Coordinates> permissibleMoves(GameBoard gameBoard);

}

class Pawn extends GamePiece {

	protected Pawn(Team team) {
		super(team);
	}

	@Override
	Set<Coordinates> permissibleMoves(GameBoard gameBoard) {
		Set<Coordinates> result = new LinkedHashSet<>();
		result.add(coordinates.addY(1));
		result.add(coordinates.addY(2));
		result.add(coordinates.add(-1, 1));
		result.add(coordinates.add(1, 1));
		return result;
	}

	@Override
	char display() {
		return team.equals(Team.WHITE) ? 'P' : 'p';
	}

}