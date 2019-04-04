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

    for(int i = 0; i <= times; i++){
      //function that sorts by the chosen digits
      sort(data, i, buckets);
      //function that links the buckets
      link(buckets);
      //funciton that copies to data
      linkCopy(data, buckets[0]);
    }
  }





  ///Plan
  // 1. write a method that sorts the digits in each bucket for each set
  //  of digits check
  // 2. write method that links the filled buckets check
  // 3. write method that copies linked list into data
  // 4. make them all return void when testing is done

  private static void sort(int[] data, int place, MyLinkedList[] buckets){
    for(int i = 0; i < data.length; i++){
      if(data[i] < 0){
        buckets[9 - Math.abs(getDigit(data[i], place))].add(data[i]);
      }
      else{
        buckets[10 + Math.abs(getDigit(data[i], place))].add(data[i]);
      }
    }
  }

  private static void link(MyLinkedList[] buckets){
    for(int i = 1; i < 20; i++){
      buckets[0].extend(buckets[i]);
    }
  }

  private static void linkCopy(int[] data, MyLinkedList fullbucket){
    int i = 0;
    while(fullbucket.size() > 0){
      data[i] = fullbucket.remove();
      i++;
    }
  }


  public static void main(String[] args){
    int[] yea = new int[]{
      3,2,4,5,4,3,2,23432,334,2344
    };
    MyLinkedList[] stuff = new MyLinkedList[20];
    for(int i = 0; i < 20; i++){
      stuff[i] = new MyLinkedList();
    }
    sort(yea, 0, stuff);
    for(int i = 0; i < 20; i++){
      System.out.println(stuff[i]);
    }
    System.out.println("///////////////////");
    link(stuff);
    for(int i = 0; i < 20; i++){
      System.out.println(stuff[i]);
    }
    System.out.println("////////////////////////////");
    linkCopy(yea, stuff[0]);
    for(int i = 0; i < 20; i++){
      System.out.println(stuff[i]);
    }

    System.out.println(Arrays.toString(yea));
    System.out.println();
    System.out.println("Radix Testing");
    System.out.println(Arrays.toString(yea));
    radixsort(yea);
    System.out.println("Should be sorted");
    System.out.println(Arrays.toString(yea));


  }


}
