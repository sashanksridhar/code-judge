import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;
 public class process {

public static void main(String args[]) throws IOException{
System.out.println("file name");
Scanner s = new Scanner(System.in);
String f = s.next();
if(compile(f) == 0)
{
exec(f);
}
}
static int compile(String f) throws IOException
{
String[] x = f.split("\\.");

String[] command = new String[2];
if(x[1].equals("java")) 
 {
	
command[0] = "javac";
command[1] = f;
}
if(x[1].equals("cpp"))
{
command = new String[4];
command[0] = "g++";
command[1] = f;
command[2] = "-o";
command[3] = x[0];
} 
if(x[1].equals("c"))
{
command = new String[4];
command[0] = "gcc";
command[1] = f;
command[2] = "-o";
command[3] = x[0];
}       
if(x[1].equals("py"))
{
exec(f);
return 1;
}
ProcessBuilder processBuilder = new ProcessBuilder(command);

    Process process = processBuilder.start();
    

    if( process.getErrorStream().read() != -1 ){
        print("Compilation Errors",process.getErrorStream());
    }
    
if( process.exitValue() == 0 ){
return 0;
}
return 1;
}
static void exec(String f) throws IOException
{
  String[] x = f.split("\\."); 
List list = new ArrayList();    
FileInputStream textFile = new FileInputStream ("data.txt");
Scanner inFile = new Scanner (textFile); 
while(inFile.hasNextLine())
{
String s = inFile.nextLine(); 
s = s+"\n";
Process process = null;
if(x[1].equals("java"))
 process = new ProcessBuilder(new String[]{"java",x[0]}).start();
if(x[1].equals("cpp"))
 process = new ProcessBuilder(new String[]{"./"+x[0]}).start();
if(x[1].equals("c"))
 process = new ProcessBuilder(new String[]{"./"+x[0]}).start();
if(x[1].equals("py"))
 process = new ProcessBuilder(new String[]{"python",f}).start();
       
     
OutputStream os = process.getOutputStream();
PrintWriter writer=new PrintWriter(os);
writer.write(s);
writer.flush();
	if( process.getErrorStream().read() != -1 ){
            print("Errors ",process.getErrorStream());
        }
        else{
         
InputStream input = process.getInputStream();
BufferedReader in = new BufferedReader(new InputStreamReader(input));
String line = null;
    while((line = in.readLine()) != null ){
  list.add(line);
     

    }
 in.close();        
}
}
 textFile = new FileInputStream ("op.txt");
 inFile = new Scanner (textFile);
int i = 0;
while(inFile.hasNextLine())
{
String e = inFile.nextLine(); 

if(list.get(i).equals(e))
{
System.out.println("\nSuccess");
}
else
{
System.out.println("\nfailure");
}
list.remove(i);

} 
    }

private static void print(String status,InputStream input) throws IOException{
    BufferedReader in = new BufferedReader(new InputStreamReader(input));
    System.out.println("************* "+status+"***********************");
    String line = null;
    while((line = in.readLine()) != null ){
    
System.out.println(line);
    }
    in.close();
}
}