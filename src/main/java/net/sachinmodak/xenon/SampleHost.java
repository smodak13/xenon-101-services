package net.sachinmodak.xenon;

import com.vmware.xenon.common.ServiceHost;

import net.sachinmodak.xenon.services.AdditionSatelessService;
import net.sachinmodak.xenon.services.StudentRosterService;

public class SampleHost extends ServiceHost {

    public static void main(String[] args) throws Throwable {
        SampleHost sampleHost = new SampleHost();
        sampleHost.initialize(args);
        sampleHost.start();
    }

    @Override
    public ServiceHost start() throws Throwable {
        super.start();
        super.startDefaultCoreServicesSynchronously();

        super.startService(new AdditionSatelessService());
        super.startFactory(new StudentRosterService());
        return this;
    }
}
