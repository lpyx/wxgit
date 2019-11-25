package com.groovy;

import org.codehaus.groovy.jsr223.GroovyScriptEngineImpl;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.script.*;
import java.util.Date;

public class TestGroovy {

    static class A{
        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        private  String b;

    }

    public static void main(String[] args) {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine= factory.getEngineByName("groovy");


        Bindings bindings = engine.createBindings();
        A a = new A();

        JSONObject json = new JSONObject();

        try {
            json.put("aa","bb");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Date date = new Date();
        Object s = "dd";

        bindings.put("你好",json);

        bindings.put("date",date);
        bindings.put("s",s);
        bindings.put("a",a);



        Object sum = null;
        try {
            sum = engine.eval("${1+2} ",bindings);
            //sum = ((Invocable)engine).invokeFunction("s");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sum);
        System.out.println(json);
        System.out.println(s);
        System.out.println(a.getB());
    }
}
