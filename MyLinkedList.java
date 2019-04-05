import java.util.*;

class Node{
 private Integer data;
 private Node next,prev;
 public Node(Integer num){
   data = num;
   next = null;//basic constructor
   prev = null;
 }
 public Node(Integer num, Node first, Node second){
   data = num;
   next = first;//more versatile constructor specifies next and prev
   prev = second;
 }
 public Node next(){
   return this.next;
 }
 public Node setNext(Node newnd){
   if(newnd == null){
     this.next = null;
     return null;
   }
   if(this.next == null){
     this.next = newnd;//changes next node in node
     newnd.prev = this;
     return newnd;
   }
   Node olnum = this.next;
   olnum.prev = null;
   this.next = newnd;
   newnd.prev = this;
   return olnum;
 }
 public Node prev(){
   return this.prev;
 }
 public Node setPrev(Node newnum){
   if(newnum == null){
     this.prev = null;
     return this;
   }
   if(this.prev == null){
     this.prev = newnum;//changes prev node in node
     newnum.next = this;
     return newnum;
   }
   Node olnum = this.prev;
   this.prev.next = null;
   this.prev = newnum;
   this.prev.next = this;
   return olnum;
 }

 public Integer getData(){
   return this.data;
 }

 public Integer setData(Integer i){
   int ol = this.data;
   this.data = i;//changes value of the node
   return ol;
 }
 public String toString(){
   String str = "" + this.getData();// + "    next: " + this.next().getData() + "    prev: " + this.prev().getData();
   return str;//debugging for setnext and setprev
 }

}
///////////////////////////////////////////////////////////////////////////////////////////////

public class MyLinkedList{
 private int length;
 private Node start,end;

 public MyLinkedList(){
   length = 0;
   start = null;//basic constructor as told by Mr. K
   end = null;
 }

 public void clear() {
    length = 0;
    start = null;
    end = null;
  }

 public boolean add(Integer value){
   if(end == null && start == null){
     this.end = new Node(value, null, null);//creates node
     this.start = this.end;//changes end reference in linkedlist
     length = 1;//changes size
     return true;
   }
   else{
     Node newnod = new Node(value, null, this.end);
     this.end.setNext(newnod);
     this.end.setNext(newnod);
     this.end = newnod;
     length++;//increases size
     return true;
   }
 }

 public int size(){
   return this.length;
 }
 public String toString(){
  String str = "[";
  if(this.length == 0){
    return "[]";
  }
  Node current = this.start;
  for(int i = 0; i < this.size(); i++){
    if(i == this.size() - 1){
      str = str + current.getData() + "]";
    }
    else{
     str = str + current.getData() + ", ";
     current = current.next();
   }
  }
  return str;
 }

 private Node getnthNode(int index){
   int cur = 0;//index of current
   Node current = this.start;//current node
   while(cur != index && cur < this.size()){
     current = current.next();
     cur++;
   }
   return current;
 }

 public Integer get(int index){
   if(index < 0 || index >= this.size()){
     throw new IndexOutOfBoundsException("Index is out of bounds");
   }
   return this.getnthNode(index).getData();
 }

