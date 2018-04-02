package crypto;

/**
 * Created by TheTStone on 3/4/2018.
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class aes {

    private byte[] key;
    private byte[][] block;


    public ArrayList<Byte> processFile(String filename)
    {

        ArrayList<Byte> raw = new ArrayList<Byte>();
        ArrayList<String> data = readFile(filename);
        for(String s: data)
        {
            byte[] b = s.getBytes();
            for (byte i:b)
            {
                raw.add(new Byte(i));
            }
        }

        return raw;
    }

    public void encrypt(String filename, String kpass)
    {
        key = kpass.getBytes();
        ArrayList<Byte> data = processFile(filename);



    }
    /*
    public void decrpyt()
    {


    }

    public String[] subBytes()
    {

    }

    public String[] shiftRows()
    {

    }

    public String[] mixColumns()
    {

    }

    public String[] xorNthRoundKey()
    {

    }

    public void roundKeys()
    {

    }
*/
    public void rotateBytes(int level)
    {
        byte temp = block[level][0];
        block[level][0] = block[level][1];
        block[level][1] = block[level][2];
        block[level][2] = block[level][3];
        block[level][3] = temp;
    }

    public void rCon()
    {

    }

    public byte[][] sBox(int num)
    {
        byte[][] sbox = new byte[16][16];
        int p=1;
        int q=1;
        do{
            p = 0;
        }while(p != 1);

        return sbox;
    }

    public void keyCore()
    {}

    public ArrayList<String> readFile(String filename)
    {
        String line;
        ArrayList<String> strings = new ArrayList<String>();
        try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferReader = new BufferedReader(fileReader);
            while((line = bufferReader.readLine()) != null)
            {
                strings.add(line);
            }
            bufferReader.close();
        }catch(FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("FILE NOT FOUND EXCEPTION:realFile");
        }catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("IO EXCEPTION:readFile");
        }
        return strings;
    }
}
