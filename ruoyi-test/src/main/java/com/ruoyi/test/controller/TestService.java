package com.ruoyi.test.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.common.core.controller.BaseController;
import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/test")
public class TestService extends BaseController
{
    private String prefix = "test";
    
     @GetMapping("/test")
    public String helloTest()
    {
        return prefix + "/view";
    }
}