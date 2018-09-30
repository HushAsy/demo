package org.hush.k8s.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.hush.api.IService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @description:
 * @author: hewater
 * @create: 2018-09-28 17:39
 **/
@Controller
@RequestMapping("/")
public class IndexController {

    @Reference(version = "1.0.0", group = "hush")
    private IService iService;

    @RequestMapping("index")
    public ModelAndView modelAndView(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hello");
        modelAndView.addObject(iService.selectAll());
        return modelAndView;
    }

}
