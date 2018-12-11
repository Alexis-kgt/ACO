package istic.KOUROUMA_KERMORGANT.API;

public interface Observer<T> {

  void update(Observable<T> o);
  
  Observable<T> getObservable();

}