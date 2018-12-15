//Suihan Dong, Kangyu Zheng
//dongx460, zhen0196
public class MatrixEntry {

    private int row;
    private int col;
    private int data;

    private MatrixEntry mCol;
    private MatrixEntry mRow;


    public MatrixEntry(int row, int col, int data){

        this.row = row;
        this.col = col;
        this.data = data;

    }


    public int getColumn(){

        return col;

    }


    public void setColumn(int col){

        this.col = col;

    }


    public int getRow(){

        return row;

    }

    public void setRow(int row){

        this.row = row;

    }

    public int getData(){

        return data;

    }

    public void setData(int data){

        this.data = data;

    }

    public MatrixEntry getNextRow(){

        return mRow;

    }

    public void setNextRow(MatrixEntry el){

        mRow = el;

    }

    public MatrixEntry getNextCol(){

        return mCol;

    }

    public void setNextCol(MatrixEntry el){

        mCol = el;

    }
}
