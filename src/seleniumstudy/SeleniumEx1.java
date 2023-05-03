package seleniumstudy;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
/*
 * 	1. https://www.selenium.dev/downloads/ => selenum jar 파일 다운로드
	2. https://chromedriver.chromium.org/downloads
	3. chrom 브라우저 -> 도움말 -> 크롬정보:버전확인
		113.0.5672.64
	4. 버전에 맞는 크롬드라이버 다운받기
	5. 크롬드라이버 압출풀기 -> 폴더확인
	6. /seleniumstudy java project 생성
	7. seleniumstudy - lib 폴더 생성
		lib 폴더에 jsoup..jar , selenium..jar
	8. jar 파일들을 calsspath 추가
		selenium프로젝트 우클릭 -> 빌드패스 -> 콘피규어 빌드패스 -> 라이브러리 탭 
		lib -> jar파일 선택 
 * */
public class SeleniumEx1 {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty // 시스템 환경설정=> chrome 드라이버파일 설정
		("webdriver.chrom.driver","D:\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://www.naver.com"); // url 로드 
		System.out.println(driver.getPageSource());
		WebElement element = null;
		element = driver.findElement
				(By.cssSelector("#account.sc_login a.link_login"));
		element.click();
		// 로그인 하기
		/*
		 *  findElement(By.함수) : 한개의 태그 찾기
		 *  findElements(By.함수) : 여러개의 태그 찾기
		 *  By.함수 : 태그 찾는 방식 설정
		 *  By.name() : name 속성으로 태그 찾기
		 *  By.id() : id 속성 태그 찾기
		 *  By.cssSelector() : css에서 사용되는 선택자 방식으로 태그 찾기
		 *  	#account.sc_login : id ="account" 이고
		 *  						class ="sc_login" 한개의 태그 선택
		 *  	a.link_login	: class="link_login"인 a 태그 선택
		 *  	#account.sc_login a.link_login :
		 *  			id="account"인 태그의 하위 태그중
		 *  			class="link_login"인 a 태그 선택
		 *  By.xpath() : xml에서 태그 선택하는 방식
		 *  	 	 / : root노드 ,문서노드
		 *  		// : 모든 위치 선택
		 *  @id='log.login' : @는 속성표시
		 *  					  id="log.login" 인 태그
		 *  By.linkText(문자열) : 태그의 text 값으로 태그찾기
		 *  By.partialLinkText(문자열) : 태그의 text 값이 포함된 태그 찾기
		 *  		
		 */
		Scanner sc = new Scanner(System.in);
		System.out.println("네이버 아이디를 입력하세요");
		String id = sc.nextLine();
		// sendKeys(값) : 찾아놓은 태그에 값 입력
		System.out.println("네이버 비밀번호를 입력하세요");
		String pw = sc.nextLine();
		element = driver.findElement(By.name("id"));
		element.sendKeys(id);
		element = driver.findElement(By.name("pw"));
		element.sendKeys(pw);
		element = driver.findElement(By.xpath("//*[@id='log.login']"));
		element.click();
		Thread.sleep(1000);
	}
}





































