package com.ikaihuo.jfm.testing;

import org.springframework.stereotype.Component;

import com.ikaihuo.gp.storage.dc.api.dc.DC;

/**
 * @author BruceZCQ
 */
@Component
public class MyDC extends DC {

    public MyDC() {
        this.idc = new RemoteDCProxy();
    }

    @Override
    protected String subSysName() {
        return "Galaxy-Test-SubSystem😄";
    }

}
