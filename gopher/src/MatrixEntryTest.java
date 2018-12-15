//Suihan Dong
//dongx460
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MatrixEntryTest {

    public MatrixEntry matrix;
    private int row = 800;
    private int col = 800;
    private int data = 0;
    private MatrixEntry mCol;
    private MatrixEntry mRow;


    @Before
    public void setup() throws Exception{

        matrix = new MatrixEntry(row, col, data);

        mCol = new MatrixEntry(row, col, data);

        mRow = new MatrixEntry(row, col, data);

    }//end of test


    @Test
    public void TestConstructor() throws Exception{

        matrix = new MatrixEntry(400,400,4);

        assertEquals(matrix.getRow(), 400);

        assertEquals(matrix.getColumn(), 400);

        assertEquals(matrix.getData(), 4);

    }//end of test


    @Test
    public void TestSetRowColData() throws Exception{

        //test of setRow method
        matrix.setRow(row);
        assertEquals(matrix.getRow(), row);

        //test of setColumn method
        matrix.setColumn(col);
        assertEquals(matrix.getColumn(), col);

        //test of setData method
        matrix.setData(data);
        assertEquals(matrix.getData(), data);

    }//end of test


    @Test
    public void TestSetNextColumn() throws Exception{

        matrix.setNextCol(mCol);
        assertEquals(matrix.getNextCol(), mCol);

    }//end of test


    @Test
    public void TestSetGetNextRow() throws Exception{

        matrix.setNextRow(mRow);
        assertEquals(matrix.getNextRow(), mRow);

    }//end of test

}//end of class
