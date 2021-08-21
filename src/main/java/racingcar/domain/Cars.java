package racingcar.domain;

import java.util.*;

public class Cars {
    private final static MovingStrategy movingStrategy = new CarMovingStrategy();

    private final List<Car> carList;

    public Cars(String[] carNames) {
        List<Car> list = new ArrayList<>(carNames.length);

        for (String name : carNames) {
            list.add(new Car(name));
        }

        carList = list;
    }

    public Cars(Car[] carInfo) {
        List<Car> list = new ArrayList<>(carInfo.length);

        for (Car car : carInfo) {
            list.add(car);
        }

        carList = list;
    }

    public void runOneRound() {
        for (Car car : carList) {
            car.move(movingStrategy);
        }
    }

    // TODO: temporary
    public List<Car> getCarsList() {
        return carList;
    }

    Winners calculateWinners() {
        sortByLocation();

        List<Car> winners = new ArrayList<>(carList.size());
        int furthestLocation = carList.get(0).getLocation();

        winners.add(carList.get(0));
        for (int i = 1; i < carList.size() && carList.get(i).getLocation() == furthestLocation; i++) {
            winners.add(carList.get(i));
        }

        return new Winners(winners);
    }

    public void sortByLocation() {
        Collections.sort(carList);
    }


    public String getCarsStateInString() {
        StringBuilder state = new StringBuilder();

        for (Car car : carList) {
            state.append(car.getCarStateInString() + "\n");
        }

        return state.toString();
    }

    public boolean equalsExactly(Object o) {
        if (o instanceof Cars == false) {
            return false;
        }

        Cars compared = (Cars) o;
        if (carList.size() != compared.carList.size()) {
            return false;
        }

        boolean result = true;
        Iterator<Car> originIter = carList.listIterator();
        Iterator<Car> comparedIter = compared.carList.listIterator();

        while (originIter.hasNext()) {
            result &= originIter.next().equals(comparedIter.next());
        }

        return result;
    }

    boolean contains(Object o) {
        if (o instanceof Car == false) {
            return false;
        }

        boolean result = false;
        Car element = (Car) o;
        for (Car car : carList) {
            result |= car.equals(element);
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Cars == false) {
            return false;
        }

        Cars compared = (Cars) o;
        if (carList.size() != compared.carList.size()) {
            return false;
        }

        boolean result = true;
        for (Car car : compared.carList) {
            result &= contains(car);
        }

        return result;
    }
}
