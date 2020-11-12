package ua.kpi;

import java.time.LocalTime;
import java.util.List;

public class Model {
    private List<Element> list;
    double tnext = 0.0;
    int event;
    double tcurr = 0.0;
    public long ellapsed;

    public long getEllapsed() {
        return ellapsed;
    }

    public void setEllapsed(long ellapsed) {
        this.ellapsed = ellapsed;
    }


    public Model(List<Element> elements) {
        list = elements;
    }

    public void simulate(double timeModeling) {
        LocalTime time = LocalTime.now();
        while (tcurr < timeModeling) {
            tnext = Double.MAX_VALUE;
            for (Element e : list) {
                if (e.getTnext() < tnext) {
                    tnext = e.getTnext();
                    event = e.getId();
                }
            }

            for (Element e : list) {
                e.statistics(tnext - tcurr);
            }
            tcurr = tnext;
            for (Element e : list) {
                e.setTcurr(tcurr);
            }
            for (Element e : list) {
                if (e.getTnext() == tcurr) {
                    e.outAct(0);
                }
            }
        }
        ellapsed = (LocalTime.now().toNanoOfDay() - time.toNanoOfDay()) / 1000000;
    }


    public void accuracy() {
        double[] theoreticalAverageQueue = new double[]{1.786, 0.003, 0.004, 0.00001};
        double[] theoreticalWorkload = new double[]{0.714, 0.054, 0.062, 0.036};

        int i = 0;
        for (Element e : list) {
            if (e instanceof Process) {
                Process p = (Process) e;

                System.out.println(p.getName() + "; avgQ= " + p.GetMeanQueue() / p.getTcurr() +
                        "; theoQ= " + theoreticalAverageQueue[i] + " awgWL= " + p.GetMeanState() / p.getTcurr() + "; theoWL = " + theoreticalWorkload[i]);
                i++;
            }
        }
        System.out.println("\nPohybka:\n----------------------------------------");
        i = 0;
        double qAccuracy;
        double wlAccuracy;
        for (Element e : list) {
            if (e instanceof Process) {
                Process p = (Process) e;
                qAccuracy = Math.abs(p.GetMeanQueue() / p.getTcurr() - theoreticalAverageQueue[i]);
                wlAccuracy = Math.abs(p.GetMeanState() / p.getTcurr() - theoreticalWorkload[i]);
                System.out.println(p.getName() + "; queueSizeAccuracy= " + qAccuracy + "; workloadAccuracy=" + wlAccuracy);
                i++;
            }
        }
    }
}

