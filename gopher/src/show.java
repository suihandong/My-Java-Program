import java.io.IOException;


public class show
{
    public show() throws IOException {
    }

    public static void main(String[] args) throws IOException
    {
        /**
         * Show the picture of file matrix_data.txt
         */
        SparseIntMatrix m1 = new SparseIntMatrix(800,800,"matrix1_data.txt");

        /**
         * please remove the "//" to see the set and remove method
         */
//        for(int i = 0; i < 800; i++)
//        {
//           m1.setElement(200,i,255);
//           m1.removeElement(600,i,255);
//        }
        MatrixViewer.show(m1);

        /**
         * Show the picture which have removed the noise part (matrix2_data.txt - matrix2_noise.txt)
         */
        SparseIntMatrix m2 = new SparseIntMatrix(800,800,"matrix2_data.txt");
        SparseIntMatrix m = new SparseIntMatrix(800,800,"matrix2_noise.txt");

        m2.minus(m);

        /**
         * please remove the "//" the set and remove method
         */
        // for(int i = 0; i < 800; i++)
//        {
//            m.setElement(200,i,255);
//            m.removeElement(600,i,255);
//        }

        MatrixViewer.show(m2);

    }



}
