package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;


/**
 * Out instruction class representation.
 * The outcome of this class will be printing content register on console.
 * @author Amran
 */

public class Outinstruction extends Instruction {

    private final RegisterName result;

    public static final String OP_CODE = "Out";

    public Outinstruction(String label, RegisterName result) {
        super(label, OP_CODE);
        this.result = result;
    }

    @Override
    public int execute(Machine machine) {
        System.out.println(machine.getRegisters().get(result));
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Outinstruction other) {
            return Objects.equals(this.label, other.label)
                    && Objects.equals(this.result, other.result)
                    && this.opcode == other.opcode;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, result, opcode);
    }
}
