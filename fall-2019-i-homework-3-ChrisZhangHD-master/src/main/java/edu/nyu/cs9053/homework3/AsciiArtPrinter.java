package edu.nyu.cs9053.homework3;

/**
 * User: blangel
 */
public class AsciiArtPrinter {

    /**
     * @implNote should only print values within {@code asciiArt} and nothing else within this method
     * @param asciiArt to print
     */
    public void print(char[][] asciiArt) {
	    for(int j = 0; j < asciiArt[0].length; j++){
	        for(int i = 0; i < asciiArt.length; i++){
                System.out.printf("%c", asciiArt[i][j]);

            }
            System.out.println();
        }
    }

    protected void clearScreen() {
        System.out.printf("\u001B[51;1H");
    }

}
