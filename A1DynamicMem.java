// Class: A1DynamicMem
// Implements DynamicMem
// Does not implement defragment (which is for A2).

public class A1DynamicMem extends DynamicMem {
      
    public A1DynamicMem() {
        super();
    }

    public A1DynamicMem(int size) {
        super(size);
    }

    public A1DynamicMem(int size, int dict_type) {
        super(size, dict_type);
    }

    public void Defragment() {
        return ;
    }

    // In A1, you need to implement the Allocate and Free functions for the class A1DynamicMem
    // Test your memory allocator thoroughly using Doubly Linked lists only (A1List.java).

    public int Allocate(int blockSize) 
    {
        // Although the DLL Data Structure supports finding keys with all possible values but allocating a memory block of size <= 0 is considered invalid and handled here.
        if(blockSize <= 0)
        {
            return -1;
        }
        Dictionary temp = freeBlk.Find(blockSize,false);
        if(temp == null)
        {
            return -1;
        }
        if(temp.size > blockSize)
        {
            freeBlk.Insert(temp.address + blockSize,temp.size - blockSize ,temp.size - blockSize);
        }
        allocBlk.Insert(temp.address,blockSize,temp.address);
        freeBlk.Delete(temp);
        return temp.address;
    } 
    
    public int Free(int startAddr) 
    {
        // Although the DLL Data Structure supports addition of elements with negative key but for the purpose of memory allocation start Address is considered to always be non-negative.
        if(startAddr < 0)
        {
            return -1;
        }
        Dictionary temp = allocBlk.Find(startAddr,true);
        if(temp == null)
        {
            return -1;
        }
        freeBlk.Insert(temp.address,temp.size,temp.size);
        allocBlk.Delete(temp);
        return 0;    
    }
}