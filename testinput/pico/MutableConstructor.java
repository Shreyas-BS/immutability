import qual.Mutable;
import qual.Immutable;
import qual.PolyImmutable;
import qual.Readonly;

public class MutableConstructor {

    @Readonly Object rof;
    @PolyImmutable Object pif;
    @Immutable Object imf;

    @Mutable MutableConstructor(@Mutable Object mo, @PolyImmutable Object po, @Immutable Object io) {
        // It looks like "this" automatically has the same type as constructor return type.
        this.rof = mo;
        this.rof = po;
        this.rof = io;

        this.pif = mo;
        //:: error: (assignment.type.incompatible)
        this.pif = po;
        //:: error: (assignment.type.incompatible)
        this.pif = io;

        //:: error: (assignment.type.incompatible)
        this.imf = mo;
        //:: error: (assignment.type.incompatible)
        this.imf = po;
        this.imf = io;
    }

    void invokeConstructor(@Mutable Object mo, @PolyImmutable Object po, @Immutable Object io) {
        new @Mutable MutableConstructor(mo, mo, io);
        //:: error: (argument.type.incompatible)
        new @Mutable MutableConstructor(mo, po, io);
        //:: error: (constructor.invocation.invalid)
        new @PolyImmutable MutableConstructor(mo, po, io);
        //:: error: (constructor.invocation.invalid)
        new @Immutable MutableConstructor(mo, io, io);
    }
}