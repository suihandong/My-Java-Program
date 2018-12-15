import java.util.HashMap;

public class StudentDatabase {

    //private int studentID;
    //private Student student;
    private HashMap<Integer, Student> database = new HashMap<>();

    public boolean addStudent(int studentID, Student student){
        if(!database.containsKey(studentID)){
            database.put(studentID, student);
            return true;
        }
        return false;
    }

    public String getStudentName(int studentID){
        if(!database.containsKey(studentID)){
            return null;
        }
        else{
            Student name = database.get(studentID);
            return name.getFirstName() + name.getLastName();
        }
    }

    public boolean removeStudent(int studentID){
        if(!database.containsKey(studentID)){
            return false;
        }
        else{
            database.remove(studentID);
            return true;
        }
    }

    public String toString(){
        String res = "";
        for (Integer key : database.keySet()){
            res += key + " : " + database.get(key).toString() + "\n";
        }
        return res;
    }

    public HashMap<Character, Integer> getLetterFrequency(int studentID){
        HashMap<Character, Integer> res = new HashMap<>();
            Student newStudent = database.get(studentID);
            String name = newStudent.getFirstName() + newStudent.getLastName();
            for (int i = 0; i < name.length(); i++){
                if(res.containsKey(name.charAt(i))){
                    res.put(name.charAt(i), res.get(name.charAt(i)) + 1);
                }else{
                    res.put(name.charAt(i) , 1);
                }
            }
        return res;
    }

    public static void main(String[] args){
        StudentDatabase dic = new StudentDatabase();
        dic.addStudent(1001, new Student("Andrew", "Gilbert"));
        dic.addStudent(1002, new Student("Owen", "Houghton"));
        dic.addStudent(1003, new Student("Nate", "Larson"));
        dic.addStudent(1004, new Student("Jared", "Otterstatter"));
        System.out.println(dic);
        System.out.println(dic.getLetterFrequency(1001));
        dic.addStudent(1005, new Student("Ovvv","Abbe"));
        System.out.println(dic.getLetterFrequency(1005));
    }


}
