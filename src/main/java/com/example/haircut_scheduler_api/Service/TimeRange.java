package com.example.haircut_scheduler_api.Service;

import java.time.LocalTime;

public class TimeRange {

    private final LocalTime start;
    private final LocalTime end;

    public TimeRange(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    public boolean contains(LocalTime time) {
        return !time.isBefore(start) && !time.isAfter(end);
    }

    public boolean contains(TimeRange other) {
        return !other.start.isBefore(this.start) &&
                !other.end.isAfter(this.end);
    }
}
