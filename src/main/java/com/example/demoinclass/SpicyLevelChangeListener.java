package com.example.demoinclass;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class SpicyLevelChangeListener implements ChangeListener {
    @Override
    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
        System.out.println("Changed it!");
        //Check the new number is a digit
        try{
            Integer.parseInt(newValue.toString());
        }
        catch (Exception e)
        {
            System.out.println(String.format("%s is not a number!",newValue));

        }


    }
}
