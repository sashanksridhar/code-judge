import java.util.Scanner;
public class hello
{
public static void main(String args[])
{
Scanner sc = new Scanner(System.in);
String n = sc.nextLine();
String [] x = n.split(" ");
int c = Integer.parseInt(x[0]) + Integer.parseInt(x[1]);
System.out.println(c);
}
}