import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AutoTrader 
{

	@FindBy(css= "#js-header-nav > ul > li.test-header__nav-listing.u-float-right > div.is-not-signed-in > a")
	WebElement SignInButton;
	
	@FindBy(css= "#js-social__signup-tab > span")
	WebElement SignUpButton;
	
	@FindBy(id= "email")
	WebElement EmailInput;
	
	@FindBy(id= "password")
	WebElement passwordInput;
	
	@FindBy(css= "#social--signup--submit")
	WebElement SignupNowbutton;
	
	@FindBy(css= "#js-header-nav > ul > li.test-header__nav-listing.u-float-right > div.is-signed-in > a > i")
	WebElement accountHover;
	
	
	
	@FindBy(css= "#js-header-nav > ul > li.test-header__nav-listing.u-float-right > div.is-signed-in > a > i")
	WebElement accountOptions;
	
	@FindBy(css= "#ursSignoutForm > button")
	WebElement signOut;
	
	@FindBy(id= "signin-email")
	WebElement emailLogin;
	
	@FindBy(id= "signin-password")
	WebElement passwordLogin;

	@FindBy(id= "signInSubmit")
	WebElement SignInNowButton;
	
	
	
	@FindBy(id= "postcode")
	WebElement postcode; 
	
	@FindBy(css= "#radius")
	WebElement distanceSelect; 
	@FindBy(css= "#radius > option:nth-child(4)")
	WebElement distanceActual;
	
	@FindBy(css= "#searchVehiclesMake > option:nth-child(13)")
	WebElement makeSelect; 
	
	@FindBy(css= "#searchVehiclesModel > option:nth-child(6)")
	WebElement model; 
	
	@FindBy(css= "#searchVehiclesPriceFrom")
	WebElement minCost; 
	@FindBy(css= "#searchVehiclesPriceFrom > option:nth-child(5)")
	WebElement minCostActual;
	
	@FindBy(css= "#searchVehiclesPriceTo > option:nth-child(21)")
	WebElement maxCost; 
	
	@FindBy(css= "#search > span")
	WebElement searchButton; 
	
	
	
	
	
	
}
