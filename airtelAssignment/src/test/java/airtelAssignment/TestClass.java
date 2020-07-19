package airtelAssignment;

import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class TestClass extends BaseClassTest{

	
	@Test
	public void useCase_1() throws Exception {

		log.info("Navigating to popular item section");
		new PageClass().navigateToPopularSection("POPULAR");
		
		String lowest_Price=new PageClass().sortProductOnAscendingOrder_Price(1);
		System.out.println("Smallest element of the array is:: " + lowest_Price);

		new PageClass().addProductIntoCart(lowest_Price);				
		new PageClass().closeCart();	
		new PageClass().goToCart();
		
		log.info("Taking screenshot of cart item");
		File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path=".//screenshot";
		FileUtils.copyFile(screen, new File(path+"/screenshot.png"));	
		log.info("Screenshot successfully taken and saved at "+path);
		
		String cartHref=new PageClass().getCartHref();	
		log.info("Camparing selected item and cart item product id");
		Assert.assertTrue(cartHref.contains(PageClass.productId));
		log.info("selected item and cart item product id are same");

	}
	
	@Test
	public void useCase_2()
	{
		new PageClass().navigateToPopularSection("Popular");	
		new PageClass().verifyFinalPriceAfterDiscount();
	}

}
