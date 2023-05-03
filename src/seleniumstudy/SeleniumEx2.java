package seleniumstudy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/*
 * daum 사이트에서 사진 받아오기
 */
public class SeleniumEx2 {
	public static void main(String[] args) throws Exception {
		System.setProperty // 시스템 환경설정=> chrome 드라이버파일 설정
		("webdriver.chrom.driver","D:\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver(); 
		System.out.println(driver.getPageSource());
		WebElement element = null;
		String url =
"https://search.daum.net/search?w=img&nil_search=btn&DA=NTB&enc=utf8&q=%EC%9E%A5%EB%AF%B8";
		driver.get(url);
		Thread.sleep(1000);
		// findElements : 태그들
		// images : img 태그 목록
		List<WebElement> images = driver.findElements
				(By.cssSelector("div.wrap_thumb a > img"));
		List<String> imgurl= new ArrayList<>(); // 이미지 링크 저장
		for(WebElement img : images) {
			// img 태그의 src 속성의 값
			String src = img.getAttribute("src");
			if(src.startsWith("http")) { // 이미지 경로가 절대경로인 이미지
				imgurl.add(src);
				System.out.println(src);
			}
		}
		File f = new File("img");
		if(!f.exists()) f.mkdirs(); // img 폴더 생성
		
		for(int i =0; i<imgurl.size(); i++) {
			// URL : 네트워크 클래스
			// imgurl.get(i) : 이미지의 절대경로 저장
			// is : 네트워크상의 이미지 파일을 읽기 위한 입력 스트림
			InputStream is =
					new URL(imgurl.get(i)).openConnection().getInputStream();
			// fos : 이미지 저장할 파일설정
			FileOutputStream fos = new FileOutputStream("img/"+i+".jpg");
			byte[] buf = new byte[8096]; // 8K 
			int len = 0;
			while((len=is.read(buf)) != -1) {
				fos.write(buf,0,len);
			}
			is.close();
			fos.close();
			Thread.sleep(100);
		}
		driver.close();
	}

}
