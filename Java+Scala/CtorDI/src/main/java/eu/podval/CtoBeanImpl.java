package eu.podval;

/**
 * Created with IntelliJ IDEA.
 * User: Martin
 * Date: 7/5/13
 * Time: 5:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class CtoBeanImpl implements Bean {

    private final OtherBean OtherBean;
    private final OtherBean2 OtherBean2;

    public CtoBeanImpl(eu.podval.OtherBean2 otherBean2, eu.podval.OtherBean otherBean) {
        if (otherBean == null) {
            throw new IllegalArgumentException("You need to provide other bean instance");
        }
        if (otherBean2 == null) {
            throw new IllegalArgumentException("You need to provide other bean #2 instance");
        }

        OtherBean2 = otherBean2;
        OtherBean = otherBean;
    }

    @Override
    public void Method() {
        long value = OtherBean.computation();
        // ...
    }

    @Override
    public void Method2() {
        // ...
    }
}
