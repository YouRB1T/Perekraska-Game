import java.util.Arrays;
import java.util.Random;

public class Logic {
    public static int[][] winArray;
    public static int maxRow;
    public static int maxColum;
    public static int[][] makeRandomArray(int colums, int rows){
        int[][] answer = new int[rows][colums];
        Random random = new Random();

        winArray = new int[rows][colums];
        maxColum = colums - 1;
        maxRow = rows - 1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < colums; j++){
                answer[i][j] = random.nextInt(5);
            }
        }

        answer[0][0] = 0;
        winArray[0][0] = 1;

        return answer;
    }

    public static boolean endGame(){
        boolean ans = true;
        for (int i = 0; i < winArray.length; i++){
            for (int j = 0; j < winArray[0].length; j++){
                if (winArray[i][j] != 1){
                    ans = false;
                }
            }
        }
        return ans;
    }

    public static int[][] changeColore(int[][] array, int n){
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array[0].length; j++){
                if (winArray[i][j] == 1){
                    array[i][j] = n;
                }
            }
        }
        return array;
    }
    private static int getCase(int row, int colum, int maxRow, int maxColum) {
        if (row == 0 && colum == 0) {
            return 1;
        } else if (colum == 0 && row == maxRow) {
            return 2;
        } else if (row == 0 && colum == maxColum) {
            return 3;
        } else if (colum == 0) {
            return 4;
        } else if (row == 0) {
            return 5;
        } else if (colum == maxColum && row > 0) {
            return 6;
        } else if (row == maxRow && colum > 0) {
            return 7;
        } else {
            return 8;
        }
    }

    /*  New logic   */
    public static int[][] rules(int[][] array){
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array[0].length; j++){
                int[] cell = {i, j};
                if (hesNearOne(cell)){
                    makeNewWinArray(array,cell, findPositionOf1(cell));
                }
            }
        }
        return array;
    }
    public static void makeNewWinArray(int[][] array, int[] cell, int[] cell2){
        if(array[cell[0]][cell[1]] == array[cell2[0]][cell2[1]]){
            winArray[cell[0]][cell[1]] = 1;
        }
    }
    public static int[] findPositionOf1(int[] cell){
        int[] answerCell = new int[2];
        int row = cell[0];
        int colum = cell[1];
        switch (getCase(row, colum, maxRow, maxColum)) {
            case 1:
                break;
            case 2:
                if (winArray[row][colum + 1] == 1) {
                    answerCell[0] = row;
                    answerCell[1] = colum + 1;
                    return answerCell;
                }
                if (winArray[row - 1][colum] == 1) {
                    answerCell[0] = row - 1;
                    answerCell[1] = colum;
                    return answerCell;
                }
                break;
            case 3:

                if (winArray[row][colum - 1] == 1) {
                    answerCell[0] = row;
                    answerCell[1] = colum - 1;
                    return answerCell;
                }
                if (winArray[row + 1][colum] == 1) {
                    answerCell[0] = row + 1;
                    answerCell[1] = colum;
                    return answerCell;
                }

                break;
            case 4:
                if (winArray[row + 1][colum] == 1) {
                    answerCell[0] = row + 1;
                    answerCell[1] = colum;
                    return answerCell;
                }
                if (winArray[row - 1][colum] == 1) {
                    answerCell[0] = row - 1;
                    answerCell[1] = colum;
                    return answerCell;
                }
                if (winArray[row][colum + 1] == 1) {
                    answerCell[0] = row;
                    answerCell[1] = colum + 1;
                    return answerCell;
                }
                break;
            case 5:
                if (winArray[row][colum - 1] == 1) {
                    answerCell[0] = row;
                    answerCell[1] = colum - 1;
                    return answerCell;
                }
                if (winArray[row + 1][colum] == 1) {
                    answerCell[0] = row + 1;
                    answerCell[1] = colum;
                    return answerCell;
                }
                if (winArray[row - 1][colum] == 1) {
                    answerCell[0] = row - 1;
                    answerCell[1] = colum;
                    return answerCell;
                }
                break;
            case 6:
                if (winArray[row][colum - 1] == 1) {
                    answerCell[0] = row;
                    answerCell[1] = colum - 1;
                    return answerCell;
                }
                if (winArray[row - 1][colum] == 1) {
                    answerCell[0] = row - 1;
                    answerCell[1] = colum;
                    return answerCell;
                }
                if (winArray[row + 1][colum] == 1) {
                    answerCell[0] = row + 1;
                    answerCell[1] = colum;
                    return answerCell;
                }
                break;
            case 7:
                if (winArray[row][colum - 1] == 1) {
                    answerCell[0] = row;
                    answerCell[1] = colum - 1;
                    return answerCell;
                }
                if (winArray[row - 1][colum] == 1) {
                    answerCell[0] = row - 1;
                    answerCell[1] = colum;
                    return answerCell;
                }
                if (winArray[row][colum + 1] == 1) {
                    answerCell[0] = row;
                    answerCell[1] = colum + 1;
                    return answerCell;
                }
                break;
            case 8:
                if (winArray[row + 1][colum] == 1) {
                    answerCell[0] = row + 1;
                    answerCell[1] = colum;
                    return answerCell;
                }
                if (winArray[row - 1][colum] == 1) {
                    answerCell[0] = row - 1;
                    answerCell[1] = colum;
                    return answerCell;
                }
                if (winArray[row][colum + 1] == 1) {
                    answerCell[0] = row;
                    answerCell[1] = colum + 1;
                    return answerCell;
                }
                if (winArray[row][colum - 1] == 1) {
                    answerCell[0] = row;
                    answerCell[1] = colum - 1;
                    return answerCell;
                }



        }
        return answerCell;
    }


    public static boolean hesNearOne(int[] cell){
        int row = cell[0];
        int colum = cell[1];
        switch (getCase(row, colum, maxRow, maxColum)) {
            case 1:
                break;
            case 2:
                if (winArray[row][colum + 1] == 1) {
                    return true;
                }
                if (winArray[row - 1][colum] == 1) {
                    return true;
                }
                break;
            case 3:

                if (winArray[row][colum - 1] == 1) {
                    return true;
                }
                if (winArray[row + 1][colum] == 1) {
                    return true;
                }

                break;
            case 4:
                if (winArray[row + 1][colum] == 1) {
                    return true;
                }
                if (winArray[row - 1][colum] == 1) {
                    return true;
                }
                if (winArray[row][colum + 1] == 1) {
                    return true;
                }
                break;
            case 5:
                if (winArray[row][colum - 1] == 1) {
                    return true;
                }
                if (winArray[row + 1][colum] == 1) {
                    return true;
                }
                if (row > 0 && winArray[row - 1][colum] == 1) {
                    return true;
                }
                break;
            case 6:
                if (winArray[row][colum - 1] == 1) {
                    return true;
                }
                if (winArray[row - 1][colum] == 1) {
                    return true;
                }
                if (row < winArray[0].length - 1 && winArray[row + 1][colum] == 1) {
                    return true;
                }
                break;
            case 7:
                if (winArray[row][colum - 1] == 1) {
                    return true;
                }
                if (winArray[row - 1][colum] == 1) {
                    return true;
                }
                if (winArray[row][colum + 1] == 1) {
                    return true;
                }
                break;
            case 8:
                if (winArray[row + 1][colum] == 1) {
                    return true;
                }
                if (winArray[row - 1][colum] == 1) {
                    return true;
                }
                if (winArray[row][colum + 1] == 1) {
                    return true;
                }
                if (winArray[row][colum - 1] == 1) {
                    return true;
                }

        }
        return false;
    }
    public static void printArray(int[][] array){
        for (int i  = 0; i < array.length; i++){
            for (int j = 0; j < array[0].length; j++){
                System.out.print(array[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
