import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.IOException;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * These tests provide *some* coverage of the requirements of the SparseIntMatrix class.
 * Note that testing all test cases in this file does not indicate a fully functional
 * SparseIntMatrix class.
 */
public class TestMatrixWithFile {

    @Rule
    public Timeout timeout = Timeout.seconds(50);

    private SparseIntMatrix matrix;
    private Random rand = new Random();
    private final int ROW = 800;
    private final int COL = 800;

    @Before
    public void setUp() throws IOException {
        matrix = new SparseIntMatrix(ROW, COL,"matrix1_data.txt");
    }

    @Test
    public void TestGetNumRowsAndCols() {
        assertEquals(ROW, matrix.getNumRows());
        assertEquals(COL, matrix.getNumCols());
    }

    @Test
    public void TestGetElement(){
        int search;
        //SparseIntMatrix test = new SparseIntMatrix(800,800,"matrix1_data.txt");
        for(int i = 0; i < 800; i++)
        {
            //System.out.println(matrix.setElement(200, i, 255));
            //System.out.println(matrix.getRowHeader(200));
            System.out.println(matrix.getElement(0,i));
           //assertEquals(255,matrix.getElement(0,i));

        }

    }

    @Test
    public void TestSetOutOfRange() {
        assertFalse("Did not check row", matrix.setElement(ROW + 10, 0, 1));
        assertFalse("Did not exclude row", matrix.setElement(ROW, 0, 1));
        assertFalse("Did not check column", matrix.setElement(0, COL + 10, 1));
        assertFalse("Did not exclude column", matrix.setElement(0, COL, 1));
        assertFalse("Did not check negatives", matrix.setElement(-1, -1, 1));
    }

    @Test
    public void TestSetInRange() {
        assertTrue(matrix.setElement(rand.nextInt(ROW), rand.nextInt(COL), 1));
    }

    @Test
    public void TestPlusMatchingDimensions() {
        SparseIntMatrix other = new SparseIntMatrix(ROW, COL);
        assertTrue(matrix.plus(other));
    }

    @Test
    public void TestMinusMatchingDimensions() {
        SparseIntMatrix other = new SparseIntMatrix(ROW, COL);
        assertTrue(matrix.minus(other));
    }

    @Test
    public void TestPlusMismatchingDimensions() {
        SparseIntMatrix other = new SparseIntMatrix(900, 900);
        assertFalse(matrix.plus(other));
    }

    @Test
    public void TestMinusMismatchingDimensions() {
        SparseIntMatrix other = new SparseIntMatrix(900, 900);
        assertFalse(matrix.minus(other));
    }
    @Test
    public void TestPlus() throws IOException
    {
        SparseIntMatrix other = new SparseIntMatrix(800,800,"matrix2_data.txt");
        SparseIntMatrix Other = new SparseIntMatrix(800,800,"matrix2_noise.txt");
        assertTrue(other.plus(Other));
        System.out.println(other.getElement(0,0));
    }
    @Test
    public void TestMinus() throws IOException
    {
        SparseIntMatrix other = new SparseIntMatrix(800,800,"matrix2_data.txt");
        SparseIntMatrix Other = new SparseIntMatrix(800,800,"matrix2_noise.txt");
        assertTrue(other.minus(Other));
        MatrixViewer.show(other);
        System.out.println(other.getElement(0,0));
    }
    @Test
    public void TestColHeader() throws IOException
    {
        SparseIntMatrix other = new SparseIntMatrix(800,800,"matrix2_data.txt");
        //System.out.println(other.getColHeaders()[1].getData());
        //System.out.println(other.getColHeaders()[2].getData());
        for(int i = 1; i < 8; i++)
            System.out.println(other.getColHeaders()[i]);
        //System.out.println(other.getColHeaders()[4].getData());
    }
    @Test
    public void Test () throws IOException
    {
        SparseIntMatrix other = new SparseIntMatrix(800,800,"matrix2_data.txt");
        for(int i = 0; i < 100; i++)
            System.out.println(other.getRowHeaders()[i].getColumn());
    }
}
