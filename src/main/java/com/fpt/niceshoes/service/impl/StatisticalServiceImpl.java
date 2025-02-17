package com.fpt.niceshoes.service.impl;

import com.fpt.niceshoes.dto.request.bill.BillSearchRequest;
import com.fpt.niceshoes.dto.response.BillResponse;
import com.fpt.niceshoes.dto.response.statistic.StatisticalDayResponse;
import com.fpt.niceshoes.dto.response.statistic.StatisticalMonthlyResponse;
import com.fpt.niceshoes.infrastructure.common.PageableObject;
import com.fpt.niceshoes.repository.IBillRepository;
import com.fpt.niceshoes.service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

@Service
public class StatisticalServiceImpl implements StatisticalService {

    private final long currentTimeMillis = System.currentTimeMillis();
    private final Date currentDate = new Date(currentTimeMillis);

    @Autowired
    private IBillRepository iBillRepository;
    @Override
    public List<StatisticalDayResponse> getAllStatisticalDay() {
        return iBillRepository.getAllStatisticalDay(getStartOfToday(), getEndOfToday());
    }

    @Override
    public List<StatisticalMonthlyResponse> getAllStatisticalMonth() {
        return iBillRepository.getAllStatisticalMonthly(getStartMonth(), getEndMonth());
    }

    @Override
    public PageableObject<BillResponse> getStatisticalByDateRange(BillSearchRequest request) {
        Pageable pageable = PageRequest.of(request.getPage() - 1, request.getSizePage());
        return new PageableObject<>(iBillRepository.getStatisticalByDateRange(request, pageable));
    }

    private Long getStartOfToday() {
        // Tạo đối tượng Calendar và đặt ngày là hôm nay
        Calendar calendarStart = Calendar.getInstance();
        calendarStart.setTimeZone(TimeZone.getTimeZone("UTC")); // Để tránh ảnh hưởng của múi giờ
        calendarStart.setTime(currentDate);
        calendarStart.set(Calendar.HOUR_OF_DAY, 0);
        calendarStart.set(Calendar.MINUTE, 0);
        calendarStart.set(Calendar.SECOND, 0);
        calendarStart.set(Calendar.MILLISECOND, 0);
        // Lấy thời điểm đầu hôm nay dưới dạng currentTimeMillis
        return calendarStart.getTimeInMillis();
    }

    private Long getEndOfToday() {
        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendarEnd.setTime(currentDate);
        calendarEnd.set(Calendar.HOUR_OF_DAY, 23);
        calendarEnd.set(Calendar.MINUTE, 59);
        calendarEnd.set(Calendar.SECOND, 59);
        calendarEnd.set(Calendar.MILLISECOND, 999);
        return calendarEnd.getTimeInMillis();
    }

    private Long getStartMonth() {
        Calendar calendarStart = Calendar.getInstance();
        calendarStart.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendarStart.setTime(currentDate);
        calendarStart.set(Calendar.DAY_OF_MONTH, 1);
        calendarStart.set(Calendar.HOUR_OF_DAY, 0);
        calendarStart.set(Calendar.MINUTE, 0);
        calendarStart.set(Calendar.SECOND, 0);
        calendarStart.set(Calendar.MILLISECOND, 0);
        return calendarStart.getTimeInMillis();
    }

    private Long getEndMonth() {
        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendarEnd.setTime(currentDate);
        calendarEnd.set(Calendar.DAY_OF_MONTH, calendarEnd.getActualMaximum(Calendar.DAY_OF_MONTH));
        calendarEnd.set(Calendar.HOUR_OF_DAY, 23);
        calendarEnd.set(Calendar.MINUTE, 59);
        calendarEnd.set(Calendar.SECOND, 59);
        calendarEnd.set(Calendar.MILLISECOND, 999);
        return calendarEnd.getTimeInMillis();
    }

}
