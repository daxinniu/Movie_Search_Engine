package project6;

import java.util.*;
import java.util.stream.Stream;

/**
 * This is a generic Binary Search Tree class that will be used to store Movies.
 * This class extends comparable and implements Collection.
 * @author Daxin Niu
 * @version 12/4/2018
 */

public class BST<E extends Comparable<E>> implements Collection<E> {
    //BST Data field.
    BSTNode<E> root;
    private int size;

    //BST Methods.
    /**
     * This is the constructor for BST and will make a BST with size 0.
     */
    public BST(){
        new BST(0);
    }

    /**
     * This constructor makes a BST with a give size.
     * @param size
     */
    public BST(int size) {
        setSize(size);
    }

    /**
     * This is a private method used to set the size for the BST.
     * @param size
     */
    private void setSize(int size) {
        this.size = size;
    }

    /**
     * This private method is used to calculate tree size recursively.
     * @param n
     * @return int
     */
    private int size(BSTNode<E> n) {
        //Base case when n is null.
        if(n == null) return 0;
        //Recursive step.
        return 1 + size(n.left) + size(n.right);
    }

    //Public methods.
    /**
     * This method is used to search for a given value and return the value
     * if it is found in the tree. Return null if the value is not found.
     * @param value
     * @return E
     */
    public E get(E value) {
        //Check if value is null.
        if(value == null) return null;
        //Check if root is null.
        if(root == null) return null;
        //Call helper method.
        return getHelper(root, value);
    }

    /**
     * This is the recursive helper method for the get method. It goes left or right
     * through the tree base on BST rules and return the value if it is found in the tree.
     * @param Node
     * @param value
     * @return E
     */
    private E getHelper(BSTNode<E> Node, E value) {
        //Check if Node is null.
        if(Node == null) return null;
        //Return the element if it is found.
        if(Node.getData().equals(value)) return Node.getData();
        //Keep going right if value is greater than Node.
        else if(Node.getData().compareTo(value) < 0) {
            return getHelper(Node.right, value);
        }
        //Keep going left if value is smaller than Node.
        else if(Node.getData().compareTo(value) > 0) {
            return getHelper(Node.left, value);
        }
        else {
            return null;
        }
    }

    /**
     * This method is used to print the BST in a given format.
     * @return output
     */
    public String toString() {
        String output = "";
        //Loop through the tree and print in given format.
        for(E i: this) {
            output += "[" + i + "], ";
        }
        return output;
    }

    /**
     * This method is used to print the BST in a tree format.
     * @return String
     */
    public String toStringTreeFormat() {
        StringBuilder s = new StringBuilder();
        preOrderPrint(root, 0, s);
        return s.toString();
    }

    /**
     * This is a private method used for toStringTreeFormat and print out
     * the tree in preOrder format.
     * @param tree
     * @param level
     * @param output
     * @author Joanna Klukowska
     */
    private void preOrderPrint(BSTNode<E> tree, int level, StringBuilder output) {
        //Check if tree is null or not.
        if (tree != null) {
            String spaces = "\n";
            if (level > 0) {
                for (int i = 0; i < level - 1; i++)
                    spaces += " ";
                spaces += "|--";
            }
            output.append(spaces);
            output.append(tree.data);
            preOrderPrint(tree.left, level + 1, output);
            preOrderPrint(tree.right, level + 1, output);
        }
        else {
            String spaces = "\n";
            if (level > 0) {
                for (int i = 0; i < level - 1; i++)
                    spaces += " ";
                spaces += "|--";
            }
            output.append(spaces);
            output.append("null");
        }
    }

    //Tree Set Methods.
    /**
     * This method returns the least element in this tree that is greater
     * or equal to the given element.
     * @param e
     * @return E
     */
    public E ceiling(E e) {
        //Check if e is null.
        if(e == null) return null;
        //Check if root is null.
        if(root == null) return null;
        //Call helper method.
        return ceilingHelper(root, e, null);
    }

