package net.sachinmodak.xenon.services;

import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

import com.vmware.xenon.common.Operation;
import com.vmware.xenon.services.common.ServiceUriPaths;

public class TestServiceHarness extends com.vmware.xenon.common.StatelessService {

    public static final String SELF_LINK = ServiceUriPaths.SAMPLES + "/test-service";

    static class TestRequest {
        String link;
        int count;
    };

    static class TestResponse {
        String totalTime;
    };

    @Override
    public void handlePost(Operation post) {
        TestRequest testRequest = post.getBody(TestRequest.class);
        int numberOfTimes = testRequest.count;
        AtomicInteger confirmRun = new AtomicInteger(numberOfTimes);
        String link = testRequest.link;
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < numberOfTimes; i++) {
            Operation.createPost(this, link)
                    .setBody(Collections.EMPTY_MAP)
                    .setCompletion((op, ex) -> {
                        if (ex != null) {
                            post.fail(ex);
                            return;
                        }
                        if (confirmRun.decrementAndGet() == 0) {
                            long endTime = System.currentTimeMillis();
                            long duration = (endTime - startTime);
                            TestResponse testResponse = new TestResponse();
                            testResponse.totalTime = new String(
                                    "duration: " + duration + " milliseconds");
                            post.setBody(testResponse).complete();
                        }
                    })
                    .sendWith(this);
        }
    }
}
