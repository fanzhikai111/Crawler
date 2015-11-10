package main.java.edu.fiu.yxjiang.system.noiser.worker;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Properties;
import org.apache.log4j.Logger;

/**
 * 
 * ����ϵͳ��Ϣ
 * 
 * @author root
 * 
 */
public final class SystemHelper {
	private static final Logger logger = Logger.getLogger(SystemHelper.class);

	// ���ϵͳ���Լ�
	public static Properties props = System.getProperties();
	// ����ϵͳ����
	public static String OS_NAME = getPropertery("os.name");
	// �з�ҳ��
	public static String OS_LINE_SEPARATOR = getPropertery("line.separator");
	// �ļ��ָ�����
	public static String OS_FILE_SEPARATOR = getPropertery("file.separator");

	/**
	 * 
	 * ����ϵͳ�����ͻ�ȡ����������ip��ַ
	 * 
	 * InetAddress inet = InetAddress.getLocalHost(); ��������������Linux�·���127.0.0.1��
	 * ��Ҫ����linux�·��ص���/etc/hosts�����õ�localhost��ip��ַ��
	 * �����������İ󶨵�ַ���������������İ󶨵�ַ������ȡ��������ip��ַ������
	 * 
	 * @throws UnknownHostException
	 */
	public static InetAddress getSystemLocalIp() throws UnknownHostException {
		InetAddress inet = null;
		String osname = getSystemOSName();
		try {
			// ���windowϵͳ
			if (osname.equalsIgnoreCase("Windows XP")) {
				inet = getWinLocalIp();
				// ���linuxϵͳ
			} else if (osname.equalsIgnoreCase("Linux")) {
				inet = getUnixLocalIp();
			}
			if (null == inet) {
				throw new UnknownHostException("������ip��ַδ֪");
			}
		} catch (SocketException e) {
			logger.error("��ȡ����ip����" + e.getMessage());
			throw new UnknownHostException("��ȡ����ip����" + e.getMessage());
		}
		return inet;
	}

	/**
	 * ��ȡFTP�����ò���ϵͳ
	 * 
	 * @return
	 */
	public static String getSystemOSName() {
		// ���ϵͳ���Լ�
		Properties props = System.getProperties();
		// ����ϵͳ����
		String osname = props.getProperty("os.name");
		if (logger.isDebugEnabled()) {
			logger.info("the ftp client system os Name " + osname);
		}
		return osname;
	}

	/**
	 * ��ȡ���Ե�ֵ
	 * 
	 * @param propertyName
	 * @return
	 */
	public static String getPropertery(String propertyName) {
		return props.getProperty(propertyName);
	}

	/**
	 * ��ȡwindow ����ip��ַ
	 * 
	 * @return
	 * @throws UnknownHostException
	 */
	public static InetAddress getWinLocalIp() throws UnknownHostException {
		InetAddress inet = InetAddress.getLocalHost();
		System.out.println("������ip=" + inet.getHostAddress());
		return inet;
	}

	/**
	 * 
	 * ���ܶ���ip��ַֻ��ȡһ��ip��ַ ��ȡLinux ����IP��ַ
	 * 
	 * @return
	 * @throws SocketException
	 */
	private static InetAddress getUnixLocalIp() throws SocketException {
		Enumeration<NetworkInterface> netInterfaces = NetworkInterface
				.getNetworkInterfaces();
		InetAddress ip = null;
		String ipAddress = "";
		while (netInterfaces.hasMoreElements()) {
			NetworkInterface ni = (NetworkInterface) netInterfaces
					.nextElement();
			Enumeration<InetAddress> ips = ni.getInetAddresses();
			while (ips.hasMoreElements()) {
				ip = ips.nextElement();
				if (ip != null && ip instanceof Inet4Address) {
					ipAddress = ip.getHostAddress();
					if (!ipAddress.equals("127.0.0.1"))
						return ip;
				}
			}
		}
		return null;
	}

	/**
	 * getUnixLocalIpAddress
	 * 
	 * @return
	 * @throws SocketException
	 */
	public static String getUnixLocalIpAddress() {
		Enumeration<NetworkInterface> allNetInterfaces;
		try {
			allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			InetAddress ip = null;
			String ipAddress = "";
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = allNetInterfaces.nextElement();
				// System.out.println(netInterface.getName());
				Enumeration<InetAddress> addresses = netInterface
						.getInetAddresses();
				while (addresses.hasMoreElements()) {
					ip = addresses.nextElement();
					if (ip != null && ip instanceof Inet4Address) {
						ipAddress = ip.getHostAddress();
						if (!ipAddress.equals("127.0.0.1"))
							return ipAddress;
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * ��ȡ��ǰ���г�����ڴ���Ϣ
	 * 
	 * @return
	 */
	public static final String getRAMinfo() {
		Runtime rt = Runtime.getRuntime();
		return "RAM: " + rt.totalMemory() + " bytes total, " + rt.freeMemory()
				+ " bytes free.";
	}

	public static void main(String[] argv) {
		System.out.println(getUnixLocalIpAddress());
	}
}
