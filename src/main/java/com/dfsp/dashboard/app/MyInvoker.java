package com.dfsp.dashboard.app;

import com.dfsp.dashboard.entities.RaportTotal;

public class MyInvoker extends RaportTotal {


//    public static Method getMethodFromClass (String className, String methodName ) {
//
//        try {
//            return Class.forName(className).getMethod(methodName);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            System.out.println("Class not found!");
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//            System.out.println("Method not found!");
//        }
//        return null;
//
//    }
//
//    public static Method[] getMethodsTableFromClass (String className) {
//
//        Method[] methods = null;
//
//        try {
//            methods = Class.forName(className).getMethods();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//            System.out.println("Class not found!");
//        }
//        return methods;
//
//    }
//
//    public static Field[] fieldsFromClass(String className) {
//        Field[] declaredFields = null;
//        try {
//            declaredFields = Class.forName(className).getDeclaredFields();
//
//        }  catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return declaredFields;
//    }
//
//    //todo pobieranie metod z klasy i towrzenie nowego obiektu automatycznie. nieskonczone
//    public static RaportTotal newObject(RaportTotal r, String s) {
//
//    //     Method[] methods = getMethodsTableFromClass(className);
//        //    try {
//        //        methods[0].invoke(r);
//        //    } catch (IllegalAccessException e) {
//        //        e.printStackTrace();
//        //    } catch (InvocationTargetException e) {
//        //       e.printStackTrace();
//        //    }
//        return  new RaportTotal(
//                s,
//                new BigDecimal(0),
//                r.getNrWewnAgenta(),
//                r.getNrKnfAgenta(),
//                r.getUzytkownik(),
//                r.getNrKnfUzytkownika(),
//                r.getKanalDystrybucji(),
//                r.getPoziom1KNF(),
//                r.getPoziom2(),
//                r.getNazwaSektoraSprzedazy(),
//                r.getPoziom2KNF(),
//                r.getPoziom3(),
//                r.getDyrektorSektora(),
//                r.getPoziom3KNF(),
//                r.getPoziom4(),
//                r.getSegmentSprzedazy(),
//                r.getPoziom4knf(),
//                r.getPoziom5(),
//                r.getDrEkspertSegmentu(),
//                r.getPoziom5knf(),
//                r.getPoziom6(),
//                r.getMiasto(),
//                r.getPoziom6knf(),
//                r.getPoziom7(),
//                r.getMzaKierownikZespolu(),
//                r.getPoziom7knf(),
//                0
//        );
//
//     //   return reportDas;
//    }

}

