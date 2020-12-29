package pers.dyx.socket;

import java.io.*;
import java.net.Socket;

/**
 * @author: dyx
 * @date: 2018/10/31 17:27
 * @description:
 */
public class EchoClient {

    private String host = "localhost";
    private int port = 8000;
    private Socket socket;

    public EchoClient() throws IOException{
        socket = new Socket(host,port);
    }

    private PrintWriter getWriter(Socket socket) throws IOException{
        OutputStream outputStream = socket.getOutputStream();
        return new PrintWriter(outputStream,true);
    }

    private BufferedReader getReader(Socket socket) throws IOException{
        InputStream inputStream = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(inputStream));
    }
    public void talk(){
        try {
            BufferedReader bufferedReader = getReader(socket);
            PrintWriter printWriter = getWriter(socket);
            BufferedReader localReader = new BufferedReader(new InputStreamReader(System.in));
            String msg = null;
            while ((msg=localReader.readLine())!=null){
                printWriter.println(msg);
                if("bye".equals(msg)){
                    break;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) throws IOException{
        new EchoClient().talk();
    }
}
