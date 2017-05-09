package net.sachinmodak.xenon.services;

import com.vmware.xenon.common.Operation;
import com.vmware.xenon.common.StatelessService;
import com.vmware.xenon.services.common.ServiceUriPaths;

public class AdditionSatelessService extends StatelessService {

    public static final String SELF_LINK = ServiceUriPaths.SAMPLES + "/add-numbers";

    static class AddRequest {
        int num1;
        int num2;
    }

    static class AddResponse {
        int result;

        public int getResult() {
            return this.result;
        }

        public void setResult(int result) {
            this.result = result;
        }
    }

    @Override
    public void handlePost(Operation post) {
        AddRequest addRequest = post.getBody(AddRequest.class);
        AddResponse addResponse = new AddResponse();
        addResponse.setResult(addRequest.num1 + addRequest.num2);
        post.setBody(addResponse).complete();
    }
}
