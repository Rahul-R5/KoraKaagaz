# Test Harness Specification Document

## Objective
- To provide an abstract class for the developers of different modules to create and manage tests.
- To discover and populate the different tests assemblies.
- Formatting the test results of different modules when tested and saving them in a loggerfile


## Interface
- The interface ITest contains all the methods needed to run a test case.
- The ITest interface is implemented with abstract class TestCase to implement getters and setters methods of the interface. 
- Each module testers have to extend the  abstract class TestCase to write their test case
- The tests sould be named in the following format for easier identification: [Testname]_Test.java
- The rests should be stored in a folder named "tests" in each module which gives us the names of the tests defined for that module


### Class Diagram for Interface 

![](https://i.imgur.com/gTvhn42.png)

### Function Elaboration ITest Interface 

- RUn 
``` java =
//return the description of the test case
public String getDescription(){}

```

- Run 
``` java =
//return the error, helpful in the case when a test case is failed
public String getError(){}

```

- Run 
``` java =
//return the catagory of the test case which is same as the moudle name of the test case  
public String getCatagory(){}
```

- Run 
``` java =
//set the description of the test case
public void setDescription(String description){}
```

- Run 
``` java =
//set the error occured int the test case 
public void setError(String error){}
```

- Run 
``` java =
//set the catagory of the test case 
public void setCategory(String category){}
```

- Run 
``` java =
//run method to run the the test case
public bool run(){
	//the method will compare the actual result with expected result 
	if(actualResult==expectedResult) return true;
	else {
		//set the error occured in the test case
		setError(error);
		return false
	}
}
```


## Test Harness 
- The tester can call the test harness class functions to run their tests 
- Tests can be run based on either test name, Module Name, priority level or all tests by calling respective functions
- Each Test has customized description of it's end result, purpose, error i.e the reason of failure.
- The tests have 3 levels of priority represented with an integer as 0,1,2 being low,medium,high respectively
- The each of the run functions of test harness function at the core of will call the run of the implemented test to get the boolean result 
- The number of successful and failed tests are tracked and the failed tests and the reason of failure along with the tracked numbers are logged to a results file using the logger object

### Function Elaboration Test Harness

-Run
``` java =
//To run all the tests irrespective of category
public void runAll(){
	foreach(string testName in testNames){
	runbyName(testName);
}
```

-Run
```java = 
// To run the tests belonging to a particular category with no specification.
public void runByCategory(Category category){
	List<string> tests = new List<string>;
	// populate the tests names
	foreach(string c in tests.category){
		//run the test case if test belongs to the given category
		if(c == category){
			//run the test case
		}
		//else move to the next test in the list 
	}

}
```

```java =
// To run the test case from high priority (priority level 2) to low priority (priority level 2)
public void runBypriority(){
	List<string> tests_1 = new List<string>;
	List<string> tests_2 = new List<string>;
	List<string> tests_3 = new List<string>;
	
	//store the tests in list according to theri proirity level
	foreach(string name in tests){
		if(test.priority == 0)tests_1.add(name);
		if(test.priority == 1)tests_2.add(name);
		if(test.priority == 2)tests_3.add(name);
	}
	//run all the test cases in list tests_3 
	//run all the test cases in list tests_2
	//run all the test cases in list tests_1 
}

```

```java =
//To run a particular test by specifying the name of the test given in the description
public bool runByName(String testName){
	// create an instance of the test and then run the test. We will get the boolean value of either being a success or failure
	Object test = Class.forName(testName).newInstance();
	bool result = test.run()
	return result;
}
```
- STRING PATH: The result of the tests run must be stored. This path of result folder will be mentioned and stored in the form of a string.

- Set Save Path: This will allow to set the path to where we must save the results of the run

- Get Save Path: This will allow to get the path of where the dev want the result to be stored, thus allowing the result display to vary


### Class Diagram

![](https://i.imgur.com/0DoSP9S.png)


## Test Cases:
- The dev's who will be working on preparing their respective tests will be explained here.
- By extending the the abstract class TestCase elaborated earlier, the dev can create their test classes.
- The examples given here are the test case classes Enqueue Test, Dequeue Test, Profile Image Test. Dev can prepare their own test classes in similar manner but should be saved in the format mentioned at the top.
- Dev can then can use the Test harness class to run the tests according to their usuage and convinence.


## Work Distribution
-Abhishek Saran: ITest Interface, Test Harness methods runByName and runAll

