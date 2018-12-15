public class StringElement implements Comparable<StringElement> {
    private String s;
    private int num;


    @Override
    public int compareTo(StringElement o) {
        return 0;
    }

    public StringElement(String s, int num) {
        this.s = s;
        this.num = num;
    }

    public String toString(){
        return this.s;
    }

}
