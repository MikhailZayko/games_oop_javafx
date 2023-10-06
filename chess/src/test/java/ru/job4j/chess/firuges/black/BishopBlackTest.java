package ru.job4j.chess.firuges.black;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BishopBlackTest {

    @Test
    public void positionTest() {
        Figure bishop = new BishopBlack(Cell.F8);
        Cell expected = Cell.F8;
        Cell result = bishop.position();
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void copyTest() {
        Figure bishop = new BishopBlack(Cell.C8);
        Figure copyBishop = bishop.copy(bishop.position());
        Cell expected = Cell.C8;
        Cell result = copyBishop.position();
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenWayC1ToG5ThenD2E3F4G5() {
        Figure bishop = new BishopBlack(Cell.C1);
        Cell[] expected = {Cell.D2, Cell.E3, Cell.F4, Cell.G5};
        Cell[] result = bishop.way(Cell.G5);
        assertThat(result).containsExactly(expected);
    }

    @Test
    public void whenWayB2ToD6ThenException() {
        Figure bishop = new BishopBlack(Cell.B2);
        ImpossibleMoveException exception = assertThrows(
                ImpossibleMoveException.class,
                () -> bishop.way(Cell.D6)
        );
        String expected = "Could not move by diagonal from B2 to D6";
        assertThat(exception.getMessage()).isEqualTo(expected);
    }
}