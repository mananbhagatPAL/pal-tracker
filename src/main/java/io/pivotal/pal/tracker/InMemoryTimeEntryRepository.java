package io.pivotal.pal.tracker;

import java.lang.reflect.Array;
import java.util.*;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    HashMap<Long,TimeEntry> result;
     long id=0L;
    public InMemoryTimeEntryRepository() {
        result=new HashMap<>();
    }

    @Override
    public TimeEntry create(TimeEntry any) {
        id++;
        any.setId(id);
        result.put(id,any);
        System.out.println(" create method inmemoryrepository "+ any);
        return any;
    }

    @Override
    public TimeEntry find(long timeEntryId) {
        if(result.containsKey(timeEntryId)) {
            return result.get(timeEntryId);
        }
        return null;

    }

    @Override
    public List<TimeEntry> list() {
        List<TimeEntry> list=new ArrayList<TimeEntry>();

        for(long id:result.keySet())
        {
            list.add(result.get(id));
        }
        return list;
    }

    @Override
    public TimeEntry update(long eq, TimeEntry any) {

        if(result.containsKey(eq)) {
            any.setId(id);
            result.put(eq, any);
            return any;
        }
        return null;

    }

    @Override
    public void delete(long timeEntryId) {
    result.remove(timeEntryId);
    }


}
