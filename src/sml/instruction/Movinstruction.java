package sml.instruction;

import com.sun.jdi.Value;
import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * Move instruction class representation.
 * After moving and storing value into the register, the move instruction class will be produced.
 * @author Amran
 */
public class Movinstruction extends Instruction {
    private final RegisterName result;

    private int value = 0;
    public static final String OP_CODE = "mov";

    public Movinstruction(String label, RegisterName result, int source) {
        super(label, OP_CODE);
        this.result = result;
        this.value = value;
    }
    @Override
    public int execute(Machine Machine) {
        Machine.getRegisters().set(result, value);
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + value;
    }
    @Override
    public boolean equals(Object o) {
        if (o instanceof Movinstruction other) {
            return Objects.equals(this.label, other.label)
                    && Objects.equals(this.result, other.result)
                    && Objects.equals(this.value, other.value)
                    && this.OP_CODE == other.opcode;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, result, value, opcode);
    }
}
