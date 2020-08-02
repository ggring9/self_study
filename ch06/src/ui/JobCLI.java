package ui;

import transcoder.JobData;
import transcoder.JobQueue;
import transcoder.Locator;

public class JobCLI {
    public void interact() {
        printInputSourceMessage();
        String source = getSourceFromConsole();
        printInputTargetMessage();
        String target = getTragetFromConsole();

        JobQueue jobQueue = Locator.getInstance().getJobQueue(); // jobQueue를 구한다
        jobQueue.addJob(new JobData(source, target));
    }

    private static void printInputSourceMessage() {
    }


    private static void printInputTargetMessage() {
    }


    private String getSourceFromConsole() {
        return null;
    }

    private String getTragetFromConsole() {
        return null;
    }


}
