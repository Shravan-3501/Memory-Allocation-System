// Class: Height balanced AVL Tree
// Binary Search Tree

public class AVLTree extends BSTree {
    
    private AVLTree left, right;     // Children. 
    private AVLTree parent;          // Parent pointer. 
    private int height;  // The height of the subtree
        
    public AVLTree() { 
        super();
        // This acts as a sentinel root node
        // How to identify a sentinel node: A node with parent == null is SENTINEL NODE
        // The actual tree starts from one of the child of the sentinel node !.
        // CONVENTION: Assume right child of the sentinel node holds the actual root! and left child will always be null.
        
    }

    public AVLTree(int address, int size, int key) { 
        super(address, size, key);
        this.height = 0;
    }

    // Implement the following functions for AVL Trees.
    // You need not implement all the functions. 
    // Some of the functions may be directly inherited from the BSTree class and nothing needs to be done for those.
    // Remove the functions, to not override the inherited functions.
    
    private AVLTree rotate()
    {
        AVLTree x = this;
        int a = -1;
        int b = -1;
        if(x.right != null)
        {
            a = x.right.height;
        }
        if(x.left != null)
        {
            b = x.left.height;
        }
        AVLTree y = x.left;
        if(a>b)
        {
            y = x.right;
        }
        a = -1;
        b = -1;
        if(y.right != null)
        {
            a = y.right.height;
        }
        if(y.left != null)
        {
            b = y.left.height;
        }
        AVLTree z;
        if(a > b)
        {
            z = y.right;
        } 
        else if(a<b)
        {
            z = y.left;
        }
        else
        {
            if(y == x.right)
            {
                z = y.right;
            }
            else
            {
                z = y.left;
            }
        }
        if(y == x.right)
        {
            if(z == y.right)    // Case 1 : RR
            {
                y.parent = x.parent;
                if(x == x.parent.right)
                {
                    x.parent.right = y;
                }
                else
                {
                    x.parent.left = y;
                }
                x.right = y.left;
                if(y.left != null)
                {
                    y.left.parent = x;
                }
                x.parent = y;
                y.left = x;
                a = -1;
                b = -1;
                if(x.right != null)
                {
                    a = x.right.height;
                }
                if(x.left != null)
                {
                    b = x.left.height;
                }
                x.height = Math.max(a,b) + 1;
                y.height = Math.max(x.height,z.height) + 1;
                return y;
            }
            else                // Case 2 : RL
            {
                z.parent = x.parent;
                if(x == x.parent.right)
                {
                    x.parent.right = z;
                }
                else
                {
                    x.parent.left = z;
                }
                y.left = z.right;
                if(z.right != null)
                {
                    z.right.parent = y;
                }
                x.right = z.left;
                if(z.left != null)
                {
                    z.left.parent = x;
                }
                x.parent = z;
                y.parent = z;
                z.left = x;
                z.right = y;
                a = -1;
                b = -1;
                if(x.right != null)
                {
                    a = x.right.height;
                }
                if(x.left != null)
                {
                    b = x.left.height;
                }
                x.height = Math.max(a,b) + 1;
                a = -1;
                b = -1;
                if(y.right != null)
                {
                    a = y.right.height;
                }
                if(y.left != null)
                {
                    b = y.left.height;
                }
                y.height = Math.max(a,b) + 1;
                z.height = Math.max(x.height,y.height) + 1;
                return z;
            }
        }
        else
        {
            if(z == y.left)  // Case 3 : LL
            {
                y.parent = x.parent;
                if(x == x.parent.right)
                {
                    x.parent.right = y;
                }
                else
                {
                    x.parent.left = y;
                }
                x.left = y.right;
                if(y.right != null)
                {
                    y.right.parent = x;
                }
                x.parent = y;
                y.right = x;
                a = -1;
                b = -1;
                if(x.right != null)
                {
                    a = x.right.height;
                }
                if(x.left != null)
                {
                    b = x.left.height;
                }
                x.height = Math.max(a,b) + 1;
                y.height = Math.max(x.height,z.height) + 1;
                return y;
            }
            else            // Case 4 : LR
            {
                z.parent = x.parent;
                if(x == x.parent.right)
                {
                    x.parent.right = z;
                }
                else
                {
                    x.parent.left = z;
                }
                y.right = z.left;
                if(z.left != null)
                {
                    z.left.parent = y;
                }
                x.left = z.right;
                if(z.right != null)
                {
                    z.right.parent = x;
                }
                x.parent = z;
                y.parent = z;
                z.right = x;
                z.left = y;
                a = -1;
                b = -1;
                if(x.right != null)
                {
                    a = x.right.height;
                }
                if(x.left != null)
                {
                    b = x.left.height;
                }
                x.height = Math.max(a,b) + 1;
                a = -1;
                b = -1;
                if(y.right != null)
                {
                    a = y.right.height;
                }
                if(y.left != null)
                {
                    b = y.left.height;
                }
                y.height = Math.max(a,b) + 1;
                z.height = Math.max(x.height,y.height) + 1;
                return z;
            }
        }
    }