    /**
     * This is the recursive helper method for ceiling method. It searches through
     * the tree and have a placeholder for possible result. If there is no matching
     * element, the method will return null.
     * @param Node
     * @param e
     * @param hold
     * @return E
     */
    private E ceilingHelper(BSTNode<E> Node, E e, E hold) {
        //Hold potential result.
        E save = hold;
        //Return save value at the end.
        if(Node == null) return save;
        //Return Node.data if it e is found in the tree.
        if(Node.getData().compareTo(e) == 0) {
            return Node.getData();
        }
        //Go right if Node.data is smaller than e.
        else if(Node.getData().compareTo(e) < 0) {
            return ceilingHelper(Node.right, e, hold);
        }
        //Go left if Node.data is greater than e.
        else if(Node.getData().compareTo(e) > 0) {
            //Hold the potential result.
            hold = Node.data;
            return ceilingHelper(Node.left, e, hold);
        }
        else {
            return null;
        }
    }

    /**
     * This method returns the greatest element in this tree that is smaller
     * or equal to the given element.
     * @param e
     * @return E
     */
    public E floor(E e) {
        //Check if e is null.
        if(e == null) return null;
        //Check is root is null.
        if(root == null) return null;
        //Call helper method.
        return floorHelper(root, e, null);
    }

    /**
     * This is the recursive helper method for ceiling method. It searches through
     * the tree and have a placeholder for possible result. If there is no matching
     * element, the method will return null.
     * @param Node
     * @param e
     * @param hold
     * @return
     */
    private E floorHelper(BSTNode<E> Node, E e, E hold) {
        //Save potential result.
        E save = hold;
        //Return save value at the end.
        if(Node == null) return save;
        //Return Node.data if e is found in tree.
        if(Node.getData().compareTo(e) == 0) {
            return Node.getData();
        }
        //Go left if Node.data is greater than e.
        else if(Node.getData().compareTo(e) > 0) {
            return floorHelper(Node.left, e, hold);
        }
        //Go right if Node.data is smaller than e.
        else if(Node.getData().compareTo(e) < 0) {
            //Hold potential result.
            hold = Node.data;
            return floorHelper(Node.right, e, hold);
        }
        else{
            return null;
        }
    }

    /**
     * This method returns the least element in this tree that is strictly
     * greater than the given element.
     * @param e
     * @return E
     */
    public E higher(E e) {
        //Check if e is null.
        if(e == null) return null;
        //Check if root is null.
        if(root == null) return null;
        //Call helper method.
        return higherHelper(root, e, null);
    }

    /**
     * This is the recursive helper method for ceiling method. It searches through
     * the tree and have a placeholder for possible result. If there is no matching
     * element, the method will return null.
     * @param Node
     * @param e
     * @param hold
     * @return E
     */
    private E higherHelper(BSTNode<E> Node, E e, E hold) {
        //Save potential result
        E save = hold;
        //Return save value at the end.
        if(Node == null) return save;
        //Go right if Node.data is less or equal to e.
        if(Node.getData().compareTo(e) <= 0) {
            return higherHelper(Node.right, e, hold);
        }
        //Go left if Node.data is greater than e.
        else if(Node.getData().compareTo(e) > 0) {
            //Hold potential result.
            hold = Node.data;
            return higherHelper(Node.left, e, hold);
        }
        else {
            return null;
        }
    }

    /**
     * This method returns the greatest element in this tree that is strictly
     * smaller than the given element.
     * @param e
     * @return E
     */
    public E lower(E e) {
        //Check if e is null.
        if(e == null) return null;
        //Check if root is null.
        if(root == null) return null;
        //Call helper method.
        return lowerHelper(root, e, null);
    }

    /**
     * This is the recursive helper method for ceiling method. It searches through
     * the tree and have a placeholder for possible result. If there is no matching
     * element, the method will return null.
     * @param Node
     * @param e
     * @param hold
     * @return E
     */
    private E lowerHelper(BSTNode<E> Node, E e, E hold) {
        //Save potential result.
        E save = hold;
        //Return save result at the end.
        if(Node == null) return save;
        //Go left if Node.data is greater or equal to e.
        if(Node.getData().compareTo(e) >= 0) {
            return lowerHelper(Node.left, e, hold);
        }
        //Go right if Node.data is smaller than e.
        else if(Node.getData().compareTo(e) < 0) {
            //Hold potential result.
            hold = Node.data;
            return lowerHelper(Node.right, e, hold);
        }
        else{
            return null;
        }
    }

