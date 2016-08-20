package com.ikaihuo.jfm.testing;

import com.ikaihuo.api.dc.DC;

/**
 * @author BruceZCQ
 */
public class MyDC extends DC {

    public MyDC() {
        this.idc = new RemoteDCProxy();
    }

    @Override
    protected String subSysName() {
        return "Galaxy-Test-SubSystem😄";
    }

}
