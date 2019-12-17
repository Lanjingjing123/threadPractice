package com.csii.ljj.thread;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void test(){
        String a = "1";
        switch (a){
            case "11":
                a = "11";
                break;
            case "10":
                a = "10";
                break;
            case "1":
                a = "2";
                break;
        }

        System.out.println(a);
    }

    @Test
    public void test2(){
        BigDecimal a = new BigDecimal(2);

        BigDecimal b = a.setScale(2);

        System.out.println("a="+a);
        System.out.println("b="+b);
    }

    @Test
    public void test3(){
        String cd = "9013010055155353";
        String a = "9013010055155353123sxx";
        String b = "629013010055155353";

        String regx = "^(9013010055155353)(.*)";
        Pattern p = Pattern.compile(regx);
        Matcher m = p.matcher(a);
        if (m.find()){
            System.out.println(m.group());
        }else{
            System.out.println("not found");
        }


    }
}
