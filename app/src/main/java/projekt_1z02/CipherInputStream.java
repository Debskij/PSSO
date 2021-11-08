package projekt_1z02;

import java.io.*;
import java.util.Vector;

public class CipherInputStream extends FilterInputStream{
    private RC4 rc4_instance = new RC4();
    
    public CipherInputStream(InputStream in){
        super(in);
    }

    @Override
    public int read(byte[] bytes) throws java.io.IOException {
        int remaining_chars = in.read(bytes);
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
        return remaining_chars;
    }

    @Override
    public int read(byte[] bytes, int off, int buffor) throws java.io.IOException {
        int remaining_chars = in.read(bytes, off, buffor);
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
        return remaining_chars;
    }
}