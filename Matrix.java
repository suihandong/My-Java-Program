public class Matrix {
    public int[][] a;
    public int[][] b;
    public int[][] m;
    /**
     * TODO: For milestone 2, test your display method here.
     *
     * TODO: For milestone 3, follow the directions in the writeup.
     */
    public static void main(String[] args){
       int[][] r1 = Matrix.placeDots(3,3,6);
       int[][] r2 = Matrix.placeDots(6,2,8);
       int[][] r3 = Matrix.placeDots(12,12,18);
       Matrix.display(r1);
       System.out.println();
       Matrix.display(r2);
       System.out.println();
       Matrix.display(r3);
       System.out.println();
    }

    /**
     * A method that takes in two matrices as 2D arrays
     * and returns the sum of the two matrices
     *
     * TODO: Implement this (milestone 2)
     */
    public static int[][] add(int[][]a, int[][] b) {
        if(a.length == b.length && a[0].length == b[0].length){
            int[][] c = new int[a.length][a[0].length];
            for(int i = 0; i < a.length; i++){
                for(int j = 0; j < a[0].length; j++){
                    c[i][j] = a[i][j] + b[i][j];
                }
            }
            return c;
        }
        else {
            return null;
        }
    }

    /**
     * A method that takes in a matrix as a 2D array
     * and returns its transpose
     *
     * TODO: Implement this (milestone 2)
     */
    public static int[][] transpose(int[][] m) {
        int[][] M = new int[m[0].length][m.length];
        for(int h = 0; h < m[0].length; h++){
            for(int k = 0; k < m.length; k++){
                M[h][k] = m[k][h];
            }
        }
        return M;
    }

    /**
     * A method that takes in a matrix as a 2D array
     * and prints it row by row
     *
     * TODO: Implement this (milestone 2)
     */
    public static void display(int[][] a) {
        for(int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * A method that takes in dimensions for a matrix and a number
     * of dots (represented by a value of 1) to randomly place in
     * the matrix
     *
     * TODO: Implement this (milestone 3)
     */
    public static int[][] placeDots(int numRows, int numColumns, int numDots){
        int count = 0;
        int[][] e = new int[numRows][numColumns];
        for(int i = 0; i < numRows; i++){
            for(int j = 0; j<numColumns; j++){
                e[i][j] = 0;
            }
        }
        double x = Math.random();
        double x1 = x * numRows;
        double y = Math.random();
        double y1 = y * numColumns;
        for(int h = 0; h < numRows; h++) {
            for (int k = 0; k < numColumns; k++) {
                while (count <= numDots){
                    if (e[h][k] == 0) {
                        e[(int) Math.floor(x1)][(int) Math.floor(y1)] = 1;
                    }
                    x = Math.random();
                    x1 = x * numRows;
                    y = Math.random();
                    y1 = y * numColumns;
                    count++;
                }
            }
        }
        return e;
    }

    public static boolean areEqual(int[][] a, int[][] b) {
        if (a.length == 0 && b.length == 0){
            return true;
        } else if (a.length != b.length || a[0].length != b[0].length) {
            return false;
        } else {
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a[0].length; j++){
                    if (a[i][j] != b[i][j]) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}