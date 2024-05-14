package org.example.bookmyshow.providers;

import lombok.NonNull;
import org.example.bookmyshow.exceptions.SeatTemporaryUnavailableException;
import org.example.bookmyshow.model.Seat;
import org.example.bookmyshow.model.SeatLock;
import org.example.bookmyshow.model.Show;

import java.util.*;

public class InMemorySeatLockProvider implements SeatLockProvider{
    private final Integer lockTimeout;
    private final Map<Show, Map<Seat, SeatLock>> locks;

    InMemorySeatLockProvider(@NonNull final Integer lockTimeout) {
        this.locks = new HashMap<>();
        this.lockTimeout = lockTimeout;
    }

    @Override
    synchronized public void lockSeats(Show show, List<Seat> seats, String user) {
        for(Seat seat : seats){
            if(isSeatLocked(show, seat)){
                throw new SeatTemporaryUnavailableException("Seat is already locked");
            }
            else{
                SeatLock seatLock = new SeatLock(seat, show, lockTimeout, new Date(), user);
                if(!locks.containsKey(show)){
                    locks.put(show,new HashMap<>());
                }

                locks.get(show).put(seat, seatLock);
            }
        }
    }

    @Override
    public void unlockSeats(Show show, List<Seat> seat, String user) {
        for(Seat s : seat){
            if(validateLock(show, s, user)){
                locks.get(show).remove(s);
            }
        }
    }

    @Override
    public boolean validateLock(Show show, Seat seat, String user) {
        return isSeatLocked(show, seat) && locks.get(show).get(seat).getLockedBy().equals(user);
    }

    @Override
    public List<Seat> getLockedSeats(Show show) {
        if(!locks.containsKey(show)){
            return new ArrayList<>();
        }

        final List<Seat> lockedSeats = new ArrayList<>();
        for(Seat seat : locks.get(show).keySet()){
            if(isSeatLocked(show,seat)){
                lockedSeats.add(seat);
            }
        }

        return lockedSeats;
    }

    public boolean isSeatLocked(Show show, Seat seat) {
        return locks.containsKey(show) && locks.get(show).containsKey(seat);
    }
}
