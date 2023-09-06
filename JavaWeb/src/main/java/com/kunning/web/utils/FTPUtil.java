package com.kunning.web.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FTP工具类
 */
public class FTPUtil {

	/**
	 * <日志>
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(FTPUtil.class);

	/**
	 * <私有化构造函数>
	 */
	private FTPUtil() {
	}

	/**
	 * 功能：获取FTP服务器的客户端实例<br>
	 *
	 * @param hostname 主机IP
	 * @param port     端口号
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	public static FTPClient getFTPClient(String hostname, String port, String username, String password) {
		LOGGER.info("【getFtpClient】【开始执行】【请求参数：】【hostname:{}, port:{}, username:{}, password:{}】",
				new Object[] { hostname, port, username, password });
		FTPClient ftpClient = null;
		// if (StringUtils.isNotBlank(hostname) && StringUtils.isNotBlank(username) &&
		// StringUtils.isNotBlank(password)) {
		try {
			ftpClient = new FTPClient();
			ftpClient.connect(hostname, Integer.parseInt(port));// 连接FTP服务器，如果采用默认端口，可以使用ftp.connect(hostname)直接连接
			if (!ftpClient.login(username, password)) {// 登录
				ftpClient.disconnect();
				LOGGER.info("【FTP server refused connection】");
				return null;
			}
		} catch (Exception e) {
			LOGGER.debug("login FTP server failed");
		}
		// }
		return ftpClient;
	}

	public static void uploadCirFile(String fileDir, String fileName, InputStream input, String ftpHost, String ftpUser,
			String ftpPwd, String ftpPort) {
		FTPClient ftp = FTPUtil.getFTPClient(ftpHost, ftpPort, ftpUser, ftpPwd);
		createDirs(ftp, fileDir);
		if (null != ftp) {
			try {
				// 生成文件
				File f = new File(fileName);
				ftp.changeWorkingDirectory(fileDir);
				ftp.storeFile(f.getName(), new FileInputStream(f));
				input.close();
				ftp.logout();
			} catch (Exception e) {
			} finally {
				if (ftp.isConnected()) {
					try {
						ftp.disconnect();
					} catch (IOException ioe) {
					}
				}
			}
		}
	}

	/**
	 * 功能：创建不存在的目录文件 <br>
	 */
	public static void createDirs(FTPClient ftp, String path) {
		if (null != ftp) {
			boolean result = false;
			String[] sub = path.split("/");
			int length = sub.length;
			int i = 1;
			StringBuilder pathsBuilder = new StringBuilder("/");
			try {
				if (ftp.changeWorkingDirectory(path)) {
					result = true;
				}
				while (!result) {
					if (i > length - 1) {
						break;
					}
					pathsBuilder.append(sub[i]).append("/");
					if (!ftp.changeWorkingDirectory(pathsBuilder.toString())) {
						ftp.makeDirectory(pathsBuilder.toString());
					}
					++i;
				}
			} catch (IOException e) {
				LOGGER.debug("【FTP创建文件夹失败】" + e.getMessage());
			}
		}
	}

	/**
	 * 
	 * 功能：上传报表至FTP服务器 〈功能详细描述〉
	 *
	 * @param fileDir
	 * @param fileName
	 * @param input
	 * @param ftpHost
	 * @param ftpUser
	 * @param ftpPwd
	 * @param ftpPort
	 */
	public static void uploadFile_1(String fileDir, String fileName, InputStream input, String ftpHost, String ftpUser,
			String ftpPwd, String ftpPort) {
		FTPClient ftp = FTPUtil.getFTPClient(ftpHost, ftpUser, ftpPwd, ftpPort);
		String workdir = fileDir + fileName.substring(0, 8);
		createDirs(ftp, workdir);
		if (null != ftp) {
			try {
				ftp.changeWorkingDirectory(workdir);
				ftp.storeFile(fileName, input);
				ftp.logout();
			} catch (Exception e) {
			} finally {
				if (ftp.isConnected()) {
					try {
						ftp.disconnect();
					} catch (IOException ioe) {
					}
				}
			}
		}
	}

