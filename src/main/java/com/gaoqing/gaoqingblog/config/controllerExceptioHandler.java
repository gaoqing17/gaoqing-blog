package com.gaoqing.gaoqingblog.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class controllerExceptioHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHander (HttpServletRequest request,Exception e) throws Exception {
        logger.error("Request URL:{},Exception:{}",request.getRequestURL(),e);

        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
            throw e;
        }


        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURL());
        mv.addObject("exception",e);
        mv.setViewName("error/error");
        return mv;
    }
}