    /**
     * This method creates a shallow copy of the original tree.
     * @return Object
     */
    public Object clone() {
        //Check if the tree is empty.
        if(root == null) {return null;}
        //Make a new tree.
        BST<E> copy = new BST<E>();
        //Point it to the original tree.
        copy = this;
        return copy;
    }

    /**
     * This method returns the first(lowest) element of the current set(tree).
     * @return E
     */
    public E first() {
        //Check if root is null.
        if(root == null) return null;
        BSTNode current = root;
        //Keep going left when there are still a left node.
        while(current.left != null) {current = current.left;}
        return (E) current.getData();
    }

    /**
     * This method returns the last(highest) element of the current set(tree).
     * @return E
     */
    public E last() {
        //Check if root is null.
        if(root == null) return null;
        BSTNode current = root;
        //Keep going right when there are still a right node.
        while(current.right != null) {current = current.right;}
        return (E) current.getData();
    }


    //Collection Methods.
    /**
     * Add an element to the BST and return true if success, false if failed.
     * @param e
     * @return boolean
     */
    public boolean add(E e) {
        //Check if e is null.
        if(e == null) {return false;}
        //Check if the tree already have the element.
        if(this.contains(e)) {return false;}
        //Call recursive helper method.
        root = addHelper(root, e);
        //Increase size by 1.
        size += 1;
        return true;
    }

    /**
     * This is the helper method for add. It creates a new BSTNode and
     * add it at the correct location.
     * @param Node
     * @param e
     * @return BSTNode
     */
    private BSTNode addHelper(BSTNode<E> Node, E e) {
        //Base case add the node when the next location is null.
        if(Node == null) {
            return new BSTNode(e);
        }
        //Go left if Node.data is greater than e.
        if(Node.getData().compareTo(e) > 0) {
            Node.left = addHelper(Node.left, e);
        }
        //Go right otherwise.
        else {
            Node.right = addHelper(Node.right, e);
        }
        return Node;
    }

    /**
     * Left for implementation.
     * @param c
     * @return
     */
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    /**
     * This method clears the tree.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * This method checks if an Object is in the tree.
     * @param o
     * @return boolean
     */
    public boolean contains (Object o) {
        //Check if o is null
        if(o == null) return false;
        //Call recursive helper method.
        return containsHelper(o, root);
    }

    /**
     * This is the recursive helper method for contains method.
     * @param o
     * @param n
     * @return boolean
     */
    public boolean containsHelper(Object o, BSTNode<E> n) {
        //Check if n is null.
        if(n == null) {
            return false;
        }
        //Check if they are the same class.
        if(o.getClass() != n.getData().getClass()) {return false;}
        //Base case: return true if o equals n.data.
        if(o.equals(n.data)) {
            return true;
        }
        //Recursive steps going either left or right.
        if(((E)o).compareTo(n.data) < 0) {
            return containsHelper(o, n.left);
        }else{
            return containsHelper(o, n.right);
        }
    }

    /**
     * This method checks if a collection of elements are contained in this set.
     * @param c
     * @return boolean
     */
    public boolean containsAll (Collection<?> c) {
        //Convert the collection c into an Array.
        Object[] input = c.toArray();
        //for loop going through input to check if any of the element not in this set.
        for(Object i: input) {
            if(!this.contains(i)) {
               return false;
            }
        }
        return true;
    }

