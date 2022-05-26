package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddressController {

    @GetMapping("/Api/address")
    public String address() {
        System.out.println("카카오 API 테스트");

        return "/Api/address";
    }
}