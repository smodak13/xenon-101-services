package net.sachinmodak.xenon.services;

import com.vmware.xenon.common.Operation;
import com.vmware.xenon.common.StatelessService;
import com.vmware.xenon.services.common.ServiceUriPaths;

public class ZipCodeStatelessService extends StatelessService {

    public static final String SELF_LINK = ServiceUriPaths.SAMPLES + "/zip-number-query";

    static class ZipRequest {
        String zipCode;
    }

    static class ZipResponse {
        String zipCode;
    }

    @Override
    public void handleGet(Operation get) {
        ZipRequest zipRequest = get.getBody(ZipRequest.class);
        ZipResponse zipResponse = new ZipResponse();
        zipResponse.zipCode = zipRequest.zipCode;
        get.setBody(zipResponse);
        get.complete();
    }
}
