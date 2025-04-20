package com.example.demo.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @RequestMapping(ERROR_PATH)
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String errorMessage = "An error occurred";
        String errorCode = "500";
        
        if (status != null) {
            errorCode = status.toString();
            switch (Integer.parseInt(errorCode)) {
                case 400: errorMessage = "Bad Request"; break;
                case 401: errorMessage = "Unauthorized"; break;
                case 403: errorMessage = "Forbidden - You don't have permission to access this resource"; break;
                case 404: errorMessage = "Page Not Found"; break;
                case 500: errorMessage = "Internal Server Error"; break;
                default: errorMessage = "An unexpected error occurred"; break;
            }
        }
        
        model.addAttribute("errorCode", errorCode);
        model.addAttribute("errorMessage", errorMessage);
        
        return "error";
    }

    public String getErrorPath() {
        return ERROR_PATH;
    }
} 