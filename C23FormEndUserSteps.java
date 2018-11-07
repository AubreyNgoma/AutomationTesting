package za.co.resbank.serenitysteps;

import net.thucydides.core.annotations.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.resbank.forms.testing.data.DataSetProvider;
import za.co.resbank.pages.External.C23FormStaticFields;
import za.co.resbank.pages.External.ExternalC23Form;
import za.co.resbank.utils.RuleSet;
import za.co.resbank.utils.TableTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This is where the logic happens of entering values into the fields of the c23 form, also checking the errors inside each table.
 * User: Lean
 * Date: 5/22/18
 * Time: 11:47 AM
 * To change this template use File | Settings | File Templates.
 */


public class C23FormEndUserSteps {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private DataSetProvider dataSetProvider = new DataSetProvider();
     private C23FormStaticFields c23FormStaticFields = new C23FormStaticFields();

    private String[] classNames;
    private String[] classValues;
    private List<String> classNameList = new ArrayList<>();
    private List<String> classValueList = new ArrayList<>();
    private ExternalC23Form editFieldsExternal;
    private TableTypes tableType;

    @Step
    public void changeTableFields(String formType,String dataSetType){
        Map<Object,Object> data = dataSetProvider.getDataSetContent(formType,dataSetType);
        classNameList.add(data.keySet().toString());
        classValueList.add(data.values().toString());
        LOGGER.info("Start populating form.");
        for(String item:classValueList){
            classValues = item.replace('[',' ').trim().replace(']',' ').trim().split(",");
        }
        for(String item:classNameList){
            classNames = item.replace('[',' ').trim().replace(']',' ').trim().split(",");
        }

        for(int i = 0; i < classNames.length;i++){
            if(classNames[i].contains("c_01")){
                editFieldsExternal.goto_table1();
            }
             while(classNames[i].contains("c_01")){
              editFieldsExternal.enterFieldsForC3Form(classNames[i], classValues[i]);
                i++;
             }
            if(classNames[i].contains("c_02")){
                editFieldsExternal.goto_table2();
            }
            while(classNames[i].contains("c_02")){
                editFieldsExternal.enterFieldsForC3Form(classNames[i], classValues[i]);
                i++;
            }
            if(classNames[i].contains("c_03")){
                editFieldsExternal.goto_table3();
            }
            while(classNames[i].contains("c_03")){
                editFieldsExternal.enterFieldsForC3Form(classNames[i], classValues[i]);
                i++;
            }
            if(classNames[i].contains("c_04")){
                editFieldsExternal.goto_table4();
            }
            while(classNames[i].contains("c_04")){
                editFieldsExternal.enterFieldsForC3Form(classNames[i], classValues[i]);
                i++;
            }
            if(classNames[i].contains("c_05")){
                editFieldsExternal.goto_table5();
            }
            while(classNames[i].contains("c_05")){
                editFieldsExternal.enterFieldsForC3Form(classNames[i], classValues[i]);
                i++;
                if(classNames.length-i==0){break;}
            }
        }
    }

    @Step
    public void clickValidate(){
        editFieldsExternal.clickValidate();
    }

