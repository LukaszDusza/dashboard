package com.dfsp.dashboard.app;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException, CannotCompileException, NoSuchMethodException {

        List<String> raport = new ArrayList<>();
        raport.add("Lukasz");
        raport.add("Marek");
        raport.add("Darek");
        raport.add("Zbigniew");

        Class clazz = generateClass();
        Object obj = clazz.newInstance();
        Method method = clazz.getDeclaredMethod("create");
        method.invoke(obj, raport);


    }

    public static Class generateClass() throws CannotCompileException {
        ClassPool pool = ClassPool.getDefault();
        CtClass cc = pool.makeClass("MainTable");

        StringBuffer method = new StringBuffer();
        //head method
        method.append("public void ")
                .append("create ")
                .append("(java.util.List<String> raport) {")
                //body method
                .append("raport.foreach(r -> java.lang.System.out.println(r));")
                .append("}");
        cc.addMethod(CtMethod.make(method.toString(), cc));
        return cc.toClass();
    }

}

