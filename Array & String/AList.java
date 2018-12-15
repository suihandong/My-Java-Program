/*
 * Modified from Frank M. Carrano's
 * Data Structures and Abstractions with Java (3rd Edition)
 */
 import java.io.FileNotFoundException;
 import java.util.Arrays;
 import java.util.Scanner;
 import java.io.File;
 import java.util.StringTokenizer;

public class AList<T extends Comparable>{

    private T[] list;
    private int numberOfEntries;

    public AList(){
        list = (T[])new Comparable[25];
        numberOfEntries = 0;
    }

    public AList(int initialCapacity){
        numberOfEntries = 0;
        T[] tempList = (T[]) new Comparable[initialCapacity];
        list = tempList;
    }

    public void add(T newEntry){
        ensureCapacity();
        list[numberOfEntries] = newEntry;
        numberOfEntries++;
    }

    public void add(T[] addList) {
      if(addList.length == 0)
        return;
      for(int i = 0; i < addList.length; i++) {
        add(addList[i]);
      }
    }

    public boolean add(int newPosition, T newEntry) {
      if(newPosition > numberOfEntries || newPosition < 0)
        return false;
      ensureCapacity();
      for(int i = numberOfEntries; i > newPosition; i--) {
        list[i] = list[i - 1];
      }
      list[newPosition] = newEntry;
      numberOfEntries++;
      return true;
    }

    public T remove(int givenPosition) {
      if(givenPosition >= numberOfEntries || givenPosition < 0)
        return null;
      T toReturn = list[givenPosition];
      for(int i = givenPosition; i < numberOfEntries - 1; i++) {
        list[i] = list[i + 1];
      }
      list[numberOfEntries - 1] = null;
      numberOfEntries--;
      return toReturn;
    }

    public T get(int item){
        if(item >= numberOfEntries || item < 0)
          return null;
        return list[item];
    }

    public int getLength(){
        return numberOfEntries;
    }

    public boolean isEmpty(){
        return numberOfEntries == 0;
    }

    public T[] toArray(){
        T[] result = (T[])new Comparable[numberOfEntries];
        for(int index = 0; index < numberOfEntries; index++){
          result[index] = list[index];
        }

        return result;
    }

    private void ensureCapacity(){
        if (numberOfEntries == list.length){
            list = Arrays.copyOf(list, 2 * list.length);
        }
    }

    public boolean contains(T element) {
      for(int i = 0; i < numberOfEntries; i++) {
        if(list[i].equals(element)) {
          return true;
        }
      }
      return false;
    }

    public String toString() {
      if(isEmpty())
        return "[]";
      String toReturn = "[";
      for(int i = 0; i < numberOfEntries - 1; i++) {
        toReturn += list[i].toString() + ", ";
      }
      toReturn += list[numberOfEntries - 1].toString() + "]";

      return toReturn;
    }


    public static AList<String> fileToAList(File input)  {
      Scanner s;
      try {
        s = new Scanner(input);
      } catch(Exception e) {
        System.out.println("There was an error opening the file");
        return null;
      }
      //Your code here
        int count = 0;

        while (s.hasNextLine()){
            String Line = s.nextLine();
            count += 1;
        }

        int[] num = new int[count];
        String[] str = new String[count];

        try {
            s = new Scanner(input);
        } catch(Exception e) {
            System.out.println("There was an error opening the file");
            return null;
        }

        int k = 0;
        while(s.hasNextLine()){
            String line = s.nextLine();
            String[] l = line.split(",");
            num[k] = Integer.valueOf(l[0]);
            str[k] = l[1];
            k++;

        }


        AList<String> temp = new AList<>(count);

        for (int i = 0; i < count; i++){
            for (int j = i+1; j < count; j++){
                if(num[i] < num[j]){
                    String tempElement = str[j];
                    str[j] = str[i];
                    str[i] = tempElement;
                }
            }
        }

        for(int idx = 0; idx < count; idx++) {
            System.out.print(str[idx]);
        }

        for(int idx = 0; idx < count; idx++) {
            System.out.print(num[idx]);
        }


        for (int idx = 0; idx < count; idx++){
            temp.add(str[idx]);
        }


      return temp;

    }

    public AList<T> slice(int start, int stop){
        AList<T> temp = new AList<T> (stop - start);
        if (stop - start >= 1){
            for ( int i = 0; i < stop - start; i++){
                temp.add(list[start + i]);
            }
            return temp;
        }
        return null;
    }

    public AList<T> slice(int start, int stop, int step){
        AList<T> temp = new AList<T> ((stop - start)/step);

        if((stop - start)/step == 1){
            temp.add(list[start]);
            return temp;
        }else if((stop - start)/step > 1){
            for ( int i = 0; i < stop - start; i += step){
                temp.add(list[start + i]);
            }
            return temp;
        }
        return null;
    }

    public void sort(boolean ascending){

        if( ascending == true){
            for (int i = 0; i < list.length; i++){
                for (int j = i + 1; j < list.length; j++) {
                    if (list[i].compareTo(list[j]) > 0) {
                        T temp = list[j];
                        list[j] = list[i];
                        list[i] = temp;
                    }
                }
            }

        }else{
            for (int i = 0; i < list.length; i++){
                for (int j = i + 1; j < list.length; j++) {
                    if (list[i].compareTo(list[j]) < 0) {
                        T temp = list[j];
                        list[j] = list[i];
                        list[i] = temp;
                    }
                }
            }
        }
    }



    public static void main(String[] args){
        System.out.println(fileToAList(new File("input.txt")));

    }

}
