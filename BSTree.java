// Class: Implementation of BST in A2
// Implement the following functions according to the specifications provided in Tree.java

public class BSTree extends Tree {

    private BSTree left, right;     // Children.
    private BSTree parent;          // Parent pointer.
        
    public BSTree(){  
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node!.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
    }    

    public BSTree(int address, int size, int key){
        super(address, size, key); 
    }

    public BSTree Insert(int address, int size, int key) 
    { 
        if(this == null)
        {
            return null;
        }
        BSTree newnode = new BSTree(address,size,key);
        BSTree current = this;
        BSTree prev = null;
        while(current.parent != null)
        {
            current = current.parent;
        }
        current = current.right;
        if(current == null)
        {
            this.right = newnode;
            newnode.parent = this;
            return newnode;
        }
        while(current != null)
        {
            prev = current;
            if(key > current.key)
            {
                current = current.right;
            }
            else if(key < current.key)
            {
                current = current.left;
            }
            else
            {
                if(current.address > address)
                {
                    current = current.left;
                }
                else
                {
                    current = current.right;
                }
            }
        }
        newnode.parent = prev;
        if(key > prev.key)
        {
            prev.right = newnode;
        }
        else if(key < prev.key)
        {
            prev.left = newnode;
        }
        else
        {
            if(prev.address < address)
            {
                prev.right = newnode;
            }
            else
            {
                prev.left = newnode;
            }
        }
        return newnode;
    }

    public boolean Delete(Dictionary e)
    { 
        BSTree prev = null;
        BSTree current = this;
        if(current == null)
        {
            return false;
        }
        while(current.parent != null)
        {
            current = current.parent;
        }
        current = current.right;
        if(current == null)
        {
            return false;
        }
        while(current != null)
        {
            if(e.key > current.key)
            {
                current = current.right;
            }
            else if(e.key < current.key)
            {
                current = current.left;
            }
            else
            {
                if(e.size == current.size && e.address == current.address)
                {
                    BSTree parentnode = current.parent;
                    if(current.left == null && current.right == null)
                    {
                        if(parentnode == null)
                        {
                            current = null;
                        }
                        else
                        {
                            if(current == parentnode.right)
                            {
                                parentnode.right = null;
                            }
                            else
                            {
                                parentnode.left = null;
                            }
                        }
                    }
                    else if(current.left == null || current.right == null)
                    {
                        BSTree child = current.left;
                        if(current.left == null)
                        {
                            child = current.right;
                        }
                        if(parentnode == null)
                        {
                            current.left = null;
                            current.right = null;
                            child.parent = null;
                        }
                        else
                        {
                            child.parent = parentnode;
                            if(current == parentnode.left)
                            {
                                parentnode.left = child;
                            }
                            else
                            {
                                parentnode.right = child;
                            }
                        }
                    }
                    else
                    {
                        BSTree a = current.right;
                        BSTree b = null;
                        while(a != null)
                        {
                            b = a;
                            a = a.left;
                        }
                        if(b.right == null)
                        {
                            BSTree x = b.parent;
                            if(b == x.right)
                            {
                                x.right = null;
                            }
                            else
                            {
                                x.left = null;
                            }
                        }
                        else
                        {
                            BSTree x = b.parent;
                            BSTree y = b.right;
                            if(b == x.right)
                            {
                                x.right = y;
                                y.parent = x;
                            }
                            else
                            {
                                y.parent = x;
                                x.left = y;
                            }

                        }
                        BSTree p = parentnode;
                        BSTree lc = current.left;
                        BSTree rc = current.right;
                        b.parent = p;
                        b.left = lc;
                        b.right = rc;
                        if(p != null)
                        {
                            if(p.right == current)
                            {
                                p.right = b;
                            }
                            else
                            {
                                p.left = b;
                            }
                        }
                        if(lc != null)
                        {
                            lc.parent = b;
                        }
                        if(rc != null)
                        {
                            rc.parent = b;
                        }
                    }
                    return true;
                }
                else
                {
                    if(current.address < e.address)
                    {
                        current = current.right;
                    }
                    else
                    {
                        current = current.left;
                    }
                }
            }
        }
        return false;
    }
        
