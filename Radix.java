import java.util.*;

public class Radix{


  public static int max(int[] nums){
    int largest = nums[0];
    for(int i = 0 ; i < nums.length; i++){
      if(nums[i] > largest){
        largest = nums[i];
      }
    }
    return largest;
  }

  public static int numOfDigits(int num){
    int digits = 0;
    while(num > 10){
      num = num / 10;
      digits++;
    }
    digits++;
    return digits;
  }


  public static int getDigit(int num, int place){
    return (num / (int)Math.pow(10, place)) % 10;
  }


  public static void radixsort(int[]data){
    MyLinkedList[] buckets = new MyLinkedList[20];
    int highest = max(data);
    int times = numOfDigits(highest);
    for(int i = 0; i < times; i++){
      ////code that distributes the numbers according to digits
    }
  }





  ///Plan -- inspired by Mohammad Jawwad - will work later tonight
  // 1. write a method that sorts the digits in each bucket for each set
  //  of digits
  // 2. write method that links the filled buckets
  // 3. write method that copies linked list into data

  public static void main(String[] args){
    int[] stuff = new int[]{
      3,2,4,5,4,3,2,23432,334,2344
    };
    System.out.println(max(stuff));
    System.out.println(numOfDigits(32432));
    System.out.println(getDigit(32432, 0));
    System.out.println(getDigit(32432, 1));
    System.out.println(getDigit(32432, 2));
    System.out.println(getDigit(32432, 3));
    System.out.println(getDigit(32432, 4));
    System.out.println(getDigit(32432, 5));



  }


}
