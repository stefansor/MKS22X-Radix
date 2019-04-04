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
    for(int i = 0; i < 20; i++){
      buckets[i] = new MyLinkedList();
    }

    int highest = max(data);
    int times = numOfDigits(highest);

    for(int i = 0; i < times; i++){
      //function that sorts by the chosen digits
      //function that links the buckets
      //funciton that copies to data


    }
  }





  ///Plan 
  // 1. write a method that sorts the digits in each bucket for each set
  //  of digits check
  // 2. write method that links the filled buckets
  // 3. write method that copies linked list into data

  private static MyLinkedList[] sort(int[] data, int place, MyLinkedList[] buckets){
    for(int i = 0; i < data.length; i++){
      if(data[i] < 0){
        buckets[9 - Math.abs(getDigit(data[i], place))].add(data[i]);
      }
      else{
        buckets[10 + Math.abs(getDigit(data[i], place))].add(data[i]);
      }
    }
    return buckets;
  }



  public static void main(String[] args){
    int[] yea = new int[]{
      3,2,4,5,4,3,2,23432,334,2344
    };
    MyLinkedList[] stuff = new MyLinkedList[20];
    for(int i = 0; i < 20; i++){
      stuff[i] = new MyLinkedList();
    }
    System.out.println(numOfDigits(32432));
    System.out.println(getDigit(32432, 0));
    System.out.println(getDigit(32432, 1));
    System.out.println(getDigit(32432, 2));
    System.out.println(getDigit(32432, 3));
    System.out.println(getDigit(32432, 4));
    System.out.println(getDigit(32432, 5));
    System.out.println("//////////////////////////");
    sort(yea, 2, stuff);
    for(int i = 0; i < 20; i++){
      System.out.println(stuff[i]);
    }


  }


}
