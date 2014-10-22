package learningresourcefinder.controller;

import java.util.List;

import learningresourcefinder.model.Resource;
import learningresourcefinder.service.ResourceListPager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResourceMoreController {

    @Autowired ResourceListPager resourceListPager;
    
    @RequestMapping("/ajax/getmoreresources")
    public ModelAndView getMoreResources(@RequestParam("tokenlistofresources") String tokenListOfResources) {
        List<Resource> listResources = resourceListPager.getMoreResources(tokenListOfResources);
        return new ModelAndView("moreresources").addObject("moreResourceList", listResources).addObject("tokenListOfResources", tokenListOfResources);
    }
}