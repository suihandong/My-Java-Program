import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T extends Comparable<T>> implements Iterable<T> {
    private Node<T> first, last;
    private int size;

    public LinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean add(T element) {
        // for the sake of lab, let's _not_ allow null data
        if(element == null) return false;

        Node<T> newNode = new Node<>(element);

        if(size == 0)
            first = newNode;
        else
            last.setNext(newNode);

        last = newNode;
        size++;

        return true;
    }

    public String toString() {
        String ret = "[";
        Node<T> ptr = first;
        for(int i = 0; i < size; i++) {
            ret += ptr.getData().toString() + ", ";
            ptr = ptr.getNext();
        }

        return size == 0 ? ret + "]" : ret.substring(0, ret.length() - 2) + "]";
    }

    // TODO implement me!
    private class LinkedListIterator implements Iterator<T> {
        Node<T> current = first;
        @Override
        public boolean hasNext() {

            if(current != null){
                return true;
            }

            return false;
        }

        @Override
        public T next() {
            if(hasNext()){
                T temp = current.getData();
                current = current.getNext();
                return temp;
            }else{
                throw new NoSuchElementException();
            }
        }
    }


    @Override
    public Iterator<T> iterator() {

        return new LinkedListIterator();
    }

    public static int[] xify(LinkedList<Integer> x){
        Iterator<Integer> X = x.iterator();

        int size = 0;
        while(X.hasNext()){
            size += X.next();
        }
        int[] myList = new int[size];

        X = x.iterator();
        int idx = 0;
        while(X.hasNext()){
            int count = X.next();

            for(int j = 0; j <count; j++ ){
                myList[idx + j] = count;
            }
            idx += count;

        }

        return myList;

    }

    public static LinkedList<Integer> countingSort(LinkedList<Integer> lst){
        int k = 0;

        Iterator<Integer> nlst = lst.iterator();
        while(nlst.hasNext()){
            int h = nlst.next();
            if(h > k){
                k = h;
            }
        }

        int[] counts = new int[k + 1];
        nlst = lst.iterator();
        while(nlst.hasNext()){
            counts[nlst.next()] += 1;
        }
        LinkedList<Integer> ret = new LinkedList<>();

        for(int i = 0; i < k+1; i++){
            for(int j = 0; j < counts[i]; j++){
                ret.add(i);
            }
        }

        return ret;
    }

    public void reverse(){
        Node<T> nf = first;
        Node<T> next = nf.getNext();

        int i = 0;
        int j = 0;
        int count = size-1;

       while ( i < this.size){
           nf = first;
           next = nf.getNext();
            while (j < count){
                if(nf.getNext() != null){
                    Node<T> temp = new Node<>();

                    temp.setData(next.getData());
                    next.setData(nf.getData());
                    nf.setData(temp.getData());

                    nf = nf.getNext();
                    next = nf.getNext();

                }
                j++;
                //count--;
            }

            System.out.println(this);
            j = 0;
            i++;
           count--;
        }

    }

    public static void main(String[] args){

        LinkedList<Integer> lst = new LinkedList<>();
        lst.add(12);
        lst.add(4);
        lst.add(2);
        lst.add(3);
        lst.add(5);
        System.out.println(lst.size);
        System.out.println(lst);
        lst.reverse();
        System.out.println();
        System.out.println(lst);
        lst.add(6);
        System.out.println(lst);


    }

}