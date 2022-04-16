package it.unibo.oop.lab.mvc;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ControllerImpl implements Controller{

    private final List<String> allString= new LinkedList<>();
    private String nextString;
    

    @Override
    public void setNextStringToPrint(String string) {
        this.nextString = Objects.requireNonNull(string, "Error: value null");
    }

    @Override
    public String getNextStringToPrint() {
        return this.nextString;
    }

    @Override
    public List<String> getPrintedStringsHistory() {
        return allString;
    }

    @Override
    public void printCurrentString() {
        if (this.nextString == null) {
            throw new IllegalStateException("Error: value nullt");
        }else {
            allString.add(this.nextString);
            System.out.println(this.nextString);
        }
        
    }

}
