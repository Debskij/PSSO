package projekt_1z02;
import java.util.Collections;
import java.util.Vector;


public class Cipher {
    private int box_size = 256;
    private Vector<Byte> s_box = new Vector<Byte>(box_size);
    private Vector<Byte> k_box = new Vector<Byte>(box_size);
    private Vector<Byte> pattern = new Vector<Byte>();

    Cipher(){
        byte[] key = {'t', 'e', 's', 't', '$', ',', '.'};
        for(byte b : key){
            this.pattern.add(b);
        }
    }

    Cipher(byte[] key){
        for(byte b : key){
            this.pattern.add(b);
        }
    }

    void fill_k_box(Vector<Byte> pattern){
        int k_index = 0;
        while(k_index < box_size){
            k_box.add(pattern.get(k_index % pattern.size()));  
            k_index += 1;              
        }
    }
        

    void fill_s_box(){
        for(int i = 0; i < box_size; i++){
            s_box.add((byte) i);
        }
    }

    void scrambling(){
        int j = 0;
        for(int i = 0; i < box_size; i++){
            j = (j + Byte.toUnsignedInt(s_box.get(i)) + Byte.toUnsignedInt(k_box.get(i))) % box_size;
            Collections.swap(s_box, i, j);
        }
    }

    private Vector<Byte> pseudo_random_generator(Vector<Byte> message){
        int i = 0, j = 0;
        Vector<Byte> output = new Vector<Byte>(message.size());
        for (int k=0; k < message.size(); k++){
            i = (i + 1) % 256;
            j = (j + Byte.toUnsignedInt(s_box.get(i))) % 256;
            Collections.swap(s_box, i, j);
            output.add(s_box.get((Byte.toUnsignedInt(s_box.get(i)) + Byte.toUnsignedInt(k_box.get(i))) % 256));
        }
        return output;
    }

    public Vector<Byte> run_chain(Vector<Byte> message){
        fill_k_box(this.pattern);
        fill_s_box();
        scrambling();
        return pseudo_random_generator(message);
    }

    public Vector<Byte> run_xor(Vector<Byte> message, Vector<Byte> key){
        Vector<Byte> modified_message = new Vector<Byte>(message.size());
        for(int i = 0; i < message.size(); i++){
            modified_message.add((byte) (message.get(i) ^ key.get(i)));
        }
        return modified_message;
    }
}
