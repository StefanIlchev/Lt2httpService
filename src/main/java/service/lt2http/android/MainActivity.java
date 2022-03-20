package service.lt2http.android;

import ilchev.stefan.binarywrapper.BaseMainActivity;

public class MainActivity extends BaseMainActivity {

	@Override
	protected Class<?> getForegroundServiceClass() {
		return ForegroundService.class;
	}
}
