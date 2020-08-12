package dbjobqueue;

import transcoder.JobData;
import transcoder.JobQueue;

public class DbJobQueue implements JobQueue {
    @Override
    public JobData addJob(String source, String target) {
        // ...
        return null;
    }

    @Override
    public JobData addJob(JobData job) {
        return null;
    }

    @Override
    public JobData getJob() {
        return null;
    }
}
