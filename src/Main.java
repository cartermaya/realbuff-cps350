// main method for RealBuff class
// Maya Carter, 9/28/2023

import java.util.Random;

public class Main {
    private static Random r = new Random();

    public static void main(String[] args) {
        // size of RealBuff for algorithm time testing
        int n = 10000000;

        // appending 10 random double values to buff1 and displaying content
        System.out.println("\nMaking buffer with 10 random double values...");
        RealBuff buff1 = createRandBuff(10);
        showBuff(buff1, "Unsorted buffer: ");

        // sorting values of buff1 by calling quickSort
        System.out.println("\nSorting buffer with quickSort...");
        buff1 = buff1.quickSort();
        showBuff(buff1, "Sorted buffer: ");

        // appending 10 random double values to buff2 and displaying content
        System.out.println("\nMaking buffer with 10 random double values...");
        RealBuff buff2 = createRandBuff(10);
        showBuff(buff2, "Unsorted buffer: ");

        // sorting values of buff1 by calling mergeSort
        System.out.println("\nSorting buffer with mergeSort...");
        buff2 = buff2.mergeSort();
        showBuff(buff2, "Sorted buffer: ");

        // creating a RealBuff array
        RealBuff[] buffArray = new RealBuff[10];

        // generating random values for each buff object in the RealBuff array
        System.out.println("\nCreating 10 buffer objects that each have " + n + " items...");
        for (int i = 0; i < 10; i++) {
            buffArray[i] = createRandBuff(n);
        }

        // iterate through array of RealBuff objects and sort each with quickSort
        System.out.println("\nSorting 10 buffers with " + n + " items with quickSort...");
        buffArrayQuickSort(buffArray);     // all buffs are sorted but not saved as to not alter original buffs

        // iterate through array of RealBuff objects and sort each with mergeSort
        System.out.println("\nSorting 10 buffers with " + n + " items with mergeSort...");
        buffArrayMergeSort(buffArray);     // all buffs are sorted but not saved as to not alter original buffs

    } // end of main


    // method to show a buff with a specified message beforehand
    public static void showBuff(RealBuff buff, String message) {
        // print out specified message and display RealBuff object
        System.out.print(message);
        buff.displayBuff();
        System.out.println();

    } // end showBuff


    // method to create a buff of random values between -100.0 and 100.0 of a specific size
    public static RealBuff createRandBuff(int size) {
        // values for making random doubles
        double value, roundValue;
        double min = -100.0,
                max = 100.0;

        RealBuff buff;

        // using different constructor to change max_size if size is above the max_size
        if (size > 100) {
            buff = new RealBuff(size);
        }
        // else use default constructor
        else {
            buff = new RealBuff();
        }

        for(int i = 0; i < size; i++) {
            // creating a random double value between min and max values
            value = min + (max - min) * r.nextDouble();

            // rounding the random double values to two decimal places
            roundValue = Math.round(value * 100.0) / 100.0;

            // adding values to buffer by calling appendBuff
            buff.appendBuff(roundValue);
        }
        return buff;

    } // end createRandBuff


    // method to sort an array of RealBuff objects with quicksort
    public static void buffArrayQuickSort(RealBuff[] buffArray) {
        // variables for calculating time of sorting algorithms
        long start, end, elapsedTime, average;

        // start time for sorting
        start = System.currentTimeMillis();
        for (int i = 0; i < buffArray.length; i++) {
            // sort current RealBuff object with mergeSort without saving sorted buffer
            buffArray[i].quickSort();
        }
        // end time
        end = System.currentTimeMillis();

        // calculate the total elapsed time and the average time to sort all RealBuffs
        elapsedTime = end - start;
        average = elapsedTime / buffArray.length;

        // display total elapsed time and average time to sort all RealBuffs with quickSort
        System.out.println("Total elapsed time for quickSort: " + elapsedTime + "ms");
        System.out.println("Average time for quickSort: " + average + "ms");

    } // end buffArrayQuickSort


    // method to sort an array of RealBuff objects with mergesort
    public static void buffArrayMergeSort(RealBuff[] buffArray) {
        // variables for calculating time of sorting algorithms
        long start, end, elapsedTime, average;

        // start time for sorting
        start = System.currentTimeMillis();
        for (int i = 0; i < buffArray.length; i++) {
            // sort current RealBuff object with mergeSort without saving sorted buffer
            buffArray[i].mergeSort();
        }
        // end time
        end = System.currentTimeMillis();

        // calculate the total elapsed time and the average time to sort all RealBuffs
        elapsedTime = end - start;
        average = elapsedTime / buffArray.length;

        // display total elapsed time and average time to sort all RealBuffs with mergeSort
        System.out.println("Total elapsed time for mergeSort: " + elapsedTime + "ms");
        System.out.println("Average time for mergeSort: " + average + "ms");

    } // end buffArrayMergeSort


} // end main