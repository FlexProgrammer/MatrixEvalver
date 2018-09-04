/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package matrixevalver;


import java.util.Scanner;

/**
 *
 * @author FleX
 */
public class MatrixEvalver {

    
    public static void main(String[] args) {
        int[][] matrix1;
        int[][] matrix2;
        System.out.println("\t###Программа для обрахунку дій над матрицями!###");
        System.out.println("Перша матриця: ");
        matrix1 = inputMatrix();
        matrix2 = operation(matrix1);
        outputMatrix(matrix2);
    }
    
    public static int[][] inputMatrix(){
        int[][] matrix;
        while(true){
            try{
                Scanner sc = new Scanner(System.in);
                String[] str = {"першу", "другу", "третю", "четверту"};
                System.out.print("Введіть кількість стрічок: ");
                int rows = sc.nextInt();
                System.out.print("Введіть кількість стовпців: ");
                int colls = sc.nextInt();
                matrix = new int[rows][colls];
                for (int i = 0; i < rows; i++) {
                    Scanner cs = new Scanner(System.in);
                    System.out.print("Введіть " + str[i] +  " стрічку: ");
                    String num = cs.nextLine();
                    String[] nums = num.split(" ");
                    for (int j = 0; j < colls; j++) {
                        matrix[i][j] = Integer.parseInt(nums[j]);
                    }
                }
                break;
            }catch(Exception e){
                System.err.println("\nВиникла помилка, введіть данні ще раз.");
            }
        }
        return matrix;
    }
    
    public static int[][] evalMatrix(int operator, int[][] matrix1, int[][] matrix2){
        int rows1 = matrix1.length;
        int colls1 = collsMatrix(matrix1);
        int rows2 = matrix2.length;
        int colls2 = collsMatrix(matrix2);
        int[][] matrix = new int[rows1][colls2];
        switch(operator){
            case 1:{ if(rows1 == rows2 && colls1 == colls2){
                for (int i = 0; i < rows1; i++) {
                    for (int j = 0; j < colls1; j++) {
                        matrix[i][j] = matrix1[i][j] + matrix2[i][j];
                    }
                }
            }
            break;
            }
            case 2:{ 
                if(rows1 == rows2 && colls1 == colls2){
                    for (int i = 0; i < rows1; i++) {
                        for (int j = 0; j < colls1; j++) {
                            matrix[i][j] = matrix1[i][j] - matrix2[i][j];
                        }
                    }
                }
                break;
            }
            case 3:{ 
                if(colls1 == rows2){
                    for (int i = 0; i < rows1; i++) {
                        for (int j = 0; j < colls1; j++) {
                            matrix[i][j] = multiplyMatrix(i, j, matrix1, matrix2);
                        }
                    }
                }
                break;
            }
        }
        return matrix;
    }
    
    public static int multiplyMatrix(int row, int coll, int[][] matrix1, int[][] matrix2){
        int result = 0;
        for (int i = 0; i < matrix2.length; i++) {
            result += matrix1[row][i] * matrix2[i][coll];
        }
        return result;
    }

    private static int collsMatrix(int[][] matrix2) {
        int colls = 0;
        for (int[] is : matrix2) {
            colls = is.length;
            break;
        }
        return colls;
    }

    private static void outputMatrix(int[][] matrix3) {
        System.out.println("\u001B[32m" + "Відповідь:" + "\u001B[0m");
        for (int[] is : matrix3) {
            System.out.print("|");
            for (int i : is) {
                System.out.print(i + " ");
            }
            System.out.println("|");
        }
    }

    private static int[][] operation(int[][] matrix1) {
        System.out.println("Оберіть операцію:");
        System.out.println("1) Додавання матриць");
        System.out.println("2) Віднімання матриць");
        System.out.println("3) Множення матриць");
        System.out.println("4) Піднесення матриці до квадрату");
        System.out.println("5) Множення матриці на число");
        Scanner sc = new Scanner(System.in);
        int operator = sc.nextInt();
        if(operator == 4) return evalMatrix(3, matrix1, matrix1);
        
        if(operator == 5){
            System.out.print("Введіть число на яке множити: ");
            int k = sc.nextInt();
            return evalMatrix(k, matrix1);
        } 
        System.out.println("Друга матриця:");
        int[][] matrix2 = inputMatrix();
        return evalMatrix(operator, matrix1, matrix2);
    }

    private static int[][] evalMatrix(int k, int[][] matrix1) {
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < collsMatrix(matrix1); j++) {
                matrix1[i][j] *= k;
            }
        }
        return matrix1;
    }
}
