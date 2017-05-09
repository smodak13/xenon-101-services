package net.sachinmodak.xenon;

import com.vmware.xenon.common.ServiceHost;

import net.sachinmodak.xenon.services.AdditionSatelessService;

public class SampleHost extends ServiceHost {

    public static void main(String[] args) throws Throwable {
        SampleHost sampleHost = new SampleHost();
        sampleHost.initialize(args);
        sampleHost.start();
    }

    @Override
    public ServiceHost start() throws Throwable {
        super.start();
        super.startService(new AdditionSatelessService());
        return this;
    }
}