    /**
     * This is the equals method to check if two trees are equal.
     * @param o
     * @return boolean
     */
    @Override
    public boolean equals (Object o) {
        //Check if o is null.
        if (o == null) {
            //True if both o and this tree is null.
            if(this == null) {return true;}
            return false;
        }
        //If same reference address, return true.
        if (o == this) {
            return true;
        }
        //If not instance of BST, return false.
        if (!(o instanceof BST)) {
            return false;
        }
        BST other = (BST) o;
        //Return false if size are different.
        if (this.size != (other.size)) {
            return false;
        }
        //Loop going through each element to check.
        for(Object e: other) {
            if(!this.contains(e)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Left for implementation.
     * @return int
     */
    public int hashCode() {
        throw new UnsupportedOperationException();
    }

    /**
     * Check if BST is empty.
     * @return boolean
     */
    public boolean isEmpty() {
        if(root == null) return true;
        else {
            return false;
        }
    }

    /**
     * Create an inOrder traversal iterator.
     * @return Iterator
     */
    public Iterator<E> iterator() {
        return new inOrderIterator();
    }

    /**
     * Create a preOrder traversal iterator.
     * @return iterator
     */
    Iterator<E> preorderIterator() {
        return new preOrderIterator();
    }

    /**
     * Create a postOrder traversal iterator.
     * @return iterator
     */
    Iterator<E> postorderIterator() {
        return new postOrderIterator();
    }

    /**
     * Left for implementation.
     * @return Stream
     */
    public Stream<E> parallelStream() {
        throw new UnsupportedOperationException();
    }

    /**
     * This method remove a given object from the tree.
     * @param o
     * @return boolean
     */
    public boolean remove (Object o) {
        //Check if root is null.
        if(root == null) {return false;}
        //Check if o is null.
        if(o == null) {return false;}
        //Call recursive helper method.
        root = recRemove( root, (E)o );
        size -= 1;
        return true;
    }

    /**
     * This method return the predecessor of a node.
     * @param n
     * @return E
     * @author Joanna Klukowska
     */
    private E getPredecessor ( BSTNode<E> n ) {
        //Check if n is null.
        if (n.left == null)
            return null;
        else {
            BSTNode<E> current = n.left;
            while (current.right != null) {
                current = current.right;
            }
            return current.data;
        }
    }

    /**
     * This method remove a method and return a BSTNode.
     * @param node
     * @return BSTNode
     * @author Joanna Klukowska
     */
    public BSTNode<E> remove ( BSTNode<E> node ) {
        if (node.left == null)
            return node.right;
        if (node.right == null)
            return node.left;
        //otherwise we have two children
        E data = getPredecessor(node);
        node.data = data;
        node.left = recRemove(node.left, data);
        return node;
    }

    /**
     * Recursive method that used for remove method.
     * @param node
     * @param item
     * @return BSTNode
     * @author Joanna Klukowska
     */
    private BSTNode <E> recRemove (BSTNode <E> node, E item ) {
        if (node == null){}
            //do nothing, the item is not in the tree
        else if (item.compareTo(node.data) < 0)
            node.left = recRemove(node.left, item); //search in the left subtree
        else if (item.compareTo(node.data) > 0)
            node.right = recRemove(node.right, item); //search in the right subtree
        else
            //found it!
            //remove the data stored in the node
            node = remove(node);
        return node;
    }

    /**
     * Left for implementation.
     * @param c
     * @return boolean
     */
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    /**
     * Left for implementation.
     * @param c
     * @return boolean
     */
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    /**
     * This method returns the size of the BST.
     * @return int
     */
    public int size() {
        //Call the recursive size method.
        return size(root);
    }

    /**
     * Left for implementation.
     * @return Spliterator
     */
    public Spliterator<E> spliterator() {
        throw new UnsupportedOperationException();
    }

    /**
     * Left for implementation.
     * @return Stream
     */
    public Stream<E> stream() {
        throw new UnsupportedOperationException();
    }

    /**
     * This method convert BST to and array.
     * @return Object[]
     */
    public Object[] toArray() {
        //Create and new ArrayList.
        ArrayList<E> outArray = new ArrayList<E>();
        //Put element in the tree into the array.
        for(E c: this) {
            outArray.add(c);
        }
        return outArray.toArray();
    }

    /**
     * This method convert BST to an array(Generic type).
     * @param a
     * @param <T>
     * @return T[]
     */
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            a = (T[])java.lang.reflect.Array.newInstance(
                    a.getClass().getComponentType(), size);
        int i = 0;
        Object[] result = a;
        for (E c: this)
            result[i++] = c;
        if (a.length > size)
            a[size] = null;
        return a;
    }


    //Iterator classes.
    /**
     * This is the preOrder Iterator class. It implements Iterator.
     */
    private class preOrderIterator implements Iterator<E> {
        //Create an ArrayList for use.
        private ArrayList<E> list = new ArrayList();
        int current;
        E now;

        /**
         * This recursive method put elements in the tree in to the list using
         * preOrder traversal.
         * @param Node
         */
        private void preOrder(BSTNode<E> Node) {
            if(Node != null) {
                list.add(Node.data);
                preOrder(Node.left);
                preOrder(Node.right);
            }
        }

        /**
         * Constructor for the preOrderIterator.
         */
        public preOrderIterator() {
            preOrder(root);
            current = 0;
            now = list.get(current);
        }

        /**
         * Check if there is a next element.
         * @return boolean
         */
        @Override
        public boolean hasNext() {
            return current < (list.size());
        }

        /**
         * This method return the next element.
         * @return E
         */
        @Override
        public E next() {
            now = list.get(current);
            current++;
            return now;
        }
    }

    /**
     * This is the inOrder Iterator class. It implements Iterator.
     */
    private class inOrderIterator implements Iterator<E> {
        private ArrayList<E> list = new ArrayList();
        int current;
        E now;

        /**
         * This recursive method put elements in the tree in to the list using
         * inOrder traversal.
         * @param Node
         */
        private void inOrder(BSTNode<E> Node) {
            if(Node != null) {
                inOrder(Node.left);
                list.add(Node.data);
                inOrder(Node.right);
            }
        }

        /**
         * Constructor for the inOrderIterator.
         */
        public inOrderIterator() {
            inOrder(root);
            current = 0;
            now = list.get(current);
        }

        /**
         * Check if there is a next element.
         * @return boolean
         */
        @Override
        public boolean hasNext() {
            return current < (list.size());
        }

        /**
         * This method return the next element.
         * @return E
         */
        @Override
        public E next() {
            now = list.get(current);
            current++;
            return now;
        }
    }

    /**
     * This is the postOrder Iterator class. It implements Iterator.
     */
    private class postOrderIterator implements Iterator<E> {
        private ArrayList<E> list = new ArrayList();
        int current;
        E now;

        /**
         * This recursive method put elements in the tree in to the list using
         * postOrder traversal.
         * @param Node
         */
        private void postOrder(BSTNode<E> Node) {
            if(Node != null) {
                postOrder(Node.left);
                postOrder(Node.right);
                list.add(Node.data);
            }
        }

        /**
         * Constructor for the postOrderIterator.
         */
        public postOrderIterator() {
            postOrder(root);
            current = 0;
            now = list.get(current);
        }

        /**
         * Check if there is a next element.
         * @return boolean
         */
        @Override
        public boolean hasNext() {
            return current < (list.size());
        }

        /**
         * This method return the next element.
         * @return E
         */
        @Override
        public E next() {
            now = list.get(current);
            current++;
            return now;
        }
    }

    //BSTNode class.
    /**
     * This is the BSTNode class for this tree.
     * @param <E>
     */
    private static class BSTNode <E> {
        //BSTNode data field.
        private E data;
        private BSTNode<E> left;
        private BSTNode<E> right;

        /**
         * This is the constructor for this class. It creates a BSTNode
         * using the given data.
         * @param data
         */
        public BSTNode(E data){
            this.data = data;
        }

        /**
         * This method return the data of a BSTNode.
         * @return E
         */
        public E getData() {
            return data;
        }

        /**
         * This is the three parameter constructor of the BSTNode taking
         * the data, left and right BSTNodes.
         * @param data
         * @param left
         * @param right
         */
        public BSTNode(E data, BSTNode<E> left, BSTNode<E> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
}
