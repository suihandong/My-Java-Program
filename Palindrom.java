public class Palindrom {
    public boolean isPalindrome(String a){
        int n = a.length();
        if (n % 2 == 0){
            int m = n / 2;
            for ( int i = 0; i < m; i++){
                if (a.charAt(i) == a.charAt(n - i)){
                    return true;
                }
            }
        }
        return false;
    }

}
