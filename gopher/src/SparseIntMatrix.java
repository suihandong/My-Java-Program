//Suihan Dong, Kangyu Zheng
//dongx460, zhen0196

/**Part V: Analysis and Comparison of Space Efficiency of your SparseIntMatrix
 * (a)For a square matrix of N * N elements with m non-zero elements, 2 * N + m memory is required for the
 *    SparseIntMatrix implementation. For a standard 2D array implementation, N * N memory id required.
 * (b)Assume N = 100,000 and m = 1,000,000
 *    SparseIntMatrix implementation: Space(SIM) = 2 * N + m = 100,000 * 2 + 1,000,000 = 1,200,000
 *    2D Array: Space(2D) = N * N = 1 * 10^10
 *    Space(2D) - Space(SIM) < 0
 *    Space(2D) - Space(SIM) = (N * N) - (2 * N + m)
 *                           = N^2 - 2N - m
 *    Space(2D) - Space(SIM) < 0 when m > N^2 - 2N
 *    If N = 100,000, m > 9.9998 * 10^9, so the 2D array implementation become more space-efficient than
 *    the SparseIntMatrix implementation.
 */

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class SparseIntMatrix {

    private int numRows;
    private int numCols;
    private MatrixEntry[] nRow;
    private MatrixEntry[] nCol;
    private int[] element = new int[10000000];

    public MatrixEntry[] getRowHeaders() {

        return nRow;

    }


    public MatrixEntry[] getColHeaders() {

        return nCol;
    }


    public SparseIntMatrix(int numRows, int numCols) {

        this.numRows = numRows;
        this.numCols = numCols;

        nRow = new MatrixEntry[numRows];
        nCol = new MatrixEntry[numCols];

    }//end of constructor


    public SparseIntMatrix(int numRows, int numCols, String inputFile) throws IOException {

        this.numRows = numRows;
        this.numCols = numCols;

        nRow = new MatrixEntry[numRows];
        nCol = new MatrixEntry[numCols];

        Scanner s = new Scanner(new File(inputFile));

        //read file
        while(s.hasNextLine()){
            String line = s.nextLine();
            String[] l = line.split(",");
            int rowM = Integer.valueOf(l[0]);
            int colM = Integer.valueOf(l[1]);
            int dataM = Integer.valueOf(l[2]);

            //set non-zero element
            if(dataM != 0){
                setElement(rowM, colM, dataM);
            }


        }//end of read file

    }//end of constructor


    public MatrixEntry[] getRowHeader() {

        return nRow;

    }


    public MatrixEntry[] getColHeader() {

        return nCol;

    }


    public int[] getelement() {

        return element;

    }


    public int getElement(int row, int col) {

        int i = row;
        int data;

        //get element in range
        if (row < this.getNumRows() && col < this.getNumCols() && row >= 0 && col >= 0) {

            MatrixEntry find = nRow[i];

            if (find.getColumn() == col) {

                return find.getData();

            } else {

                while (find.getNextCol() != null && find.getColumn() < col) {
                    find = find.getNextCol();
                }//end of while loop

                if (find.getRow() == row && find.getColumn() == col) {
                    data = find.getData();
                    return data;
                } else {
                    System.out.println("location");
                    return 0;
                }//end of 3rd level of if & else condition

            }//end of 2nd level of if & else condition

        } else {
            System.out.println("out of bound");
            return 0;
        }//end of 1st level of if & else condition

    }//end of getElement method


    public boolean setElement(int row, int col, int data) {

        MatrixEntry m = new MatrixEntry(row, col, data);

        //in the range
        if (row >= 0 && row < this.getNumRows() && col >= 0 && col < this.getNumCols()) {

            //by Row
            if (nRow[row] == null) {
                //create new position when it DNE
                nRow[row] = m;

            } else {

                MatrixEntry newEntry = nRow[row];//first
                MatrixEntry myEntry = nRow[row];//second

                MatrixEntry set = new MatrixEntry(row, col, data);
                int position = nRow[row].getColumn();

                while (newEntry.getNextCol() != null && position < col) {
                    myEntry = newEntry;
                    newEntry = newEntry.getNextCol();
                    position = newEntry.getColumn();
                }//end of while loop

                if (position > col) {
                    myEntry.setNextCol(set);
                    set.setNextCol(newEntry);
                } else {
                    newEntry.setNextCol(set);
                }//end of 2nd level of if & else condition

            }//end of if condition by Row

            //by Col
            if (nCol[col] == null) {
                //create new position when it DNE
                nCol[col] = m;

            } else {

                MatrixEntry newEntry1 = nCol[col];
                MatrixEntry myEntry1 = nCol[col];

                MatrixEntry set1 = new MatrixEntry(row, col, data);
                int position1 = nCol[col].getRow();

                while (newEntry1.getNextRow() != null && position1 < row) {
                    myEntry1 = newEntry1;
                    newEntry1 = newEntry1.getNextRow();
                    position1 = newEntry1.getRow();
                }//end of while loop

                if (position1 > row) {
                    myEntry1.setNextRow(set1);
                    set1.setNextRow(newEntry1);
                } else {
                    newEntry1.setNextRow(set1);
                }//end of 2nd level of if & else condition

            }//end of if condition by Column

            return true;

        }//end of if condition in range

        // out of range
        return false;

    }//end of setElement method


    public boolean removeElement(int row, int col, int data) {

        MatrixEntry find = nRow[row];
        MatrixEntry before = nRow[row];

        MatrixEntry now;
        MatrixEntry zero = new MatrixEntry(row,col,0);

        //in range
        if(row >= 0 && col >= 0 && row < getNumRows() && col < getNumCols()) {

            if (find.getNextCol() instanceof MatrixEntry)
            {
                if (find.getColumn() == col && find.getData() == data)
                {
                    now = find.getNextCol();
                    nRow[row] = now;
                    return true;
                }
                else
                {
                    while (find.getNextCol() != null && find.getColumn() < col)
                    {
                       before = find;
                       find = find.getNextCol();
                    }//end of while loop

                    if(find.getNextCol() != null)
                    {
                        if(find.getData() == data)
                        {
                            before.setNextCol(find.getNextCol());
                            return true;
                        }
                        else
                            return false;
                    }
                    else
                    {
                        before.setNextCol(zero);
                        return true;
                    }//end of not empty condition

                }//end of 3rd if & else condition

            }//end of 2nd if & else condition
            return false;

        }//end of in range if condition

        return false;

    }//end of removeElement method


    public int getNumCols() {

        return numCols;

    }//end of getNumCols method


    public int getNumRows() {

        return numRows;

    }//end of getNumRows method


    public boolean plus(SparseIntMatrix otherMat) {

        MatrixEntry thisCurrent, otherCurrent;

        //if two matrix are equal
        if (otherMat.getNumCols() == this.getNumCols() && otherMat.getNumRows() == this.getNumRows()) {

            for (int i = 0; i < this.getNumRows(); i++) {
                thisCurrent = this.getRowHeader()[i];
                otherCurrent = otherMat.getRowHeader()[i];

                while (thisCurrent != null && otherCurrent != null && thisCurrent.getNextCol() != null && otherCurrent.getNextCol() != null) {
                    if (thisCurrent.getColumn() == otherCurrent.getColumn() && thisCurrent.getRow() == otherCurrent.getRow()) {
                        int thisData = thisCurrent.getData();
                        int otherData = otherCurrent.getData();

                        thisCurrent.setData(thisData + otherData);
                        thisCurrent = thisCurrent.getNextCol();
                        otherCurrent = otherCurrent.getNextCol();
                    }//end of same position if condition
                    else {

                        if (thisCurrent.getColumn() < otherCurrent.getColumn()) {
                            thisCurrent = thisCurrent.getNextCol();
                        }
                        else if (thisCurrent.getColumn() > otherCurrent.getColumn()){
                            otherCurrent = otherCurrent.getNextCol();
                        }
                    }//end of not same position condition

                }//end of not empty condition while loop

            }//end of for loop

            return true;

        }//end of in range condition

        return false;

    }//end of plus method


    public boolean minus(SparseIntMatrix otherMat) {

        MatrixEntry thisCurrent, otherCurrent;

        //if two matrix are equal
        if (otherMat.getNumCols() == this.getNumCols() && otherMat.getNumRows() == this.getNumRows()) {

            for (int i = 0; i < this.getNumRows(); i++) {

                thisCurrent = this.getRowHeader()[i];
                otherCurrent = otherMat.getRowHeader()[i];

                while (thisCurrent != null && otherCurrent != null && thisCurrent.getNextCol() != null && otherCurrent.getNextCol() != null) {

                    if (thisCurrent.getColumn() == otherCurrent.getColumn() && thisCurrent.getRow() == otherCurrent.getRow()) {
                        int thisData = thisCurrent.getData();
                        int otherData = otherCurrent.getData();

                        thisCurrent.setData(thisData - otherData);
                        thisCurrent = thisCurrent.getNextCol();
                        otherCurrent = otherCurrent.getNextCol();
                    } //end of same position condition

                    else {
                        if (thisCurrent.getColumn() < otherCurrent.getColumn())
                            thisCurrent = thisCurrent.getNextCol();
                        else if (thisCurrent.getColumn() > otherCurrent.getColumn())
                            otherCurrent = otherCurrent.getNextCol();
                    }//end of not equal position condition

                }//end of not while not empty loop

            }//end of for loop

            return true;

        }//end of two matrix are equal condition

        return false;

    }//end of minus method

}//end of class
