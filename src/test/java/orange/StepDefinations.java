package orange;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Yogesh on 29-08-2016.
 */
public class StepDefinations {

    WebDriver driver;

    // Random r =new Random();
    // public String EmpId =r.nextInt()+"a"

    public static String EmpId;
    public static String username1;
    public static String reportTypename;
    public static String firstnameOfEmployee;
    public static String empprofilename;
    public String newAdminRole;
    public String newStatus;
    public String newSystemUsername;
    public String newEmployeeName;
    public String newPassword;
    public String newRePassword;

    @Before
    public void openBrowser() {
        System.setProperty("webdriver.chrome.driver", "/src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://sbtechnos11.trial52.orangehrmlive.com/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    public boolean isTextPresent(String keyword, By element) {

        List<WebElement> elementList = driver.findElements(element);
        int size = elementList.size();
        return elementList.contains("keyword");
    }

    public void doLogin(String userName, String passWord) {
        driver.findElement(By.id("txtUsername")).sendKeys(userName);
        driver.findElement(By.id("txtPassword")).sendKeys(passWord);
    }


    public void isTextPresentAt(By element, String text) {
        String textboxvalue = driver.findElement(element).getText();
        Assert.assertTrue(textboxvalue.contains(text));// Assert.assertTrue(driver.findElement(element).getText().contains(text));
    }

    public void selectTovisibleText(By element, String value) {
        Select visibleTxt = new Select(driver.findElement(element));
        visibleTxt.selectByVisibleText("value");
    }


    public void doSearch(By element, String searchkey, By element1) {
        WebElement searchbox = driver.findElement(element);
        // searchbox.click();
        searchbox.sendKeys(searchkey);
        WebElement searchclick = driver.findElement(element1);
        searchclick.click();
    }

    public void hoverTo(By element, By element1) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(element)).build().perform();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement option = driver.findElement(element1);
        option.click();
    }

    public void selectLogin() {
        driver.findElement(By.id("btnLogin")).click();
    }

    public void clickWebElement(By element) {
        WebElement webelement = driver.findElement(element);
        webelement.click();
    }


    ////@Login test cases//////////////////////////////////////////////////////////////////////////
    @Given("^Admin is on login page$")
    public void goToLoginPage() {
        Assert.assertTrue(driver.findElement(By.id("btnLogin")).isDisplayed());
    }

    @When("^Admin enters username as\"([^\"]*)\" and password as \"([^\"]*)\"$")
    public void adminDoLogin(String username, String password) {
        doLogin(username, password);
    }

    @When("^Andmin select login$")
    public void andminSelectlogin() {
        selectLogin();
    }

    @Then("^Admin should login successfully$")
    public void adminLoggedsuccessfully() {
        Assert.assertTrue(driver.findElement(By.id("menu_pim_viewPimModule")).isEnabled());
    }

    @Then("^admin should see welcome message as \"([^\"]*)\"$")
    public void adminOnWelcomePage(String message) {
        isTextPresentAt(By.id("welcome"), message);
    }

    @When("^Admin enters  invalid \"([^\"]*)\" and invalid \"([^\"]*)\"$")
    public void adminDoInvalid(String username, String password) {
        doLogin(username, password);
        selectLogin();
    }

    @Then("^Admin should navigate to retry login page$")
    public void adminGotoRetryLoginPage() {
        Assert.assertTrue(driver.getCurrentUrl().contentEquals("http://sbtechnos11.trial52.orangehrmlive.com/securityAuthentication/retryLogin"));
    }

    @When("^Admin login with below credentials$")
    public void adminLoginWithCredentials(DataTable usercredentials) {
        List<List<String>> credential = usercredentials.raw();
        driver.findElement(By.id("txtUsername")).sendKeys(credential.get(0).get(0));
        driver.findElement(By.id("txtPassword")).sendKeys(credential.get(0).get(1));
    }

    @Given("^admin user is on Welcome page$")
    public void aadminOnWelcomePage() {
        doLogin("Admin", "aediMNjU");
        selectLogin();// admin_enters_username_as_and_password_as("Admin", "aediMNjU");// driver.findElement(By.id("btnLogin")).click();
    }

    @When("^user  search data with keyword \"([^\"]*)\"$")
    public void userSearch(String Username, String Status, String region) {
        driver.findElement(By.id("searchSystemUser_userName")).sendKeys(Username);
        selectTovisibleText(By.id("searchSystemUser_status"), Status);
    }


