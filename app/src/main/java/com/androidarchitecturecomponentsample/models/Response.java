package com.androidarchitecturecomponentsample.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Shubham Gupta
 */

public class Response {
    private List<IndentDetails> indentDetails;

    public List<IndentDetails> getIndentDetails ()
    {
        return indentDetails;
    }

    public void setIndentDetails (List<IndentDetails> indentDetails)
    {
        this.indentDetails = indentDetails;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [indentDetails = "+indentDetails+"]";
    }
}
