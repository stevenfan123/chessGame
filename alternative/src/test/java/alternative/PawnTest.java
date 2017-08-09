package alternative;

import static alternative.Team.WHITE;

import java.util.Iterator;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class PawnTest {

	@Test
	public void test() {
		Pawn pawn = new Pawn(WHITE);
		pawn.setCoordinates(newCoordinates(1, 1));
		Set<Coordinates> permissibleMoves = pawn.permissibleMoves(null);
		Iterator<Coordinates> iterator = permissibleMoves.iterator();
		Coordinates first = iterator.next();
		Assert.assertEquals(new Coordinates(1, 1), first);
	}

	private Coordinates newCoordinates(int i, int j) {
		return new Coordinates(i, j);
	}

}
