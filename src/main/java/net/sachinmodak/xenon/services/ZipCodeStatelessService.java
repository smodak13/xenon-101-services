package net.sachinmodak.xenon.services;

import com.vmware.xenon.common.Operation;
import com.vmware.xenon.common.StatelessService;
import com.vmware.xenon.services.common.QueryTask;
import com.vmware.xenon.services.common.QueryTask.Query;
import com.vmware.xenon.services.common.QueryTask.QuerySpecification.QueryOption;
import com.vmware.xenon.services.common.ServiceUriPaths;

import net.sachinmodak.xenon.services.StudentRosterService.StudentRosterReport;

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
        String zipCode = zipRequest.zipCode;

        Query query = Query.Builder.create().addKindFieldClause(StudentRosterReport.class)
                .addFieldClause(StudentRosterReport.FIELD_ZIP_CODE, zipCode)
                .build();
        QueryTask queryTask = QueryTask.Builder.createDirectTask()
                .addOption(QueryOption.EXPAND_CONTENT)
                .setQuery(query)
                .build();
        Operation.createPost(this, ServiceUriPaths.CORE_QUERY_TASKS)
                .setBody(queryTask)
                .setCompletion((o, e) -> {
                    QueryTask rsp = o.getBody(QueryTask.class);
                    System.out.println("data:" + rsp);
                    get.setBody(rsp.results.documents);
                    get.complete();
                }).sendWith(this);
    }
}