	/**
	 * 
	 * 功能：上传文件至FTP服务器 〈功能详细描述〉
	 *
	 * @param fileDir
	 * @param fileName
	 * @param input
	 * @param ftpHost
	 * @param ftpUser
	 * @param ftpPwd
	 * @param ftpPort
	 * @throws Exception
	 */
	public static void uploadFile_2(String fileDir, String tranDate, String fileName, InputStream input, String ftpHost,
			String ftpUser, String ftpPwd, String ftpPort) throws IOException {
		FTPClient ftp = FTPUtil.getFTPClient(ftpHost, ftpUser, ftpPwd, ftpPort);
		String workdir = fileDir + fileName.substring(0, 14) + "_" + tranDate;
		createDirs(ftp, workdir);
		if (null != ftp) {
			FileInputStream fis = null;
			try {
				// 生成文件
				File f = new File(fileName);
				fis = new FileInputStream(f);
				ftp.changeWorkingDirectory(workdir);
				ftp.storeFile(f.getName(), fis);
				input.close();
				fis.close();
				ftp.logout();
			} catch (Exception e) {

			} finally {
				if (fis != null) {
					fis.close();
				}
				if (input != null) {
					input.close();
				}
				if (ftp.isConnected()) {
					try {
						ftp.disconnect();
					} catch (IOException ioe) {
						LOGGER.error("" + 165, ioe);
					}
				}
			}
		}
	}

