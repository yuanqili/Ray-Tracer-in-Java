package samplers;

import utilties.Maths;
import utilties.Point2D;

/**
 * @author Yuanqi Li
 * @version 0.5
 */
public class Random extends Sampler {

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Constructors
\*--------------------------------------------------------------------------------------------------------------------*/

    public Random(int numSamples) {
        super(numSamples);
    }

/*--------------------------------------------------------------------------------------------------------------------*\
 *  Override methods
\*--------------------------------------------------------------------------------------------------------------------*/

    @Override
    public void generateSamples() {
        int n = (int)Math.sqrt(numSamples);
        for (int p = 0; p < numSets; p++)
            for (int j = 0; j < n; j++)
                for (int k = 0; k < n; k++)
                    squareSamples.add(
                            new Point2D(Maths.randDouble(), Maths.randDouble()));
    }
}
