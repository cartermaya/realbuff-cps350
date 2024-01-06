// RealBuff class
// Maya Carter, 9/28/2023

public class RealBuff {
    static final int max_size = 100;    // the default maximum size of the buffer
    double[] content;                   // the content of the buffer
    int current_size;                   // the number of valid elements

    // initializing an empty buffer of the default maximum size
    RealBuff()
    {
        content = new double[max_size];
        current_size = 0;
    }

    // initializing an empty buffer of the maximum size given by n
    RealBuff(final int n)
    {
        content = new double[n];
        current_size = 0;
    }

    // initializing a buffer which is a copy of buff
    RealBuff(final RealBuff buff)
    {
        // set current size of buffer to that of given buffer
        current_size = buff.current_size;

        // copying the content of given buffer
        content = new double[buff.content.length];
        for (int i = 0; i < current_size; i++) {
            content[i] = buff.content[i];
        }
    }

    // function appends a given value to the buffer
    public boolean appendBuff(double value) {
        // if the buffer is currently full return false with buffer unchanged
        if (current_size == content.length) {
            return false;
        }
        // add the given value to the buff
        content[current_size] = value;

        // increase number of valid elements by 1 and return true
        current_size++;
        return true;
    }

    // inserts given double value at given position in buffer
    public boolean insertBuff(double value, int index) {
        // if the buffer is currently full return false and the buffer is unchanged
        if (current_size == content.length) {
            return false;
        }

        // if index is greater than current number of valid elements or less than zero, return false
        if (index > current_size || index < 0) {
            return false;
        }

        // create a temp array to store the values of the buff
        double[] temp = new double[current_size + 1];

        // search through valid elements of array plus 1 to find given index
        for (int i = 0; i < current_size + 1; i++) {
            // store unaltered content of buffer in the temp array
            temp[i] = content[i];

            // set current value to given value if current index equals given index
            if (i == index) {
                content[i] = value;
            }
            // shift values by setting current value to unaltered previous value if current index is greater than given index
            else if (i > index) {
                content[i] = temp[i - 1];
            }
        }

        // increase number of valid elements by 1 and return true
        current_size++;
        return true;
    }


    // deletes a value in buffer at given index, or returns false if buffer is empty
    public boolean deleteBuff(int index) {
        // return false with unchanged buffer if buffer is currently empty
        if (current_size == 0) {
            return false;
        }

        // if index is greater than current highest index of a valid element or less than zero, return false
        if (index > current_size - 1 || index < 0) {
            return false;
        }

        // search through valid elements of buffer to find given index
        for (int i = 0; i < current_size; i++) {
            // shift values by setting previous value to current value if current index is greater than given to delete index value
            if (i > index) {
                content[i - 1] = content[i];
            }
        }

        // sets content of last valid element to zero
        content[current_size - 1] = 0.0;

        // decrease number of valid elements by 1 and return true
        current_size--;
        return true;
    }

    // displays the content of the buff
    public void displayBuff() {
        for (int i = 0; i < current_size; i++) {
            System.out.print(content[i] + " ");
        }
    }

    // uses insertion sort algorithm to sort the valid elements in the buffer
    public RealBuff insertionSort() {

        // create copy of original buff
        RealBuff res = new RealBuff(this);

        // iterate through the valid elements starting with second value
        for (int i = 1; i < res.current_size; i++) {

            // store content of current index
            double key = res.content[i];

            // store previous index in j
            int j = i - 1;

            // loop through previous values while they are greater than current value
            while ((j >= 0) && (res.content[j] > key)) {

                // save current previous value to next index
                res.content[j + 1] = res.content[j];

                // go to next previous value
                j--;
            }

            // save the current value in its sorted position
            res.content[j + 1] = key;
        }
        return res;

    } // end insertionSort


    /**
     * puts pivot value in place to partition array so all items before are
     * less than and all after are greater or equal to pivot
     * returns pivot index
     */
    private int partition(double[] array, int first, int last) {
        // last value is pivot value
        double pivot = array[last];
        // index which will end up to the right of the pivot
        int i = first - 1;
        double temp;

        for (int j = first; j < last; j++) {
            // if current value is less than pivot value
            if (array[j] < pivot) {
                // move i to the right
                i++;
                // swap j and i
                temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        // put pivot in its place
        temp = array[i + 1];
        array[i + 1] = array[last];
        array[last] = temp;

        // return location of pivot
        return i + 1;

    } // end partition

    // partitions array into smaller arrays with pivot index and sorts smaller arrays
    private double[] quickSort(double[] array, int first, int last) {
        if (first < last) {
            // get pivot index partition array
            int pivot = partition(array, first, last);

            // recursive call to sort first half
            quickSort(array, first, pivot - 1);

            // recursive call to sort second half
            quickSort(array, pivot + 1, last);
        }
        return array;

    } // end quickSort

    // method calls private quicksort method with a copy of buff so only valid values are sorted
    public RealBuff quickSort() {
        // create a copy of the buff object
        RealBuff res = new RealBuff(this);

        // call quicksort with copied buff content array, 0 as first index, and last valid element index as last index
        quickSort(res.content, 0, res.current_size - 1);

        // return buff copy
        return res;

    } // end quickSort


    // merges two half arrays into a sorted array
    private double[] merge(double[] a, double[] b) {
        // create new array to hold all values in both a and b
        double[] c = new double[a.length + b.length];

        // index for iterating through each array
        int indexA = 0,
            indexB = 0,
            indexC = 0;

        // iterate until one array has reached its length
        while ((indexA < a.length) && (indexB < b.length)) {
            // if current a value is less than or equal to current b value, put a value in merged array c
            if (a[indexA] <= b[indexB]) {
                c[indexC++] = a[indexA++];
            }
            // else put b value in merged array c
            else {
                c[indexC++] = b[indexB++];
            }
        }

        // iterate until a has reached its length
        while (indexA < a.length) {
            // put a value in merged array c
            c[indexC++] = a[indexA++];
        }

        // iterate until b has reached its length
        while (indexB < b.length) {
            // put b value in merged array c
            c[indexC++] = b[indexB++];
        }

        // return merged array
        return c;

    } // end merge

    // method halves array, sorts the halves, and merges sorted halves into one sorted array
    private double[] mergeSort(double[] array, int length) {

        // if length of array is less than or equal to one return array
        if (length <= 1) {
            return array;
        }

        // middle value to halve the array
        int mid = length / 2;

        // create new array with first half of arrays values
        double[] p1 = new double[mid];
        for (int i = 0; i < p1.length; i++) {
            p1[i] = array[i];
        }

        // create new array with second half of arrays values
        double[] p2 = new double[length - mid];
        for (int i = 0; i < p2.length; i++) {
            p2[i] = array[i + mid];
        }

        // recursively call mergeSort to divide first and second half into smaller halves
        double[] q1 = mergeSort(p1, p1.length);
        double[] q2 = mergeSort(p2, p2.length);

        // merge sorted arrays
        return merge(q1, q2);

    } // end mergeSort

    // method calls private mergeSort method with copy of buff content so only valid elements are sorted
    public RealBuff mergeSort() {

        // call mergeSort with buff content and length of valid elements
        double[] temp = mergeSort(content, current_size);

        // create a buff with same max size and current size of buff
        RealBuff res = new RealBuff(content.length);
        res.current_size = current_size;

        // copy sorted temp array into res buff
        for (int i = 0; i < current_size; i++) {
            res.content[i] = temp[i];
        }

        // return new sorted buff
        return res;

    } // end mergeSort
}
