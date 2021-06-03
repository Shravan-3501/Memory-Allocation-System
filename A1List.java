// Implements Dictionary using Doubly Linked List (DLL)
// Implement the following functions using the specifications provided in the class List

public class A1List extends List {

    private A1List  next; // Next Node
    private A1List prev;  // Previous Node 

    public A1List(int address, int size, int key) { 
        super(address, size, key);
    }
    
    public A1List(){    
        super(-1,-1,-1);
        // This acts as a head Sentinel

        A1List tailSentinel = new A1List(-1,-1,-1); // Intiate the tail sentinel
        
        this.next = tailSentinel;
        tailSentinel.prev = this;
    }

    public A1List Insert(int address, int size, int key)
    {
        A1List a = new A1List(address,size,key);
        if(this == null)
        {
            return null;
        }
        if(this.next == null)
        {
            return null;
        }
        a.next = this.next;
        a.next.prev = a;
        this.next = a;
        a.prev = this;
        return a;
    }

    public boolean Delete(Dictionary d) 
    {
        if(this == null || d == null)
        {
            return false;
        }
        if(this.key == d.key)
        {
            if(this.address == d.address && this.size == d.size) 
            {
                if(this.next != null && this.prev != null)
                {
                    // Convention assumed that if the node which identifies the list is to be deleted, the new identification node is the one previous to older identification node.
                    A1List prevnode = this.prev;   
                    this.address = prevnode.address;
                    this.size = prevnode.size;
                    this.key = prevnode.key;
                    this.prev = prevnode.prev;
                    if(prevnode.prev != null)
                    {
                        prevnode.prev.next = this;
                    }
                    return true;
                }
            }
        }
        // Convention assumed that first we start finding the node in the forward direction and then the backward direction.
        A1List current = this.next;
        while(current != null && current.next != null)
        {
            if(d.key == current.key)
            {
                if(d.address == current.address && d.size == current.size)
                {
                    if(current.next != null && current.prev != null)
                    {
                        A1List b = current.prev;
                        A1List f = current.next;
                        b.next = f;
                        f.prev = b;
                        current = null;
                        return true;
                    }
                }
            }
            current = current.next;
        }
        current = this.prev;
        while(current != null && current.prev != null)
        {
            if(d.key == current.key)
            {
                if(d.address == current.address && d.size == current.size)
                {
                    if(current.next != null && current.prev != null)
                    {
                        A1List b = current.prev;
                        A1List f = current.next;
                        b.next = f;
                        f.prev = b;
                        current = null;
                        return true;   
                    }
                }
            }
            current = current.prev;   
        }
        return false;
    }

    public A1List Find(int k, boolean exact)
    {
        A1List current = this;
        if(current == null)
        {
            return null;
        }
        while(current.prev != null)
        {
            current = current.prev;
        }
        current = current.next;
        if(exact == true)
        {
            // Head and tail sentinal are considered to NOT be parts of out DLL Data Structure.
            while(current != null && current.next != null)
            {
                if(current != null && current.key == k)
                {
                    return current;
                }
                current = current.next;
            }
            return null;
        } 
        else
        {
            while(current!= null && current.next != null)
            {
                if(current != null && current.key >= k)
                {
                    return current;
                }
                current = current.next;
            }
            return null;
        }
    }

    public A1List getFirst()
    {
        if(this == null)
        {
            return null;
        }
        A1List current = this.prev;
        A1List f = this;
        while(current != null)
        {
            f = current;
            current = current.prev;
        }
        A1List res = f.next;
        if(res == null || res.next == null)
        {
            return null;
        }
        return res;
    }
    
    public A1List getNext() 
    {
        if(this == null || this.next == null)
        {
            return null;
        }
        A1List nextnode = this.next;
        if(nextnode.next == null)
        {
            return null;
        }
        return nextnode;
    }

    public boolean sanity()
    {
        if(this == null)
        {
            return true;
        }
        // check loop using next pointers
        A1List first = this.getFirst();
        if(first != null)
        {
            A1List fp = first.next.next;
            A1List sp = first.next;
            while(fp != null && fp.next != null && sp != null)
            {
                if(fp == sp)
                {
                    return false;
                }
                fp = fp.next.next;
                sp = sp.next;
            }
        }
        // check loop using prev pointers
        A1List f = this.next;
        A1List current = this;
        while(f != null)
        {
            current = f;
            f = f.next;
        }
        A1List last = current.prev;
        if(last != null && last.prev != null)
        {
            A1List fp = last.prev.prev;
            A1List sp = last.prev;
            while(fp != null && fp.prev != null && sp != null)
            {
                if(fp == sp)
                {
                    return false;
                }
                fp = fp.prev.prev;
                sp = sp.prev;
            }
        }
    

        //check tail next is null and head prev is null and there is no other element in the DLL with next or prev as null
        current = this;
        while(current != null && current.next != null)
        {
            current = current.next;
        }
        if(current != null)
        {
            if(current.key != -1 || current.size != -1 || current.address != -1)
            {
                return false;
            }
        }
        current = this;
        while(current != null && current.prev != null)
        {
            current = current.prev;
        }
        if(current != null)
        {
            if(current.key != -1 || current.size != -1 || current.address != -1)
            {
                return false;
            }
        }
        // checks if node.next.prev is node
        current = this;
        while(current != null && current.next != null)
        {
            if(current.next != null && current.next.prev != current)
            {
                return false;
            }
            if(current.prev != null && current.prev.next != current)
            {
                return false;
            }
            current = current.next;
        }
        current = this;
        while(current != null && current.prev != null)
        {
            if(current.next != null && current.next.prev != current)
            {
                return false;
            }
            if(current.prev != null && current.prev.next != current)
            {
                return false;
            }
            current = current.prev;
        }
        return true;
    }

}


