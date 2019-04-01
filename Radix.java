import.java.util.*;

public class Radix{


  //helper methods such as getDigit() and others

  public static int getDigit(int num, int place){
    int divider = Math.pow(10, place);
    return (num / divider) % 10;
  }






  public static void radixsort(int[]data){

  }


  public static void main(String[] args){
      System.out.println(getDigit(34, 2));
  }


}
