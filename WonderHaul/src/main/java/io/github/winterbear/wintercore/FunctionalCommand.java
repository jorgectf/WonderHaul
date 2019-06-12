package io.github.winterbear.wintercore;

/**
 * Created by WinterBear on 20/05/2019.
 */

@FunctionalInterface
public interface FunctionalCommand<A,B,C,D> {

    public void execute(A sender, B command, C label, D string);

}
