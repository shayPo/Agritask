package polak.shay.agritask.model;

import java.util.Observable;

public class CustomObservable extends Observable
{
   public void Changed() {
        super.setChanged();
    }
}
