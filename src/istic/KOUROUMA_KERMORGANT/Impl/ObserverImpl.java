package istic.KOUROUMA_KERMORGANT.Impl;

import istic.KOUROUMA_KERMORGANT.API.Observable;
import istic.KOUROUMA_KERMORGANT.API.Observer;

import java.util.Objects;

public class ObserverImpl<T> implements Observer<T> {

	private ConfigurationImpl<T> subject;
	@Override
	public void update(Observable<T> o) {
		// TODO Auto-generated method stub
		Objects.requireNonNull(o, "o cannot be null");
		subject=(ConfigurationImpl<T>) o;
		//System.out.print(subject.ViewConfiguation());

	}

	@Override
	public ConfigurationImpl<T> getObservable() {
		// TODO Auto-generated method stub
		return subject;
	}

}
