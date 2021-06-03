// Class: A2DynamicMem
// Implements Degragment in A2. No other changes should be needed for other functions.

public class A2DynamicMem extends A1DynamicMem {
      
    public A2DynamicMem() {  super(); }

    public A2DynamicMem(int size) { super(size); }

    public A2DynamicMem(int size, int dict_type) { super(size, dict_type); }

    // In A2, you need to test your implementation using BSTrees and AVLTrees. 
    // No changes should be required in the A1DynamicMem functions. 
    // They should work seamlessly with the newly supplied implementation of BSTrees and AVLTrees
    // For A2, implement the Defragment function for the class A2DynamicMem and test using BSTrees and AVLTrees. 

    public void Defragment() 
    {
        if(freeBlk instanceof BSTree)
        {
            BSTree copy = new BSTree();
            Dictionary current = freeBlk.getFirst();
            while(current != null)
            {
                copy.Insert(current.address,current.size,current.address);
                current = current.getNext(); 
            }
            current = copy.getFirst();
            if(current == null)
            {
                return;
            }
            Dictionary next = current.getNext();
            while(next != null)
            {
                if(current.address + current.size == next.address)
                {
                    int a = current.address;
                    int b = current.size;
                    int c = next.size;
                    int d = next.address;
                    BSTree temp = new BSTree(a,b,b);
                    freeBlk.Delete(temp);
                    BSTree temp2 = new BSTree(d,c,c);
                    freeBlk.Delete(temp2);
                    freeBlk.Insert(a,b+c,b+c);
                    BSTree temp3 = new BSTree(d,c,d);
                    copy.Delete(temp3);
                    BSTree temp4 = new BSTree(a,b,a);
                    copy.Delete(temp4);
                    current = copy.Insert(a,b+c,a);
                    next = current.getNext();
                }
                else
                {
                    current = next;
                    next = next.getNext();
                }
            }
        }
        else if(freeBlk instanceof AVLTree)
        {
            AVLTree copy = new AVLTree();
            Dictionary current = freeBlk.getFirst();
            while(current != null)
            {
                copy.Insert(current.address,current.size,current.address);
                current = current.getNext(); 
            }
            current = copy.getFirst();
            if(current == null)
            {
                return;
            }
            Dictionary next = current.getNext();
            while(next != null)
            {
                if(current.address + current.size == next.address)
                {
                    int a = current.address;
                    int b = current.size;
                    int c = next.size;
                    int d = next.address;
                    AVLTree temp = new AVLTree(a,b,b);
                    freeBlk.Delete(temp);
                    AVLTree temp2 = new AVLTree(d,c,c);
                    freeBlk.Delete(temp2);
                    freeBlk.Insert(a,b+c,b+c);
                    AVLTree temp3 = new AVLTree(d,c,d);
                    copy.Delete(temp3);
                    AVLTree temp4 = new AVLTree(a,b,a);
                    copy.Delete(temp4);
                    current = copy.Insert(a,c+b,a);
                    next = current.getNext();
                }
                else
                {
                    current = next;
                    next = next.getNext();
                }
            }        
        }
        return ;
    }
}