package projekt_1z02;

import java.io.*;

public class UnpackerInputStream extends FilterInputStream{
    static final int FIVE_BIT = 5;
    static final int SIX_BIT = 6;

    public UnpackerInputStream(InputStream in){
        super(in);
    }
    
    @Override
    public int read(byte[] bytes, int off, int buffor) throws java.io.IOException {
        int remaining_chars = in.read(bytes);
        Unpacker unpacker = new Unpacker();
        String unpacked = unpacker.decode(bytes, SIX_BIT);
        bytes = unpacked.getBytes();
        return remaining_chars;
    }

    @Override
    public int read(byte[] bytes) throws java.io.IOException {
        int remaining_chars = in.read(bytes);
        Unpacker unpacker = new Unpacker();
        String unpacked = unpacker.decode(bytes, SIX_BIT);
        bytes = unpacked.getBytes();
        return remaining_chars;
    }
}
