public interface ISubscriber {
    public void update();
    public void subscribe(ISubject channel);
    public void unSubscribe(ISubject channel);
   }
