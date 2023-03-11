package sml;

import sml.instruction.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static sml.Registers.Register;

/**
 * This class is a Java class named Translator that converts SML code into a list of SML commands.
 * Provides a function Object() { [native code] } that accepts a file name and a readAndTranslate method that accepts two inputs: a Labels object and a ListInstruction> object.
 * <p>
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 *
 * @author .Amran..
 */
public final class Translator {

    private final String fileName; // source file of SML code

    // line contains the characters in the current line that's not been processed yet
    private String line = "";

    public Translator(String fileName) {
        this.fileName =  fileName;
    }

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"

    public void readAndTranslate(Labels labels, List<Instruction> program) throws IOException {
        try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            labels.reset();
            program.clear();

            // Each iteration processes line and reads the next input line into "line"
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String label = getLabel();

                Instruction instruction = getInstruction(label);
                if (instruction != null) {
                    if (label != null)
                        labels.addLabel(label, program.size());
                    program.add(instruction);
                }
            }
        }
    }

    /**
     * Translates the current line into an instruction with the given label
     *
     * @param label the instruction label
     * @return the new instruction
     * <p>
     * The input line should consist of a single SML instruction,
     * with its label already removed.
     */
    private Instruction getInstruction(String label) {
        if (line.isEmpty())
            return null;

        String opcode = scan();


        // TODO: Then, replace the switch by using the Reflection API

        String className = "sml.instruction." + opcode.substring(0, 1).toUpperCase() + opcode.substring(1) + "Instruction";
        System.out.println("class name " + className);
        try {
            Class<?> c = Class.forName(className);
            Constructor<?> ins = c.getConstructors()[0];
            Parameter[] paramType = ins.getParameters();
            List<Object> objList = new LinkedList<>();
            objList.add(label);
            for (int i = 1; i < paramType.length; i++) {
                String r = scan();
                if (paramType[i].getType().getName().equals("sml.RegisterName")) {
                    objList.add(Register.valueOf(r));
                } else if (paramType[i].getType().getName().equals("int")) {
                    objList.add(Integer.parseInt(r));
                } else {
                    objList.add(r);
                }
            }
            return (Instruction) ins.newInstance(objList.toArray());
        } catch (Exception e) {
        }
        return null;
    }

            // TODO: Next, use dependency injection to allow this machine class
            //       to work with different sets of opcodes (different CPUs)



    private String getLabel() {
        String word = scan();
        if (word.endsWith(":"))
            return word.substring(0, word.length() - 1);

        // undo scanning the word
        line = word + " " + line;
        return null;
    }

    /*
     * Return the first word of line and remove it from line.
     * If there is no word, return "".
     */
    private String scan() {
        line = line.trim();

        for (int i = 0; i < line.length(); i++)
            if (Character.isWhitespace(line.charAt(i))) {
                String word = line.substring(0, i);
                line = line.substring(i);
                return word;
            }

        return line;
    }
}