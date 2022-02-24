package com.cskku.project;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Set;

import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.python.core.PyString;
import org.python.core.PyFloat;

public class PythonChecker {
    
    public static boolean check(Work work)  {
        
        Document document = work.getDocument();
        String functionName = work.getLaboratory().getFunctionName();
        Set<TestCase> testCases = work.getLaboratory().getTestCases();
        if(testCases==null) {
            return true;
        }
        
        boolean flagChecker = true;
        
        try {
            PythonInterpreter py_interpreter = new PythonInterpreter();
            InputStream inputStream = new ByteArrayInputStream(document.getContent());
            py_interpreter.execfile(inputStream);
            PyObject py_function = py_interpreter.get(functionName);
            for(var testCase:testCases) {
                PyObject output = getPyObject(testCase.getOutput(),testCase.getType());
                int nInput = testCase.getInputs().size();
                PyObject[] py_inputs = new PyObject[nInput];
                int i=0;
                for(var input:testCase.getInputs()) {
                    PyObject py_input = getPyObject(input.getValue(),input.getType());
                    py_inputs[i]=py_input;
                    i++;
                }
                PyObject result = py_function.__call__(py_inputs);
                System.out.println(result);
                System.out.println(output);
                if(! result.equals(output)) {
                    flagChecker = false;
                }
            }
            py_interpreter.close();
        } catch (Exception e) {
            return false;
        }
        
        
        return flagChecker;
    }
    
    private static PyObject getPyObject(String value,String type) {
        PyObject pyObject = null;
        if(type.equals("String")) {
            pyObject = new PyString(value);
        }else if(type.equals("Integer")) {
            pyObject = new PyInteger(Integer.parseInt(value));
        }else if(type.equals("Float")) {
            pyObject = new PyFloat(Float.parseFloat(value));
        }
        return pyObject;
    }
}