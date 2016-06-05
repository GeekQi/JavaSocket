import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 服务器线程处理类 注：每一个客户端来了以后， 服务端都会创建一个socket与之通信
 * 
 * @author GeekQi
 *
 */
public class ServerThread extends Thread {
	// 和本线程相关的Socket
	Socket socket = null;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	// 执行线程操作，响应客户端的请求
	public void run() {
		// 3.依赖于客户端建立Socket通信以后，下面我们需要在客户端和服务器端实现数据的交互，获取客户端提交的登录信息，如何获取？通过输入输出来实现
		InputStream is = null;
		// 为了提高读取的性能，可以将字节输入流进行包装，变为字符流
		InputStreamReader isr = null;
		// 我们可以为字符流添加缓冲，以缓冲的方式去进行读取
		BufferedReader br = null;
		OutputStream os = null;
		PrintWriter pw = null;
		try {
			// 3.获取输入流，并读取客户端信息
			is = socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			// 有了输入流以后，下面要进行数据的读取，获取客户端提交的登录信息
			String info = null;
			while ((info = br.readLine()) != null) {// 循环读取客户端的信息
				System.out.println("我是//有响应//服务器， 客户端说：" + info);
			}
			// 读取完了之后需要把输入流进行关闭
			socket.shutdownInput();
			// 3.5获取输出流，用来响应客户端的请求//响应的步骤和客户端发送的步骤类似
			os = socket.getOutputStream();
			pw = new PrintWriter(os);
			pw.write("//有响应//欢迎您");
			pw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {// 这样就必需执行
			// 4.关闭相关的资源
			// 同时，进行判断
			try {
				if (pw != null)
					pw.close();
				if (os != null)
					os.close();
				if (br != null)
					br.close();
				if (isr != null)
					isr.close();
				if (is != null)
					is.close();
				if (socket != null)
					socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
