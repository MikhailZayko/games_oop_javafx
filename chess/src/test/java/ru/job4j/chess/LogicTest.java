package ru.job4j.chess;

import org.junit.jupiter.api.Test;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;
import ru.job4j.chess.firuges.black.BishopBlack;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LogicTest {

    @Test
    public void whenMoveThenFigureNotFoundException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        FigureNotFoundException exception = assertThrows(FigureNotFoundException.class, () -> {
            logic.move(Cell.C1, Cell.H6);
        });
        assertThat(exception.getMessage()).isEqualTo("Figure not found on the board.");
    }

    @Test
    public void whenMoveThenOccupiedCellException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        Figure movingBishop = new BishopBlack(Cell.C8);
        Figure bishopOccupier = new BishopBlack(Cell.B7);
        logic.add(movingBishop);
        logic.add(bishopOccupier);
        OccupiedCellException exception = assertThrows(OccupiedCellException.class, () -> {
            logic.move(movingBishop.position(), Cell.A6);
        });
        assertThat(exception.getMessage()).isEqualTo("Cell is occupied");
    }

    @Test
    public void whenMoveThenImpossibleMoveException()
            throws FigureNotFoundException, OccupiedCellException, ImpossibleMoveException {
        Logic logic = new Logic();
        Figure movingBishop = new BishopBlack(Cell.C8);
        logic.add(movingBishop);
        ImpossibleMoveException exception = assertThrows(ImpossibleMoveException.class, () -> {
            logic.move(movingBishop.position(), Cell.B3);
        });
        assertThat(exception.getMessage()).isEqualTo("Could not move by diagonal from C8 to B3");
    }
}