/**
 * @author        Abhishek Saran
 * @author        Manas Sanjay
 * @module        Infrastructure module 
 * @team          Test Harness
 * @description   Test Harness main class
 * @summary       To provide functionality to tester to run test case sets in various ways- 
                  Methods such as runAll(), runByName(), runByCategroy() and run by  Priority() are provided. 
 */
package infrastructure.validation.testing;
import java.io.File;
import java.util.ArrayList;



public class TestHarness{
  
  private String savePath;
  
  void setSavePath(String path){
    this.savePath=path;
  }
  
  String getSavePath(){
    return this.savePath;
  }

   

  
  /* 
  * Manas Sanjay may start righting his two methods runByCategroy() and run by Priority() from here. 
  * The guideline to avoid merge conflicts is as follows-
  * The spacing must follow a tab being two spaces otherwise identation may get messy with tabs + spaces combination.
  * If one is using file editor gedit, then tab width has to be 2 spaces with use spaces option enabled. 
  * Please don't edit anything beyond the space alloted to you otherwise we might get into a messy merge conflict.
  * 
  * For your two methods and their javadocs, 90 lines of code space(line no 38 - line no 127) is alloted to you. 
  * In case A) you need more lines to be alloted or B) there is any need to make some changes outside the alloted space, 
  * A pull request has to be made on my branch.
  * I'll merge your branch and A) modify my branch to make requested changes or B) give some more lines of code space in my branch. 
  * You need to then fetch my branch and again make a pull request on my branch for final merge.
  */
  //Manas's code starts from here.




  void runByCategroy(String Category){
    /* Parameters: Category -> The name of the category of tests to be run
                 : Tests    -> The List of tests for which the sorting by category of tests should be done in. This function will be applicable to all the tests mentioned in the lists}
                 */
    
    String path = "../../../" + Category + "/tests";

    File directoryPath = new File(path);

    String tests[] = directoryPath.list();

    for(int i =0; i<tests.length; i++){
      String[] arrOfStr = tests[i].split(".", 2); 
      String testClassName = arrOfStr[0];
      Class<?> clazz = Class.forName("testClassName");
      Object test = clazz.getDeclaredConstructor().newInstance();

      
      if(Category.equals(test.getCategory())){
        runByName(testClassName);
      }


    }

  }


  void runBypriority(int priority){

    ArrayList<String> Priority = new ArrayList<String>();

    String[][] Tests = getAllTests();
    
    for(int i=0;i<4;i++){
      for(String testname : Tests[i]){
        String[] arrOfStr = testname.split(".", 2); 
        String testClassName = arrOfStr[0];
        Class<?> clazz = Class.forName("testClassName");
        Object test = clazz.getConstructor().newInstance();
  
        
        if(priority.equals(test.getPriority())){
          Priority.add(testClassName);
        }
      }

    }
    
    for(String testname : Priority){
      runByName(testname);
    }
  }



  // Manas's code finishes here

 /**
  * Run all test cases of all modules one by one 
  */
  void runAll(){
    int Total_number_of_tests = 0;
    int Successful_Tests = 0;
    int Failed_Tests = 0;
    ArrayList<String> Failed_Tests_List = new ArrayList<String>();
    
    /*String InfrastructurePath = "../../../infrastructure/tests";
    File Infrastucture = new File(InfrastructurePath);
    String[] Tests1 = Infrastucture.list();

    String ContentPath = "../../../content/tests";
    File Content = new File(ContentPath);
    String[] Tests2 = Content.list();

    String NetworkPath = "../../../network/tests";
    File Network = new File(NetworkPath);
    String[] Tests3 = Network.list();

    String UIPath = "../../../UI/tests";
    File UI = new File(UIPath);
    String[] Tests4 = UI.list();*/

    String[][] Tests = getAllTests();

    

    for(int i =0; i < 4; i++){
      for(int j = 0; j<Tests[i].length;j++){
        boolean result = runByName(Tests[i][j]);
        if(result){
          Successful_Tests++;
        }
        else{
          Failed_Tests_List.add(Tests[i][j]);
          Failed_Tests++;

        }
        Total_number_of_tests++;
      }
    }



    /*//get all the test cases in infrastucture module
    File testFolderInfrastucture = new File("../../../infrastucture/tests");
    File[] listOfTests1 = testFolderInfrastucture.listFiles();
    System.out.println("Running all the test cases of infrastucture module...");
    for(File file : listOfTests1){
      if(file.isFile()){
        runByName(file.getName());
      }
    }
    
    //get all the test cases in networking module
    File testFolderNetworking = new File("../../../networking/tests");
    File[] listOfTests2 = testFolderNetworking.listFiles();
    System.out.println("Running all the test cases of networking module...");
    for(File file : listOfTests2){
      if (file.isFile()){
        runByName(file.getName());
      }
    }

    //get all the test cases in processing module
    File testFolderProcessing = new File("../../../processing/tests");
    File[] listOfTests3 = testFolderProcessing.listFiles();
    System.out.println("Running all the test cases of processing module...");
    for(File file : listOfTests3){
      if(file.isFile()){
        runByName(file.getName());
      }
    }
    
    //get all the test cases in UI module
    File testFolderUI = new File("../../../UI/tests");
    File[] listOfTests4 = testFolderUI.listFiles();
    System.out.println("Running all the test cases of UI module...");
    for(File file : listOfTests4){
      if(file.isFile()) {
        runByName(file.getName());
      }
    }
    */
}

public String[][] getAllTests(){
  String InfrastructurePath = "../../../infrastructure/tests";
    File Infrastucture = new File(InfrastructurePath);
    String[] Tests1 = Infrastucture.list();

    String ContentPath = "../../../content/tests";
    File Content = new File(ContentPath);
    String[] Tests2 = Content.list();

    String NetworkPath = "../../../network/tests";
    File Network = new File(NetworkPath);
    String[] Tests3 = Network.list();

    String UIPath = "../../../UI/tests";
    File UI = new File(UIPath);
    String[] Tests4 = UI.list();

    return new String [][] {Tests1, Tests2, Tests3, Tests4};

}
 
  
 /**
  * Boolean method to give result of the Test Case Class
  * @param testName    The absoulete path to file name of test case class including .java extension as a string
  * @return    <code>true</code> if the test case pass 
  *     and actual output is same as expected output; 
  *     <code>false</code> otherwise.
  */
  public boolean runByName(String testName){
    String[] arrOfStr = testName.split(".", 2); 
    String testClassName = arrOfStr[0];
    Class<?> clazz = Class.forName("testClassName");
    Object test = clazz.getDeclaredConstructor().newInstance();
    boolean result = test.run();
    
    //after saving the result using logger
    return result;
  }

}
