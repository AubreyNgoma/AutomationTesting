package za.co.resbank.pages.External;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used for finding elements in the c23 form ant return the text of it.
 * User: Lean
 * Date: 7/30/18
 * Time: 9:29 AM
 * To change this template use File | Settings | File Templates.
 */
@DefaultUrl("http://localhost:4502/editor.html/content/forms/af/sarb-forms-web-interface/c23.html")
public class C23FormStaticFields extends PageObject {

    List<String> errorMessages = new ArrayList<>();
    WebElement element;
    WebDriverWait wait;
    Actions act;
    public int indication1 = 0;
    public int indication2 = 0;
    public int indication3 = 0;
    public int indication4 = 0;
    public int indication5 = 0;


    public int tableOneErrorIndication(){
        wait = new WebDriverWait(this.getDriver(), 30);
        act = new Actions(this.getDriver());
        element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_1306459901-panel-panel-table-Row1527254387480-tableItem1527254387482___widget\"]"));
        act.moveToElement(element);
        act.click(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_1306459901-panel-panel-table-Row1527254387480-tableItem1527254387482___widget\"]")))).build().perform();

        for(int i = 2; i < 6; i++)   {
            element = this.getDriver().findElement(By.xpath("//*[@id=\'guideContainer-rootPanel-panel_100111925-panel_1306459901-panel-panel-table-Row1527253821370-tableItem152725382137"+i+"__\']/div[3]"));
            act.moveToElement(element);

            String T1R1 = element.getText();
            if(T1R1.isEmpty()){indication1++;}
            errorMessages.add(T1R1);
        }
        for(int i = 8; i < 10; i++){
            element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_1306459901-panel-panel-table-Row1527509426096-tableItem152750942609" + i + "__\"]/div[3]"));
            act.moveToElement(element);

            String T1R16 = element.getText();
            if(T1R16.isEmpty()){indication1++;}
            errorMessages.add(T1R16);
        }
        for(int i = 0; i < 2; i++){
            element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_1306459901-panel-panel-table-Row1527509426096-tableItem152750942610" + i + "__\"]/div[3]"));
            act.moveToElement(element);

            String T1R16 = element.getText();
            if(T1R16.isEmpty()){indication1++;}
            errorMessages.add(T1R16);
        }
        for(int i = 6; i < 10; i++)   {
            element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_1306459901-panel-panel-table-Row1527501392044-tableItem152750139204" + i + "__\"]/div[3]"));


            String T1R10 = element.getText();
            if(T1R10.isEmpty()){indication1++;}
            errorMessages.add(T1R10);
    }
        //Done
        for(int i = 5; i < 9; i++)   {
            element =  this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_1306459901-panel-panel-table-Row1527509542953-tableItem152750954295"+i+"__\"]/div[3]"));

            String T1R23 = element.getText();
            if(T1R23.isEmpty()){indication1++;}
            errorMessages.add(T1R23);
        }
        //Done
        for(int i = 6; i < 10; i++)   {
            element =  this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_1306459901-panel-panel-table-Row1528450438273-tableItem152845043827"+i+"__\"]/div[3]"));

            String T1R26 = element.getText();
            if(T1R26.isEmpty()){indication1++;}
            errorMessages.add(T1R26);
        }
        return indication1;
    }

    public int tableTwoErrorIndication(){

        wait = new WebDriverWait(this.getDriver(), 30);
        act = new Actions(this.getDriver());
        element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_280151870-panel-panel-table-Row1527254473990-tableItem1527254473992___widget\"]"));
        act.moveToElement(element);
        act.click(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_280151870-panel-panel-table-Row1527254473990-tableItem1527254473992___widget\"]")))).build().perform();

        //done
        for(int i = 2; i < 6; i++)   {

            element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_280151870-panel-panel-table-Row1527253821370-tableItem152725382137"+i+"__\"]/div[3]"));
            act.moveToElement(element);
            //*[@id="guideContainer-rootPanel-panel_100111925-panel_280151870-panel-panel-table-Row1527253821370-tableItem1527253821372__"]/div[3]
            String T2R31 = element.getText();
            if(T2R31.isEmpty()){indication2++;}
            errorMessages.add(T2R31);
        }
        //done
        for(int i = 1; i < 5; i++){
            element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_280151870-panel-panel-table-Row1527508443859-tableItem152750844386" + i + "__\"]/div[3]"));
            act.moveToElement(element);

            String T2R44 = element.getText();
            if(T2R44.isEmpty()){indication2++;}
            errorMessages.add(T2R44);
        }

        for(int i = 0; i < 4; i++)   {
            element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_280151870-panel-panel-table-Row1527509732068-tableItem152750973207" + i + "__\"]/div[3]"));


            String T2R49 = element.getText();
            if(T2R49.isEmpty()){indication2++;}
            errorMessages.add(T2R49);
        }
        return indication2;
    }

    public int tableThreeErrorIndication(){

        wait = new WebDriverWait(this.getDriver(), 30);
        act = new Actions(this.getDriver());
        element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_248469527-panel-panel-table-Row1527254387480-tableItem1527254387482___widget\"]"));
        act.moveToElement(element);
        act.click(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_248469527-panel-panel-table-Row1527254387480-tableItem1527254387482___widget\"]")))).build().perform();

        //done
        for(int i = 2; i < 6; i++)   {

            if(i>2&&i<5){
                element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_248469527-panel-panel-table-Row1527253821370-tableItem152725382137"+i+"__\"]/div[2]"));
            }else{
                element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_248469527-panel-panel-table-Row1527253821370-tableItem152725382137"+i+"__\"]/div[3]"));
            }
            act.moveToElement(element);

            String T3R58 = element.getText();
            if(T3R58.isEmpty()){indication3++;}
            errorMessages.add(T3R58);
        }
        //done
        for(int i = 2; i < 6; i++){
            if(i>2&&i<5){
                element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_248469527-panel-panel-table-Row1527254473990-tableItem152725447399" + i + "__\"]/div[2]"));
            }else{
                element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_248469527-panel-panel-table-Row1527254473990-tableItem152725447399" + i + "__\"]/div[3]"));
            }
            act.moveToElement(element);

            String T3R63 = element.getText();
            if(T3R63.isEmpty()){indication3++;}
            errorMessages.add(T3R63);
        }


         //Done
        for(int i = 6; i < 10; i++)   {

          if(i>6&&i<9){
              element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_248469527-panel-panel-table-Row1527501392044-tableItem152750139204" + i + "__\"]/div[2]"));
          }else{
              element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_248469527-panel-panel-table-Row1527501392044-tableItem152750139204" + i + "__\"]/div[3]"));
          }
            String T3R68 = element.getText();
            if(T3R68.isEmpty()){indication3++;}
            errorMessages.add(T3R68);
        }


        for(int i = 8; i < 10; i++)   {
            if(i==9){
                element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_248469527-panel-panel-table-Row1527509426096-tableItem152750942609" + i + "__\"]/div[2]"));
            }else{
              element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_248469527-panel-panel-table-Row1527509426096-tableItem152750942609" + i + "__\"]/div[3]"));
            }
            String T3R73 = element.getText();
            if(T3R73.isEmpty()){indication3++;}
            errorMessages.add(T3R73);
        }

        for(int i = 0; i < 2; i++){
            if(i==0){
                element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_248469527-panel-panel-table-Row1527509426096-tableItem152750942610" + i + "__\"]/div[2]"));
            }else{
                element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_248469527-panel-panel-table-Row1527509426096-tableItem152750942610" + i + "__\"]/div[3]"));
            }

            act.moveToElement(element);
            String T3R73 = element.getText();
            if(T3R73.isEmpty()){indication3++;}
            errorMessages.add(T3R73);
        }

        for(int i = 3; i < 7; i++)   {
            element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_248469527-panel-panel-table-Row1527509663081-tableItem152750966308"+ i + "__\"]/div[3]"));
            String T2R49 = element.getText();
            if(T2R49.isEmpty()){indication3++;}
            errorMessages.add(T2R49);
        }
        for(int i = 4; i < 8; i++)   {

            element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_248469527-panel-panel-table-Row1528462797242-tableItem152846279724"+ i + "__\"]/div[3]"));
            String T2R49 = element.getText();
            if(T2R49.isEmpty()){indication3++;}
            errorMessages.add(T2R49);
        }

        return indication3;
    }


    public int tableFourErrorIndication(){

        wait = new WebDriverWait(this.getDriver(), 30);
        act = new Actions(this.getDriver());
        element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_197133626-panel-panel-table-Row1527254387480-tableItem1527254387482___widget\"]"));
        act.moveToElement(element);
        act.click(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_197133626-panel-panel-table-Row1527254387480-tableItem1527254387482___widget\"]")))).build().perform();

        //done
        for(int i = 2; i < 6; i++)   {
            if(i>2&&i<5){
                element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_197133626-panel-panel-table-Row1527253821370-tableItem152725382137"+i+"__\"]/div[2]"));
            }else{
                element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_197133626-panel-panel-table-Row1527253821370-tableItem152725382137"+i+"__\"]/div[3]"));
            }

            act.moveToElement(element);

            String T4R83 = element.getText();
            if(T4R83.isEmpty()){indication4++;}
            errorMessages.add(T4R83);
        }
        //done
        for(int i = 2; i < 6; i++){
            if(i>2&&i<5){
                element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_197133626-panel-panel-table-Row1527254443450-tableItem152725444345" + i + "__\"]/div[2]"));
            }else{
                element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_197133626-panel-panel-table-Row1527254443450-tableItem152725444345" + i + "__\"]/div[3]"));
            }

            act.moveToElement(element);

            String T4R86 = element.getText();
            if(T4R86.isEmpty()){indication4++;}
            errorMessages.add(T4R86);
        }
         //done
        for(int i = 4; i < 8; i++)   {
            if(i>4&&i<7){
                element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_197133626-panel-panel-table-Row1527501799082-tableItem152750179908" + i + "__\"]/div[2]"));
            }else{
                element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_197133626-panel-panel-table-Row1527501799082-tableItem152750179908" + i + "__\"]/div[3]"));
            }

            String T4R91 = element.getText();
            if(T4R91.isEmpty()){indication4++;}
            errorMessages.add(T4R91);
        }
        //done
        for(int i = 0; i < 4; i++)   {

            element = this.getDriver().findElement(By.xpath("  //*[@id=\"guideContainer-rootPanel-panel_100111925-panel_197133626-panel-panel-table-Row1527508473818-tableItem152750847382" + i + "__\"]/div[3]"));

            String T4R91 = element.getText();
            if(T4R91.isEmpty()){indication4++;}
            errorMessages.add(T4R91);
        }

        return indication4;
    }


    public int tableFiveErrorIndication(){

        wait = new WebDriverWait(this.getDriver(), 30);
        act = new Actions(this.getDriver());
        element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_201441536-panel-panel-table-Row1527253821370-tableItem1527253821372___widget\"]"));
        act.moveToElement(element);
        act.click(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_201441536-panel-panel-table-Row1527253821370-tableItem1527253821372___widget\"]")))).build().perform();

            element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_201441536-panel-panel-table-Row1527253821370-tableItem1527253821375__\"]/div[3]"));
            act.moveToElement(element);

            String T5R99 = element.getText();
            if(T5R99.isEmpty()){indication5++;}
            errorMessages.add(T5R99);

            element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_201441536-panel-panel-table-Row1527253836738-tableItem1527253836743__\"]/div[3]"));
            act.moveToElement(element);

            String T5R100 = element.getText();
            if(T5R100.isEmpty()){indication5++;}
            errorMessages.add(T5R100);

            element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_201441536-panel-panel-table-Row1527254387480-tableItem1527254387485__\"]/div[3]"));

            String T5R101 = element.getText();
            if(T5R101.isEmpty()){indication5++;}
            errorMessages.add(T5R101);

            element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_201441536-panel-panel-table-Row1527254443450-tableItem1527254443455__\"]/div[3]"));

            String T5R102 = element.getText();
            if(T5R102.isEmpty()){indication5++;}
            errorMessages.add(T5R102);

           element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_201441536-panel-panel-table-Row1527254459627-tableItem1527254459632__\"]/div[3]"));

           String T5R103 = element.getText();
           if(T5R103.isEmpty()){indication5++;}
           errorMessages.add(T5R103);

        element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_201441536-panel-panel-table-Row1527254473990-tableItem1527254473995__\"]/div[3]"));

        String T5R104 = element.getText();
        if(T5R104.isEmpty()){indication5++;}
        errorMessages.add(T5R104);


        element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_201441536-panel-panel-table-Row1527254514954-tableItem1527254514959__\"]/div[3]"));

        String T5R105 = element.getText();
        if(T5R105.isEmpty()){indication5++;}
        errorMessages.add(T5R105);



        element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_201441536-panel-panel-table-Row1527501785288-tableItem1527501785293__\"]/div[3]"));

        String T5R106 = element.getText();
        if(T5R106.isEmpty()){indication5++;}
        errorMessages.add(T5R106);

        element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_201441536-panel-panel-table-Row1527501799082-tableItem1527501799087__\"]/div[3]"));

        String T5R107 = element.getText();
        if(T5R107.isEmpty()){indication5++;}
        errorMessages.add(T5R107);

        element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_201441536-panel-panel-table-Row1527501819538-tableItem1527501819543__\"]/div[3]"));

        String T5R108 = element.getText();
        if(T5R108.isEmpty()){indication5++;}
        errorMessages.add(T5R108);

        element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_201441536-panel-panel-table-Row1527501392044-tableItem1527501392049__\"]/div[3]"));

        String T5R109 = element.getText();
        if(T5R109.isEmpty()){indication5++;}
        errorMessages.add(T5R109);

        element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_201441536-panel-panel-table-Row1527501412484-tableItem1527501412489__\"]/div[3]"));

        String T5R110 = element.getText();
        if(T5R110.isEmpty()){indication5++;}
        errorMessages.add(T5R110);

        element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_201441536-panel-panel-table-Row1527508418382-tableItem1527508418388__\"]/div[3]"));

        String T5R111 = element.getText();
        if(T5R111.isEmpty()){indication5++;}
        errorMessages.add(T5R111);


        element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_201441536-panel-panel-table-Row1527508443859-tableItem1527508443864__\"]/div[3]"));

        String T5R112 = element.getText();
        if(T5R112.isEmpty()){indication5++;}
        errorMessages.add(T5R112);

        element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_201441536-panel-panel-table-Row1527508473818-tableItem1527508473823__\"]/div[3]"));

        String T5R113 = element.getText();
        if(T5R113.isEmpty()){indication5++;}
        errorMessages.add(T5R113);


        for(int i = 8; i < 10; i++)   {

            element = this.getDriver().findElement(By.xpath("  //*[@id=\"guideContainer-rootPanel-panel_100111925-panel_201441536-panel-panel-table-Row1527509426096-tableItem152750942609"+i+"__\"]/div[3]"));

            String T3R73 = element.getText();
            if(T3R73.isEmpty()){indication5++;}
            errorMessages.add(T3R73);
        }

        for(int i = 0; i < 2; i++){

            element = this.getDriver().findElement(By.xpath("//*[@id=\"guideContainer-rootPanel-panel_100111925-panel_201441536-panel-panel-table-Row1527509426096-tableItem152750942610"+i+"__\"]/div[3]"));

            act.moveToElement(element);
            String T3R73 = element.getText();
            if(T3R73.isEmpty()){indication5++;}
            errorMessages.add(T3R73);
        }


        return indication5;
    }
}