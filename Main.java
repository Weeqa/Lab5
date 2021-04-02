import java.io.IOException;
public class Main
{
	public static void main(String[] args) throws IOException {
		Matrix.SetMatrixWay();
        if(Matrix.GetCheck())
            Matrix.SetMatrixFromKey(5,5);
        else
            Matrix.SetMatrixFromFile(5,5, "massiv.txt");
        Matrix.GetArrFromMatrix(5);

        System.out.println();
        Matrix.PrintMatrix();

        System.out.println();
        Matrix.PrintArr();

        Matrix.ArrToFile("matrix.txt");
	}
}
