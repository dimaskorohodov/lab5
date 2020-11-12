package ua.kpi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

       // checkAccuracy();
        double processorDelay = Math.random() * 5 + 1;
       // nModelSerial(4, 10000.0, processorDelay);
        thirdTask(4, 10000, processorDelay);

        System.out.println("\n4\n");

        theoreticalEstimate(4, 10000.0, processorDelay);
      //  newNModelSerial(4, 10000.0, processorDelay);
    }

    static void checkAccuracy() {

        System.out.println("\n1\n");

        Create c = new Create("CREATOR", "exp", 2.0);
        Process p1 = new Process("PROCESSOR1", "exp", 0.6);
        Process p2 = new Process("PROCESSOR2", "exp", 0.3);
        Process p3 = new Process("PROCESSOR3", "exp", 0.4);
        Process p4 = new Process("PROCESSOR4", "exp", 0.1);
        c.nextElement(p1);
        p1.nextElement(null, 0.42);
        p1.nextElement(p2, 0.15);
        p1.nextElement(p3, 0.13);
        p1.nextElement(p4, 0.3);
        p2.nextElement(p1);
        p3.nextElement(p1);
        p4.nextElement(p1);
        p4.MaxParallel(2);

        List<Element> list = Arrays.asList(c, p1, p2, p3, p4);
        Model model = new Model(list);
        model.simulate(10000.0);
        model.accuracy();
    }

    static void nModelSerial(int eventsNum, double timeModeling, double processorDelay) {
        int n = eventsNum;
        List<Element> listProcesses;

        String processName;

        for (int iS = 0; iS < 10; iS++) {
            listProcesses = new ArrayList<>();
            Create c = new Create("CREATOR", "exp", processorDelay);
            listProcesses.add(c);
            for (int i = 1; i <= n; i++) {
                processName = "P" + i;
                listProcesses.add(new Process(processName, "exp", processorDelay));
                listProcesses.get(i - 1).nextElement(listProcesses.get(i));
            }
            Model model = new Model(listProcesses);
            model.simulate(timeModeling);
            n += 5;
        }
    }

    static void thirdTask(int eventsNum, double timeModeling, double processorDelay) {
        System.out.println("\n3\n");

        int n = eventsNum;
        List<Element> listProcesses;

        String processName;
        int numTest = 0;
        while (numTest < 15) { // серия испытаний

            listProcesses = new ArrayList<>();
            Create c = new Create("CREATOR", "exp", processorDelay);
            listProcesses.add(c);
            for (int i = 1; i <= n; i++) {
                processName = "P" + i;
                listProcesses.add(new Process(processName, "exp", processorDelay));
                listProcesses.get(i - 1).nextElement(listProcesses.get(i));
            }

            Model model = new Model(listProcesses);
            model.simulate(timeModeling);
            System.out.println("N= " + n + "; time(ms)= " + model.ellapsed);
            n += 5;
            numTest++;
        }
    }

    static void theoreticalEstimate(int eventsNum, double timeModeling, double processorDelay) {
        double estimate;

        int n = eventsNum;
        for (int i = 0; i < 10; i++) {
            estimate = 2 * Math.log(processorDelay) * timeModeling * (n + 1);
            System.out.println("Nevent= " + n + "; valuation= " + estimate);
            n++;
        }
    }

    static void newNModelSerial(int eventsNum, double timeModeling, double processorDelay) {

        System.out.println("\n5\n");

        int n = eventsNum;
        List<Element> listProcesses;
        int numTest = 0;

        while (numTest < 15) {

            listProcesses = new ArrayList<>();

            Create c = new Create("CREATOR", "exp", processorDelay);
            listProcesses.add(c);

            for (int i = 1; i <= n; i++) {
                listProcesses.add(new Process("P", "norm", processorDelay));
            }

            for (int i = 0; i < n - 4; i++) {
                listProcesses.get(i).nextElement(listProcesses.get(i + 1), 0.3);
                listProcesses.get(i).nextElement(listProcesses.get(i + 2), 0.3);
                listProcesses.get(i).nextElement(listProcesses.get(i + 3), 0.4);
            }

            listProcesses.get(n - 2).nextElement(null, 1.0);
            listProcesses.get(n - 1).nextElement(null, 1.0);
            listProcesses.get(n).nextElement(null, 1.0);

            Model model = new Model(listProcesses);
            model.simulate(timeModeling);

            System.out.println("Nevent= " + n + "; mseconds= " + model.ellapsed);
            n += 5;
            numTest++;
        }
    }
}
