import java.io.*;
import java.lang.String.*;

public class send_order {

  public static void main(String[] args) throws Exception {

  try {
      String server  = "202.39.54.130"; //SMS Gateway IP
      int port	     = 8000;            //SMS Gateway Port

      if(args.length<4){
         System.out.println("Use: java send_order id passwd tel order_time message");
         System.out.println(" Ex: java send_order test test123 0910123xxx 061102153000 HiNet預約簡訊!");
         return;
      }
      String user    = args[0]; //帳號
      String passwd  = args[1]; //密碼
      String tel     = args[2]; //手機號碼
      String order_time = args[3]; //預約時間
      String message = new String(args[4].getBytes(),"big5"); //簡訊內容

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

      //傳送預約文字簡訊
      //如需同時傳送多筆簡訊，請多次呼叫send_message()即可。
      ret_code=mysms.send_text_message(tel,message,order_time);
      if( ret_code == 0 ) {
           System.out.println("簡訊已送到簡訊中心!");
           System.out.println("MessageID="+mysms.get_message()); //取得MessageID
      } else {
           System.out.println("簡訊傳送發生錯誤!");
           System.out.print("ret_code="+ret_code+",");
           System.out.println("ret_content="+mysms.get_message());//取得錯誤的訊息
           //結束連線
           mysms.close_conn();
           return ;
      }

      //結束連線
      mysms.close_conn();

  }catch (Exception e)  {

      System.out.println("I/O Exception : " + e);
   }
 }

}//end of class
