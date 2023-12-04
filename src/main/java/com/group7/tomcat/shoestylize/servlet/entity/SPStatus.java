package com.group7.tomcat.shoestylize.servlet.entity;

import com.group7.tomcat.shoestylize.servlet.database.DBObject;
import java.text.DecimalFormat;

public class SPStatus {

    private final int countCancelled;
    private final int countInProgress;
    private final int countSuccess;
    private final double totalEarn;
    private DecimalFormat decimalFormat = new DecimalFormat("#.##");
    private final int totalOrders;

    public SPStatus(DBObject obj) {
        this.totalOrders = obj.getInt("total_orders");
        this.countCancelled = obj.getInt("cancelled");
        this.countInProgress = obj.getInt("in_progress");
        this.countSuccess = obj.getInt("success");
        this.totalEarn = obj.getDouble("total_earn");
    }
    
    public int getTotalOrders() {
        return totalOrders;
    }

    public int getCountCancelled() {
        return countCancelled;
    }

    public int getCountInProgress() {
        return countInProgress;
    }

    public int getCountSuccess() {
        return countSuccess;
    }

    public double getTotalEarn() {
        return totalEarn;
    }

    public String getSuccessPercentage() {
        int total = countCancelled + countInProgress + countSuccess;
        if (total == 0) {
            return "0.00";
        }
        double percentage = (double) countSuccess / total;
        return decimalFormat.format(percentage);
    }

    public String getCancelPercentage() {
        int total = countCancelled + countInProgress + countSuccess;
        if (total == 0) {
            return "0.00";
        }
        double percentage = (double) countCancelled / total;
        return decimalFormat.format(percentage);
    }

    public String getInProgressPercentage() {
        int total = countCancelled + countInProgress + countSuccess;
        if (total == 0) {
            return "0.00";
        }
        double percentage = (double) countInProgress / total;
        return decimalFormat.format(percentage);
    }
}
