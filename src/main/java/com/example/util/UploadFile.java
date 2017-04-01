package com.example.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Created by wxf on 2016-12-05.
 */
public class UploadFile {

	/**
	 * 多文件文件上传 upfile 前台文件流 path 文件路径 如 d:\\file/test/test 返回值: 若多个文件则多个文件信息
	 * 存库是使用path+/filename
	 */
	public static void upload(MultipartFile[] upfiles, String basePath, String path) {
		try {
			File file = new File(basePath + path);
			if (!file.exists()) {
				file.mkdirs();
			}
			System.out.println(upfiles.length+"======================");
			for (MultipartFile upfile : upfiles) {
				String oldName = upfile.getOriginalFilename();
				long size = upfile.getSize();
				if (size > 0) {
					String fileType = oldName.substring(oldName.lastIndexOf("."), oldName.length());
					String newName = UUID.randomUUID().toString().replace("-", "");
					// 文件上传;
					FileUtils.writeByteArrayToFile(new File(basePath + path + newName + fileType), upfile.getBytes());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// newName 自定义path+新名字
	public static InputStream downLoad(String path) {
		InputStream is = null;
		try {
			is = new FileInputStream(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;
	}

	public static boolean delete(String path) {
		boolean flag = false;
		if (deleteFile(path)) {
			String imgPath = path.substring(0, path.lastIndexOf("."));
			System.out.println(imgPath);
			return deleteDirectory(imgPath);
		}
		return flag;
	}

	/**
	 * 删除单个文件
	 *
	 * @param sPath
	 *            被删除文件的文件名
	 * @return 单个文件删除成功返回true，否则返回false
	 */
	private static boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

	/**
	 * 删除目录（文件夹）以及目录下的文件
	 *
	 * @param sPath
	 *            被删除目录的文件路径
	 * @return 目录删除成功返回true，否则返回false
	 */
	private static boolean deleteDirectory(String sPath) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!sPath.endsWith(File.separator)) {
			sPath = sPath + File.separator;
		}
		File dirFile = new File(sPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		boolean flag = true;
		// 删除文件夹下的所有文件(包括子目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			} // 删除子目录
			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag)
			return false;
		// 删除当前目录
		if (dirFile.delete()) {
			return true;
		} else {
			return false;
		}
	}
}
