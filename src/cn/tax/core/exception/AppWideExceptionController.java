package cn.tax.core.exception;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

@ControllerAdvice
public class AppWideExceptionController {
	
	@ExceptionHandler(value={ConversionNotSupportedException.class,NoSuchRequestHandlingMethodException.class})
	public String sysException(Exception ex,Model model){
		model.addAttribute("error","(error"+ex.getMessage()+")");
		return "exception/500";
	}
}
