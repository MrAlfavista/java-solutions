package game;

public interface Position {
    Cell getTurn();

    int getRows();

    int getColumns();

    boolean isValid(Move move);

    Cell getCell(int row, int column);
}

