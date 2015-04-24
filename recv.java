import java.io.*;
import java.lang.String.*;

public class recv {

  public static void main(String[] args) throws Exception {

  try {
      String server  = "202.39.54.130"; //SMS Gateway IP
      int port	     = 8000;            //SMS Gateway Port

      if(args.length<2){
         System.out.println("Use: java recv id passwd");
         System.out.println(" Ex: java recv test test123");
         return;
      }
      String user    = args[0]; //帳號
      String passwd  = args[1]; //密碼

      //----建立連線 and 檢查帳號密碼是否錯誤
      sms2 mysms = new sms2();
      int ret_code = mysms.create_conn(server,port,user,passwd) ;
      if( ret_code == 0 ) {
           System.out.println("帳號密碼Login OK!");
      } else {
      	   System.out.println("帳號密碼Login Fail!");
           System.out.println("ret_code="+ret_code + ",ret_content=" + mysms.get_message());
           //結束連線
           mysms.close_conn();
           return ;
      }

      //傳送文字簡訊
      //如需同時接收多筆簡訊，請多次呼叫recv_text_message()即可。
      ret_code=mysms.recv_text_message();
      if( ret_code == 0 ) {
           System.out.println("系統有手機回傳的資料，資料如下:");
           System.out.println("message="+mysms.get_message()); //取得MessageID
      }else if(ret_code == 1){
           System.out.println("系統無手機回傳的資料!");
      }else {
           System.out.println("接收簡訊失敗!");
           System.out.print("ret_code="+ret_code+",");
           System.out.println("ret_content="+mysms.get_message());//取得錯誤的訊息
      }

      //結束連線
      mysms.close_conn();

  }catch (Exception e)  {

      System.out.println("I/O Exception : " + e);
   }
 }

}//end of class
