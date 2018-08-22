package com.xh.comm.service;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;

public class LogAspect {
	 private Logger logger = Logger.getLogger(LogAspect.class); 
     
	    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {  
	        logger.warn("Beginning method: " + joinPoint.toLongString());  
	        long startTime = System.currentTimeMillis();  
	        Object result; 
	        try{  
	            result = joinPoint.proceed();  
	        }finally{  
	        	logger.warn("Method invocation time: " + (System.currentTimeMillis() - startTime) + " ms.");             
	        }  
	        return result; 
	   } 
	     
}