///////////////////////@addEmployee///////////////////////////////////////////////////////////////////////


    public void adminOnAddEmployeePage() {
        clickWebElement(By.cssSelector("#menu_pim_viewPimModule>b"));// driver.findElement(By.cssSelector("#menu_pim_viewPimModule>b")).click();
        clickWebElement(By.cssSelector("#menu_pim_addEmployee"));//driver.findElement(By.cssSelector("#menu_pim_addEmployee")).click();
    }

    @Given("^admin  is on add employee page$")
    public void adminAtAddEmployepage() {
        //admin_user_is_on_Welcome_page();
        adminOnAddEmployeePage();
    }

    public boolean isTextPresent(String text) {
        try {
            driver.getPageSource().contains(text);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean isTextsame(String text1, String text2) {
        if (text1.contains(text2)) {
            return true;
        } else {
            return false;
        }
    }


    public void addEmployeeDetails(DataTable details2) {
        List<List<String>> empldetails1 = details2.raw();

        try {

            WebElement firstname = driver.findElement(By.cssSelector("#firstName"));// firstname.sendKeys((new Random()).nextInt()+empldetails1.get(1).get(0));
            firstname.sendKeys(empldetails1.get(1).get(0));
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            WebElement lastname = driver.findElement(By.cssSelector("#lastName"));
            lastname.sendKeys(empldetails1.get(1).get(1));
            String lastnameOfEmployee = lastname.getAttribute("value");
            WebElement empid = driver.findElement(By.cssSelector("#employeeId"));
            firstnameOfEmployee = firstname.getAttribute("value") + " " + lastnameOfEmployee;
            EmpId = driver.findElement(By.cssSelector("#employeeId")).getAttribute("value");
            WebElement photo = driver.findElement(By.cssSelector("#photofile"));
            //  photo.sendKeys("src/test/resources/photo/cute_haircut_baby_girl-t2.jpg/");
            WebElement status = driver.findElement(By.cssSelector("#chkLogin"));
            status.click();
            WebElement textUsername = driver.findElement(By.cssSelector("#user_name"));
            username1 = new Random().nextInt() + "test";
            //username.sendKeys(empdetails.get(1).get(2));
            textUsername.sendKeys(username1);
            WebElement password = driver.findElement(By.cssSelector("#user_password"));
            password.sendKeys(empldetails1.get(1).get(2));
            String password1 = empldetails1.get(1).get(2);
            // username1 = username.getText();
            WebElement rempassword = driver.findElement(By.cssSelector("#re_password"));
            rempassword.sendKeys(empldetails1.get(1).get(3));
            String repassword = empldetails1.get(1).get(3);
            Select status1 = new Select(driver.findElement(By.cssSelector("#status")));
            status1.selectByVisibleText(empldetails1.get(1).get(4));
            Select location = new Select(driver.findElement(By.cssSelector("#location")));
            location.selectByVisibleText(empldetails1.get(1).get(5));
            driver.findElement(By.cssSelector("#btnSave")).click();
            WebElement content_ui = driver.findElement(By.cssSelector("#profile-pic>h1"));
            empprofilename = content_ui.getText();
            //  Assert.assertTrue(empprofilename.contains(username1));
            if (isTextsame(empprofilename, firstnameOfEmployee) == true) {
                driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            } else {
                System.out.println("Check all manadatory field");
                driver.close();
            }
        } catch (Exception e) {
            System.out.println("the message is" + e.getMessage());
        }
    }

    @When("^admin add  new employee details with login details$")
    public void adminAddNewEmployeeDetails(DataTable details) {
        addEmployeeDetails(details);
    }

    @Then("^admin  can see added employee in  employeelist$")
    public void adminAddedEmployeeSuccessfully() {
        clickWebElement(By.cssSelector("#menu_pim_viewEmployeeList "));//driver.findElement(By.cssSelector("#menu_pim_viewEmployeeList")).click();
        doSearch(By.cssSelector("#empsearch_id"), EmpId, By.cssSelector("#searchBtn"));
        //driver.findElement(By.cssSelector("#empsearch_id")).sendKeys(EmpId);//driver.findElement(By.cssSelector("#searchBtn")).click();
        String Id = driver.findElement(By.cssSelector(".left>a")).getText();
        Assert.assertTrue(Id.contains(EmpId));
    }
    /*  @Given("^Admin is on home page$")
    public void adminOnHomePage() {
        adminOnWelcomePage();
    }*/

    @When("^Admin  add  \"([^\"]*)\" reporting  method in configuration from PIM$")
    public void adminAddReportingMethod(String subdirect) throws Throwable {
        try {
            driver.findElement(By.cssSelector("#menu_pim_viewPimModule>b")).click();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            hoverTo(By.cssSelector("#menu_pim_Configuration"), By.linkText("Reporting Methods"));
            clickWebElement(By.cssSelector("#btnAdd"));//driver.findElement(By.cssSelector("#btnAdd")).click();
            WebElement reportname = driver.findElement(By.cssSelector("#reportingMethod_name"));
            Random r1 = new Random();
            reportTypename = r1.nextInt() + "subdirect";
            reportname.sendKeys(reportTypename);// String reportTypename = reportname.getText();
            clickWebElement(By.cssSelector("#btnSave"));// driver.findElement(By.cssSelector("#btnSave")).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("^user can create employee profile with new reporting method Subdirect in add supervisor under Report to section$")
    public void employeeWithNewReportingMethod(DataTable detailsupervisar) {
        try {
            adminOnAddEmployeePage();
            addEmployeeDetails(detailsupervisar);
            clickWebElement(By.linkText("Report-to"));
            // WebElement reportTo = driver.findElement(By.linkText("Report-to"));reportTo.click();
            clickWebElement(By.cssSelector("#btnAddSubordinateDetail"));
            // WebElement addSubordinate = driver.findElement(By.cssSelector("#btnAddSubordinateDetail"));addSubordinate.click();
            clickWebElement(By.cssSelector("#reportto_subordinateName_empName"));
            // WebElement superVisorName = driver.findElement(By.cssSelector("#reportto_subordinateName_empName"));superVisorName.sendKeys("Aruna p");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            // new Select(superVisorName).selectByVisibleText("Aruna p");
            WebElement reportto = driver.findElement(By.cssSelector("#reportto_reportingMethodType"));
            Select report = new Select(reportto);
            report.selectByVisibleText(reportTypename);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("The error is:" + e.getMessage());
        }

    }
//@AddLeaveEntitlement

    @Given("^Admin is on Welcome  page$")
    public void adminOnWelcomePage() {
        adminOnWelcomePage();
    }

    @When("^Admin created new employee with following details$")
    public void adminAddemployee(DataTable leavemployedetail) {
        adminOnAddEmployeePage();
        addEmployeeDetails(leavemployedetail);
    }


    @When("^admin added leave entitlement for new employee$")
    public void addLeaveToEmployee() {

    }

    @When("^admin add (\\d+) days leave entitlement for new employee$")
    public void addLeaveDays(int arg1) {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        clickWebElement(By.cssSelector("#menu_leave_viewLeaveModule>b"));
        //WebElement leave = driver.findElement(By.cssSelector("#menu_leave_viewLeaveModule>b"));leave.click();
        try {
            hoverTo(By.cssSelector("#menu_leave_Entitlements"), By.linkText("Add Entitlements"));
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement empfirstname = driver.findElement(By.cssSelector("#entitlements_employee_empName"));
            empfirstname.clear();
            empfirstname.sendKeys(empprofilename);
//empfirstname.sendKeys(Keys.ENTER);
            // driver.findElement(By.id("Value")).sendKeys(Keys.ENTER);
            //empfirstname.sendKeys(Keys.ENTER);

           /* driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            WebElement leaveNubr = driver.findElement(By.cssSelector("#entitlements_entitlement"));
            leaveNubr.sendKeys("13");
            WebElement save = driver.findElement(By.cssSelector("#btnSave"));
            save.click();
            driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
            WebElement empname = driver.findElement(By.cssSelector("#entitlements_employee_empName"));
            String name = empname.getAttribute("value");
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            Assert.assertTrue(name.contains(empprofilename));*/

            WebElement searchbutton = driver.findElement(By.cssSelector("#searchBtn"));
            searchbutton.click();
            driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
            WebElement leaveaddButton = driver.findElement(By.cssSelector("#btnAdd"));
            leaveaddButton.click();
            WebElement leavedays = driver.findElement(By.cssSelector("#entitlements_entitlement"));
            leavedays.sendKeys("10");
            WebElement leavesavebtn = driver.findElement(By.cssSelector("#btnSave"));
            leavesavebtn.click();
        } catch (Exception e) {
            System.out.println("not found:" + e.getMessage());
        }


    }

    @Then("^he should see employee with given leave  days$")
    public void employeeWithLeaveDays() {

    }
    ////////////////////// //@Search System User/////////////////////////////////////////////////////

 /*   @Given("^Admin  is on Welcome page$")
    public void admin_is_on_Welcome_page() throws Throwable {

    }*/

    @When("^Admin create user with given data$")
    public void admin_create_user_with_given_data(DataTable userDetails)  {
        adminOnAddEmployeePage();
        addEmployeeDetails(userDetails);
    }

    //////////////////////////////////search in user management menu for usename/////////////////////////////////////////
    public void doSearchUserMenu(String searchkey) {
       clickWebElement(By.cssSelector("menu_admin_viewAdminModule"));
       // WebElement adminMenu = driver.findElement(By.cssSelector("#menu_admin_viewAdminModule>b"));adminMenu.click();
        hoverTo(By.id("menu_admin_UserManagement"), By.linkText("Users"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        doSearch(By.cssSelector("#searchSystemUser_userName"), searchkey, By.cssSelector("#searchBtn"));
    }

    @When("^Admin  click on User Management menu$")
    public void gotoUserManagementmenu() {
        doSearchUserMenu(username1);
    }


    @Then("^Admin can see employee details in user list$")
    public void doSearchInUserManagement(){

        try {
            String userUsername = driver.findElement(By.cssSelector(".left>a")).getText();

            Assert.assertTrue(userUsername.contains(username1));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ////////////////////////////////////////Edit System User///////////////////////////////////


    public void editUserDetails(DataTable newUserDetail) {

        try {
            List<List<String>> userDetail = newUserDetail.raw();

            driver.findElement(By.linkText(username1)).click();

            WebElement edit = driver.findElement(By.cssSelector("#btnSave"));
            edit.click();
            //WebElement EmployeeName = driver.findElement(By.cssSelector("#systemUser_employeeName_empName"));
            // EmployeeName.clear();
            // newEmployeeName = userDetail.get(1).get(3);
            //  EmployeeName.sendKeys(newEmployeeName);
            WebElement adminRole = driver.findElement(By.cssSelector("#systemUser_adminRole"));
            newAdminRole = userDetail.get(1).get(0);//////new Admin role
            new Select(adminRole).selectByVisibleText(userDetail.get(1).get(0));//select new admin role
            WebElement systemusername = driver.findElement(By.cssSelector("#systemUser_userName"));
            systemusername.clear();
            newSystemUsername = new Random().nextInt() + userDetail.get(1).get(2);/////assign new username///
            systemusername.clear();
            systemusername.sendKeys(newSystemUsername);
            WebElement SystemUserStatus = driver.findElement(By.cssSelector("#systemUser_status"));
            newStatus = userDetail.get(1).get(1);
            //////////select new status///////////
            new Select(SystemUserStatus).selectByVisibleText(newStatus);
            WebElement changePassword = driver.findElement(By.cssSelector("#systemUser_chkChangePassword"));
            changePassword.click();
            newPassword = userDetail.get(1).get(4);
            WebElement password = driver.findElement(By.cssSelector("#systemUser_password"));
            password.sendKeys(newPassword);
            newRePassword = userDetail.get(1).get(5);
            WebElement repassword = driver.findElement(By.cssSelector("#systemUser_confirmPassword"));
            repassword.sendKeys(newRePassword);
            WebElement save = driver.findElement(By.cssSelector("#btnSave"));
            save.click();
            WebElement resave = driver.findElement(By.cssSelector("#btnSave"));
            save.click();

        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("The error is:" + e.getMessage());
        }

    }


    @When("^Admin edit user details with following data$")
    public void doEditUserDetails(DataTable newdata)  {
        editUserDetails(newdata);
    }

    @Then("^Admin can see user  details in user list$")
    public void userSuccessfullyEdited() {

        System.out.println("hi");
    }

    @Then("^Admin can see edited user details in user list$")
    public void doCheckEditedUserDetails() {
        doSearchUserMenu(newSystemUsername);
        isTextPresentAt(By.cssSelector(".left>a"), newSystemUsername);//isTextPresentAt(By.cssSelector(".left"), newEmployeeName);
        isTextPresentAt(By.xpath(".//*[@id='resultTable']/tbody/tr/td[5]"), newStatus);
        isTextPresentAt(By.xpath(".//*[@id='resultTable']/tbody/tr/td[3]"), newAdminRole);

///////////////////////////////////////////for modify region//////////////////////////
       clickWebElement(By.linkText("Modify Region"));// WebElement changeRegion = driver.findElement(By.linkText("Modify Region"));changeRegion.click();
       clickWebElement(By.cssSelector("#userRegion_global"));//WebElement allRegion = driver.findElement(By.cssSelector("#userRegion_global"));allRegion.click();
        WebElement regions = driver.findElement(By.cssSelector("#userRegion_txtCountry"));
        regions.sendKeys("India");
        List<WebElement> autosuggest = driver.findElements(By.cssSelector(".ac_even.ac_over"));
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        String optionSelect = "India";
        //if(autosuggest.contains())
        autosuggest.get(0).click();
        driver.findElement(By.cssSelector("#saveBtn")).click();
    }

///////////////////////////////////////////////////Edit user with invalid details///////////////////////////////////////////////

    @When("^Admin can see user deatils in user list$")
    public void doCheckUserInUserlist() {

    }

    @When("^Admin edit user details with invalid data$")
    public void doEditWithInvalidata(DataTable arg1) {

    }

    @Then("^admin get error message \"([^\"]*)\"$")
    public void getErrorMessage(String arg1){

    }


}