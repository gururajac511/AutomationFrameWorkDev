package others.unde.annotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


public class RunTest2 {
	
	public static void main(String[] args) {
		int passed = 0, failed = 0, count = 0, ignore = 0;
	Class<TestExample> obj = TestExample.class;
	
	Method[] Method=obj.getDeclaredMethods();
	
	for(Method m:Method){
		System.out.println(m.getName());
		
		if (m.isAnnotationPresent(TesterInfo.class)) {
			System.out.println(m.getName() + " has  TesterInfo.class anootation");
			
			Annotation infoAnato=(Annotation) m.getAnnotation(TesterInfo.class);
			
			TesterInfo testerInfo = (TesterInfo) infoAnato;

			System.out.printf("%nPriority :%s", testerInfo.priority());
			System.out.printf("%nCreatedBy :%s", testerInfo.createdBy());
			System.out.printf("%nTags :");

			int tagLength = testerInfo.tags().length;
			for (String tag : testerInfo.tags()) {
				if (tagLength > 1) {
					System.out.print(tag + ", ");
				} else {
					System.out.print(tag);
				}
				tagLength--;
			}

			System.out.printf("%nLastModified :%s%n%n", testerInfo.lastModified());

			
			
			
			
		}
		if (m.isAnnotationPresent(Test.class)){
			System.out.println(m.getName() + " has  Test.class anootation");


			Annotation annotation = m.getAnnotation(Test.class);
			Test test = (Test) annotation;

			// if enabled = true (default)
			if (test.enabled()) {

			  try {
				m.invoke(obj.newInstance());
				System.out.printf("%s - Test '%s' - passed %n", ++count, m.getName());
				passed++;
			  } catch (Throwable ex) {
				System.out.printf("%s - Test '%s' - failed: %s %n", ++count, m.getName(), ex.getCause());
				failed++;
			  }

			} else {
				System.out.printf("%s - Test '%s' - ignored%n", ++count, m.getName());
				ignore++;
			}

		
		}
		
	}
	
	
		
		
	}

}
