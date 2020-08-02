import ffmpegtranscoder.FfmpegTranscoder;
import filejobqueue.FileJobQueue;
import transcoder.JobQueue;
import transcoder.Locator;
import transcoder.Transcoder;
import transcoder.Worker;
import ui.JobCLI;

public class Main {
    public static void main(String[] args) {
//        상위 수준 모듈인 transcoder 패키지에서 사용함
//        하위수준 모듈 객체 생성

        JobQueue jobQueue = new FileJobQueue();
        Transcoder transcoder = new FfmpegTranscoder();

        // 상위 수준 모듈이 하위 수준 모듈을 사용할 수 있도록 Locator 초기화
        Locator locator = new Locator(jobQueue, transcoder);
        Locator.init(locator);

        // 상위 수준 모듈 객체를 생성하고 실행
        final Worker worker = new Worker();
        Thread t = new Thread(()->worker.run());

        JobCLI cli = new JobCLI();
        cli.interact();
    }
}
