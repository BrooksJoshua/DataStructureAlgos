package search;

/**
 * @author Joshua.H.Brooks
 * @description
 * @date 2022-05-20 15:10
 */
public class TenSearchs {
    public static void main(String[] args) {
        int[] arr = {-1, 0, 3, 4, 9, 11, 18};
        int target = 9;
        int i = binarySearch(arr, target);
        System.out.println(i);


    }
    public static int binarySearch(int[] arr, int target){
        int low = 0, high = arr.length-1;
        while(low <= high){
            int mid = (low+high) >> 1;
            if(arr[mid] == target){
                return mid;
            }else if(arr[mid] > target){
                 high = mid;
            }else {
                  low = mid;
            }
        }
        return -1;
    }

}