    public BSTree Find(int key, boolean exact)
    { 
        BSTree current = this;
        if(current == null)
        {
            return null;
        }
        BSTree prev = null;
        while(current.parent != null)
        {
            current = current.parent;
        }
        current = current.right;
        if(current == null)
        {
            return null;
        }
        BSTree res = null;
        if(exact == true)
        {
            while(current != null)
            {
                if(current.key == key)
                {
                    res = current;
                    current = current.left;
                }
                else if(key > current.key)
                {
                    current = current.right;
                }
                else
                {
                    current = current.left;
                }
            }
        }
        else
        {
            while(current != null)
            {
                if(current.key == key)
                {
                    res = current;
                    current = current.left;
                }
                else if(key > current.key)
                {
                    current = current.right;
                }
                else
                {
                    res = current;
                    current = current.left;
                }
            }
        }
        return res;
    }

    public BSTree getFirst()
    { 
        if(this == null)
        {
            return null;
        }
        BSTree prev = null;
        BSTree current = this;
        while(current.parent != null)
        {
            current = current.parent;
        }
        current = current.right;
        while(current != null)
        {
            prev = current;
            current = current.left;
        }
        return prev;
    }

    public BSTree getNext()
    {
        BSTree current = this; 
        if(current == null)
        {
            return null;
        }
        if(current.parent == null)
        {
            return null;
        }
        if(current.right != null)
        {
            BSTree a = current.right;
            BSTree b = null;
            while(a != null)
            {
                b = a;
                a = a.left;
            }
            return b;
        }
        else                        
        {
            BSTree a = current;
            BSTree b = current.parent;
            while(b.parent != null)
            {
                if(a == b.left)
                {
                    return b;
                }
                a = b;
                b = b.parent;
            }
        }  
        return null;
    }

    private boolean BSTProperty(int keymin,int keymax,int addrmin,int addrmax,boolean isLeftChild)
    {
        if(this.key < keymin || this.key > keymax)
        {
            return false;
        }
        else 
        {
            if(this.key == keymax)
            {
                if(this.address > addrmax)
                {
                    return false;
                }
            }
            if(this.key == keymin)
            {
                if(this.address < addrmin)
                {
                    return false;
                }
            }
        }
        if(this.left != null)
        {
            boolean x = this.left.BSTProperty(keymin,this.key,addrmin,this.address,true);
            if(x == false)
            {
                return false;
            }
        }
        if(this.right != null)
        {
            boolean y = this.right.BSTProperty(this.key,keymax,this.address,addrmax,false);
            if(y == false)
            {
                return false;
            }
        }
        return true;
    }
    
    private boolean checkConnection(boolean isLeftChild)
    {
        BSTree parentnode = this.parent;
        BSTree lc = this.left;
        BSTree rc = this.right;
        if(parentnode != null)
        {
            if(isLeftChild == true)
            {
                if(parentnode.left != this)
                {
                    return false;
                }
            }
            else
            {
                if(parentnode.right != this)
                {
                    return false;
                }
            }
        }
        if(lc != null)
        {
            if(lc.parent != this)
            {
                return false;
            }
        }
        if(rc != null)
        {
            if(rc.parent != this)
            {
                return false;
            }
        }
        if(lc != null)
        {
            boolean x = lc.checkConnection(true);
            if(x == false)
            {
                return false;
            }
        }
        if(rc != null)
        {
            boolean y = rc.checkConnection(false);
            if(y == false)
            {
                return false;
            }
        }
        return true;
    }
    
    public boolean sanity()
    { 
        BSTree current = this;
        if(this.parent != null)
        {
            BSTree fp = this.parent.parent;
            BSTree sp = this.parent;
            while(fp != null && fp.parent != null && sp != null)
            {
                if(fp == sp)
                {
                    return false;
                }
                fp = fp.parent.parent;
                sp = sp.parent;
            }
        }
        while(current.parent != null)
        {
            current = current.parent;
        }
        if(current.key != -1 || current.address != -1 || current.size != -1)
        {
            return false;
        }
        current = current.right;
        if(current == null)
        {
            return true;
        }
        if(this.checkConnection(false) == false)
        {
            return false;
        }
        if(this.BSTProperty(Integer.MIN_VALUE,Integer.MAX_VALUE,Integer.MIN_VALUE,Integer.MAX_VALUE,false) == false)
        {
            return false;
        }
        current = this;
        if(this.parent == null)
        {
            current = current.right;
        }
        current = current.getFirst();
        BSTree next = current.getNext();
        while(next != null)
        {
            if(current.key > next.key)
            {
                return false;
            }
            else if(current.key == next.key)
            {
                if(current.address > next.address)
                {
                    return false;
                }
            }
            current = next;
            next = next.getNext();
        }
        return true;
    }
}


 


