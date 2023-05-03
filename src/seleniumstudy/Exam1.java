package seleniumstudy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
/*
 *	jspstudy2의 로그인창 띄우기
 */
public class Exam1 {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty // 시스템 환경설정=> chrome 드라이버파일 설정
		("webdriver.chrom.driver","D:\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8080/jspstudy2/member/loginForm"); // url 로드 
		System.out.println(driver.getPageSource());
		WebElement element = null;
		element = driver.findElement(By.name("id"));
		String id = "admin";
		element.sendKeys(id);
		element = driver.findElement(By.name("pass"));
		String pass = "1234";
		element.sendKeys(pass);
		/*
		 * element = driver.findElement (By.cssSelector(".btn.btn-dark"));
		 * element.click();
		 *  => 버튼 찾아서 클릭
		 */
		element = driver.findElement(By.name("f"));
		element.submit(); // form submit
		driver.switchTo().alert().accept(); // alert 클릭
		Thread.sleep(1000);
		driver.get("http://localhost:8080/jspstudy2/member/list");
		// Jsoup으로 분석하기
		// driver.getPageSource() : 브라우저에서 제공되는 html 페이지.
		Document doc = Jsoup.parse(driver.getPageSource());
		Elements div = doc.select("table");
		for(Element e : div) {
			for(Element tr : e.select("tr")) {
				Elements tds = tr.select("td");
				if(tds.size() > 3) {
					// tds.get(2) : 회원이름 td 인덱스값
					System.out.println(tds.get(2).html());
				}
			}
		}
		Thread.sleep(3000);
		driver.close(); // 브라우저 닫기 
	}

}
