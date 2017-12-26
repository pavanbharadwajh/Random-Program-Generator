package Generator;

import java.util.concurrent.ThreadLocalRandom;

/*Generating random number between the min and max specified for the appropriate parameters in the config file
 */

public class RandomGenerator {
    int rand;
    public int random(int p_min, int p_max)
    {
        rand = ThreadLocalRandom.current().nextInt(p_min, p_max + 1);
        return rand;
    }
}
