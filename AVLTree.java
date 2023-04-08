/* Online Java Compiler and Editor */
public class AVLTree{

Node root;

    private int height(Node node){
        if(node ==  null){
            return 0;
        }
        return node.height;
    }
    
    private int max(int a, int b){
        return ( a > b ) ? a : b;
    }
    
    public void updateHeight(Node n ){
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }
    
    public int getBalance(Node n){
        return ( n == null ? 0 : height(n.right) - height(n.left));
    }
    
    
    /*
                y                       x
              /   \                  /     \
            /      \      Right     /         \
          x         e    ----->    d          y
        /  \                                /    \
      /     \                              /      \
     d      z                           z           e
    
    // z x se bada hai but y se chota hai
    */
    private Node rightRotate(Node y){
        
              System.out.println("Doing right rotation");
        Node x = y.left;
        Node z = x.right;
        x.right = y;
        y.left = z;
        updateHeight(y);
        updateHeight(x);
        return x;

    }
    
    private Node leftRotate(Node y){
        
              System.out.println("Doing left rotation");
        Node x = y.right;
        Node z = x.left;
        x.left = y;
        y.right = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }
    
    public Node rebalance(Node z){
        updateHeight(z);
        int balance = getBalance(z);
        System.out.println(" Balance of node" + z.key + "is" + balance);
        if( balance > 1 ){
            if(height(z.right.right) > height(z.right.left)){
                z = leftRotate(z);
            }else{
                z.right = rightRotate(z.right);
                z = leftRotate(z);
            }
        }else if(balance < 1){
            if(height(z.left.left) > height(z.left.right)){
                z = rightRotate(z);
            }else{
                z.left = leftRotate(z.left.right);
                z = rightRotate(z);
            }
        }
        return z;
    }
    
    public Node insert(Node node, int key){
        if(node == null) return new Node(key);
        else if ( node.key > key)
              node.left = insert(node.left, key);
        else if( node.key < key)
            node.right = insert(node.right, key);
        else throw new RuntimeException("Duplicate key");
        return rebalance(node);
    }
    
    public Node mostLeftChild(Node node) {
    Node current = node;
    while (current.left != null)
      current = current.left;
    return current;
  }
    
    public Node delete(Node node, int key){
        if(node == null) return node;
        else if( node.key > key)
            node.left = delete(node.left, key);
        else if ( node.key < key)
            node.right = delete(node.right, key);
        else{
            if(node.left == null || node.right == null){
                node= ( node.left == null) ? node.right : node.left;
            }else{
                //find inorder successor in right
                Node mostLeftChild = mostLeftChild(node.right);
                //copy the key to node
                node.key = mostLeftChild.key;
                //delete the key from right child of z
                node.right = delete(node.right,node.key);
            }
        }
        if(node != null){
            node = rebalance(node);
        }
        return node;
    }
    
    public boolean isBalanced(Node node){
        return Math.abs( height(node.right)-height(node.left)) <= 1;
    }


     public static void main(String []args){
         
          AVLTree tree = new AVLTree();
        tree.root = new Node(1);
        
        tree.insert(tree.root,2);
            tree.insert(tree.root,3);
                tree.insert(tree.root,4);
       // tree.root.left = new Node(2);
      //  tree.root.right = new Node(3);
      //  tree.root.left.left = new Node(4);
      //  tree.root.left.right = new Node(5);
      //  tree.root.left.left.left = new Node(8);
 
        if (tree.isBalanced(tree.root))
            System.out.println("Tree is balanced");
        else
            System.out.println("Tree is not balanced");
    
     }
}

class Node{
    int key;
    Node left;
    Node right;
    int height;
    Node(int key){
        this.key= key;
        this.left = null;
        this.right = null;
        this.height = 1;
    }
}