/* Online Java Compiler and Editor */
public class CircularQueue{
    
    private int size = 5;
    int front = -1;
    int rear = -1;
    int[] q;
    CircularQueue(){
        q = new int[size];
    }
    public boolean isQFull(){
        if( front == 0 && rear == q.length - 1){
            return true;
        }else if( front == rear + 1){
            return true;
        }
        return false;
    }
    
    public boolean isEmpty(){
        if( front == -1) return true;
        else return false;
    }
    
    public void enqueue(int num){
        if(isQFull()){
            System.out.println("Q is full");
        }
        else{
            if( front==-1) front =0;
            rear = (rear+1) % size;
            q[rear] = num;
        }
    }
    
    public int dequeue(){
        
        if(isEmpty()){
            System.out.println("q is empty");
            return -1;
        }else{
            int ele = q[front];
            if(front == rear){
                front = -1;
                rear = -1;
            }else{
                front = (front + 1) % size;
            }
           return ele; 
            
        }
        
    }
    
    void display() {
    /* Function to display status of Circular Queue */
    int i;
    if (isEmpty()) {
      System.out.println("Empty Queue");
    } else {
      System.out.println("Front -> " + front);
      System.out.println("Items -> ");
      for (i = front; i != rear; i = (i + 1) % size)
        System.out.print(q[i] + " ");
      System.out.println(q[i]);
      System.out.println("Rear -> " + rear);
    }
  }

     public static void main(String []args){
     
    CircularQueue q = new CircularQueue();

    // Fails because front = -1
    q.dequeue();

    q.enqueue(1);
    q.enqueue(2);
    q.enqueue(3);
    q.enqueue(4);
    q.enqueue(5);

    // Fails to enqueue because front == 0 && rear == SIZE - 1
    q.enqueue(6);

    q.display();

    int elem = q.dequeue();

    if (elem != -1) {
      System.out.println("Deleted Element is " + elem);
    }
    q.display();

    q.enqueue(7);

    q.display();

    // Fails to enqueue because front == rear + 1
    q.enqueue(8);
  }
}