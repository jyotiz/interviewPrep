// Online Java Compiler
// Use this editor to write, compile and run your Java code online

public class SortingExamples {
    
    //Common swap method
    private void swap(int start, int end, int[] arr){
        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
        return;
    }
        
	
	/*so we can introduce an extra variable swapped. The value of swapped is set true if there occurs swapping of elements. Otherwise, it is set false.

After an iteration, if there is no swapping, the value of swapped will be false. This means elements are already sorted and there is no need to perform further iterations.

This will reduce the execution time and helps to optimize the bubble sort.*/
    //Bubble sort
    public void bubbleSort(int[] arr){
       for(int i = 0; i < arr.length; i++){
           for(int j = 0; j < arr.length - 1; j++){
               if(arr[j] > arr[j+1]){
                   swap(j,j+1,arr);
               }
           }
       } 
    }
    
    //Merge sort
    //Copy over elements in helper, compare two arrays from start and choose whichever is small
    private void  merge(int[] arr, int[] helper, int low,int mid, int high){
        
        for(int i = 0; i < arr.length; i++){
            helper[i] = arr[i];
        }
        int helperRight = mid+1;
        int helperLeft = low;
        int curr = low;
         while(helperLeft <= mid && helperRight <=high){
             if(helper[helperLeft] <= helper[helperRight]){
                 arr[curr] = helper[helperLeft];
                 helperLeft++;
             }else{
                   arr[curr] = helper[helperRight];
                 helperRight++;
             }
             curr++;
         }
         
         int remaining = mid - helperLeft;
          for(int i= 0; i <= remaining; i++){
              arr[curr++] = helper[helperLeft++];
          }
        
    }
    
    //Private method does recursive breaking and calls merge
    private void mergeSort(int[] arr, int[] helper, int low, int high){
        if( low < high ){
        int mid = (low + high) / 2;
        mergeSort(arr,helper, low, mid);
        mergeSort(arr,helper, mid+1, high);
        merge(arr, helper, low, mid, high);
        }
    }
    
    //Public method creates a helper and passes that
    public void mergeSort(int [] arr){
        int[] helper = new int[arr.length];
        mergeSort(arr, helper, 0, arr.length-1);
    }
    
    //Quicksort
    /*Worst Case Complexity [Big-O]: O(n2)
It occurs when the pivot element picked is either the greatest or the smallest element.

This condition leads to the case in which the pivot element lies in an extreme end of the sorted array. One sub-array is always empty and another sub-array contains n - 1 elements. Thus, quicksort is called only on this sub-array.*/
    
    private int partition(int[] arr, int low, int high){
        //Choose mid as pivot
        int pivot = arr[(low + high) /2];
        while( low <= high){
            //Make all small left to pivot and big to right
            while(arr[low] < pivot) low++;
            while(arr[high] > pivot) high--;
            //swap when we stop
            if( low <= high){
                swap(low,high,arr);
                low++;
                high--;
            }
        }
        return low;
    }
    
        
 
    private void quickSort(int[]arr, int low, int high){
        //Find index to cut the array in two parts
       int index = partition(arr,low,high);
       if(low < index-1){
           quickSort(arr, low, index-1);
       }
       if( high > index){
        quickSort(arr, index, high);
       }
    }
    
    public void quickSort(int[] arr){
     quickSort(arr, 0, arr.length-1);
    }
    
    //Heap sort
    /*Heap Sort has O(nlog n) time complexities for all the cases ( best case, average case, and worst case).

Let us understand the reason why. The height of a complete binary tree containing n elements is log n

As we have seen earlier, to fully heapify an element whose subtrees are already max-heaps, we need to keep comparing the element with its left and right children and pushing it downwards until it reaches a point where both its children are smaller than it.

In the worst case scenario, we will need to move an element from the root to the leaf node making a multiple of log(n) comparisons and swaps.

During the build_max_heap stage, we do that for n/2 elements so the worst case complexity of the build_heap step is n/2*log n ~ nlog n.

During the sorting step, we exchange the root element with the last element and heapify the root element. For each element, this again takes log n worst time because we might have to bring the element all the way from the root to the leaf. Since we repeat this n times, the heap_sort step is also nlog n.

Also since the build_max_heap and heap_sort steps are executed one after another, the algorithmic complexity is not multiplied and it remains in the order of nlog n.

Also it performs sorting in O(1) space complexity. Compared with Quick Sort, it has a better worst case ( O(nlog n) ). Quick Sort has complexity O(n^2) for worst case. But in other cases, Quick Sort is fast. Introsort is an alternative to heapsort that combines quicksort and heapsort to retain advantages of both: worst case speed of heapsort and average speed of quicksort.*/
    
    public void heapSort(int[] arr){
        int n = arr.length;
        
        //Build max heap first
        //here we make sure all root elements are greater than respective children but array is not sorted
        // In the case of a complete tree, the first index of a non-leaf node is given by n/2 - 1
        for(int i = n/2 -1; i >=0; i-- ){
            heapify(arr,n,i);
        }
        
        //Heap sort
        //Here we sort by swapping root with last element - since its the largest and reducing the size of array to heapify again. Thus we fill array from end with largest element
        for(int i = n-1; i >=0; i--){
            swap(i, 0, arr);
            heapify(arr, i, 0);
        }
    }
    
    private void heapify(int[] arr, int n, int i){
        
        int largest = i;
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;
        
        if( leftChild < n && arr[leftChild] > arr[largest]){
           largest = leftChild;
        }
        
        if( rightChild < n && arr[rightChild] > arr[largest]){
           largest = rightChild;
        }
        
        if(largest != i){
            swap(largest, i, arr);
            heapify(arr, n, largest);
        }
    }
    
    public static void main(String[] args) {
        int[] arr= new int[]{4,9,1,4,66,0,99,-2};
        SortingExamples obj= new SortingExamples();
        //obj.bubbleSort(arr);
        //obj.mergeSort(arr);
        //obj.quickSort(arr);
        obj.heapSort(arr);
        
        for(int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }
        
     
    }
}