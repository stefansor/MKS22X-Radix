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
    System.out.println("Size\t\tMax Value\tquick/builtin ratio ");
  int[]MAX_LIST = {1000000000,500,10};
  for(int MAX : MAX_LIST){
    for(int size = 31250; size < 2000001; size*=2){
      long qtime=0;
      long btime=0;
      //average of 5 sorts.
      for(int trial = 0 ; trial <=5; trial++){
        int []data1 = new int[size];
        int []data2 = new int[size];
        for(int i = 0; i < data1.length; i++){
          data1[i] = (int)(Math.random()*MAX);
          data2[i] = data1[i];
        }
        long t1,t2;
        t1 = System.currentTimeMillis();
        Radix.radixsort(data2);
        t2 = System.currentTimeMillis();
        qtime += t2 - t1;
        t1 = System.currentTimeMillis();
        Arrays.sort(data1);
        t2 = System.currentTimeMillis();
        btime+= t2 - t1;
        if(!Arrays.equals(data1,data2)){
          System.out.println("FAIL TO SORT!");
          System.exit(0);
        }
      }
      System.out.println(size +"\t\t"+MAX+"\t"+1.0*qtime/btime);
    }
    System.out.println();
  }

  }


}
