class A {
  protected static int num1 = 3;
  protected int num2;
  public A() {}
 public A(int v) { num1 = v; num2 = v; }
 public int get(){return num2;}
}
class B extends A {
  protected int num2 = 9;
  public B() { super(); }
  public B(int v1, int v2) { super(v1 + v2); }
  public int getNum1() { return num1; }
 public int getNum2() { return num2; }
}
public class test {
  public static void main(String[] args) {
  String s1=new String("hello");
  String s2="hello";
  System.out.println(s1==s2);
} }
