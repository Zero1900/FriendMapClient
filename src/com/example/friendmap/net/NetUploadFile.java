package com.example.friendmap.net;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.example.friendmap.ui.LoginActivity;
import com.example.friendmap.utils.FMCallBack;

import android.os.Handler;
import android.util.Log;

public class NetUploadFile extends NetBase {
	private String name ="image.jpg";
    private String uploadFile ="/sdcard/image.JPG";
	public NetUploadFile(FMCallBack fmCallBack,String name,String uploadFile) {
		super(fmCallBack);
		// TODO Auto-generated constructor stub
		setUrl(encodeUrl("Client/UploadFile"));
		this.name=name;
		this.uploadFile=uploadFile;
	}
	public void post(){
		
	}
	/* 上传文件至Server的方法 */
    private void uploadFile()
    {
      String end ="\r\n";
      String twoHyphens ="--";
      String boundary ="*****";
      try
      {
        URL url =new URL(getUrl());
        HttpURLConnection con=(HttpURLConnection)url.openConnection();
        /* 允许Input、Output，不使用Cache */
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false);
        /* 设置传送的method=POST */
        con.setRequestMethod("POST");
        /* setRequestProperty */
        con.setRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("Charset", "UTF-8");
        con.setRequestProperty("Content-Type",
                           "multipart/form-data;boundary="+boundary);
        /* 设置DataOutputStream */
        DataOutputStream ds =
          new DataOutputStream(con.getOutputStream());
        ds.writeBytes(twoHyphens + boundary + end);
        ds.writeBytes("Content-Disposition: form-data; "+
                      "name=\"file1\";filename=\""+
                      name +"\""+ end);
        ds.writeBytes(end);  
        /* 取得文件的FileInputStream */
        FileInputStream fStream =new FileInputStream(uploadFile);
        /* 设置每次写入1024bytes */
        int bufferSize =1024;
        byte[] buffer =new byte[bufferSize];
        int length =-1;
        /* 从文件读取数据至缓冲区 */
        while((length = fStream.read(buffer)) !=-1)
        {
          /* 将资料写入DataOutputStream中 */
          ds.write(buffer, 0, length);
        }
        ds.writeBytes(end);
        ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
        /* close streams */
        fStream.close();
        ds.flush();
        /* 取得Response内容 */
        InputStream is = con.getInputStream();
        int ch;
        StringBuffer b =new StringBuffer();
        while( ( ch = is.read() ) !=-1 )
        {
          b.append( (char)ch );
        }
        /* 将Response显示于Dialog */
        Log.i("上传成功",b.toString().trim());
        /* 关闭DataOutputStream */
        ds.close();
      }
      catch(Exception e)
      {
        Log.i("上传失败"+e,"2");
      }
    }
}
