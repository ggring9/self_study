package transcoder;

public interface JobQueue {

    public JobData addJob(JobData job);

    public JobData getJob();

}
