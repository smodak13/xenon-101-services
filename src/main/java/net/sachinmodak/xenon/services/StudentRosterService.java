package net.sachinmodak.xenon.services;

import com.vmware.xenon.common.FactoryService;
import com.vmware.xenon.common.Operation;
import com.vmware.xenon.common.ServiceDocument;
import com.vmware.xenon.common.StatefulService;
import com.vmware.xenon.services.common.ServiceUriPaths;

public class StudentRosterService extends StatefulService {

    public static final String FACTORY_LINK = ServiceUriPaths.SAMPLES + "/student-roster";

    /**
     * State representing student roster for name and zip code.
     */
    public static class StudentRosterReport extends ServiceDocument {
        public static final String FIELD_ZIP_CODE = "zipCode";

        @Documentation(description = "Student id")
        public String id;

        @Documentation(description = "Student First Name.")
        public String firstName;

        @Documentation(description = "Student Last Name.")
        public String lastName;

        @Documentation(description = "Student zipCode.")
        public String zipCode;
    }

    public static FactoryService createFactory() {
        return FactoryService.create(StudentRosterService.class);
    }

    public StudentRosterService() {
        super(StudentRosterReport.class);
        super.toggleOption(ServiceOption.PERSISTENCE, true);
        super.toggleOption(ServiceOption.REPLICATION, true);
    }

    @Override
    public void handleCreate(Operation startPost) {

        if (!startPost.hasBody()) {
            startPost.fail(new IllegalArgumentException("initial state is required"));
            return;
        }

        startPost.complete();
    }

    @Override
    public void handleDelete(Operation operation) {
        operation.complete();
    }
    //    @Override
    //    public void handleGet(Operation get) {
    //        Query query = Query.Builder.create().addKindFieldClause(StudentRosterReport.class).build();
    //        QueryTask queryTask = QueryTask.Builder.create().addOption(QueryOption.EXPAND_CONTENT).
    //                orderAscending(StudentRosterReport.FIELD_NAME T)
    //    }
}
