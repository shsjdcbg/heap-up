package pers.dyx.visitor;

public interface ComputerPart {
    void accept(ComputerPartVisitor computerPartVisitor);
}
