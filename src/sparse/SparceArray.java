package sparse;

/**
 * @author Joshua.H.Brooks
 * 稀疏数组
 * @date 2022-05-20 15:03
 */
public class SparceArray {
    private static final int ROWS = 11;
    private static final int COLUMNS = 11;
    private static final int COLUMNS_OF_SPARSE = 3;
    public static void main(String[] args) {

        int[][] arr = new int[ROWS][COLUMNS];
        arr[1][2] = 1;
        arr[2][3] = 2;
        int nonZeros = 0;
        for(int[] row:arr){
            for(int cell:row){
                System.out.printf("%d\t",cell);
                if(cell!=0) nonZeros++;
            }
            System.out.println();
        }
        System.out.println(nonZeros);
        //根据原矩阵中非零元素的个数创建稀疏矩阵， 固定三列
        int[][] sparseArr = new int[nonZeros+1][COLUMNS_OF_SPARSE];
        //第一行表示原始数据的纬度, m*n矩阵， 且非零个数为2
        sparseArr[0][0] = ROWS;
        sparseArr[0][1] = COLUMNS;
        sparseArr[0][2] = nonZeros;
        int count = 0;
        for (int i = 1; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if(arr[i][j]!=0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = arr[i][j];
                }
            }
        }
        printArray(sparseArr);



    }

    public static void printArray(int[][] arr){
        for (int[] row:arr) {
            for (int cell:row) {
                System.out.printf("%d\t",cell);
            }
            System.out.println();
        }
    }
}
