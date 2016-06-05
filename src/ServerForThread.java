import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerForThread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// 1.创建一个服务端Socket，即ServerSocket，指定绑定的端口， 并监听此端口
			ServerSocket serverSocket = new ServerSocket(8888);
			Socket socket = null;
			//记录客户端的数量
			int count = 0;
			System.out.println("***服务器即将启动，等待客户端的连接***");
			// 循环监听等待客户端的连接
			while (true) {
				// 2.调用accept（） 方法开始监听， 等待客户端的连接
				socket = serverSocket.accept();// 一旦调用方法，就会处于阻塞的状态，等待客户端的监听
				// 一旦某一个客户端连接以后，服务器会创建一个与之对应的socket，需要启动一个线程，通过当前线程来与客户端通信
				// 创建一个新的线程
				ServerThread serverThread = new ServerThread(socket);
				// 启动线程，执行与客户端的通信
				serverThread.start();
				count++;//统计客户端的数量
				System.out.println("客户端的数量：" + count);
				InetAddress address = socket.getInetAddress();
				System.out.println("当前客户端的IP：" + address.getHostAddress());
			}
			// 如果客户端发送连接请求，这个时候会接受客户端的请求，并且我们看到accept（）方法会返回一个Socket实例，用来与客户端进行通信

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
