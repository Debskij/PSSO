package projekt_1z02;

import java.io.*;
import java.util.Vector;

public class CipherOutputStream extends FilterOutputStream{
    private Cipher rc4_instance = new Cipher();

    public CipherOutputStream(OutputStream out){
        super(out);
    }

    @Override
    public void write(byte[] bytes) throws java.io.IOException {
        Vector<Byte> current_read = new Vector<Byte>();
        for (byte b : bytes) {
            current_read.add(b);
        }
        Vector<Byte> key = rc4_instance.run_chain(current_read);
        Vector<Byte> modified_message = rc4_instance.run_xor(current_read, key);
        byte[] new_bytes = new byte[modified_message.size()];
        for (int i = 0; i<modified_message.size(); i++){
            new_bytes[i] = modified_message.get(i);
        }
        out.write(new_bytes);
    }

    @Override
    public void write(byte[] bytes, int off, int len) throws java.io.IOException {
        Vector<Byte> current_read = new Vector<Byte>();
        for (byte b : bytes) {
            current_read.add(b);
        }
        Vector<Byte> key = rc4_instance.run_chain(current_read);
        Vector<Byte> modified_message = rc4_instance.run_xor(current_read, key);
        byte[] new_bytes = new byte[modified_message.size()];
        for (int i = 0; i<modified_message.size(); i++){
            new_bytes[i] = modified_message.get(i);
        }
        out.write(new_bytes, off, len);
    }
}
