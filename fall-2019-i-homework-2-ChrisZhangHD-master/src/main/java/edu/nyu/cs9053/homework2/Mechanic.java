package edu.nyu.cs9053.homework2;

import edu.nyu.cs9053.homework2.model.DiagnosticTroubleCode;

/**
 * User: blangel
 */
public class Mechanic {

    /**
     * @see {@literal https://en.wikipedia.org/wiki/OBD-II_PIDs#Mode_3_(no_PID_required)}
     * @implNote For simplification of this homework, contrary to the Wikipedia article {@code data} is not in the ISO 15765-2 protocol.
     *           It is simply an array of data where the length should be equal to {@code expectedAmount} times 2.
     * @implNote If {code data} is null, empty or not equal to {@code expectedAmount} times 2 then throw
     *           an {@linkplain IllegalArgumentException}; i.e. {@code throw new IllegalArgumentException}
     * @param data to parse
     * @param expectedAmount of {@linkplain DiagnosticTroubleCode} to decode
     * @return the decoded {@linkplain DiagnosticTroubleCode} objects see {@linkplain DiagnosticTroubleCode#construct(String)} to create the object.
     */
    public static DiagnosticTroubleCode[] decode(byte[] data, int expectedAmount) {

        if(data == null || data.length == 0 || data.length != expectedAmount * 2)
            throw new IllegalArgumentException();

        DiagnosticTroubleCode[] diagnosticTroubleCodes = new DiagnosticTroubleCode[expectedAmount];
        DiagnosticTroubleCode diagnosticTroubleCode;

        String firstDTCChar = "", secondDTCChar = "", thirdDTCChar = "", fourthDTCChar, fifthDTCChar;

        for(int i = 0; i < data.length; i++){
            if(i % 2 == 0){
                firstDTCChar = FirstDTCCharToString((byte)((data[i] >>> 6) & 3 ));
                secondDTCChar = SecondDTCCharToString((byte)((data[i] >>> 4) & (byte)3));
                thirdDTCChar = ThirdFourthFifthDTCCharToString((byte)(data[i] & 15));
            }else {
                fourthDTCChar = ThirdFourthFifthDTCCharToString((byte)((data[i] >>> 4) & 15));
                fifthDTCChar = ThirdFourthFifthDTCCharToString((byte)(data[i] & 15));
                diagnosticTroubleCode = DiagnosticTroubleCode.construct(
                        firstDTCChar + secondDTCChar + thirdDTCChar + fourthDTCChar + fifthDTCChar);
                diagnosticTroubleCodes[i/2] = diagnosticTroubleCode;
            }
        }
        return diagnosticTroubleCodes;
    }

    private static String FirstDTCCharToString(byte data){
        switch (data){
            case 0:
                return "P";
            case 1:
                return "C";
            case 2:
                return "B";
            case 3:
                return "U";
            default :
                return "";
        }
    }

    private static String SecondDTCCharToString(byte data){
        return String.valueOf(data);
    }


    private static String ThirdFourthFifthDTCCharToString(byte data){

        if(data < (byte)10)
            return String.valueOf(data);
        else
            return String.valueOf('A' + (data - (byte)10));
    }
}
