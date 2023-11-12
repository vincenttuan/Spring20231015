package spring.core.session04.proxy.dyn;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

// 紀錄所有使用者下達的參數命令的 Aspect 切面程式
public class MyLoggerAspect {
	// before: 前置通知-公用邏輯/方法
	// 紀錄所有使用者下達的參數命令
	public static void before(Object[] args) {
		if(args != null) {
			String path = "src/main/java/spring/core/session04/proxy/dyn/log.txt";
			String content = Arrays.toString(args);
			try {
				Files.write(
						Paths.get(path), // 存檔路徑 
						content.getBytes(StandardCharsets.UTF_8), // 轉 byte[] 陣列 
						StandardOpenOption.CREATE, // 如果文件不存在, 則創建新的文件
						StandardOpenOption.APPEND  // 如果文件存在, 則在文件末尾添加資料
				);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
