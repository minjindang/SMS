import java.io.*;
import java.lang.String.*;

public class query {

  public static void main(String[] args) throws Exception {

  try {
      String server  = "202.39.54.130"; //Socket to Air Gateway IP
      int port	     = 8000;            //Socket to Air Gateway Port

      if(args.length<3){
         System.out.println("Use: java query id passwd messageid");
         System.out.println(" Ex: java query test test123 A2967053126752930574");
         return;
      }
      String user    = args[0]; //帳號
      String passwd  = args[1]; //密碼
      String messageid = args[2];//訊息ID
      int    query_type = 2; //query 文字簡訊
      
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

      //查詢傳送結果
      //如需同時查詢多筆簡訊，請多次呼叫query_message()即可。
      ret_code=mysms.query_message(query_type,messageid);
      if( ret_code == 0 ) {
           System.out.println("簡訊已送到接收端手機!");
           System.out.println("MessageID="+mysms.get_message()); //取得MessageID
      } else {
           System.out.println("簡訊尚未傳送到接收端手機!");
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
