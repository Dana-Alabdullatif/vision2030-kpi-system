/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vision2030kpisystem;

/**
 *
 * @author dana
 */
public class KPI implements Comparable<KPI>{

    private String kpiCode;//O(1)
    private char pillarCode;//O(1)
    private double currentProgress;//O(1)
    private int targetYear;//O(1)
    private int priorityLevel;//O(1)
    private String reportingMinistry;//O(1)
    private double quarterlyChange;//O(1)
    private int priorityScore;//O(1)

    // Constructor for KPI
    public KPI(String kpiCode, char pillarCode, double currentProgress,
            int targetYear, int priorityLevel, String reportingMinistry,
            double quarterlyChange) {
        this.kpiCode = kpiCode;//T(n) = 1
        this.pillarCode = pillarCode;//T(n) = 1
        this.currentProgress = currentProgress;//T(n) = 1
        this.targetYear = targetYear;//T(n) = 1
        this.priorityLevel = priorityLevel;//T(n) = 1
        this.reportingMinistry = reportingMinistry;//T(n) = 1
        this.quarterlyChange = quarterlyChange;//T(n) = 1
        this.priorityScore = 0;                // T(n) = 1
        //T(n)=8,O(1)
    }

    //  Getters/Setters(can not set anything but PriorityScore() due to data corruption
    public String getKpiCode() {
        return kpiCode;      // T(n) = 1,O(1)
    }

    public char getPillarCode() {
        return pillarCode;   // T(n) = 1,O(1)
    }

    public double getCurrentProgress() {
        return currentProgress;  // T(n) = 1,O(1)
    }

    public int getTargetYear() {
        return targetYear;   // T(n) = 1,O(1)
    }

    public int getPriorityLevel() {
        return priorityLevel;    // T(n) = 1,O(1)
    }

    public String getReportingMinistry() {
        return reportingMinistry;     // T(n) = 1,O(1)
    }

    public double getQuarterlyChange() {
        return quarterlyChange;  // T(n) = 1,O(1)
    }

    public int getPriorityScore() {
        return priorityScore;    // T(n) = 1,O(1)
    }

    public void setPriorityScore(int score) {
        this.priorityScore = score;  // T(n) = 1,O(1)
    }

    //Process all KPIs and calculate their priority scores
    public static void processKPIs(LinkedList kpiList, int currentYear) {
        Node pointer = kpiList.getHead();     // T(n) = 2,pointer
        int count = 0;                        // T(n) = 1  , to make sure it went through all 3000 lines
        while (pointer != null) {             // T(n) = n , loops through the entire list
            KPI kpi = pointer.getData();      // T(n) = 2n, the data that will be worked on
            kpi.calculatePriorityScore(currentYear);  // T(n) = n, current year for reference to calculate priority level
            pointer = pointer.getNext();      // T(n) = 2n, updates to the next node
            count++;                          // T(n) = 2n
        }

                              

        //T(n)=3+8n,O(n)
    }

    // Calculate priority score based on all weighted rules
    public void calculatePriorityScore(int currentYear) {
        int score = 50;                        // T(n) = 1,base score  50

        // Progress Factor
        if (currentProgress < 30) {            // T(n) = 1,low progress
            score += 30;                       // T(n) = 2
        } else if (currentProgress < 60) {     // T(n) = 1,average progress
            score += 15;                       // T(n) = 2
        } else if (currentProgress < 90) {     // T(n) = 1,good progress
            score += 0;                        // T(n) = 2
        } else {                                // excellent progress
            score -= 20;                       // T(n) = 2
        }

        // Time Urgency
        int yearDifference = targetYear - currentYear;  // T(n) = 2

        if (yearDifference == 0) {             // T(n) = 1,super urget
            score += 40;                       // T(n) = 2
        } else if (yearDifference == 1) {      // T(n) = 1, soon to be urgent
            score += 30;                       // T(n) = 2
        } else if (yearDifference == 2) {      // T(n) = 1,moderate
            score += 20;                       // T(n) = 2
        } else if (yearDifference >= 3 && yearDifference <= 5) {//T(n) = 2, future goal
            score += 10;                       // T(n) = 2
        } else if (yearDifference < 0) {       // T(n) = 1,overdue
            score -= 30;                       // T(n) = 2
        }

        // Priority Level: from 1 (low) to 5 (high)
        switch (priorityLevel) {               // T(n) = 1
            case 5:                         // T(n) = 1, top priority
                score += 25;                   // T(n) = 2
                break;                       // T(n) = 1
            case 4:                         // T(n) = 1,high priority
                score += 15;                   // T(n) = 2
                break;                       // T(n) = 1
            case 3:                         // T(n) = 1,mid priority
                score += 0;                    // T(n) = 2
                break;                         // T(n) = 1
            case 2:                         // T(n) = 1,low priority
                score -= 10;                   // T(n) = 2
                break;                       // T(n) = 1
            case 1:                     // T(n) = 1, very low priority
                score -= 20;                   // T(n) = 2
                break;                            // T(n) = 1
        }

        // Recent Performance 
        if (quarterlyChange < -10) {           // T(n) = 1
            score += 20;                       // T(n) = 2, low
        } else if (quarterlyChange < 0) {      // T(n) = 1
            score += 10;                       // T(n) = 2, slight low
        } else if (quarterlyChange <= 10) {    // T(n) = 1
            score += 0;                        // T(n) = 2, leave it as is
        } else {
            score -= 10;                       // T(n) = 2, too high 
        }

        // Pillar Criticality 
        switch (pillarCode) {                  // T(n) = 1
            case 'A':                        // T(n) = 1,A= ambitious nation
                score += 15;                   // T(n) = 2
                break;                      // T(n) = 1
            case 'T':                       // T(n) = 1,T=thriving economy
                score += 10;                   // T(n) = 2
                break;                       // T(n) = 1
            case 'V':                        // T(n) = 1,V=ibrant society
                score += 5;                    // T(n) = 2
                break;                            // T(n) = 1
        }

        // bounding scores
        if (score < 0) {                       // T(n) = 1,min 0
            score = 0;                         // T(n) = 1
        }
        if (score > 100) {                     // T(n) = 1, max 100 
            score = 100;                       // T(n) = 1
        }

        this.priorityScore = (int) Math.round(score);            // T(n) = 1, final value
        //O(1),T(n)=80
    }
    // implement compareTo for descending order of priorityScore
    public int compareTo(KPI other) {
      return Double.compare(other.priorityScore, this.priorityScore);
    }

    // Converting KPI to string format for file output
    @Override
    public String toString() {
        return kpiCode + ";" + pillarCode + ";" + currentProgress + ";"
                + targetYear + ";" + priorityLevel + ";" + reportingMinistry + ";"
                + quarterlyChange + ";" + priorityScore;                // T(n) = 1,O(1)
    }
}
