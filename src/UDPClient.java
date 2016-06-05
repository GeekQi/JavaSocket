import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * 客户端
 * 
 * @author GeekQi
 *
 */
public class UDPClient {
	public static void main(String[] args) throws IOException {
		/**
		 * 向服务器端发送数据
		 */
		// 1.定义服务器的地址、端口号、数据
		InetAddress address = InetAddress.getByName("localhost");
		int port = 8800;
		byte[] data = "用户名：admin； 密码：123".getBytes();
		// 2.根据已定义的信息，创建数据报，包含发送的数据信息等
		DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
		// 3.创建DatagramScoket对象，通过它来实现数据的发送。
		DatagramSocket socket = new DatagramSocket();
		// 4.向服务器端发送数据报
		socket.send(packet);
		/**
		 * 接受服务器端响应的信息
		 */
		// 1.创建数据报， 用于接受服务器端响应的数据
		byte[] data2 = new byte[1024];
		DatagramPacket packet2 = new DatagramPacket(data2, data2.length);
		// 2.接受服务器端响应的数据
		socket.receive(packet2);
		// 3.读取服务器端响应的数据信息
		String reply = new String(data2, 0, packet2.getLength());
		System.out.println("我是客户端, 服务器说" + reply);
		// 4.关闭资源
		socket.close();
	}
}
