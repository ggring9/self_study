package transcoder;

import filejobqueue.FileJobQueue;

public class Worker {

    private JobQueue jobQueue;
    private Transcoder transcoder;

    public Worker() {
        this.jobQueue = null;
        this.transcoder = null;
    }

    public Worker(JobQueue jobQueue, Transcoder transcoder) {
        this.jobQueue = jobQueue;
        this.transcoder = transcoder;
    }

    public void run() {
        boolean someRunningCondition = true;
        while (someRunningCondition) {
            JobData jobData = jobQueue.getJob();
            transcoder.transcode(jobData.getSource(), jobData.getTarget());
        }
    }

/*
    public void run(){
        // 직성 콘크리트 클래스를 사용
        JobQueue jobQueue = new FileJobQueue(); // DIP 위반
    }
*/


}
