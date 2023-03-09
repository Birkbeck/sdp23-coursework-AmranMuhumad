package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 *  Jnz instruction class representation.
 *  If the register's contents are not zero, the Jnz instruction will transfer control to the location given in label.
 * @author Amran
 */
public class Jnzinstruction extends Instruction {

        private RegisterName result;
        private String jmpLabel;

        public static final String OP_CODE = "jnz";

    public Jnzinstruction(String label, RegisterName result, String jmpLabel) {
        super(label, OP_CODE);
        this.result = result;
        this.jmpLabel = String.valueOf(jmpLabel);
    }
    @Override
    public int execute (Machine machine) {
        int value = machine.getRegisters().get(result);
        if (value != 0) {
             return machine.getLabels().getAddress(jmpLabel);
        }
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
        public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + jmpLabel;
    }

        @Override
        public boolean equals(Object o) {
        if (o instanceof Jnzinstruction other) {
            return Objects.equals(this.label, other.label)
                    && Objects.equals(this.result, other.result)
                    && Objects.equals(this.jmpLabel, other.jmpLabel)
                    && this.opcode == other.opcode;
         }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, result, jmpLabel, opcode);
    }
 }
