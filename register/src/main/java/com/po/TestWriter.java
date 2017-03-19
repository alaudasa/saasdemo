package com.po;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestWriter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String username = "tes";
		String password = "123456";
		String[] str = new String [25];
		str[0]="db:\n";
		str[1]="  alauda_lb: HA\n";
		str[2]="  environment:\n";
		str[3]="  - MYSQL_ROOT_PASSWORD=123456\n";
		str[4]="  - DEMOUSER="+username+"\n";
		str[5]="  - DEMOPASS="+password+"\n";
		str[6]="  image: index.alauda.cn/alaudaorg/testmysql:latest\n";
		str[7]="  net: bridge\n";
		str[8]="  number: 1\n";
		str[9]="  ports:\n";
		str[10]="  - '3306'\n";
		str[11]="  size: S\n";
		str[12]="login:\n";
		str[13]="  alauda_lb: HA\n";
		str[14]="  environment:\n";
		str[15]="  - MYSQL_HOST=$DB_PORT_3306_TCP_ADDR\n";
		str[16]="  - MYSQL_PORT=$DB_PORT_3306_TCP_PORT\n";
		str[17]="  image: index.alauda.cn/alaudaorg/testlogintemp:latest\n";
		str[18]="  links:\n";
		str[19]="  - db:DB\n";
		str[20]="  net: bridge\n";
		str[21]="  number: 1\n";
		str[22]="  ports:\n";
		str[23]="  - 8080/http\n";
		str[24]="  size: S\n";
		File file = new File("WebRoot/resource/compose.yaml");
		FileOutputStream in = null;
		try {
			in = new FileOutputStream("WebRoot/resource/compose.yaml");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        for(int i=0;i<25;i++){
        	byte bt[] = new byte[1024];  
            bt = str[i].getBytes(); 
                try {  
                    in.write(bt, 0, bt.length);  
                } catch (IOException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }  
        }
            
                    try {
						in.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
	}
}
