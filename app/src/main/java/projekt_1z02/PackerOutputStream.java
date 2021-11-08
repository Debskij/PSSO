package projekt_1z02;

import java.io.*;

public class PackerOutputStream extends FilterOutputStream{
    static final int FIVE_BIT = 5;
    static final int SIX_BIT = 6;

    public PackerOutputStream(OutputStream out){
        super(out);
    }

    @Override
    public void write(byte[] bytes) throws java.io.IOException {
        Packer packer = new Packer();
        String string_form = new String(bytes);
        byte[] packed = packer.encode(string_form, SIX_BIT);
        out.write(packed);
    }
}
