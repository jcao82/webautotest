import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		WebDriver dr = new HtmlUnitDriver();
		dr.get("http://www.baidu.com");
		WebElement element = dr.findElement(By.name("wd"));
	    element.sendKeys("webdriver");
		element.submit();
		Thread.sleep(5000);
	    System.out.println("page title is:" +dr.getTitle());
	    System.out.println(dr.getPageSource());
	}
}
