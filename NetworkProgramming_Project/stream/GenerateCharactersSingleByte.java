package stream;

import java.io.IOException;
import java.io.OutputStream;

public class GenerateCharactersSingleByte {

    public static void main(String[] args){
        try{
//            long start1 = System.nanoTime();
//            generateCharacters(System.out);
//            long end1 = System.nanoTime();
//
//            long start2 = System.nanoTime();
//            generateCharacterByteArray(System.out);
//            long end2 = System.nanoTime();
//
//            System.out.println("Run Time(1): " + (end1 - start1)/1000.0);
//            System.out.println("Run Time(2): " + (end2 - start2)/1000.0);
            example2(System.out);
        }catch (IOException ex){

        }
    }

    private static void generateCharacters(OutputStream out) throws IOException{
        int firstPrintableCharacter = 33;
        int numberOfPrintableCharacters = 94;
        int numberOfCharactersPerLine = 72; // 33~104까지 출력됨
        int start = firstPrintableCharacter; //일단은 33부터 출발
        int count = 0;
        // 순환 : 127 출력안되고 126담에 바로 21출력됨.
        while(count<100){
            for(int i=start; i<start+numberOfCharactersPerLine; i++){
                out.write((byte) ((i - firstPrintableCharacter) % numberOfPrintableCharacters + firstPrintableCharacter));
            }

            out.write((byte)'\r');
            out.write((byte)'\n');

            start = ((start+1) - firstPrintableCharacter) % numberOfPrintableCharacters + firstPrintableCharacter;

            count++;
        }
    }
    public static void example2(OutputStream out) throws IOException{
        int firstPrintableCharacter = 33;
        int numberOfPrintableCharacters = 94;
        int numberOfCharactersPerLine = 72; // 33~104까지 출력됨
        int start = firstPrintableCharacter;
        int count = 0;
        byte[] line = new byte[numberOfCharactersPerLine+2];
        while(count<100){
            for(int i=start;i<start+numberOfCharactersPerLine;i++){
                line[i-start] = (byte)((i-firstPrintableCharacter)%numberOfPrintableCharacters+firstPrintableCharacter);
            }
            line[numberOfCharactersPerLine]=(byte)'\r';
            line[numberOfCharactersPerLine+1]=(byte)'\n';
            out.write(line);
            start = ((start+1)-firstPrintableCharacter)%numberOfPrintableCharacters+firstPrintableCharacter;
            count++;
        }

    }
    private static void generateCharacterByteArray(OutputStream out) throws IOException{
        int firstPrintableCharacter = 33;
        int numberOfPrintableCharacters = 94;
        int numberOfCharactersPerLine = 72; // 33~104까지 출력됨
        int start = firstPrintableCharacter; //일단은 33부터 출발
        int count = 0;
        byte[] line = new byte[numberOfCharactersPerLine+2];

        while(count<100){
            for(int i=start;i<start+numberOfCharactersPerLine;i++){
                line[i-start] = (byte)((i-firstPrintableCharacter)%numberOfPrintableCharacters+firstPrintableCharacter);
            }
            line[numberOfCharactersPerLine]=((byte)'\r');
            line[numberOfCharactersPerLine+1]=((byte)'\n');
            out.write(line);
            start=((start+1)-firstPrintableCharacter) % numberOfPrintableCharacters + firstPrintableCharacter;
            count++;
        }
    }


}
