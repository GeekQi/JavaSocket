import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 服务器端，实现基于UDP的用户登录
 * 
 * @author GeekQi
 *
 */
public class UDPServer {
	public static void main(String[] args) throws IOException {
		/**
		 * 接受客户端发送的数据
		 */
		// 1.创建服务器端DatagramScoket，指定端口
		DatagramSocket socket = new DatagramSocket(8800);
		// 2.创建数据报，用于接受客户端发送的数据。数据报中的数据是要保存在字节数组中
		byte[] data = new byte[1024];// 创建字节数据，指定接收的数据报的大小
		DatagramPacket packet = new DatagramPacket(data, data.length);
		// 3.接受客户端发送的数据
		System.out.println("***服务器端已经启动***等待客户端发送数据");
		socket.receive(packet);// 当我们调用receive方法的时候，它会处于阻塞状态，会等待接受客户端发送的数据
		// 此方法在接受到数据报之前会一直阻塞
		// 4.读取数据
		String info = new String(data, 0, packet.getLength());
		System.out.println("我时服务器，客户端说：" + info);

		/**
		 * 向客户端响应数据。 与客户端向服务端发送数据类似
		 */
		// 1.定义客户端的地址、端口号、数据
		InetAddress address = packet.getAddress();
		int port = packet.getPort();
		byte[] data2 = "欢迎您".getBytes();
		// 2.创建数据报，包含响应的数据信息
		DatagramPacket packet2 = new DatagramPacket(data2, data2.length, address, port);
		// 3.响应客户端
		socket.send(packet2);
		// 4.关闭资源
		socket.close();

	}
}
