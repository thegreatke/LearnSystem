package com.online.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {


    @RequestMapping(value = "/form.html", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    String form() {

        return "form";
    }


    @RequestMapping(value = "/info", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    String info() {

        return "info";
    }

    @RequestMapping(value = "/calendar.html", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    String calendar() {

        return "calendar";
    }

    @RequestMapping(value = "/404.html", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    String error() {

        return "404";
    }


    @RequestMapping(value = "/table-list.html", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    String table_list() {

        return "table-list";
    }

    @RequestMapping(value = "/table-list-img.html", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    String table_list_img() {

        return "table-list-img";
    }

    @RequestMapping(value = "/liuyanban.html", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    String liuyanban() {

        return "liuyanban";
    }

}
