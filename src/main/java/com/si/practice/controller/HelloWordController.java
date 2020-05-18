package com.si.practice.controller;


//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("order")
public class HelloWordController {

//    @GetMapping(value = "/hello/")
//    public String hello(@RequestParam("name") String name){
//
//        return "hello" + name;
//    }
//
//    @GetMapping(value = "/test/")
//    public String test(){
//
//        return "hello";
//    }

    public static void main(String[] args) {

        int count = foreach();
        int a = 0;
    }

    private static int foreach(){
        int size =  10;
        int jumpSize = 2;
        int rightSize = 2;
        int count = 0;
        for (int i = 0 ; i < 10 ; i++){
            count += 1;
            if (i == 1){
                i += jumpSize;
                if (i >= size - rightSize){
                    return count;
                }
//                for ( ; i < 5; i++){
//                    if (i == 4){
//                        break;
//                    }
//                }
            }
        }
        return count;
    }
}
