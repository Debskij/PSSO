package projekt_1z02;

import java.io.*;

public class UnpackerInputStream extends FilterInputStream{
    static final int FIVE_BIT = 5;
    static final int SIX_BIT = 6;

    public UnpackerInputStream(InputStream in){
        super(in);
    }
    
    @Override
    public int read(byte[] bytes) throws java.io.IOException {
        Unpacker unpacker = new Unpacker();
        String unpacked = unpacker.decode(bytes, SIX_BIT);
        byte[] new_bytes = unpacked.getBytes();
        in.read(new_bytes);
        return new_bytes.length;
    }
}
