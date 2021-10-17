

public class Road extends Stage {
    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000L);
            System.out.println(c.getName() + " закончил этап: " + description);

            c.stageCounterIncrease();
            if (c.getStageCounter() == 3 && !Main.winnerWinnerChickenDinner) {
                Main.winnerWinnerChickenDinner = true;
                System.out.println(c.getName() +  " WIN!");
            }

            Main.cdl_finish.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
