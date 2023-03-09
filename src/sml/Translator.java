package sml;

import sml.instruction.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

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
        switch (opcode) {
            case AddInstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                return new AddInstruction(label, Register.valueOf(r), Register.valueOf(s));
            }

            // TODO: add code for all other types of instructions

            case Divinstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                return new Divinstruction(label, Register.valueOf(r), Register.valueOf(s));
            }

            case Subinstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                return new Subinstruction(label, Register.valueOf(r), Register.valueOf(s));
            }

            case Mulinstruction.OP_CODE -> {
                String r = scan();
                String s = scan();
                return new Mulinstruction(label, Register.valueOf(r), Register.valueOf(s));
            }

            case Movinstruction.OP_CODE -> {
                String r = scan();
                int val = Integer.parseInt(scan());
                return new Movinstruction(label, Register.valueOf(r), val);
            }

            case Jnzinstruction.OP_CODE -> {
                String r = scan();
                String val = String.valueOf(Integer.parseInt(scan()));
                return new Jnzinstruction(label, Register.valueOf(r), val);
            }

            case Outinstruction.OP_CODE -> {
                String r = scan();
                String val = String.valueOf(Integer.parseInt(scan()));
                return new Outinstruction(label, Register.valueOf(r));
            }





            // TODO: Then, replace the switch by using the Reflection API







            // TODO: Next, use dependency injection to allow this machine class
            //       to work with different sets of opcodes (different CPUs)





























                    default -> {
                System.out.println("Unknown instruction: " + opcode);
            }
        }
        return null;
    }


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
