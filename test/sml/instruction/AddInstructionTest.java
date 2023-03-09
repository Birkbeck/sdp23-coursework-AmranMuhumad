package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class AddInstructionTest {
  private Machine machine;
  private Registers registers;

  @BeforeEach
  void setUp() {
    machine = new Machine(new Registers());
    registers = machine.getRegisters();
    //...
  }

  @AfterEach
  void tearDown() {
    machine = null;
    registers = null;
  }

  @Test
  void executeValid() {
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(11, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidTwo() {
    registers.set(EAX, -5);
    registers.set(EBX, 6);
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(1, machine.getRegisters().get(EAX));
  }
}

  class SubinstructionTest {
    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
      machine = new Machine(new Registers());
      registers = machine.getRegisters();
      //...
    }

    @AfterEach
    void tearDown() {
      machine = null;
      registers = null;
    }

    @Test
    void executeValid() {
      registers.set(EAX, 10);
      registers.set(EBX, 5);
      Instruction instruction = new Subinstruction(null, EAX, EBX);
      instruction.execute(machine);
      Assertions.assertEquals(15, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
      registers.set(EAX, -10);
      registers.set(EBX, 5);
      Instruction instruction = new Subinstruction(null, EAX, EBX);
      instruction.execute(machine);
      Assertions.assertEquals(-5, machine.getRegisters().get(EAX));
    }
  }

class DivinstructionTest {
  private Machine machine;
  private Registers registers;

  @BeforeEach
  void setUp() {
    machine = new Machine(new Registers());
    registers = machine.getRegisters();
    //...
  }

  @AfterEach
  void tearDown() {
    machine = null;
    registers = null;
  }

  @Test
  void executeValid() {
    registers.set(EAX, 8);
    registers.set(EBX, 5);
    Instruction instruction = new Divinstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(13, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidTwo() {
    registers.set(EAX, -8);
    registers.set(EBX, 5);
    Instruction instruction = new Divinstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(-3, machine.getRegisters().get(EAX));
  }
}

class MulinstructionTest {
  private Machine machine;
  private Registers registers;

  @BeforeEach
  void setUp() {
    machine = new Machine(new Registers());
    registers = machine.getRegisters();
    //...
  }

  @AfterEach
  void tearDown() {
    machine = null;
    registers = null;
  }

  @Test
  void executeValid() {
    registers.set(EAX, 7);
    registers.set(EBX, 5);
    Instruction instruction = new Mulinstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(12, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidTwo() {
    registers.set(EAX, -7);
    registers.set(EBX, 5);
    Instruction instruction = new Mulinstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(-2, machine.getRegisters().get(EAX));
  }
}

class MovinstructionTest {
  private Machine machine;
  private Registers registers;

  @BeforeEach
  void setUp() {
    machine = new Machine(new Registers());
    registers = machine.getRegisters();
    //...
  }

  @AfterEach
  void tearDown() {
    machine = null;
    registers = null;
  }

  @Test
  void executeValid() {
    registers.set(EAX, 9);
    registers.set(EBX, 5);
    Instruction instruction = new Movinstruction(null, EAX, EBX.ordinal());
    instruction.execute(machine);
    Assertions.assertEquals(14, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidTwo() {
    registers.set(EAX, -9);
    registers.set(EBX, 5);
    Instruction instruction = new Movinstruction(null, EAX, EBX.ordinal());
    instruction.execute(machine);
    Assertions.assertEquals(-4, machine.getRegisters().get(EAX));
  }
}

class OutinstructionTest {
  private Machine machine;
  private Registers registers;

  @BeforeEach
  void setUp() {
    machine = new Machine(new Registers());
    registers = machine.getRegisters();
    //...
  }

  @AfterEach
  void tearDown() {
    machine = null;
    registers = null;
  }

  @Test
  void executeValid() {
    registers.set(EAX, 11);
    registers.set(EBX, 5);
    Instruction instruction = new Outinstruction(null, EAX);
    instruction.execute(machine);
    Assertions.assertEquals(16, machine.getRegisters().get(EAX));
  }

  @Test
  void executeValidTwo() {
    registers.set(EAX, -11);
    registers.set(EBX, 5);
    Instruction instruction = new Outinstruction(null, EAX);
    instruction.execute(machine);
    Assertions.assertEquals(-6, machine.getRegisters().get(EAX));
  }
}

class JnzinstructionTest {
  private Machine machine;
  private Registers registers;

  @BeforeEach
  void setUp() {
    machine = new Machine(new Registers());
    registers = machine.getRegisters();
    //...
  }

  @AfterEach
  void tearDown() {
    machine = null;
    registers = null;
  }

  @Test
  void executeJNZ() {
    registers.set(EAX, 13);
    machine.getLabels().addLabel("EBX", 5);
    Instruction instruction = new Jnzinstruction(null, EAX, "EBX");
    Assertions.assertEquals(18, instruction.execute(machine));
  }

  @Test
  void executeJNZero() {
    registers.set(EAX, -13);
    machine.getLabels().addLabel("EBX", 5);
    Instruction instruction = new Jnzinstruction(null, EAX, "EBX");
    Assertions.assertEquals(-8, instruction.execute(machine));
  }
}








