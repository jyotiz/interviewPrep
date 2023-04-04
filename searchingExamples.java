/* Online Java Compiler and Editor */
public class searchingExamples{


    //Binary Search - olog(n)
    private int binarySearchRecursive(int[] arr, int num, int low, int high){
        //Check this for -1 return
        if( high >= low ){
                int mid = low + (high - low) / 2;
                if(num ==  arr[mid]){
                    return mid;
                }
                
                //Imp - boundaries : mid-1 and mid+1
                if(num < arr[mid]){
                    return binarySearchRecursive(arr, num , low, mid-1);
                }else{
                  return binarySearchRecursive(arr, num , mid+1, high);
                }
        }
                return -1;
    }
    
    public int binarySearchRecursive(int[] arr, int num){
        int low = 0;
        int high = arr.length - 1;
        return binarySearchRecursive(arr,num, low, high);
    }
    
    public int binarySearchIterative(int[] arr, int num){
        int low = 0;
        int high = arr.length - 1;
   
         while (low < high){
                  int mid = low + ( high- low) / 2;
                  if( num == arr[mid]) return mid;
                  else if( num < arr[mid]){
                      high = mid-1;
                  }else if( num > arr[mid]){
                      low=  mid +1;
                  }
         }
         return -1;
        
        
    }
    
     public static void main(String []args){
        int[] arr= new int[]{1,7,99,300,4567};
        searchingExamples obj= new searchingExamples();
        //int answer = obj.binarySearchRecursive(arr, 300);
        int answer = obj.binarySearchIterative(arr,999);
        System.out.println("Found at index" + answer);
     }
}