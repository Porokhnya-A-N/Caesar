package App.Decryption_Mechanism;
import App.WR_Streams.WR_File.RWFile;
import App.Encryption_Mechanism.EncodingProcess;

import java.util.Scanner;

public class DecodingBrut {
   private RWFile WRFile = new RWFile();
   private EncodingProcess encodingProcess = new EncodingProcess();
   private String path, newPath;
   private String text;
   char[] resultDecoding;
   private int countShift;
   private int sampleLength1, sampleLength2, sampleLength3, sampleLength4;

   public DecodingBrut(String nameFile, String newFileName) {
      path = nameFile;
      newPath = newFileName;
   }
//Требуется оптимизация алгоритма.
   public void runDecodingFile() {
      text = WRFile.readFile(path);
      if (text.length() > 1000) {
         decodingBigText(text);
      } else if (text.length() < 1000 && text.length() > 0) {
         decodingSmallText(text);
      }
   }

   private void decodingBigText(String text) {
      int countPun = 0;
      int countPun2 = 0;
      for (int i = 0; i < 41; i++) {
         encodingProcess.setShift(i);
         encodingProcess.setTextForCoding(text);
         encodingProcess.RunEncodingProcess();
         resultDecoding = encodingProcess.getResultShift();
         sampleLength1 = new String(resultDecoding).split(". ").length;
         sampleLength2 = new String(resultDecoding).split(", ").length;
         if (sampleLength1 > sampleLength3) {
            sampleLength3 = sampleLength1;
            countPun = i;
         }
         if (sampleLength2 > sampleLength4) {
            sampleLength4 = sampleLength2;
            countPun2 = i;
         }
      }
      if (countPun > countPun2) {
         countShift = countPun;
      } else {
         countShift = countPun2;
      }
      encodingProcess.setShift(countShift);
      encodingProcess.setTextForCoding(text);
      encodingProcess.RunEncodingProcess();
      WRFile.writeFile(encodingProcess.getResultShift(), newPath);
   }

   private void decodingSmallText(String text) {
      System.out.println("Входных данных текста недостаточно для Пунктуационной декодировки файла");
      System.out.println("Необходимо подобрать ключ в ручном режиме (найти читаемые слова среди всех сдвигов) и установить сдвиг для дешефрования");
      if (text.length() >= 10) {
         String line = text.substring(0, 10);;
         for (int i = 1; i < 41; i++) {
            encodingProcess.setShift(i); //Повторение вынести в отдельный метод (Улучшение алгоритма)
            encodingProcess.setTextForCoding(line);
            encodingProcess.RunEncodingProcess();
            resultDecoding = encodingProcess.getResultShift();
               System.out.print("Сдвиг:" + i + ": (-" + new String(resultDecoding) + "-) ");
            if(i%5==0){
               System.out.print("\n");
            }}
            System.out.println("выберите сдвиг: ");
            Scanner console = new Scanner(System.in);
            if(console.hasNextInt()) {
               int shift = console.nextInt();
               if (shift > -41 && shift < 41){
                  countShift = shift;
               }else{
                  System.out.println("Сдвиг выходит за пределы требований");
               }}
            encodingProcess.setShift(countShift);
            encodingProcess.setTextForCoding(text);
            encodingProcess.RunEncodingProcess();
            WRFile.writeFile(encodingProcess.getResultShift(), newPath);
         }
      else{
         System.out.println("Отсутствует минимальное количество символов, приминение дешифровки методом Brute force не практично");
      }
   }
}