    @Step
    public boolean gotoTable(String tableType, String dataSetType){

        boolean check = false;
        RuleSet ruleSet = RuleSet.valueOf(dataSetType.toUpperCase());
        this.tableType = TableTypes.valueOf(tableType);
        switch (this.tableType){
            case TABLE1:
                editFieldsExternal.goto_table1();
                switch (ruleSet){
                    case PASS:
                        check = c23FormStaticFields.tableOneErrorIndication()==20;
                        return check;
                    case RULE1:
                        check = c23FormStaticFields.tableOneErrorIndication()==20;
                        return check;
                    case RULE2:
                        check = c23FormStaticFields.tableOneErrorIndication()==20;
                        return check;
                    case RULE3:
                        check = c23FormStaticFields.tableOneErrorIndication()==16;
                        return check;
                    case RULE4:
                        check = c23FormStaticFields.tableOneErrorIndication()==16;
                        return check;
                    case RULE5:
                        check = c23FormStaticFields.tableOneErrorIndication()==16;
                        return check;
                    case RULE6:
                        check= c23FormStaticFields.tableOneErrorIndication()==16;
                        return check;
                    case RULE7:
                        check =  c23FormStaticFields.tableOneErrorIndication()==16;
                        return check;
                    case RULE8:
                        check =  c23FormStaticFields.tableOneErrorIndication()==20;
                        return check;
                    case RULE9:
                        check =  c23FormStaticFields.tableOneErrorIndication()==20;
                        return check;
                    case RULE10:
                        check =  c23FormStaticFields.tableOneErrorIndication()==20;
                        return check;
                    case RULE11:
                        check =  c23FormStaticFields.tableOneErrorIndication()==20;
                        return check;
                    case RULE12:
                        check =  c23FormStaticFields.tableOneErrorIndication()==20;
                        return check;
                    case RULE13:
                        check =  c23FormStaticFields.tableOneErrorIndication()==20;
                        return check;
                    case RULE14:
                        check =  c23FormStaticFields.tableOneErrorIndication()==20;
                        return check;
                    case RULE15:
                        check =  c23FormStaticFields.tableOneErrorIndication()==20;
                        return check;
                    case RULE16:
                        check =  c23FormStaticFields.tableOneErrorIndication()==20;
                        return check;
                    case RULE17:
                        check =  c23FormStaticFields.tableOneErrorIndication()==20;
                        return check;
                    case RULE18:
                        check =  c23FormStaticFields.tableOneErrorIndication()==20;
                        return check;
                    case RULE19:
                        check =  c23FormStaticFields.tableOneErrorIndication()==20;
                        return check;
                    case RULE20:
                        check =  c23FormStaticFields.tableOneErrorIndication()==20;
                        return check;
                    case RULE21:
                        check =  c23FormStaticFields.tableOneErrorIndication()==20;
                        return check;
                    case RULE22:
                        check =  c23FormStaticFields.tableOneErrorIndication()==20;
                        return check;
                    case RULE23:
                        check =  c23FormStaticFields.tableOneErrorIndication()==20;
                        return check;
                    case RULE24:
                        check =  c23FormStaticFields.tableOneErrorIndication()==20;
                        return check;
                    default:
                        return check;

                }

            case TABLE2:
                editFieldsExternal.goto_table2();
                switch (ruleSet){
                    case PASS:
                        check = c23FormStaticFields.tableTwoErrorIndication()==12;
                        return check;
                    case RULE1:
                        check = c23FormStaticFields.tableTwoErrorIndication()==12;
                        return check;
                    case RULE2:
                        check = c23FormStaticFields.tableTwoErrorIndication()==12;
                        return check;
                    case RULE3:
                        check = c23FormStaticFields.tableTwoErrorIndication()==12;
                        return check;
                    case RULE4:
                        check = c23FormStaticFields.tableTwoErrorIndication()==12;
                        return check;
                    case RULE5:
                        check = c23FormStaticFields.tableTwoErrorIndication()==12;
                        return check;
                    case RULE6:
                        check= c23FormStaticFields.tableTwoErrorIndication()==12;
                        return check;
                    case RULE7:
                        check =  c23FormStaticFields.tableTwoErrorIndication()==12;
                        return check;
                    case RULE8:
                        check =  c23FormStaticFields.tableTwoErrorIndication()==8;
                        return check;
                    case RULE9:
                        check =  c23FormStaticFields.tableTwoErrorIndication()==8;
                        return check;
                    case RULE10:
                        check =  c23FormStaticFields.tableTwoErrorIndication()==8;
                        return check;
                    case RULE11:
                        check =  c23FormStaticFields.tableTwoErrorIndication()==12;
                        return check;
                    case RULE12:
                        check =  c23FormStaticFields.tableTwoErrorIndication()==12;
                        return check;
                    case RULE13:
                        check =  c23FormStaticFields.tableTwoErrorIndication()==12;
                        return check;
                    case RULE14:
                        check =  c23FormStaticFields.tableTwoErrorIndication()==12;
                        return check;
                    case RULE15:
                        check =  c23FormStaticFields.tableTwoErrorIndication()==12;
                        return check;
                    case RULE16:
                        check =  c23FormStaticFields.tableTwoErrorIndication()==12;
                        return check;
                    case RULE17:
                        check =  c23FormStaticFields.tableTwoErrorIndication()==12;
                        return check;
                    case RULE18:
                        check =  c23FormStaticFields.tableTwoErrorIndication()==12;
                        return check;
                    case RULE19:
                        check =  c23FormStaticFields.tableTwoErrorIndication()==12;
                        return check;
                    case RULE20:
                        check =  c23FormStaticFields.tableTwoErrorIndication()==12;
                        return check;
                    case RULE21:
                        check =  c23FormStaticFields.tableTwoErrorIndication()==12;
                        return check;
                    case RULE22:
                        check =  c23FormStaticFields.tableTwoErrorIndication()==12;
                        return check;
                    case RULE23:
                        check =  c23FormStaticFields.tableTwoErrorIndication()==12;
                        return check;
                    case RULE24:
                        check =  c23FormStaticFields.tableTwoErrorIndication()==8;
                        return check;
                    default:
                    return check;
                }
            case TABLE3:
                editFieldsExternal.goto_table3();
                switch (ruleSet){
                    case PASS:
                        check = c23FormStaticFields.tableThreeErrorIndication()==24;
                        return check;
                    case RULE1:
                        check = c23FormStaticFields.tableThreeErrorIndication()==22;
                        return check;
                    case RULE2:
                        check = c23FormStaticFields.tableThreeErrorIndication()==22;
                        return check;
                    case RULE3:
                        check = c23FormStaticFields.tableThreeErrorIndication()==24;
                        return check;
                    case RULE4:
                        check = c23FormStaticFields.tableThreeErrorIndication()==24;
                        return check;
                    case RULE5:
                        check = c23FormStaticFields.tableThreeErrorIndication()==24;
                        return check;
                    case RULE6:
                        check= c23FormStaticFields.tableThreeErrorIndication()==24;
                        return check;
                    case RULE7:
                        check =  c23FormStaticFields.tableThreeErrorIndication()==24;
                        return check;
                    case RULE8:
                        check =  c23FormStaticFields.tableThreeErrorIndication()==24;
                        return check;
                    case RULE9:
                        check =  c23FormStaticFields.tableThreeErrorIndication()==24;
                        return check;
                    case RULE10:
                        check =  c23FormStaticFields.tableThreeErrorIndication()==24;
                        return check;
                    case RULE11:
                        check =  c23FormStaticFields.tableThreeErrorIndication()==22;
                        return check;
                    case RULE12:
                        check =  c23FormStaticFields.tableThreeErrorIndication()==22;
                        return check;
                    case RULE13:
                        check =  c23FormStaticFields.tableThreeErrorIndication()==22;
                        return check;
                    case RULE14:
                        check =  c23FormStaticFields.tableThreeErrorIndication()==22;
                        return check;
                    case RULE15:
                        check =  c23FormStaticFields.tableThreeErrorIndication()==20;
                        return check;
                    case RULE16:
                        check =  c23FormStaticFields.tableThreeErrorIndication()==24;
                        return check;
                    case RULE17:
                        check =  c23FormStaticFields.tableThreeErrorIndication()==24;
                        return check;
                    case RULE18:
                        check =  c23FormStaticFields.tableThreeErrorIndication()==24;
                        return check;
                    case RULE19:
                        check =  c23FormStaticFields.tableThreeErrorIndication()==24;
                        return check;
                    case RULE20:
                        check =  c23FormStaticFields.tableThreeErrorIndication()==24;
                        return check;
                    case RULE21:
                        check =  c23FormStaticFields.tableThreeErrorIndication()==24;
                        return check;
                    case RULE22:
                        check =  c23FormStaticFields.tableThreeErrorIndication()==24;
                        return check;
                    case RULE23:
                        check =  c23FormStaticFields.tableThreeErrorIndication()==24;
                        return check;
                    case RULE24:
                        check =  c23FormStaticFields.tableThreeErrorIndication()==24;
                        return check;
                    default:
                        return check;
                }

            case TABLE4:
                editFieldsExternal.goto_table4();
                switch (ruleSet){
                    case PASS:
                        check = c23FormStaticFields.tableFourErrorIndication()==16;
                        return check;
                    case RULE1:
                        check = c23FormStaticFields.tableFourErrorIndication()==16;
                        return check;
                    case RULE2:
                        check = c23FormStaticFields.tableFourErrorIndication()==16;
                        return check;
                    case RULE3:
                        check = c23FormStaticFields.tableFourErrorIndication()==16;
                        return check;
                    case RULE4:
                        check = c23FormStaticFields.tableFourErrorIndication()==16;
                        return check;
                    case RULE5:
                        check = c23FormStaticFields.tableFourErrorIndication()==16;
                        return check;
                    case RULE6:
                        check= c23FormStaticFields.tableFourErrorIndication()==16;
                        return check;
                    case RULE7:
                        check =  c23FormStaticFields.tableFourErrorIndication()==16;
                        return check;
                    case RULE8:
                        check =  c23FormStaticFields.tableFourErrorIndication()==16;
                        return check;
                    case RULE9:
                        check =  c23FormStaticFields.tableFourErrorIndication()==16;
                        return check;
                    case RULE10:
                        check =  c23FormStaticFields.tableFourErrorIndication()==16;
                        return check;
                    case RULE11:
                        check =  c23FormStaticFields.tableFourErrorIndication()==16;
                        return check;
                    case RULE12:
                        check =  c23FormStaticFields.tableFourErrorIndication()==16;
                        return check;
                    case RULE13:
                        check =  c23FormStaticFields.tableFourErrorIndication()==16;
                        return check;
                    case RULE14:
                        check =  c23FormStaticFields.tableFourErrorIndication()==16;
                        return check;
                    case RULE15:
                        check =  c23FormStaticFields.tableFourErrorIndication()==16;
                        return check;
                    case RULE16:
                        check =  c23FormStaticFields.tableFourErrorIndication()==14;
                        return check;
                    case RULE17:
                        check =  c23FormStaticFields.tableFourErrorIndication()==14;
                        return check;
                    case RULE18:
                        check =  c23FormStaticFields.tableFourErrorIndication()==14;
                        return check;
                    case RULE19:
                        check =  c23FormStaticFields.tableFourErrorIndication()==14;
                        return check;
                    case RULE20:
                        check =  c23FormStaticFields.tableFourErrorIndication()==14;
                        return check;
                    case RULE21:
                        check =  c23FormStaticFields.tableFourErrorIndication()==16;
                        return check;
                    case RULE22:
                        check =  c23FormStaticFields.tableFourErrorIndication()==16;
                        return check;
                    case RULE23:
                        check =  c23FormStaticFields.tableFourErrorIndication()==16;
                        return check;
                    case RULE24:
                        check =  c23FormStaticFields.tableFourErrorIndication()==16;
                        return check;
                    default:
                        return check;
                }

            case TABLE5:
                editFieldsExternal.goto_table5();
                switch (ruleSet){
                    case PASS:
                        check = c23FormStaticFields.tableFiveErrorIndication()==19;
                        return check;
                    case RULE1:
                        check = c23FormStaticFields.tableFiveErrorIndication()==19;
                        return check;
                    case RULE2:
                        check = c23FormStaticFields.tableFiveErrorIndication()==19;
                        return check;
                    case RULE3:
                        check = c23FormStaticFields.tableFiveErrorIndication()==19;
                        return check;
                    case RULE4:
                        check = c23FormStaticFields.tableFiveErrorIndication()==19;
                        return check;
                    case RULE5:
                        check = c23FormStaticFields.tableFiveErrorIndication()==19;
                        return check;
                    case RULE6:
                        check= c23FormStaticFields.tableFiveErrorIndication()==19;
                        return check;
                    case RULE7:
                        check =  c23FormStaticFields.tableFiveErrorIndication()==19;
                        return check;
                    case RULE8:
                        check =  c23FormStaticFields.tableFiveErrorIndication()==19;
                        return check;
                    case RULE9:
                        check =  c23FormStaticFields.tableFiveErrorIndication()==19;
                        return check;
                    case RULE10:
                        check =  c23FormStaticFields.tableFiveErrorIndication()==19;
                        return check;
                    case RULE11:
                        check =  c23FormStaticFields.tableFiveErrorIndication()==19;
                        return check;
                    case RULE12:
                        check =  c23FormStaticFields.tableFiveErrorIndication()==19;
                        return check;
                    case RULE13:
                        check =  c23FormStaticFields.tableFiveErrorIndication()==19;
                        return check;
                    case RULE14:
                        check =  c23FormStaticFields.tableFiveErrorIndication()==19;
                        return check;
                    case RULE15:
                        check =  c23FormStaticFields.tableFiveErrorIndication()==19;
                        return check;
                    case RULE16:
                        check =  c23FormStaticFields.tableFiveErrorIndication()==19;
                        return check;
                    case RULE17:
                        check =  c23FormStaticFields.tableFiveErrorIndication()==19;
                        return check;
                    case RULE18:
                        check =  c23FormStaticFields.tableFiveErrorIndication()==19;
                        return check;
                    case RULE19:
                        check =  c23FormStaticFields.tableFiveErrorIndication()==19;
                        return check;
                    case RULE20:
                        check =  c23FormStaticFields.tableFiveErrorIndication()==19;
                        return check;
                    case RULE21:
                        check =  c23FormStaticFields.tableFiveErrorIndication()==15;
                        return check;
                    case RULE22:
                        check =  c23FormStaticFields.tableFiveErrorIndication()==3;
                        return check;
                    case RULE23:
                        check =  c23FormStaticFields.tableFiveErrorIndication()==17;
                        return check;
                    case RULE24:
                        check =  c23FormStaticFields.tableFiveErrorIndication()==19;
                        return check;
                    default:
                        return check;
                }

            default:
                return check;
        }
    }
}
