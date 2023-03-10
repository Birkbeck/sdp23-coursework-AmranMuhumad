package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * Representation of Multiplication class.
 * Executing multiple instructions.

 * @author Amran Muhumad
 */

public class Mulinstruction extends Instruction {
    private final RegisterName result;
    private final RegisterName source;

    public static final String OP_CODE = "mul";

    public Mulinstruction(String label, RegisterName result, RegisterName source) {
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }

    @Override
    public int execute(Machine m) {
        int value1 = m.getRegisters().get(result);
        int value2 = m.getRegisters().get(source);
        m.getRegisters().set(result, value1 * value2);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() { return getLabelString() + getOpcode() + " " + result + " " + source; }


    @Override
    public boolean equals(Object o) {
        if (o instanceof Mulinstruction other) {
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
