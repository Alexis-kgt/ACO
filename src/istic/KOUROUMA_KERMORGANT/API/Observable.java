package istic.KOUROUMA_KERMORGANT.API;

public interface Observable<T> {

  void register(Observer<T> o);

  void unregister(Observer<T> o);

  Boolean isRegistred(Observer<T> o);

  T getValue();

}