package stream;

import java.io.*;

public class DataOutputStreamTest {

    public static void main(String[] args){

        FileOutputStream fos = null;
        DataOutputStream dos = null;

        boolean addTab = false;

        try {
            fos = new FileOutputStream("data.text"); //
            // fos = new FileOutputStream(FileDescriptor.out);
            dos = new DataOutputStream(fos);
            dos.writeBoolean(false);
            if(addTab) dos.writeChar('\n');
            // System.out.println((byte)1);
            dos.writeByte((byte)125);
            //dos.writeByte(127);
            // dos.writeByte(256); -> 0이됨. 이유 : 1byte만 적는것이기 때문에
            // 257하면 다시 1이된다.
            if(addTab) dos.writeChar('\n');
            // System.out.println((byte)5);
            dos.writeInt(10);
            if(addTab) dos.writeChar('\n');
            // System.out.println((byte)1000000);
            dos.writeDouble(200.5);
            if(addTab) dos.writeChar('\n');

            dos.writeUTF("hello world");

            System.out.println("저장하였습니다.");
            fos.close();
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        byte[] buffer = new byte[2];
//        int readCount = 0;

        FileInputStream fis = null;
        DataInputStream dis = null;
        try {
            fis = new FileInputStream("data.text");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        dis = new DataInputStream(fis);

        // readcount = fis.read(buffer);
        // System.out.println(buffer);

        boolean boolVar = false;
        try {
            boolVar = dis.readBoolean();
            if(addTab) dis.readChar();
            byte byteVar = dis.readByte();
            if(addTab) dis.readChar();
            int intVar = dis.readInt();
            if(addTab) dis.readChar();
            double doubleVar = dis.readDouble();
            if(addTab) dis.readChar();
            String stringVar = dis.readUTF();

            System.out.println(boolVar);
            System.out.println(byteVar);
            System.out.println(intVar);
            System.out.println(doubleVar);
            System.out.println(stringVar);
            fis.close();
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
