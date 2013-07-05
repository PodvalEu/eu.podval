package eu.podval;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.mockito.Mockito.mock;

@RunWith(JUnit4.class)
public class BeanImplTest {

    @Test(expected = IllegalArgumentException.class)
    public void Method_Throws_When_OtherBean_Is_Missing() {
        BeanImpl bean = new BeanImpl();
        bean.Method();
    }

    @Test(expected = IllegalArgumentException.class)
    public void Method_Throws_When_OtherBean2_Is_Missing() {
        BeanImpl bean = new BeanImpl();
        bean.OtherBean = mock(OtherBean.class);
        bean.Method();
    }

    @Test(expected = IllegalArgumentException.class)
    public void Method2_Throws_When_OtherBean2_Is_Missing() {
        // ...
    }
}