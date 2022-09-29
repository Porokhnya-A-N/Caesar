package App.WR_Streams.WR_File;
import App.Encryption_Mechanism.EncodingProcess;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
public class RWFile {
    public void run(String path, String pathResult, int shift){
        EncodingProcess enCode = new EncodingProcess();
        enCode.setShift(shift);
        enCode.setTextForCoding(readFile(path));
        enCode.RunEncodingProcess();
        writeFile(enCode.getResultShift(), pathResult);
    }
public String readFile(String path){
        String resultReadFile = " ";
            try(FileInputStream fileInputStream = new FileInputStream(path);
                FileChannel channelREad = fileInputStream.getChannel();){
                ByteBuffer byteBuffer = ByteBuffer.allocate((int) channelREad.size());
                channelREad.read(byteBuffer);
                byteBuffer.flip();
               resultReadFile = new String(byteBuffer.array(), Charset.defaultCharset());

            }catch (Exception e){
                e.printStackTrace();
            }
        return resultReadFile;
}
public void writeFile(char[] text, String path){
        try(FileOutputStream fileOutputStream = new FileOutputStream(path);
        FileChannel channelWrite = fileOutputStream.getChannel();){
            byte[] buffer = new String(text).getBytes(Charset.defaultCharset());
            ByteBuffer byteBuffer = ByteBuffer.allocate(buffer.length);
            byteBuffer.put(buffer);
            byteBuffer.flip();
            channelWrite.write(byteBuffer);

        }catch (Exception e){
            e.printStackTrace();
        }

}

}
