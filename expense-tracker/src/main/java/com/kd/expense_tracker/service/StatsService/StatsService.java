package com.kd.expense_tracker.service.StatsService;

import com.kd.expense_tracker.dto.GraphDTO;
import com.kd.expense_tracker.dto.StatsDTO;

public interface StatsService {
    GraphDTO getChartData();
    StatsDTO getStats();
}
