package airtelAssignment;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class PageClass extends BaseClassTest
{
	@FindBy(xpath="//ul[@id='home-page-tabs']/li")
	List<WebElement> itemSections;
	
	@FindBy(xpath="//ul[@id='homefeatured']/child::li/div/div[@class='right-block']/child::div/span[@class='price product-price']")
	List<WebElement> itemPriceList;
	
	@FindBy(xpath="//ul[@id='homefeatured']/child::li/div/div[@class='right-block']/child::div[2]/a[1]")
	List<WebElement> addToCartBtn;
	
	@FindBy(xpath="//span[@title='Close window']")
	WebElement closeCart;
	
	@FindBy(xpath="//b[text()='Cart']")
	WebElement cart;
	
	@FindBy(xpath="//a[@id='button_order_cart']")
	WebElement checkOutCart;
	
	@FindBy(xpath="//div[@class='product-name']/a")
	WebElement cartProductDetails;
	
	@FindBy(xpath="//ul[@id='homefeatured']/child::li/div/div[@class='right-block']/child::div/span[@class='price-percent-reduction']")
	List<WebElement> discountPercentage;
	
	@FindBy(xpath="//ul[@id='homefeatured']/child::li/div/div[@class='right-block']/child::div[@class='content_price']")
	List<WebElement> allPrice;
	
	
	static String productId = null;
	Actions act=new Actions(driver);
	ArrayList<String> itemsPrice = new ArrayList<String>();


	public PageClass()
	{
		PageFactory.initElements(driver, PageClass.this);
	}
	
	public void navigateToPopularSection(String sectionName)
	{
		for (WebElement item : itemSections) 
		{
			String itm = item.getAttribute("class");
			String eleTxt = item.getText();
			if (!itm.equals("active") && eleTxt.contains(sectionName)) 
			{
				log.info("Clicking on "+item +" item section");
				item.click();
				log.info(eleTxt +" section is open");
				System.out.println(sectionName+" items section open");
				break;
			} else if(!itm.equals("active") && eleTxt.contains(sectionName))
			{
				log.info("Clicking on "+eleTxt +" item section");
				item.click();
				log.info(eleTxt +" section is already open");
				System.out.println(sectionName +" items section open");
				break;
			}
			else
			{
				log.info(eleTxt +" section is already open");
				break;
			}
		}
	}
	
	public String sortProductOnAscendingOrder_Price(int number)
	{
		log.info("Sorting the product in asending oreder based on price");
		for (WebElement price : itemPriceList) {
			String itemP=price.getText().replace("$", "");
			log.info("Storing product price into array,Price is "+itemP);
			itemsPrice.add(itemP);
		}
		int size = itemsPrice.size();

		String[] item = itemsPrice.toArray(new String[size]);

		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				if (Float.parseFloat(item[i]) > Float.parseFloat(item[j])) {
					String temp = item[i];
					item[i] = item[j];
					item[j] = temp;
				}
			}
		}
		log.info("Returning the lowest price"+item[number-1]);
		return item[number-1];
	}
	
	public void addProductIntoCart(String lowerPrice)
	{
		log.info("Adding the product into cart");
		for (WebElement itm1 : itemPriceList) {
			String str2 = itm1.getText().replace("$", "");
			String str3 = lowerPrice;
			if (str2.equalsIgnoreCase(str3)) 
			{
				act.moveToElement(itm1).perform();
				log.info("Move to lowest price item "+ str2 );
				log.info("Clicking on add on button");

				for(WebElement element:addToCartBtn)
				{
					if(element.isDisplayed() && element.isEnabled())
					{
						productId=element.getAttribute("data-id-product");
						element.click();
						log.info("Clicked on add on buttton");
						System.out.println("Selected item productId:- "+productId);
						log.info("Item added into cart and product id is "+productId);
						System.out.println("Item added in cart");
						break;
					}
				}
				break;
			}
		}
	}
	
	public void closeCart()
	{
		log.info("Clicking on close cart tab");
		closeCart.click();
		log.info("Cart tab closed");
	}
	
	public void goToCart()
	{
		log.info("Moving to cart");
		act.moveToElement(cart).perform();
		log.info("Clicking on check out cart button");
		checkOutCart.click();
		log.info("Successfully navigated to added cart item");	
	}
	
	public String getCartHref()
	{
		String href=cartProductDetails.getAttribute("href");
		log.info("Cart item href is "+href);
		String pTitle=cartProductDetails.getAttribute("title");
		log.info("Cart itme name is "+pTitle);
		System.out.println("Cart item name:- "+pTitle);	
		log.info("Returning href of cart item "+href);
		return href;		
	}
	
	public void verifyFinalPriceAfterDiscount()
	{
	    DecimalFormat df2 = new DecimalFormat("#.##");

		for(WebElement price:allPrice)
		{
			
			for(WebElement discount:discountPercentage)
			{
				String st=price.getText();
				String st1=discount.getText();
				if(st.contains(st1))
				{
					log.info("Checking discount percentage in price of items");

					log.info("Item price is with final amount, original amount and discount percentage "+st);
					log.info("Discount on item in percentage is "+ st1);


					System.out.println("Discount item price:- "+st);
					String[] allP=st.split(" ");
					double discountPrice = Double.parseDouble(allP[0].replace("$", ""));
					df2.format(discountPrice);
					log.info("Item price after discount is "+discountPrice);
					System.out.println("Item price after discount is:- "+discountPrice);
					
					double originalPrice= Double.parseDouble(allP[1].replace("$", ""));
					df2.format(originalPrice);
					log.info("Original price of item before descount is "+originalPrice);
					System.out.println("Original price of item before descount is:- "+originalPrice);
					
					double disountPercentage=Integer.parseInt(allP[2].replace("$", "").replace("-", "").replace("%", ""));
					df2.format(disountPercentage);
					log.info("Discount on item is "+disountPercentage);
					System.out.println("disountPercentage on item:- "+disountPercentage);
					
					double discountAmount=(originalPrice*disountPercentage)/100;
					df2.format(discountAmount);
					log.info("Discount amount on item is "+discountAmount);
					System.out.println("discountAmount:-"+discountAmount);
					
					double finalPrice=originalPrice-discountAmount;
					df2.format(finalPrice);
					log.info("Final amount of item after discount is "+finalPrice);
					System.out.println("finalPrice:-"+finalPrice);
					
					log.info("Comparing the final amount with discount price");
					Assert.assertTrue(Double.toString(finalPrice).contains(Double.toString(discountPrice)));
					System.out.println("Discount amount is correct");
					log.info("final amount and discount price are same");
				}
			}
			
		}
	}
	

}
