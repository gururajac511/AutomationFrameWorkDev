package com.incture.annotations;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(java.lang.annotation.ElementType.METHOD)
public @interface Testinfo {
	
	public enum Locationenum {
		   LOCAL,REMOTE
		}
	
String TestCaseId();

Locationenum getlocation(); 
	
}