 public Integer set(int index, Integer value){
   if(index < 0 || index >= this.size()){
     throw new IndexOutOfBoundsException("Index is out of bounds");
   }
  Integer ol = this.getnthNode(index).getData();//saves old value of node
  this.getnthNode(index).setData(value);//changes value of node
  return ol;//returns old value
}

public boolean contains(Integer value){
  boolean in = false;
  int cur = 0;//keeps track of index
  Node current = this.start;
  while(cur < this.size()){
    if(current.getData() == value){
      in = true;//if the value is found in becomes true
    }
    current = current.next();
    cur++;//index and next progress to the next node
  }
  return in;
}

public int indexOf(Integer value){
  int cur = 0;//keeps track of index
  Node current = this.start;//current node
  while(cur < this.size()){
    if(current.getData() == value){
      return cur;//if a node is founs with value then return index
    }
    current = current.next();
    cur++;//otherwise progress down the linked list
  }
  return -1;//while loop is done with no return - return -1 to show value isnt in
}

public void add(int index, Integer value){
  if(index < 0 || index >= this.size()){
    throw new IndexOutOfBoundsException("Index is out of bounds");
  }
  Node addnod = new Node(value);//new node to add with value
  if(index == 0){//start case is unique to any other case
    this.start.setPrev(addnod);
    addnod.setNext(this.start);
    this.start = addnod;
    this.length++;
  }
  if(index > 0 && index < this.size() - 1){
    Node curprev = this.getnthNode(index - 1);
    Node curnext = this.getnthNode(index);
    curprev.setNext(addnod);
    curnext.setPrev(addnod);
    addnod.setPrev(curprev);
    addnod.setNext(curnext);
    this.length++;
  }
  if(index == this.size() - 1){
    this.add(value);
  }
}

public Integer remove(){
  Integer ol = this.start.getData();
  if(size() == 1){
    clear();
    return ol;
  }
  this.start = this.start.next();
  this.start.setPrev(null);
  this.length--;
  return ol;
}


public Integer remove(int index){
  if(index < 0 || index >= this.size()){
    throw new IndexOutOfBoundsException("Index is out of bounds");
  }
  Node removee = this.getnthNode(index);
  if(index == 0){
    Integer ol = this.start.getData();
    this.start = this.start.next();
    this.start.setPrev(null);
    this.length--;
    return ol;
  }
  else if(index == this.size() - 1){
    Integer ol = this.end.getData();
    this.end = this.end.prev();
    this.end.setNext(null);
    this.length--;
    return ol;
  }
  else{
    Integer ol = this.getnthNode(index).getData();
    Node preremovee = this.getnthNode(index - 1);
    Node postremovee = this.getnthNode(index + 1);
    preremovee.setNext(postremovee);
    postremovee.setPrev(preremovee);
    removee.setNext(null);
    removee.setPrev(null);
    this.length--;
    return ol;
  }
}

public boolean remove(Integer value){
  if(this.contains(value)){
    int removal = this.indexOf(value);
    this.remove(removal);
    return true;
  }
  return false;
}


public void extend(MyLinkedList other){
  if(other.start == null){
  }
  if(this.start != null && other.start != null){
    this.end.setNext(other.start);
    other.start.setPrev(this.end);
    this.length = this.length + other.length;
    this.end = other.end;
    other.end = null;
    other.start = null;
    other.length = 0;
  }
  if(this.start == null){
    this.start = other.start;
    this.end = other.end;
    this.length = other.length;
    other.end = null;
    other.start = null;
    other.length = 0;
  }
}




