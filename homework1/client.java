import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Base64;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class client {
  public static void main(String[] args) throws IOException {
    try{
      // Created SSLSocketFactory and connected to GOOGLE GMAIL result (smtp.gmail.com) with SSLSocket. 
      SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
      SSLSocket socket = (SSLSocket) factory.createSocket("smtp.gmail.com", 465);
      
      // Creating necessary input and output streams for Read and Write operations
      OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
      BufferedWriter writer = new BufferedWriter(outputStreamWriter);

      InputStreamReader rStreamReader = new InputStreamReader(socket.getInputStream());
      BufferedReader reader = new BufferedReader(rStreamReader);

      String email    = "your-gmail-address";  
      String password = "yourapplication-password-generated-from-myaccounts.google.com";

      // mail address and application password, convert to base64 
      String base64Email = Base64.getEncoder().encodeToString(email.getBytes());
      String base64Passw = Base64.getEncoder().encodeToString(password.getBytes());
     
      String from   = email;                                                  
      String rctpTo = "destination-address-not-encoded";                                       
      String subject = "Topic title";                                             
      String massej = "Email contentEmail Email content...";
      
      
      // Attention! Do not edit after this line.
      System.out.println("receives:\n***");
      System.out.println(reader.readLine());  //read to first result 
      writer.write("HELO localhost\r\n"); // write commend
      writer.flush();                         // flushing
      System.out.println(reader.readLine());  // read the result

      writer.write("AUTH LOGIN\r\n");
      writer.flush();
      System.out.println(reader.readLine());

      writer.write( base64Email+"\r\n");
      writer.flush();
      System.out.println(reader.readLine());

      writer.write(base64Passw +"\r\n");
      writer.flush();
      System.out.println(reader.readLine());

      writer.write("MAIL FROM: <"+from+">\r\n");
      writer.flush();
      System.out.println(reader.readLine());

      writer.write("rcpt to: <"+rctpTo+">\r\n");
      writer.flush();
      System.out.println(reader.readLine());

      writer.write("DATA\r\n");
      writer.flush();
      System.out.println(reader.readLine());

      writer.write("Subject:"+subject+"\r\n");
      writer.write(massej+"\r\n");

      writer.write(".\r\n");
      writer.write(".\r\n");
      writer.flush();
      System.out.println(reader.readLine());

      writer.write("QUIT\r\n");
      writer.flush();
      System.err.println(reader.readLine());

      socket.close(); // socket close.
    }catch(IOException e){
      e.printStackTrace();
    }
  }
}
