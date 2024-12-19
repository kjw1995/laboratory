package com.kjw.laboratory.room;

/**
 * SimpleCountDownLatch 클래스
 * CountDownLatch 를 익히고 또 해당 클래스와 다른 상위 수준의 클래스를 구현하는 능력을 증진하기 위해, 단순한 버전의  CountDownLatch 와
 * CountDownLatch 기능의 대부분을 구현해 보겠습니다.
 * CountDownLatch 와 그 클래스의 단순한 버전인  SimpleCountDownLatch a는 음수가 아닌 count로 초기화됩니다.
 * SimpleCountDownLatch 를 사용하면 한 개 이상의 스레드를 다른 스레드에서 수행되는 일련의 작업이 완료될 때까지 대기하게 만들 수 있습니다.
 * SimpleCountDownLatch 클래스는 다음과 같은 주요 작업을 수행합니다.
 * countDown() - 각 latch의 count 를 감소시켜, count가 0에 도달하면 대기하고 있던 모든 스레드를 릴리스합니다. 만약 현재 count가 이미
 * 0이라면, 아무 일도 일어나지 않습니다.
 * await() - 현재 스레드를 latch의 count가 0으로 감소할 때까지 기다리게 합니다. 만약 현재 count가 이미 0이라면, 해당 메서드가 바로 반환됩니다.
 * 이  await  메서드는  countDown() 메서드의 호출로 인해 현재 count가 0 에 도달할 때까지 블록 처리를 하게 되며, 그 이후에는 기다리고 있던
 * 모든 스레드가 릴리스되고 await 에 이어지는 모든 호출들이 바로 반환됩니다.
 * 더 자세한 정보 및 예시는 여기  CountDownLatch 에서 확인하세요.
 * 	힌트 : 조건 변수 의 버전 중 하나(락의 조건 변수 또는 객체의 wait/notify)를 사용하는 것을 고려해 보세요. 연습 삼아 둘 다 시도해 보셔도 됩니다.
 * 	참고:
 * SimpleCountDownLatch 인스턴스는 일회용입니다. 즉, 일단 count가 0에 도달하게 되면 SimpleCountDownLatch 는 추가로 사용되지 않는다는 뜻입니다.
 * 클래스는 스레드 안전 상태여야 합니다.
 */
public class SimpleCountDownLatch {

	private int count;

	public SimpleCountDownLatch(int count) {
		this.count = count;
		if(count < 0) {
			throw new IllegalArgumentException("count cannot be negative");
		}
	}

	public void await() throws InterruptedException {
		synchronized (this) {
			while(count > 0) {
				this.wait();
			}
		}

	}

	public void countDown() {
		synchronized (this) {
			if(count > 0) {
				count--;

				if(count == 0) {
					this.notifyAll();
				}

			}
		}


	}

	public int getCount() {
		return this.count;
	}

}