	/**
	 * 
	 * 功能：上传文件至FTP服务器。
	 *
	 * @param hostname    FTP服务器IP地址
	 * @param port        FTP服务器端口
	 * @param username    用户名
	 * @param password    密码
	 * @param path        目录（路径）
	 * @param fileName    文件名
	 * @param inputStream 输入流
	 */
	public static void uploadFile_3(String hostname, int port, String username, String password, String path,
			String fileName, InputStream inputStream) {
		LOGGER.info(
				"【uploadToFTP】【开始执行】【请求参数：】【hostname:{}, port:{}, username:{}, password:{}, path:{}, fileName:{}, inputStream:{}】",
				new Object[] { hostname, port, username, password, path, fileName, inputStream });

		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect(hostname, port);// 连接FTP服务器，如果采用默认端口，可以使用ftp.connect(hostname)直接连接
			ftpClient.login(username, password);// 用户名密码登录

			if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {// 检查状态码
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);// 指定上传文件的类型 二进制文件
				ftpClient.makeDirectory(path);// 创建指定路径文件夹
				ftpClient.changeWorkingDirectory(path);// 切换到指定路径
				ftpClient.enterLocalPassiveMode();// 客户端发起的传输数据的模式
				ftpClient.storeFile(fileName, inputStream);// 以指定的名字保存文件到ftp服务器。如果文件存在，会覆盖掉老的文件。
			} else {
				LOGGER.error("【uploadToFTP】【登录服务器时】【登录失败】");
			}
		} catch (SocketException e) {
			LOGGER.error("【uploadToFTP】【创建FTP连接时】【发生异常】【SocketException：{}】", e);
		} catch (IOException e) {
			LOGGER.error("【uploadToFTP】【上传文件时】【发生异常】【IOException：{}】", e);
		} finally {
			if (inputStream != null) {// 关闭流
				try {
					inputStream.close();
				} catch (IOException e) {
					LOGGER.error("【uploadToFTP】【关闭流时】【发生异常】【IOException：{}】", e);
					inputStream = null;// 关闭流失败，置为null，等待GC来回收掉资源。
				}
			}

			if (ftpClient.isConnected()) {// 断开连接
				try {
					ftpClient.logout();
					ftpClient.disconnect();
				} catch (IOException e) {
					LOGGER.error("【uploadToFTP】【断开FTP连接时】【发生异常】【IOException：{}】", e);
					ftpClient = null;
				}
			}
			LOGGER.info("【uploadToFTP】【结束执行】【执行成功】【响应参数：】【无】");
		}
	}

	/**
	 * 
	 * 功能: 上传贷款信息报表至FTP服务器<br>
	 *
	 * @param fileName
	 * @param filePath
	 * @param input
	 * @param host
	 * @param port
	 * @param user
	 * @param pwd
	 * @param path
	 */
	public static void uploadFile_4(String fileName, String filePath, InputStream input, String host, String port,
			String user, String pwd, String path) {
		FTPClient ftp = FTPUtil.getFTPClient(host, port, user, pwd);
		String realPath = ("".equals(filePath) ? path + "/" : path + filePath + "/");

		createDirs(ftp, realPath);
		try {
			ftp.changeWorkingDirectory(realPath);
			if (fileName.endsWith(".zip")) {
				ftp.setFileType(FTPClient.BINARY_FILE_TYPE); // 解决上传zip无法打开的BUG
			}
			// 生成文件
			ftp.storeFile(fileName, input);
			input.close();
			ftp.logout();
		} catch (Exception e) {
			LOGGER.error("上传至FTP服务器失败", e);
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
					LOGGER.error("FTP服务器断开连接失败", ioe);
				}
			}
		}
	}

	/**
	 * 
	 * 功能：下载文件。
	 *
	 * @param hostname    FTP服务器IP地址
	 * @param port        FTP服务器端口
	 * @param username    用户名
	 * @param password    密码
	 * @param path        目录（路径）
	 * @param fileName    文件名
	 * @param inputStream 输入流
	 */
	public static void downloadFromFTP(String hostname, int port, String username, String password, String path,
			String fileName, HttpServletResponse response) {
		LOGGER.info("【uploadToFTP】【开始执行】【请求参数：】【hostname:{}, port:{}, username:{}, password:{}, path:{}, fileName:{}】",
				new Object[] { hostname, port, username, password, path, fileName });

		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect(hostname, port);// 连接FTP服务器，如果采用默认端口，可以使用ftp.connect(hostname)直接连接
			ftpClient.login(username, password);// 用户名密码登录

			if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {// 检查状态码
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);// 指定上传文件的类型 二进制文件
				ftpClient.makeDirectory(path);// 创建指定路径文件夹
				ftpClient.changeWorkingDirectory(path);// 切换到指定路径
				ftpClient.enterLocalPassiveMode();// 客户端发起的传输数据的模式
				// ftpClient.storeFile(fileName, inputStream);//
				// 以指定的名字保存文件到ftp服务器。如果文件存在，会覆盖掉老的文件。
				ftpClient.retrieveFile(fileName, response.getOutputStream());
			} else {
				LOGGER.error("【uploadToFTP】【登录服务器时】【登录失败】");
			}
		} catch (SocketException e) {
			LOGGER.error("【uploadToFTP】【创建FTP连接时】【发生异常】【SocketException：{}】", e);
		} catch (IOException e) {
			LOGGER.error("【uploadToFTP】【上传文件时】【发生异常】【IOException：{}】", e);
		} finally {
			if (ftpClient.isConnected()) {// 断开连接
				try {
					ftpClient.logout();
					ftpClient.disconnect();
				} catch (IOException e) {
					LOGGER.error("【uploadToFTP】【断开FTP连接时】【发生异常】【IOException：{}】", e);
					ftpClient = null;
				}
			}
			LOGGER.info("【uploadToFTP】【结束执行】【执行成功】【响应参数：】【无】");
		}
	}

	/**
	 * 
	 * 功能描述: <br>
	 * 删除当天ftp生成文件
	 *
	 * @param host
	 * @param port
	 * @param user
	 * @param pwd
	 * @param path
	 */
	public static void deletefiles(String host, String port, String user, String pwd, String path) {
		FTPClient ftp = FTPUtil.getFTPClient(host, port, user, pwd);

		if (null != ftp) {
			deletefilesDetail(ftp, path);

			try {
				ftp.changeWorkingDirectory(path);
				ftp.changeToParentDirectory();
				ftp.removeDirectory("");
			} catch (IOException e1) {
				LOGGER.error("删除FTP服务器当日文件目录失败", e1);
			} finally {
				if (ftp.isConnected()) {
					try {
						ftp.disconnect();
					} catch (IOException e) {
						LOGGER.error("FTP服务器断开连接失败", e);
					}
				}
			}

		}
	}

	/**
	 * 功能：删除path目录下的所有文件、目录
	 *
	 * @param ftp
	 * @param path
	 */
	public static void deletefilesDetail(FTPClient ftp, String path) {
		try {
			ftp.changeWorkingDirectory(path);
			FTPFile[] ftpFiles = ftp.listFiles();
			for (FTPFile ftpFile : ftpFiles) {
				if (ftpFile.isDirectory()) {
					if (!ftp.removeDirectory(path + ftpFile.getName())) {
						deletefilesDetail(ftp, path + ftpFile.getName() + "/");
						ftp.changeToParentDirectory();
						ftp.removeDirectory(path + ftpFile.getName());
					}
				} else {
					ftp.deleteFile(ftpFile.getName());
				}
			}
		} catch (IOException e) {
			LOGGER.error("删除FTP服务器文件失败", e);
		}
	}

	/**
	 * 功能：获取FTP上的文件
	 *
	 * @param host
	 * @param port
	 * @param user
	 * @param pwd
	 * @param path
	 * @return
	 */
	public static List<File> getFtpFiles(String host, String port, String user, String pwd, String path,
			String filePath) {
		FTPClient ftp = FTPUtil.getFTPClient(host, port, user, pwd);
		List<File> files = new ArrayList<File>();
		String realPath = path + filePath + "/";

		if (null != ftp) {
			try {
				ftp.changeWorkingDirectory(realPath);
				FTPFile[] ftpFiles = ftp.listFiles();
				for (FTPFile ftpFile : ftpFiles) {
					File localFile = new File(ftpFile.getName());
					OutputStream is = new FileOutputStream(localFile);
					ftp.retrieveFile(ftpFile.getName(), is);
					files.add(localFile);
					is.close();
				}
			} catch (IOException e) {
				LOGGER.error("【获取FTP服务器文件失败】", e);
			}
		}
		return files;
	}

}
