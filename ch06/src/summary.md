# DI(Dependeny Injection)와 서비스 로케이터

로버트 C 마틴은 SW를 두 영역으로 구분해서 설명함.
- 어플리케이션 영역: 고수준 정책 및 저수준 구현을 포함한 영역
- 메인영역: 어플리케이션이 동작하도록 각 객체들을 연결해주는 영역 

특히 메인영역에서 객체를 연결하기위해서는 의존성주입(Dependency Injection), 서비스 로케이터를 사용하게된다.


고수준 정책이란?  `인터페이스`

저수준 구현이라 함은? `상속/구현된 클래스`


### 어플리케이션 영역과 메인영역

간단한 비디오 포맷 변환 예제

요구사항:
- 파일의 확장자를 이용해서 비디오 파일의 포맷을 변환한다
- 변환 요청을 등록하면 순차적으로 변환작업을 수행한다.
  - 변환요청정보는 파일 또는 DB를 이용해서 보관할 수 있어야 한다.
- 비디오 형식의 변환 처리는 오픈소스인 ffmpeg을 이용하거나 구매예정인 변환솔루션을 사용할 수 있어야 한다.
- 명령행에서 변환할 원본 파일과 변환 결과로 생성된 파일을 입력한다.

요구사항 분석시 핵심기능은 다음과 같다.
- 변환작업을 요청하면 순차적으로 변환작업을 수행한다

기능제공에 있어 변화가 발생하는 부분은 다음의 두가지이다.
- 변환 요청 정보 저장: 파일에 보관 / DB에 보관
- 변환 처리 : ffmpeg 사용 / 솔루션 사용

`변화가 발생할 수 있는 부분은 인터페이스로 처리?`

JobData(source, target) 라는 자료구조를 Worker 클래스와 Jobqueue가 사용하는 형태의 설계구조를 갖게 되면서
실제 객체를 구하기 위해 Locator 라는 객체를 사용하도록 설계에 추가한다.
단, Locator 클래스는 **패키지간 순환의존을 발생**시키지 않도록 transcoder 패키지에 위치시켜야 한다.

순환의존이 발생할 경우 한 패키지의 변경이 다른 패키지에 영향을 줄 가능성이 높아지게되고, 유지보수가 어렵게 만드는 방법일 수 있다.

---

### DI를 이용한 의존객체 사용

어떤것도 상속받지 않은 concrete 클래스를 사용해서 객체를 생성하면 유연하지 못한 코드를 만들게 됨
```java
class Worker{
    // Use Concrete Class
    public void run(){
        JobQueue jobQueue = new FileJobQueue(sth);
        Transcoder transcoder = new FfmpegTranscoder(sth);
    }
}
```
서비스 로케이터를 이용할 경우도 단점이 존재함.
```java
class Worker{
    // Use Service Locator
    public void run(){
        JobQueue jobQueue = Locator.getInstance().getJobQueue();
        Transcoder transcoder = Locator.getInstance().getTranscoder();
    }
    //...
}
```
이를 보완하기 위해 DI(Dependency Injection:의존성 주입)를 사용하게 됨.
```java
// 생성자를 이용한 객체주입
class Worker{
    private JobQueue jobQueue;
    private Transcoder transcoder;
    
    public Worker(JobQueue jobQueue, Transcoder transcoder){
        this.jobQueue = jobQueue;
        this.transcoder = transcoder;
    }
}
```
위에서 생성자를 준비해둔 다음 흐름제어단계에서 필요한 클래스를 주입해준다

```java
class Main{
    public static void main(String args[]){
        JobQueue jobQueue = new FileJobQueue;
        Transcoder transcoder = new FfmpegTranscoder();
        
        final Worker worker = new Worker(jobQueue, transcoder);
        Thread t = new Thread(()->worker.run());
    }
}
```

설정메서드 방식으로 구현하는 방법

---

스프링 프레임워크