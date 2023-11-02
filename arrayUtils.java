import java.util.Arrays;

/*
 * Class includes static implementations of various array find and sort algorithms:
 *      - Insertion Sort (insertionSort)
 *      - Binary Search  (binSearch)
 *      - Median of Two Arrays (findMedianSorted)
 */
public class arrayUtils {
    
    public static void main (String args[]){
        // n = 7
        int[] A = {1, 3, 4, 7, 8, 10};
        int[] B = {4, 5, 6, 8, 9, 14};

        // sorted array = {1, 3, 4, 4, 5, 6, 7, 8, 8, 9, 10, 14}
        // sorted median = (6+7)/2 = 6.5

        System.out.println("A" + Arrays.toString(A));
        System.out.println("B" + Arrays.toString(B));
        System.out.println("Median of A U B: " + arrayUtils.findMedianSorted(A,B)+"\n");  
    }

    /*
     * Given two sorted arrays, A and B, returns the median of the the combined array, A U B.
     */
    public static double findMedianSorted(int[] A, int[] B){
        int length = A.length;
        
        double AMedian, BMedian;

        // Base cases
        if (length == 0){
            return 0.0;
        }
        else if (length == 1){
            return (A[0]+B[0])/2.0;
        }
        else if (length == 2){
            return (Math.min(A[1], B[1]) + Math.max(A[0],B[0]))/2.0;
        }

        // deal with even/odd - calculate true median of A, B
        if (length % 2 == 0){
            AMedian = (A[length/2]+A[length/2+1])/2;
            BMedian = (B[length/2]+B[length/2+1])/2;
        }
        else{
            AMedian = A[length/2];
            BMedian = B[length/2];
        }
       
        // 3 cases
        if (AMedian == BMedian){
            // it's the median
            return (double) AMedian;
        }
        else if (AMedian > BMedian){
            if (length % 2 != 0){     // odd length. return half-arrays inclusive of middle element    
                return arrayUtils.findMedianSorted(Arrays.copyOfRange(A, 0, length/2+1), Arrays.copyOfRange(B, length/2, length)); //exclusive range
            }
            else{  // even length. return half-arrays. 
                return arrayUtils.findMedianSorted(Arrays.copyOfRange(A, 0, length/2), Arrays.copyOfRange(B, length/2, length)); //exclusive range
            }
        }
        else if (AMedian < BMedian){
            if (length % 2 != 0){   // odd length. return half-arrays inclusive of middle element    
                return arrayUtils.findMedianSorted(Arrays.copyOfRange(A, length/2, length), Arrays.copyOfRange(B, 0, length/2+1)); //exclusive range
            }
            else{
                return arrayUtils.findMedianSorted(Arrays.copyOfRange(A, length/2+1, length), Arrays.copyOfRange(B,0, length/2)); //exclusive range
            }
        }
        
        return 0.0;
    }

    /*
     * Recursive implementation of insertion sort to sort an array of integers in ascending order.
     * 
     * Running time: O(n^2), quadratic on the size of the array.
     */
    public static void insertionSort(int [] a, int index){
        // base case
        if (index == 1){
            return;
        }

        arrayUtils.insertionSort(a, index-1);
        
        int j = index;

        while (j > 0 && a[j-1] > a[j]){
            int swap = a[j];
            a[j]=a[j-1];
            a[j-1]=swap;
            j--;
        }
    }

    /*
     * Implementation of binary search. 
     * Given a sorted array, A, and a target value, v, return the index of the target, otherwise return -1.
     * 
     * Running time: O(log n), logarithmic on the size of the array
     */
    public static int binSearch(int[] A, int v, int x, int y){
        // base case - length == 1
        if (x==y){
            if (A[x] == v)
                return x;
            else
                return -1;
        }

        //check middle value
        double middle;

        if ((y-x+1) % 2 == 0){     // even-length array
            middle = (A[x+((y-x+1)/2)] + A[x+((y-x+1)/2)-1])/2;
        }
        else{
            middle =  A[x+((y-x+1)/2)]; 
            if (middle == x){
                return x+((y-x+1)/2);
            }
        }

        if (v < middle){
            return arrayUtils.binSearch(A, v, x, x+(y-x+1)/2 - 1);
        }
        else{
            if ((y-x+1) % 2 == 0){      // even-length array
                return arrayUtils.binSearch(A, v, x + (y-x+1)/2 +1, y);
            }
            else{
                return arrayUtils.binSearch(A, v, x + (y-x+1)/2, y);
            }
        }

    }

}
