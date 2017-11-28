package others.incture.testcases;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.testng.IInvokedMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.incture.annotations.Testinfo;
import com.incture.annotations.Testinfo.Locationenum;

import others.unde.annotations.TestExample;
import others.unde.annotations.RunTest3.MyAnnotation;
import others.unde.annotations.RunTest3.X;

public class TestNGClass {

	/*@Testinfo(TestCaseId = "1234", getlocation = Locationenum.LOCAL)
	public void tc1(@Optional String TestCaseId2,@Optional Locationenum location){
		System.out.println("tc1 id is "+TestCaseId2 + " & the location is "+location);
		
	}
	
	
	@Testinfo( TestCaseId = "1235", getlocation = Locationenum.REMOTE)
	public void tc2(@Optional String TestCaseId1,@Optional Locationenum location){
		System.out.println("tc2 id is "+TestCaseId1+ " & the location is "+location);
		
	}*/
	
	@Test
	@Testinfo(TestCaseId = "1234", getlocation = Locationenum.LOCAL)
	public void TestNGtc1(String TestCaseId2,Locationenum location){
		System.out.println("tc1 id is "+TestCaseId2 + " & the location is "+location);
		
	}
	
	@Test
	@Testinfo( TestCaseId = "1235", getlocation = Locationenum.REMOTE)
		public void TestNGtc2(String TestCaseId2,Locationenum location){
		System.out.println("tc1 id is "+TestCaseId2 + " & the location is "+location);
		
	}
	
	
	
	
	
	 public static void invokeMethods() throws InvocationTargetException, IllegalAccessException, IllegalArgumentException, InstantiationException {
		 
		 Class<TestNGClass> obj = TestNGClass.class;
		 
	        for (Method m : TestNGClass.class.getDeclaredMethods()) {
	        	//System.out.println(m.getName());
	        	Testinfo annotation = m.getAnnotation(Testinfo.class);
	            if (annotation != null) {
	                //m.invoke(null, annotation.TestCaseId());
	            	m.invoke(obj.newInstance(), annotation.TestCaseId(),annotation.getlocation());
	            
	            }
	        }
	    }
	 
	 public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, IllegalArgumentException, InstantiationException {
		 System.out.println();
		 invokeMethods();
	}
}
