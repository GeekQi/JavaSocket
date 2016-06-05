import java.net.MalformedURLException;
import java.net.URL;

/**
 * URL的常用方法
 *
 * @author GeekQi
 *
 */
public class UrlGetTest {
	public static void main(String[] args) {
		try {
			// 创建一个URL实例
			URL imooc = new URL("http://www.imooc.com");
			// ?后面表示参数，＃后面表示锚点
			URL url = new URL(imooc, "/index.html?username=tom#test");
			// 如果未指定端口号，则使用默认的端口号80，此时getPort（）方法返回只为－1
			System.out.println("协议: " + url.getProtocol() + "\n" + "主机: " + url.getHost() + "\n" + "端口号" + url.getPort()
					+ "\n" + "文件路径" + url.getPath() + "\n" + "文件名： " + url.getFile() + "\n" + "相对路径" + url.getRef()
					+ "\n" + "查询字符串" + url.getQuery());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
//协议: http
//主机: www.imooc.com
//端口号-1
//文件路径/index.html
//文件名： /index.html?username=tom
//相对路径test
//查询字符串username=tom