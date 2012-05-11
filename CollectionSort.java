/*****************************************************************/
// File Name: CollectionSort.java
// Summary: Sorts a collection of randomly generated integers 
// as many times as you choose.
// Uses: Insertion Sort, Merge Sort, & Quick Sort.
// Includes: Methods & Client to Run Tests
// Author: Chad Whitman
/*****************************************************************/

import java.util.*;

public class CollectionSort {

    public static final int SENTINEL = 0;

    public static int mnumcalls = 0;

    public static int inumcalls = 0;

    public static int qnumcalls = 0;

    public static int mmoves = 0;

    public static int mcount = 0;

    public static int icount = 0;

    public static int qcount = 0;

    public static void main(String[] args) {

        int sizeCount = 1;

        int value;

        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter the number of sorting runs to be executed, or 0 to quit:");

        System.out.println();

        value = keyboard.nextInt();

        while (value != SENTINEL) {

            int SIZE = 16;

            if (value > 0) {

                System.out.format("%6s %24s %33s %33s \n", "", "MERGE SORT", "QUICKSORT", "INSERTION SORT");

                System.out.format("%6s %10s %10s %10s %10s %10s %10s %10s %10s %10s \n", "SIZE", "min", "max", "avg", "min", "max", "avg", "min", "max", "avg");

                System.out.println();

                for (sizeCount = 1; sizeCount < 11; sizeCount++) {

                    SIZE = SIZE * 2;

                    int[] mergemoves = new int[value];

                    int[] quickcount = new int[value];

                    int[] insertcount = new int[value];

                    for (int x = 0; x < value; x++) {

                        mnumcalls = inumcalls = qnumcalls = 0;

                        mmoves = mcount = icount = qcount = 0;

                        Random gen = new Random();

                        int[] a = new int[SIZE];

                        int[] b = new int[SIZE];

                        int[] c = new int[SIZE];

                        int i;

                        for (i = 0; i < a.length; i++) {

                            c[i] = b[i] = a[i] = gen.nextInt(5000) + 1;

                        }

                        mergesort(a, 0, a.length - 1);

                        mergemoves[x] = mmoves;

                        insertionSort(b);

                        insertcount[x] = icount;

                        quickSort(c, 0, a.length - 1);

                        quickcount[x] = qcount;

                    }

                    int minmoves = 0, maxmoves = 0, avgmoves = 0, summoves = 0;

                    int minqcount = 0, maxqcount = 0, avgqcount = 0, sumqcount = 0;

                    int minicount = 0, maxicount = 0, avgicount = 0, sumicount = 0;

                    quickSort(mergemoves, 0, mergemoves.length - 1);

                    quickSort(quickcount, 0, quickcount.length - 1);

                    quickSort(insertcount, 0, insertcount.length - 1);

                    for (int y = 0; y < mergemoves.length; y++) {

                        summoves += mergemoves[y];

                        sumqcount += quickcount[y];

                        sumicount += insertcount[y];

                    }

                    avgmoves = summoves / value;

                    minmoves = mergemoves[0];

                    maxmoves = mergemoves[mergemoves.length - 1];

                    avgqcount = sumqcount / value;

                    minqcount = quickcount[0];

                    maxqcount = quickcount[quickcount.length - 1];

                    avgicount = sumicount / value;

                    minicount = insertcount[0];

                    maxicount = insertcount[insertcount.length - 1];

                    System.out.format("%6d %10d %10d %10d %10d %10d %10d %10d %10d %10d \n", SIZE, minmoves, maxmoves, avgmoves, minqcount, maxqcount, avgqcount, minicount, maxicount, avgicount);

                }

            }

            System.out.println();

            System.out.println("Enter the number of sorting runs to be executed, or 0 to quit:");

            System.out.println();

            value = keyboard.nextInt();

        }

    }

    /*********************************************************************/

    /* MergeSort */

    /*********************************************************************/

    public static void mergesort(int[] a, int top, int bottom) {

        mnumcalls++;

        if (top != bottom) {

            int middle = (top + bottom) / 2;

            mergesort(a, top, middle);

            mergesort(a, middle + 1, bottom);

            merge(a, top, bottom);

        }

    }

    /*********************************************************************/

    /* Function called by MergeSort */

    /*********************************************************************/

    public static void merge(int[] a, int top, int bottom) {

        int t = top;

        int middle = (top + bottom) / 2;

        int b = middle + 1;

        int i = 0;

        int[] s = new int[bottom - top + 1];

        while ((t <= middle) && (b <= bottom)) {

            if (a[t] < a[b]) {

                s[i] = a[t];

                t++;

            } else {

                s[i] = a[b];

                b++;

            }

            i++;

            mcount++;

            mmoves++;

        }

        int last = middle;

        if (b <= bottom) {

            t = b;

            last = bottom;

        }

        while (t <= last) {

            s[i] = a[t];

            t++;

            i++;

            mmoves++;

        }

        for (i = 0; i < s.length; i++) {

            a[i + top] = s[i];

            mmoves++;

        }

    }

    /*********************************************************************/

    /* QuickSort */

    /*********************************************************************/

    public static void quickSort(int arr[], int to, int from) {

        qnumcalls++;

        int index = partition(arr, to, from);

        if (to < index - 1) {

            quickSort(arr, to, index - 1);

        }

        if (index < from) {

            quickSort(arr, index, from);

        }

    }

    /*********************************************************************/

    /* Creates a Partition for QuickSort */

    /*********************************************************************/

    public static int partition(int arr[], int to, int from) {

        int i = to,

                j = from;

        int tmp;

        int pivot = arr[(to + from) / 2];

        while (i <= j) {

            while (arr[i] < pivot) {

                i++;

                qcount++;

            }

            while (arr[j] > pivot) {

                j--;

                qcount++;

            }

            if (i <= j) {

                tmp = arr[i];

                arr[i] = arr[j];

                arr[j] = tmp;

                i++;

                j--;

                qcount++;

            }
        }

        return i;

    }

    /*********************************************************************/

    /* InsertionSort */

    /*********************************************************************/

    public static void insertionSort(int array[]) {

        int i, j, next;
        
        inumcalls++;

        for (i = 1; i < array.length; i++) {

            next = array[i];

            j = i;

            while (j > 0 && array[j - 1] > next) {

                array[j] = array[j - 1];

                j--;

                icount++;

            }

            array[j] = next;

        }

    }

}