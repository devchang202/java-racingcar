package racingCar.view;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import racingCar.domain.CarHistory;
import racingCar.domain.Cars;
import racingCar.domain.Game;
import racingCar.domain.LapHistory;
import racingCar.domain.RaceHistory;

/**
 * @author : byungkyu
 * @date : 2020/12/07
 * @description :
 **/
public class View {
	private static final Scanner scanner = new Scanner(System.in);

	public static int inputMatchCount() {
		System.out.println("시도할 회수는 몇 회 인가요?");
		return scanner.nextInt();
	}

	private static void printResult(Cars resultCars) {
		RaceHistory history = resultCars.getHistory();
		List<LapHistory> lapHistories = history.get();
		System.out.println("실행 결과");
		for (LapHistory lapHistory : lapHistories) {
			printLapResult(lapHistory);
			System.out.println("");
		}
		printWinners(resultCars);
	}

	private static void printWinners(Cars resultCars) {
		String result = resultCars.getWinners().stream()
			.map(car -> car.getName())
			.collect(Collectors.joining(","));
		System.out.println(result + "가 최종 우승했습니다.");
	}

	private static void printLapResult(LapHistory lapHistory) {
		for (CarHistory carHistory : lapHistory.get()) {
			printPosition(carHistory);
			System.out.println("");
		}
	}

	private static void printPosition(CarHistory carHistory) {
		System.out.print(carHistory.getName() + " : ");
		for (int i = 0; i < carHistory.getPosition(); i++) {
			System.out.print("-");
		}
	}

	public static String requireCarNames() {
		System.out.println("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
		return scanner.nextLine();
	}

	public static void startGame() {
		Game game = new Game(View.requireCarNames(), View.inputMatchCount());
		Cars resultCars = game.start();
		printResult(resultCars);
	}
}