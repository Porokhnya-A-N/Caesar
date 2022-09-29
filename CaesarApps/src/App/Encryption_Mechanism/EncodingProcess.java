package App.Encryption_Mechanism;
public class EncodingProcess {
    //class variables
    //-------------------------------------------------------------
    private String textForCoding;
    private char[] arraySymbol;
    private int[] arrayUnicode;
    private int[] resultShift;
    private int shift;

    //---------------------------------------------------------------
    private static final char[] BIG_ALPHABET = {'А','Б','В','Г','Д','Е','Ж','З','И','Й','К','Л','М','Н','О','П','Р','С','Т','У','Ф','Х','Ц','Ч','Ш','Щ','Ъ','Ы','Ь','Э','Ю','Я','.',',','"','\'',':','-','!','?',' '};
    private static final char[] SMALL_ALPHABET = {'а','б','в','г','д','е','ж','з','и','й','к','л','м','н','о','п','р','с','т','у','ф','х','ц','ч','ш','щ','ъ','ы','ь','э','ю','я','.',',','"','\'',':','-','!','?',' '};
    private static final int SIZE_ALPHABET = BIG_ALPHABET.length;
    //---------------------------------------------------------------
    // class constructors.
    //---------------------------------------------------------------
    //---------------------------------------------------------------
    //start system.
    public void RunEncodingProcess(){
        arrayUnicode = charToInteger(textForCoding.toCharArray());
        shiftArrayInteger(arrayUnicode,shift);
        intToCharArray(resultShift);

    }
    private int[] charToInteger(char[] array){
        int[] arrayInt = new int[array.length];
        for(int i = 0; i < array.length;i++){
            arrayInt[i] =  array[i];
        }
        return arrayInt;
    }
    private void shiftArrayInteger(int[] array, int shiftArray) {
        resultShift = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 1039 && array[i] < 1072) {
                resultShift[i] = shiftAlphabet(shiftArray,SIZE_ALPHABET,indexAlphabet(array[i],1040), BIG_ALPHABET);
            } else if (array[i] > 1071 && array[i] < 1104) {
                resultShift[i] = shiftAlphabet(shiftArray,SIZE_ALPHABET,indexAlphabet(array[i],1072), SMALL_ALPHABET);
            }else if (array[i] > 31 && array[i] < 64){
                switch (array[i]) {
                    //46 44 34 39 58 45 33 63 32
                    case 46: {
                        resultShift[i] = shiftAlphabet(shiftArray, SIZE_ALPHABET, indexAlphabet(32), SMALL_ALPHABET);
                        break;
                    }
                    case 44: {
                        resultShift[i] = shiftAlphabet(shiftArray, SIZE_ALPHABET, indexAlphabet(33), SMALL_ALPHABET);
                        break;
                    }
                    case 34: {
                        resultShift[i] = shiftAlphabet(shiftArray, SIZE_ALPHABET, indexAlphabet(34), SMALL_ALPHABET);
                        break;
                    }
                    case 39: {
                        resultShift[i] = shiftAlphabet(shiftArray,SIZE_ALPHABET,indexAlphabet(35), SMALL_ALPHABET);
                        break;
                    }
                    case 58: {
                        resultShift[i] = shiftAlphabet(shiftArray,SIZE_ALPHABET,indexAlphabet(36), SMALL_ALPHABET);
                        break;
                    }
                    case 45: {
                        resultShift[i] = shiftAlphabet(shiftArray,SIZE_ALPHABET,indexAlphabet(37), SMALL_ALPHABET);
                        break;
                    }
                    case 33: {
                        resultShift[i] = shiftAlphabet(shiftArray,SIZE_ALPHABET,indexAlphabet(38), SMALL_ALPHABET);
                        break;
                    }
                    case 63: {
                        resultShift[i] = shiftAlphabet(shiftArray,SIZE_ALPHABET,indexAlphabet(39), SMALL_ALPHABET);
                        break;
                    }
                    case 32: {
                        resultShift[i] = shiftAlphabet(shiftArray,SIZE_ALPHABET,indexAlphabet(40), SMALL_ALPHABET);
                        break;
                    }
                    default: {
                        resultShift[i] = array[i];
                    }
                }

            }else{
                resultShift[i] = array[i];
            }
        }
    }
    private void intToCharArray(int[] array){
        arraySymbol = new char[array.length];
        for(int i = 0; i < array.length; i++){
            arraySymbol[i] =  (char) array[i];
        }

    }
    private int shiftAlphabet(int shift, int sizeAlphabet, int indexSymbol, char[] alphabet){
        int sumInAndSH = indexSymbol + shift;
        int subSumAndAlp = sumInAndSH - sizeAlphabet;
        int sumAlpAndSum = sumInAndSH + sizeAlphabet;
        if(sumInAndSH < sizeAlphabet && sumInAndSH > -1){
            return (int ) alphabet[sumInAndSH];
        }else if (sumInAndSH >= sizeAlphabet){
            return (int) alphabet[subSumAndAlp];
        }else if (sumInAndSH < 0 ){
            return (int) alphabet[sumAlpAndSum];
        }else {
            return (int) alphabet[indexSymbol];
        }
    }
    private int indexAlphabet(int codeSymbol, int bias){
        return codeSymbol - bias;
    }
    private int indexAlphabet(int indexSymbol){
        return indexSymbol;
    }
    public void setTextForCoding(String line){
        textForCoding = line;
    }
    public char[] getResultShift(){
        return arraySymbol;
    }
    public void setShift(int shiftText){
        shift = shiftText;
    }
}
