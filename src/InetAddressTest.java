import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * InetAddress类
 *
 * @author GeekQi
 *
 */
public class InetAddressTest {

	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		// 获取本机的InetAddress实例
		InetAddress address = InetAddress.getLocalHost();
		System.out.println("计算机名" + address.getHostName());
		System.out.println("IP地址" + address.getHostAddress());
		byte[] bytes = address.getAddress();// 获取字节数组形式的IP地址
		System.out.println("字节数组形式的IP" + Arrays.toString(bytes));
		// 根据机器名获取InetAddress实例
		// InetAddress address2 = InetAddress.getByName("Geeks.local");
		// 根据IP地址获取实例
		InetAddress address2 = InetAddress.getByName("10.202.30.104");
		System.out.println("计算机名" + address2.getHostName());
		System.out.println("IP地址" + address2.getHostAddress());
	}
}
// 计算机名Geeks.local
// IP地址10.202.30.104
// 字节数组形式的IP[10, -54, 30, 104]
// 注：注：在计算机系统中，数值一律用补码来表示和存储。原因在于，使用补码，可以将符号位和数值域统一处理
// 计算机名10.202.30.104
// IP地址10.202.30.104
