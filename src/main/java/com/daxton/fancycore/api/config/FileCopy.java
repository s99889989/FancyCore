package com.daxton.fancycore.api.config;

import org.bukkit.plugin.Plugin;

import java.io.*;
import java.nio.channels.FileChannel;

public class FileCopy {

	//複製.jar內的資源
	public static void resourceCopy(Plugin plugin, String resourcePath, String savePath, boolean replace){

		//建立缺失資料夾
		makeFile(plugin, savePath);

		if(savePath.contains(".")){
			File outFile = new File(plugin.getDataFolder(), savePath);

			InputStream in = plugin.getResource(resourcePath);

			try {
				if (!outFile.exists() || replace) {
					File outFileF = new File(plugin.getDataFolder(), savePath);
					OutputStream out = new FileOutputStream(outFileF);
					byte[] buf = new byte[1024];
					int len;
					while ((len = in.read(buf)) > 0) {
						out.write(buf, 0, len);
					}
					out.close();
					in.close();
				}
			}catch (IOException exception) {
				exception.printStackTrace();
			}
		}

	}

	//複製插件設定檔路徑檔案
	public static void filePluginFile(Plugin plugin, String source, String dest){

		//建立缺失資料夾
		makeFile(plugin, dest);

		File inputFile = new File(plugin.getDataFolder(), source);
		File outputFile = new File(plugin.getDataFolder(), dest);
		try {
			copyFile(inputFile, outputFile);
		}catch (IOException exception){
			//
		}
	}

	//建立缺失資料夾
	public static void makeFile(Plugin plugin, String dest){
		int lastIndex = dest.lastIndexOf('/');
		File outDir = new File(plugin.getDataFolder(), dest.substring(0, lastIndex >= 0 ? lastIndex : 0));
		if (!outDir.exists()) {
			outDir.mkdirs();
		}
	}

	//複製檔案
	public static void copyFile(File source, File dest) throws IOException {
		FileChannel inputChannel = null;
		FileChannel outputChannel = null;
		try {
			inputChannel = new FileInputStream(source).getChannel();
			outputChannel = new FileOutputStream(dest).getChannel();
			outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
		} finally {
			inputChannel.close();
			outputChannel.close();
		}
	}

}
