package test.java;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Test;

import chessAttempt.Coordinates;

public class OptionalExample {

	@Test
	public void test() {
		Optional<Coordinates> posMove = Optional.empty();
//		posMove = Optional.of(new Coordinates(1, 1));
		posMove.ifPresent(x -> x.display());
	} 

}
