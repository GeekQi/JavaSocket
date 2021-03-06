import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 客户端
 *
 * @author GeekQi
 *
 */
public class Client {
	public static void main(String[] args) {
		try {
			// 1.创建客户端Socket，指定服务器地址和端口，用来与服务器端连接
			Socket socket = new Socket("localhost", 8888);
			// 如果一旦服务器接受请求以后，客户端与服务器端就建立了这么一个连接，客户端可以向服务端发送登录信息，要想发送登录信息就需要发送一个输出流
			// 2.获取输出流，用来向服务器端发送信息
			OutputStream os = socket.getOutputStream();// 获取字节输出流//对它进行包装
			PrintWriter pw = new PrintWriter(os);// 将输出流包装为打印流
			// 有了这个输出流以后，可以向服务器端发送信息
			pw.write("用户名：tom; 密码：123");// 要进行登录，需要向服务器端发送用户名和密码的信息
			pw.flush();// 刷新缓存，向服务器端输出信息
			// 输出之后，关闭socket的输出流
			socket.shutdownOutput();
			// 3.关闭其他的相关资源
			pw.close();
			os.close();
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}