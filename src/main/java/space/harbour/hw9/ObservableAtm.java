package space.harbour.hw9;

public interface ObservableAtm {
    public void addObserver(ObserverAtm observer);

    public void removeObserver(ObserverAtm observer);

    public void notifyObserver(String message);
}
