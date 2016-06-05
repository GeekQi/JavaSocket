import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 基于TCP协议的Socket通信，实现用户登录 服务器端
 * 
 * @author GeekQi 注：要早于客户端启动
 */
public class ServerWithResponse {
	public static void main(String[] args) {
		try {
			// 1.创建一个服务端Socket，即ServerSocket，指定绑定的端口， 并监听此端口
			ServerSocket serverSocket = new ServerSocket(8888);
			// 2.调用accept（） 方法开始监听， 等待客户端的连接
			System.out.println("***//有响应//服务器即将启动，等待客户端的连接***");
			Socket socket = serverSocket.accept();// 一旦调用方法，就会处于阻塞的状态，等待客户端的监听
			// 如果客户端发送连接请求，这个时候会接受客户端的请求，并且我们看到accept（）方法会返回一个Socket实例，用来与客户端进行通信
			// 3.依赖于客户端建立Socket通信以后，下面我们需要在客户端和服务器端实现数据的交互，获取客户端提交的登录信息，如何获取？通过输入输出来实现
			// 3.获取输入流，并读取客户端信息
			InputStream is = socket.getInputStream();// 字节输入流
			// 为了提高读取的性能，可以将字节输入流进行包装，变为字符流
			InputStreamReader isr = new InputStreamReader(is);// 将字节流转化为字符流
			// 我们可以为字符流添加缓冲，以缓冲的方式去进行读取
			BufferedReader br = new BufferedReader(isr);// 为输入流添加缓冲
			// 有了输入流以后，下面要进行数据的读取，获取客户端提交的登录信息
			String info = null;
			while ((info = br.readLine()) != null) {// 循环读取客户端的信息
				System.out.println("我是//有响应//服务器， 客户端说：" + info);
			}
			// 读取完了之后需要把输入流进行关闭
			socket.shutdownInput();
			// 3.5获取输出流，用来响应客户端的请求//响应的步骤和客户端发送的步骤类似
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);// 包装为打印流
			pw.write("//有响应//欢迎您");
			pw.flush();
			// 4.关闭相关的资源
			pw.close();
			os.close();
			// 多加的
			br.close();
			isr.close();
			is.close();
			socket.close();
			serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
