package eu.podval;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanImpl implements Bean {

    @Autowired
    public OtherBean OtherBean;

    @Override
    public void Method() {
        if(OtherBean == null) {
            throw new IllegalArgumentException("You need to provide other bean instance");
        }
        long value = OtherBean.computation();
        // ...
    }
}
