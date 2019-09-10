/*  Name: Ethan Chen
 *  PennKey: etc
 *  Recitation: 217
 *
 *  Execution: java Harp
 * 
 * Makes a Harp with 37 strings that each play a different sound depending on
 * key pressed
 */

public class Harp {
    private static double FREQUENCY = 440.0;
    private static String NOTE_MAPPING = 
        "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    
    public static void main(String[] args) {
        HarpString[] theHarp = new HarpString[NOTE_MAPPING.length()];
        for (int i = 0; i < NOTE_MAPPING.length(); i++) {
            theHarp[i] = new HarpString(FREQUENCY * Math.pow(2, 
                                                              (i - 24.0) / 
                                                              12.0));
        }
        // infinite loop to check if a key is pressed
        // and play the associated note
        while (true) {
            // check if the user has typed a key; if so, process it   
            if (PennDraw.hasNextKeyTyped()) {
                //save key pressed
                char key = PennDraw.nextKeyTyped();
                
                //select note to play
                int keyIndex = NOTE_MAPPING.indexOf(key);
                
                //play note if in array
                if (keyIndex != -1) {
                    theHarp[keyIndex].pluck();
                }
            }
                    // compute the combined sound of all harp strings
                    double sample = 0;
                    for (int i = 0; i < NOTE_MAPPING.length(); i++) {
                        sample += theHarp[i].sample();
                    }
                    // play the sample on standard audio
                    StdAudio.play(sample);
                    
                    // advance the simulation of each harp string by one step   
                    for (int i = 0; i < NOTE_MAPPING.length(); i++) {
                        theHarp[i].tic();
                    }
                }
    }
}