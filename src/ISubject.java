public interface ISubject {
    public void notifyObservers();
    public void addObserver(ISubscriber newSubscriber);
    public void removeObserver(ISubscriber aSubscriber);
   }
   