  public static void main(String[] args) {

    System.out.println("---------TESTING MYLINKEDLIST ---------");
    System.out.println();

    System.out.println("---- Testing toString() / add(value) / size() ----");
    System.out.println();
    MyLinkedList list = new MyLinkedList();
    System.out.println("Empty List should print []: " + list);
    System.out.println("List size should be 0: " + list.size());
    System.out.println("Adding the value 0 should print true: " + list.add(0));
    System.out.println("List should now be [0]: " + list);
    System.out.println("List size should now be 1: " + list.size());
    for (int x = 1; x < 10; x++){
      list.add(x);
    }
    System.out.println("Adding values up to 10, list should be [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]: " + list);
    System.out.println("List size should now be 10: " + list.size());
    for (int x = 0; x < 1000; x++) {
      list.add(x);
    }
    System.out.println("Adding 1000 values to list, size should be 1010: " + list.size());
    System.out.println();
    System.out.println();
    System.out.println("---- Testing get(index) ----");
    System.out.println();
    list = new MyLinkedList();
    for (int x = 0; x < 10; x++){
      list.add(x);
    }
    System.out.println("Current list: " + list);
    System.out.println("Get first index should print 1: " + list.get(1));
    System.out.print("Get -1th index, should print an error: ");
    try {
      list.get(-1);
    }
    catch (IndexOutOfBoundsException e) {
      System.out.println(e);
    }
    System.out.println();
    System.out.println();

    System.out.println("---- Testing set(index, value) ----");
    System.out.println();
    System.out.println("Current list: " + list);
    System.out.println("Set 3rd index to 10 should print 3: " + list.set(3, 10));
    System.out.println("Current list: " + list);
    System.out.print("Set -1th index, should print an error: ");
    try {
      list.set(-1, 0);
    }
    catch (IndexOutOfBoundsException e) {
      System.out.println(e);
    }
    for (int x = 0; x < 10; x++) {
      list.set(x, x+1);
    }
    System.out.println("Setting every index of list with a loop, should be a list of 1-10 inclusive: " + list);
    System.out.println();
    System.out.println();

    System.out.println("---- Testing contains and indexOf ----");
    System.out.println();
    System.out.println("Current list: " + list);
    System.out.println("list.contains(6) should print true: " + list.contains(6));
    System.out.println("list.contains(70) should print false: " + list.contains(70));
    System.out.println("list.indexOf(4) should print 3: " + list.indexOf(4));
    System.out.println("list.indexOf(50) should print -1: " + list.indexOf(50));
    System.out.println("list.indexOf(1) should print 0: " + list.indexOf(1));
    System.out.println("list.indexOf(10) should print 9: " + list.indexOf(10));
    System.out.println();
    System.out.println();

    System.out.println("---- Testing add(index, value) ----");
    System.out.println();
    System.out.println("Current list: " + list);
    System.out.print("Add to -1th index, should print an error: ");
    try {
      list.add(-1, 0);
    }
    catch (IndexOutOfBoundsException e) {
      System.out.println(e);
    }
    list.add(0, 0);
    System.out.println("Add 0 to the 0th index: " + list);
    list.add(10, 11);
    System.out.println("Add 11 to the 10th index: " + list);
    System.out.println();
    System.out.println();

    System.out.println("---- Testing remove methods ----");
    System.out.println();
    System.out.println("Current list: " + list);
    System.out.println("Remove 0th index should print 0: " + list.remove(0));
    System.out.println("Current list: " + list);
    System.out.print("Removing -1th index, should print an error: ");
    try {
      list.remove(-1);
    }
    catch (IndexOutOfBoundsException e) {
      System.out.println(e);
    }
    Integer num = 11;
    System.out.println("Removing by value, trying to remove 11, should print true: " + list.remove(num));
    System.out.println("Current list: " + list);
    num = 100;
    System.out.println("Trying to remove value 100, should print false: " + list.remove(num));
    System.out.println("Current list: " + list);
    System.out.println();
    System.out.println();

    System.out.println("---- Testing Extend with two LinkedLists: a and b----");
    MyLinkedList a = new MyLinkedList();
    MyLinkedList b = new MyLinkedList();
    for (int x = 0; x < 5; x++) {
      a.add(x);
      b.add(x+5);
    }
    System.out.println("linkedlist a should be [0, 1, 2, 3, 4]: " + a);
    System.out.println("linkedlist b should be [5, 6, 7, 8, 9]: " + b);
    a.extend(b);
    System.out.println("After a.extend(b), a should be [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]: " + a);
    System.out.println("After extending, b should be []: " + b);
    System.out.println("Size of a should be 10: " + a.size());
    System.out.println("Size of b should be 0: " + b.size());
    System.out.println();
    a.extend(b);
    System.out.println("Trying to extend b (now empty) to a, a should be the same: " + a);
    System.out.println("b should still be []: " + b);
    System.out.println();
    System.out.println("----Changing a and b----");
    a = new MyLinkedList();
    b.add(5);
    b.add(7);
    System.out.println("a should be []: " + a);
    System.out.println("b should be [5, 7]: " + b);
    a.extend(b);
    System.out.println("after a.extend(b), a should be [5, 7]: " + a);
    System.out.println("b should still be []: " + b);
    System.out.println("size of a should be 2: " + a.size());
    System.out.println("size of b should be 0: " + b.size());
    System.out.println("--------- END OF TEST ----------");
    System.out.println();
  }

}