    private void UpdateHeight()
    {
        AVLTree current = this;
        while(current.parent != null)
        {
            if(current.left == null && current.right == null)
            {
                current.height = 0;
            }
            else if(current.left == null)
            {
                current.height = current.right.height + 1;
            }
            else if(current.right == null)
            {
                current.height = current.left.height + 1;
            }
            else
            {
                current.height = Math.max(current.left.height,current.right.height) + 1;
            }
            current = current.parent;
        }
    }

    private AVLTree FindImbalance()
    {
        AVLTree current = this;
        while(current.parent != null)
        {
            int a = -1;
            int b = -1;
            if(current.right != null)
            {
                a = current.right.height;
            }
            if(current.left != null)
            {
                b = current.left.height;
            }
            if(Math.abs(a-b) > 1)
            {
                return current;
            }
            current = current.parent;
        }
        return null;
    }
    
    public AVLTree Insert(int address, int size, int key) 
    { 
        AVLTree newnode = new AVLTree(address,size,key);
        AVLTree current = this;
        AVLTree prev = null;
        while(current.parent != null)
        {
            current = current.parent;
        }
        current = current.right;
        if(current == null)
        {
            this.right = newnode;
            newnode.parent = this;
            newnode.height = 0;
            newnode.UpdateHeight();
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
        newnode.height = 0;
        newnode.UpdateHeight();
        AVLTree badnode = newnode.FindImbalance();
        if(badnode != null)
        {
            badnode = badnode.rotate();
            badnode.UpdateHeight();
        }
        return newnode;
    }

    public boolean Delete(Dictionary e)
    {
        AVLTree prev = null;
        AVLTree current = this;
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
                    AVLTree checknode = null;
                    AVLTree parentnode = current.parent;
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
                        checknode = parentnode;
                    }
                    else if(current.left == null || current.right == null)
                    {
                        AVLTree child = current.left;
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
                            checknode = child;
                        }
                    }
                    else
                    {
                        AVLTree a = current.right;
                        AVLTree b = null;
                        while(a != null)
                        {
                            b = a;
                            a = a.left;
                        }
                        if(b.right == null)
                        {
                            AVLTree x = b.parent;
                            if(b == x.right)
                            {
                                x.right = null;
                            }
                            else
                            {
                                x.left = null;
                            }
                            checknode = x;
                        }
                        else
                        {
                            AVLTree x = b.parent;
                            AVLTree y = b.right;
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
                            checknode = y;
                        }
                        AVLTree p = parentnode;
                        AVLTree lc = current.left;
                        AVLTree rc = current.right;
                        b.parent = p;
                        b.left = lc;
                        b.right = rc;
                        b.height = current.height;
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
                        int d = -1;
                        int c = -1;
                        if(b.right != null)
                        {
                            d = b.right.height;
                        }
                        if(b.left != null)
                        {
                            c = b.left.height;
                        }
                        b.height = Math.max(d,c) + 1;
                        if(checknode == current)
                        {
                            checknode = b;
                        }
                    }
                    AVLTree temp = checknode;
                    temp.UpdateHeight();
                    while(temp != null && temp.parent != null)
                    {
                        AVLTree badnode = temp.FindImbalance();
                        if(badnode != null)
                        {
                            temp = badnode.rotate();
                            temp.UpdateHeight();
                        }
                        else
                        {
                            break;
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
        
    public AVLTree Find(int key, boolean exact)
    { 
        AVLTree current = this;
        if(current == null)
        {
            return null;
        }
        while(current.parent != null)
        {
            current = current.parent;
        }
        current = current.right;
        if(current == null)
        {
            return null;
        }
        AVLTree res = null;
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

    public AVLTree getFirst()
    { 
        AVLTree prev = null;
        AVLTree current = this;
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

    public AVLTree getNext()
    {
        AVLTree current = this; 
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
            AVLTree a = current.right;
            AVLTree b = null;
            while(a != null)
            {
                b = a;
                a = a.left;
            }
            return b;
        }
        else                        
        {
            AVLTree a = current;
            AVLTree b = current.parent;
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
        AVLTree parentnode = this.parent;
        AVLTree lc = this.left;
        AVLTree rc = this.right;
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

    private boolean checkHeight()
    {
        AVLTree current = this;
        int a = -1;
        int b = -1;
        if(this.right != null)
        {
            a = this.right.height;
        }
        if(this.left != null)
        {
            b = this.left.height;
        }
        if(Math.abs(a-b) > 1)
        {
            return false;
        }
        if(this.right != null)
        {
            if(this.right.checkHeight() == false)
            {
                return false;
            }
        }
        if(this.left != null)
        {
            if(this.left.checkHeight() == false)
            {
                return false;
            }
        }
        return true;
    }
    
    public boolean sanity()
    { 
        AVLTree current = this;
        if(this.parent != null)
        {
            AVLTree fp = this.parent.parent;
            AVLTree sp = this.parent;
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
        if(current.BSTProperty(Integer.MIN_VALUE,Integer.MAX_VALUE,Integer.MIN_VALUE,Integer.MAX_VALUE,false) == false)
        {
            return false;
        }
        if(current.checkHeight() == false)
        {
            return false;
        }
        current = this;
        if(this.parent == null)
        {
            current = current.right;
        }
        current = current.getFirst();
        AVLTree next = current.getNext();
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


