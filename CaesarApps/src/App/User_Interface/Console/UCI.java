package App.User_Interface.Console;

import App.Decryption_Mechanism.DecodingBrut;
import App.WR_Streams.WR_File.RWFile;

import java.nio.file.Path;
import java.nio.file.Files;
import java.util.Scanner;

// user console interface (UCI)
public class UCI { // подключения интерфейса многонитевости.
    private boolean RunUCI; //логическая переменная для проверки активности интерфейса.
    private Path path; // объект для работы с путем файла.
    private Scanner console;// объект для работы с консолью.
    private int SymbolShift; // переменная для сдвига.
    public UCI(){ // конструктор класса
        console = new Scanner(System.in); // инициализация забора данных из консоли
        RunUCI = true; //Итерфейс запущен
    }

    public void run() {
        while (RunUCI) {
            System.out.println("Добро пожаловать в UCI");
            System.out.println("----------------------");
            System.out.println("Для шифрования файла нажмите 1 и Enter");
            System.out.println("Для дешифрования файла нажмите 2 и Enter");
            System.out.println("Для дешефрования файла по средством Brute force нажмите 3 и Enter");
            System.out.println("Для завершения программы введите exit");
            switch (console.nextLine()) {
                case "1": {
                    System.out.println("Шифрование файла методом Цезаря:");
                    setPath();
                    setSymbolShift();
                    RWFile startProcess = new RWFile();
                    startProcess.run(getPath().toString(),newNameFile(path,"ENC"),getSymbolShift());
                    break;

                }
                case "2": {
                    System.out.println(" Выбран вариант дешифрования файла методом Цезаря");
                    setPath();
                    setSymbolShift();
                    RWFile startProcess = new RWFile();
                    startProcess.run(getPath().toString(),newNameFile(path,"DEC"),getSymbolShift()*-1);
                    break;
                }
                case "3":{
                    System.out.println(" Выбран вариант дешифрования файла по средством Brute force");
                    setPath();
                    DecodingBrut decBrut = new DecodingBrut(getPath().toString(),newNameFile(getPath(),"BrutDEC"));
                    decBrut.runDecodingFile();
                    break;
                }
                case "exit": {
                    RunUCI = false;
                    System.out.println("Пользователь инициализировал выход.");
                    break;
                }
                default: {
                    System.out.println("Ваш вариан не соответствует параметрам выбора");
                    
                }
            }
        }
    }
    public void setPath()  {
        System.out.println("Укажите путь к текстовому файлу:");
        String pathFile = console.nextLine();
        Path of = Path.of(pathFile);
        System.out.println(of.toString());
        if(Files.exists(of)) {
            path = of;
        }else {
            System.out.println("файла не существует по данному пути");
            setPath();
        }
    }
    public void setSymbolShift(){
        System.out.println("Укажите сдвиг (от 1 до 40):");
        if(console.hasNextInt()) {
            int shift = console.nextInt();
            if (shift > -41 && shift < 41){
                SymbolShift = shift;
        }else{
            System.out.println("Сдвиг выходит за пределы требований");
            setSymbolShift();
        }}

    }
    public Path getPath()  {
        return path;
    }
    public int getSymbolShift(){
        return SymbolShift;
    }
    public String newNameFile(Path path, String fragName){
        String oldName = path.toString();
        String newName = path.toString().substring(0,oldName.lastIndexOf(".")) + fragName + ".txt";
        return newName;
    }
}
