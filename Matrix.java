import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

public class Matrix {
    private static Scanner in = new Scanner(System.in);
    private static int row;
    private static int col;
    private static boolean check;
    private static int[][] matrix;
    private static int[] arr;

    public static void SetMatrixWay(){
        System.out.println("Вводить размер матрицы с клавиатуры или из файла (true / false)");
        check = in.nextBoolean();
    }
    public static void SetMatrixFromKey(int row1, int col1){
        System.out.println("Вводите матрицу поэлементно");
        row = row1;
        col = col1;
        matrix = new int[row][col];
        for (int i = 0; i < row; i++)
            for(int j = 0; j < col; j++)
                matrix[i][j] = in.nextInt();
    }
    public static void SetMatrixFromFile(int row1, int col1, String str) throws IOException {
        row = row1;
        col = col1;
        Path path = Paths.get(str);
        try (Scanner fin = new Scanner(path)) {
            fin.useDelimiter(" ");
            matrix = new int[row][col];
            for (int i = 0; i < row; i++)
                for(int j = 0; j < col; j++)
                    matrix[i][j] = fin.nextInt();
        }
        catch (FileNotFoundException e) {
            System.out.println("Ошибка " + e + "! файл " + str + " не найден!");
        }
    }
    public static void GetArrFromMatrix(int size){ 
        arr = new int[size];
        int[] min = new int[col], max = new int[col];
        boolean look;
        for (int i = 0; i < row; i++) {
            for (int k = 0; k < col; k++)
                min[k] = matrix[i][k];
            max = min.clone();
            max = SortMax(max);
            min = SortMin(min);
            look = true;
            for (int j = 0; j < col; j++)
                if (matrix[i][j] != max[j] && matrix[i][j] != min[j])
                    look = false;
            if(look)
                arr[i] = 1;
            else
                arr[i] = -1;
        }
    }
    public static void PrintMatrix(){
        System.out.println("Матрица");
        for (int i = 0; i < row; i++){
            for(int j = 0; j < col; j++)
                System.out.print(matrix[i][j] + "\t");
            System.out.println();
        }
    }
    public static void PrintArr(){
        System.out.println("Массив");
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
    }
    public static void ArrToFile(String path) throws FileNotFoundException {
        String str = "";
        for (int i = 0; i < arr.length; i++)
            str += String.valueOf(arr[i]) + " ";
        PrintWriter pw = new PrintWriter(new File(path));
        pw.println(str);
        pw.close();
    }
    public static boolean GetCheck(){
        return check;
    }
    public static int[] SortMin(int[] arr)
    {
        int[] temp = new int [arr.length];
        int min = 0, size = arr.length;
        for(int i = 0; i < size; i++)
        {
            min = arr[0];
            for(int j = 0; j < arr.length; j++)
                if(arr[j] < min){min = arr[j];}
            temp[i] = min;

            int[] t = new int [arr.length - 1];
            boolean check = true;
            for (int k = 0; k < arr.length; k++)
            {
                if(check)
                {
                    if(arr[k] == min){check = false; continue;}
                    t[k] = arr[k];
                }
                else
                    t[k - 1] = arr[k];
            }
            arr = t;
        }
        return temp;
    }
    public static int[] SortMax(int[] arr)
    {
        int[] temp = new int [arr.length];
        int max = 0, size = arr.length;
        for(int i = 0; i < size; i++)
        {
            max = arr[0];
            for(int j = 0; j < arr.length; j++)
                if(arr[j] > max){max = arr[j];}
            temp[i] = max;

            int[] t = new int [arr.length - 1];
            boolean check = true;
            for (int k = 0; k < arr.length; k++)
            {
                if(check)
                {
                    if(arr[k] == max){check = false; continue;}
                    t[k] = arr[k];
                }
                else
                    t[k - 1] = arr[k];
            }
            arr = t;
        }
        return temp;
    }
}