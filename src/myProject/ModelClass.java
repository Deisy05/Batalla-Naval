package myProject;

/**
 * This class is designed in order to view ModelClass
 *
 * @author borja.carlos@correounivalle.edu.co
 *         deisy.melo@correounivalle.edu.co
 * @version v.2.0 date: 30/12/2024
 */
public class ModelClass {
    private static final int BOARD_SIZE = 10;
    private static final int TOTAL_SHIP_CELLS = 20;

    private final String[][] userBoard;
    private final String[][] machineBoard;
    private final String[][] userTrackingBoard;
    private final String[][] machineTrackingBoard;
    private final Machine machine;

    private String errorMessage;
    private int userHitCount = 0;
    private int machineHitCount = 0;

    public ModelClass() {
        userBoard = initializeBoard();
        machineBoard = initializeBoard();
        userTrackingBoard = initializeBoard();
        machineTrackingBoard = initializeBoard();
        machine = new Machine();
    }

    private String[][] initializeBoard() {
        String[][] board = new String[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = "";
            }
        }
        return board;
    }

    public boolean placeUserShip(int x, int y, String orientation, String shipType) {
        return placeShip(userBoard, x, y, orientation, shipType);
    }

    private boolean placeShip(String[][] board, int x, int y, String orientation, String shipType) {
        if (!isValidPosition(board, x, y)) {
            errorMessage = "Invalid position";
            return false;
        }

        int shipSize = getShipSize(shipType);
        if (!canPlaceShip(board, x, y, orientation, shipSize)) {
            return false;
        }

        placeShipOnBoard(board, x, y, orientation, shipType, shipSize);
        return true;
    }

    private boolean isValidPosition(String[][] board, int x, int y) {
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE && board[x][y].isEmpty();
    }

    private boolean canPlaceShip(String[][] board, int x, int y, String orientation, int shipSize) {
        if (orientation.equals("horizontal")) {
            if (x + shipSize > BOARD_SIZE) {
                errorMessage = "Ship extends beyond board horizontally";
                return false;
            }
            return checkHorizontalPlacement(board, x, y, shipSize);
        } else {
            if (y + shipSize > BOARD_SIZE) {
                errorMessage = "Ship extends beyond board vertically";
                return false;
            }
            return checkVerticalPlacement(board, x, y, shipSize);
        }
    }

    private boolean checkHorizontalPlacement(String[][] board, int x, int y, int shipSize) {
        for (int i = x; i < x + shipSize; i++) {
            if (!board[i][y].isEmpty()) {
                errorMessage = "Position already occupied";
                return false;
            }
        }
        return true;
    }

    private boolean checkVerticalPlacement(String[][] board, int x, int y, int shipSize) {
        for (int i = y; i < y + shipSize; i++) {
            if (!board[x][i].isEmpty()) {
                errorMessage = "Position already occupied";
                return false;
            }
        }
        return true;
    }

    private void placeShipOnBoard(String[][] board, int x, int y, String orientation, String shipType, int size) {
        String alignmentCode = orientation.equals("horizontal") ? "H" : "V";
        for (int i = 0; i < size; i++) {
            String cellValue = String.format("%s.%s.%d", shipType, alignmentCode, i + 1);
            if (orientation.equals("horizontal")) {
                board[x + i][y] = cellValue;
            } else {
                board[x][y + i] = cellValue;
            }
        }
    }

    public boolean processUserShot(int x, int y) {
        if (!isValidShot(x, y)) {
            return false;
        }

        String targetCell = machineBoard[x][y];
        if (targetCell.equals("agua") || targetCell.equals("hundido") ||
                targetCell.contains(".T")) {
            // La celda ya fue disparada antes, ignoramos el tiro
            return false;
        }

        boolean hit = processShotOnBoard(machineBoard, userTrackingBoard, x, y);
        if (hit) {
            userHitCount++;
        }
        return !hit;
    }

    private boolean isValidShot(int x, int y) {
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE;
    }

    private boolean processShotOnBoard(String[][] targetBoard, String[][] trackingBoard, int x, int y) {
        String targetCell = targetBoard[x][y];

        if (targetCell.isEmpty()) {
            targetBoard[x][y] = "agua";
            trackingBoard[x][y] = "agua";
            return false;
        }

        if (targetCell.equals("agua") || targetCell.equals("hundido")) {
            return false;
        }

        if (targetCell.contains(".") && !targetCell.contains(".T")) {
            String shipType = targetCell.substring(0, targetCell.indexOf("."));
            String rest = targetCell.substring(targetCell.indexOf("."));
            targetBoard[x][y] = shipType + ".T" + rest;

            if (checkIfSunk(targetBoard, x, y)) {
                markShipAsSunk(targetBoard, trackingBoard, x, y);
            } else {
                trackingBoard[x][y] = "tocado";
            }
            return true;
        }
        return false;
    }

    private boolean checkIfSunk(String[][] board, int x, int y) {
        String cellInfo = board[x][y];
        if (!cellInfo.contains(".")) {
            return false;
        }

        String shipType = cellInfo.substring(0, cellInfo.indexOf("."));
        int shipSize = getShipSize(shipType);

        return shipSize == 1 || checkAllPartsHit(board, x, y, cellInfo);
    }

    private boolean checkAllPartsHit(String[][] board, int x, int y, String cellInfo) {
        String orientation = cellInfo.substring(cellInfo.indexOf(".") + 3, cellInfo.indexOf(".") + 4);
        int partNumber = Integer.parseInt(cellInfo.substring(cellInfo.lastIndexOf(".") + 1));
        String shipType = cellInfo.substring(0, cellInfo.indexOf("."));
        int shipSize = getShipSize(shipType);
        int startPos = orientation.equals("H") ? x - partNumber + 1 : y - partNumber + 1;

        for (int i = 0; i < shipSize; i++) {
            String checkCell = orientation.equals("H") ?
                    board[startPos + i][y] : board[x][startPos + i];
            if (!checkCell.contains(".T")) {
                return false;
            }
        }
        return true;
    }

    private void markShipAsSunk(String[][] targetBoard, String[][] trackingBoard, int x, int y) {
        String cellInfo = targetBoard[x][y];
        String shipType = cellInfo.substring(0, cellInfo.indexOf("."));
        String orientation = cellInfo.substring(cellInfo.indexOf(".") + 3, cellInfo.indexOf(".") + 4);
        int partNumber = Integer.parseInt(cellInfo.substring(cellInfo.lastIndexOf(".") + 1));
        int shipSize = getShipSize(shipType);
        int startPos = orientation.equals("H") ? x - partNumber + 1 : y - partNumber + 1;

        for (int i = 0; i < shipSize; i++) {
            if (orientation.equals("H")) {
                targetBoard[startPos + i][y] = "hundido";
                trackingBoard[startPos + i][y] = "hundido";
            } else {
                targetBoard[x][startPos + i] = "hundido";
                trackingBoard[x][startPos + i] = "hundido";
            }
        }
    }

    private int getShipSize(String shipType) {
        return switch (shipType) {
            case "portaaviones" -> 4;
            case "submarino" -> 3;
            case "destructor" -> 2;
            case "fragata" -> 1;
            default -> throw new IllegalArgumentException("Invalid ship type: " + shipType);
        };
    }

    public void processMachineMove() {
        int x = machine.getDisparoX();
        int y = machine.getDisparoY();
        processShotOnBoard(userBoard, machineTrackingBoard, x, y);
        machine.generarDisparos(machineTrackingBoard);
    }

    public boolean hasWinner() {
        return userHitCount >= TOTAL_SHIP_CELLS || machineHitCount >= TOTAL_SHIP_CELLS;
    }

    public String getWinner() {
        if (machineHitCount >= TOTAL_SHIP_CELLS) {
            return "maquina";
        } else if (userHitCount >= TOTAL_SHIP_CELLS) {
            return "usuario";
        }
        return "";
    }

    public void ingresarBarcosMaquina() {
        for (int i = 0; i < 10; i++) {
            String shipType = machine.getBarco();
            while (!placeShip(machineBoard, machine.getCoordenadaX(),
                    machine.getCoordenadaY(), machine.getOrientacion(), shipType));
        }
    }

    // Getters
    public String getErrorMessage() { return errorMessage; }
    public String[][] getUserBoard() { return userBoard; }
    public String[][] getMachineBoard() { return machineBoard; }
    public String[][] getUserTrackingBoard() { return userTrackingBoard; }
}