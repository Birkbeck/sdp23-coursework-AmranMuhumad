package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * A class that allows for the division of register values by one another.

 * @author Amran
 */

public class Divinstruction extends Instruction {
    private final RegisterName result;
    private final RegisterName source;

    public static final String OP_CODE = "div";

    public Divinstruction(String label, RegisterName result, RegisterName source) {
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }

    @Override
    public int execute(Machine m) {
        int value1 = m.getRegisters().get(result);
        int value2 = m.getRegisters().get(source);
        m.getRegisters().set(result, value1 / value2);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() { return getLabelString() + getOpcode() + " " + result + " " + source; }


    @Override
    public boolean equals(Object o) {
        if (o instanceof Divinstruction other) {
            return Objects.equals(this.label, other.label)
                    && Objects.equals(this.result, other.result)
                    && Objects.equals(this.source, other.source)
                    && this.OP_CODE == other.OP_CODE;
        }
        return false;
    }

    @Override
    public int hashCode() { return Objects.hash(label, result, source, OP_CODE); }

}



