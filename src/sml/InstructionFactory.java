package sml;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.function.Supplier;

public class InstructionFactory {
    public Instruction creatIns(String opcode, String label, Supplier<String> s) {
        String className = "sml.instruction." + opcode.substring(0, 1).toUpperCase() + opcode.substring(1) + "Instruction";
        System.out.println("class name " + className);
        try {
            Class<?> c = Class.forName(className);
            Constructor<?> ins = c.getConstructors()[0];
            Parameter[] paramType = ins.getParameters();
            List<Object> objList = new LinkedList<>();
            objList.add(label);
            for (int i = 1; i < paramType.length; i++) {
                String r = s.get();
                if (paramType[i].getType().getName().equals("sml.RegisterName")) {
                    objList.add(Registers.Register.valueOf(r));
                } else if (paramType[i].getType().getName().equals("int")) {
                    objList.add(Integer.parseInt(r));
                } else {
                    objList.add(r);
                }
            }
            return (Instruction) ins.newInstance(objList.toArray());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}