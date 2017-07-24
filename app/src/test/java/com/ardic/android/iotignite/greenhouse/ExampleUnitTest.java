package com.ardic.android.iotignite.greenhouse;

import android.util.Log;

import com.ardic.android.iotignite.greenhouse.activities.SignUpActivity;
import com.ardic.android.iotignite.greenhouse.utils.ValidationResult;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void whichOneisTrue(){

        ValidationResult r1 = ValidationResult.OK;
        ValidationResult r2 = ValidationResult.INCORRECT_MAIL;


        if(r1 == r2){
            System.out.println("AWESOME TRUE");
        }else {
            System.out.println("AWESOME FALSE");
        }


        if(r1.equals(r2)){
            System.out.println("AWESOME TRUE EQUAL");
        }else {
            System.out.println("AWESOME FALSE EQUAL");
        }



    }
}